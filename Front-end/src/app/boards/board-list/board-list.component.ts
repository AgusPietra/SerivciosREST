import { Component, OnInit } from '@angular/core';
import {Board} from '../board.model';
import {BoardsService} from '../boards.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs/Subscription';
import {BoardsRestService} from '../boards-rest.service';
import {AuthentService} from '../../authent/authent.service';

@Component({
  selector: 'app-board-list',
  templateUrl: './board-list.component.html',
  styleUrls: ['./board-list.component.css']
})
export class BoardListComponent implements OnInit {

  boards: Board[];

  subscritionBoards: Subscription;
  subscritionServerAlive: Subscription;

  private serverAlive: boolean;

  constructor(private boardsService: BoardsService,
              private boardsRestService: BoardsRestService,
              private router: Router,
              private route: ActivatedRoute,
              private authentService: AuthentService) {
    this.serverAlive = true;
  }

  ngOnInit() {
    this.subscritionBoards = this.boardsService.boardsChanged
      .subscribe((boards: Board[]) => { this.boards = boards;
      console.log('updating lists');});
    this.subscritionServerAlive = this.boardsRestService.serverAlive
      .subscribe((serverAlive: boolean) => {
        serverAlive ? console.log('Server alive') : console.log('Server dead');
        this.serverAlive = serverAlive;
    });
    this.boardsService.setBoards(this.boardsRestService.getBoards(this.authentService.authenticatedUserName));

  }

  onNewBoard() {
    this.router.navigate(['new'], {relativeTo: this.route});
  }
}
