import { Component, OnInit } from '@angular/core';
import {Board} from '../board.model';
import {BoardsService} from '../boards.service';

@Component({
  selector: 'app-board-list',
  templateUrl: './board-list.component.html',
  styleUrls: ['./board-list.component.css']
})
export class BoardListComponent implements OnInit {

  boards: Board[];

  constructor(private boardsService: BoardsService) { }

  ngOnInit() {

    this.boards = this.boardsService.getBoards();
  }

  onNewBoard(){
    //TODO
    console.log('new board');
  }
}
