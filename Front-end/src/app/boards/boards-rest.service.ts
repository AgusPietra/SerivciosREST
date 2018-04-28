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
        return boards;
      }
    )
      .subscribe(
        (boards: Board[]) => {
          this.boardsService.setBoards(boards);
        },
        (error) => {
          this.serverAlive.next(false);
        }
      );
  }

  public setBoards() {
    console.log('setting boards from user ' + this.authentService.authenticatedUserName + ' from server');

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
    console.log('creating board on server');
    console.log('user name: ' + board.userName);
    console.log('board name: ' + board.boardName);

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

  public updateBoard(board: Board, actualBoardName: String) {

    board.userName = this.authentService.authenticatedUserName;
    console.log('updating board to server');
    console.log('user name: ' + board.userName);
    console.log('board name: ' + board.boardName);

    this.httpClient.put<ServerResponse>('http://localhost:8080/users/' + board.userName + '/boards/' + actualBoardName, board, {
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
  public deleteBoard(board: Board) {
    board.userName = this.authentService.authenticatedUserName;
    console.log('deleting board of server');
    console.log('user name: ' + board.userName);
    console.log('board name: ' + board.boardName);

    this.httpClient.delete<ServerResponse>('http://localhost:8080/users/' + board.userName + '/boards/' + board.boardName, {
      observe: 'body',
      params: new HttpParams()
    }).subscribe(
      (data: ServerResponse) => {
        if ( +data.code === 0 ) {
          this.serverAlive.next(true);
          console.log('board deleted ok');
        }
        if ( +data.code === -1 ) {
          console.log('board not deleted ok');
          this.serverAlive.next(false);//TODO, diferenciar caso, hay comunicación pero también un error interno
        }
      },
      (error) => {
        console.log('board deleted not performed due to cant connect');
        this.serverAlive.next(false);
      }
    );
  }
}
