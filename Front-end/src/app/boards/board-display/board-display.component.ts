import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {Board} from '../board.model';
import {BoardsService} from '../boards.service';
import {BoardsRestService} from '../boards-rest.service';

@Component({
  selector: 'app-board-display',
  templateUrl: './board-display.component.html',
  styleUrls: ['./board-display.component.css']
})
export class BoardDisplayComponent implements OnInit {

  board: Board;
  id: number;

  constructor(private boardsService: BoardsService,
              private boardsRestService: BoardsRestService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    this.route.params
      .subscribe(
        (params: Params) => {
          this.id = +params['id'];
          this.board = this.boardsService.getBoard(this.id);
        }
      );
  }

  onEdit(){
    this.router.navigate(['edit'], {relativeTo: this.route});
  }

  onDelete() {
    this.boardsRestService.deleteBoard(this.boardsService.getBoard(this.id));
    this.boardsService.deleteBoard(this.id);
    this.router.navigate(['../'], {relativeTo: this.route});
  }
}
