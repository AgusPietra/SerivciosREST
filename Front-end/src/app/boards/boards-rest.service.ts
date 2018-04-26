import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BoardsService} from './boards.service';
import {Board} from './board.model';
import {Subject} from 'rxjs/Subject';

@Injectable()
export class BoardsRestService implements OnInit{

  serverAlive = new Subject<boolean>();

  ngOnInit() {
  }

  constructor(private httpClient: HttpClient,
              private boardsService: BoardsService) {
  }

  public getBoards(userName: string) {
    console.log('getting boards from user ' + userName + ' from server');

    //TODO
    this.serverAlive.next(false);
    return [
      new Board (
        userName,
        'Test board',
        [
          '@Pepe',
          '#Chess',
          '#Cheese',
          '@Pipo'
        ]
      ),
      new Board (
        userName,
        'Another board',
        [
          '@Domi',
          '@Dante',
          '#all'
        ],
      )
    ];
  }
  public createBoard(board: Board) {
    this.serverAlive.next(false);
    console.log('creating board on server');//TODO
  }
  public updateBoard(board: Board) {
    this.serverAlive.next(false);
    console.log('updating board to server');//TODO
  }
  public deleteBoard(board: Board) {
    this.serverAlive.next(false);
    console.log('Deleting board of server');//TODO
  }
}
