import { Component, OnInit } from '@angular/core';
import {Board} from '../board.model';
import {BoardsService} from '../boards.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-board-list',
  templateUrl: './board-list.component.html',
  styleUrls: ['./board-list.component.css']
})
export class BoardListComponent implements OnInit {

  boards: Board[];

  constructor(private boardsService: BoardsService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {

    this.boards = this.boardsService.getBoards();
  }

  onNewBoard(){
    this.router.navigate(['new'], {relativeTo: this.route});
  }
}
