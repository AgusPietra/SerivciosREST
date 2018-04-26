import {Injectable, OnInit} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {BoardsService} from './boards.service';
import {Board} from './board.model';
import {Subject} from 'rxjs/Subject';
import 'rxjs/add/operator/map';
import {ServerResponse} from '../shared/server-response.model';
import {AuthentService} from '../authent/authent.service';

@Injectable()
export class BoardsRestService implements OnInit{

  serverAlive = new Subject<boolean>();

  ngOnInit() {
  }

  constructor(private httpClient: HttpClient,
              private boardsService: BoardsService,
              private authentService: AuthentService) {
  }

  public getBoards() {
    console.log('getting boards from user ' + this.authentService.authenticatedUserName + ' from server');

    this.httpClient.get<Board[]>('http://localhost:8080/users/' + this.authentService.authenticatedUserName + '/boards', {
      observe: 'body',
      responseType: 'json'
    }).map(
      (boards) => {
        console.log(boards);
        // for (let board of boards) {
        //   if (!board.in['ingredients']) {
        //     recipe['ingredients'] = [];
        //   }
        // }
        return boards;
      }
    )
      .subscribe(
        (boards: Board[]) => {
          this.boardsService.setBoards(boards);
        }
      );
  }

  public setBoards() {
    console.log('getting boards from user ' + this.authentService.authenticatedUserName + ' from server');

    this.httpClient.post<ServerResponse>('http://localhost:8080/users/' + this.authentService.authenticatedUserName + '/boards', this.boardsService.getBoards(), {
      observe: 'body',
      params: new HttpParams()
    }).subscribe(
      (data: ServerResponse) => {
        if ( +data.code === 0 ) {
          this.serverAlive.next(true);
        }
        if ( +data.code === -1 ) {
          this.serverAlive.next(false);//TODO, diferenciar caso, hay comunicación pero también un error interno
        }
      },
      (error) => {
        this.serverAlive.next(false);
      }
    );
  }


  public createBoard(board: Board) {

    board.userName = this.authentService.authenticatedUserName;
    console.log('creating board on server');//TODO
    console.log('user name: ' + board.userName);//TODO
    console.log('board name: ' + board.boardName);//TODO

    this.httpClient.post<ServerResponse>('http://localhost:8080/users/' + board.userName + '/boards/' + board.boardName, board, {
      observe: 'body',
      params: new HttpParams()
    }).subscribe(
      (data: ServerResponse) => {
        if ( +data.code === 0 ) {
          this.serverAlive.next(true);
          console.log('board saved ok');
        }
        if ( +data.code === -1 ) {
          console.log('board not saved ok');
          this.serverAlive.next(false);//TODO, diferenciar caso, hay comunicación pero también un error interno
        }
      },
      (error) => {
        console.log('board saved not performed due to cant connect');
        this.serverAlive.next(false);
      }
    );
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
