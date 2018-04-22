import { Component, OnInit } from '@angular/core';
import {FormArray, FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {BoardsService} from '../boards.service';

@Component({
  selector: 'app-board-edit',
  templateUrl: './board-edit.component.html',
  styleUrls: ['./board-edit.component.css']
})
export class BoardEditComponent implements OnInit {

  id: number;
  editMode = false;
  boardForm: FormGroup;

  constructor(private route: ActivatedRoute,
              private boardsService: BoardsService,
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
      boardName = board.name;
      if (board['interests']) {
        for (const interest of board.interests) {
          boardInterests.push(
            new FormGroup({
              'interest': new FormControl(interest, [Validators.required, Validators.pattern(/^(@|#)/)]),
            })
          );
        }
      }
    }

    this.boardForm = new FormGroup({
      'name': new FormControl(boardName, Validators.required),
      'interests': boardInterests
    });
  }

  onSubmit() {
    if (this.editMode) {
      this.boardsService.updateBoard(this.boardForm.value, this.id);
    } else {
      this.boardsService.addBoard(this.boardForm.value);
    }
    this.onCancel();
  }

  onCancel() {
    this.router.navigate(['../'], {relativeTo: this.route});
  }

  onAddInterest() {
    (<FormArray>this.boardForm.get('interests')).push(
      new FormGroup({
        'interest': new FormControl(null, [Validators.required, Validators.pattern(/^(@|#)/)]),
      })
    );
  }

  onDeleteInterest(index: number) {
    (<FormArray>this.boardForm.get('interests')).removeAt(index);
  }
}

