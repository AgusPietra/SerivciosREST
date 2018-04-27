import { Component, OnInit } from '@angular/core';
import {FormArray, FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {BoardsService} from '../boards.service';
import {BoardsRestService} from '../boards-rest.service';

@Component({
  selector: 'app-board-edit',
  templateUrl: './board-edit.component.html',
  styleUrls: ['./board-edit.component.css']
})
export class BoardEditComponent implements OnInit {

  id: number;
  editMode = false;
  boardForm: FormGroup;
  actualBoardName: String; //Para ediciones de nombre.

  constructor(private route: ActivatedRoute,
              private boardsService: BoardsService,
              private boardsRestService: BoardsRestService,
              private router: Router) { }

  ngOnInit() {
    this.route.params
      .subscribe(
        (params: Params) => {
          this.id = +params['id'];
          this.editMode = params['id'] != null;
          this.initForm();
        }
      );
  }

  private initForm() {
    let boardName = '';
    let boardInterests = new FormArray([]);

    if (this.editMode) {
      const board = this.boardsService.getBoard(this.id);
      this.actualBoardName = board.boardName;
      boardName = board.boardName;
      if (board['interests']) {
        for (const interest of board.interests) {
          boardInterests.push(
            new FormControl(interest, [Validators.required, Validators.pattern(/^(@|#)/)]),
          );
        }
      }
    }

    this.boardForm = new FormGroup({
      'boardName': new FormControl(boardName, [Validators.required, this.usedBoardNames.bind(this)]),
      'interests': boardInterests
    },);
    console.log(this.boardForm);
  }

  onSubmit() {
    if (this.editMode) {
      this.boardsService.updateBoard(this.boardForm.value, this.id);
      this.boardsRestService.updateBoard(this.boardForm.value, this.actualBoardName);
    } else {
      this.boardsService.addBoard(this.boardForm.value);
      this.boardsRestService.createBoard(this.boardForm.value);
    }
    this.onCancel();
  }

  onCancel() {
    this.router.navigate(['../'], {relativeTo: this.route});
  }

  onAddInterest() {
    (<FormArray>this.boardForm.get('interests')).push(
      new FormControl(null, [Validators.required, Validators.pattern(/^(@|#)/)]),
    );
  }

  onDeleteInterest(index: number) {
    (<FormArray>this.boardForm.get('interests')).removeAt(index);
  }

  usedBoardNames(control: FormControl): {[s: string]: boolean}{
    if (this.boardsService.checkIfBoardNamesUsed(control.value)) {
      return {'boardNameUsed': true};
    }
    return null;
  }
}

