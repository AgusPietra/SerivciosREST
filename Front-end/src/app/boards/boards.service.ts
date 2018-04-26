import {Board} from './board.model';
import {Subject} from 'rxjs/Subject';
import {BoardsRestService} from './boards-rest.service';

export class BoardsService {
  private boards: Board[];

  constructor () {}

  boardsChanged = new Subject<Board[]>();

  setBoards(boards: Board[]) {
    this.boards = boards;
    this.boardsChanged.next(this.boards.slice());
  }

  getBoards() {
    return this.boards.slice();
  }

  getBoard(index: number) {
    return this.boards[index];
  }

  addBoard(board: Board) {
    //TODO, update in server
    this.boards.push(board);
    this.boardsChanged.next(this.boards.slice());
  }

  updateBoard(board: Board, index: number) {
    //TODO, update in server
    this.boards[index] = board;
    this.boardsChanged.next(this.boards.slice());
  }

  deleteBoard(index: number) {
    //TODO, update in server
    this.boards.splice(index, 1);
    this.boardsChanged.next(this.boards.slice());
  }

}
