import {Board} from './board.model';
import {Subject} from 'rxjs/Subject';

export class BoardsService {
  private boards: Board[];
  constructor () {
    this.boards = new Array<Board>();
  }

  boardsChanged = new Subject<Board[]>();

  setBoards(boards: Board[]) {
    if(boards) {
      this.boards = boards;
      this.boardsChanged.next(this.boards.slice());
    }
  }

  getBoards() {
    if(this.boards) {
      return this.boards.slice();
    }
  }

  getBoard(index: number) {
    return this.boards[index];
  }

  addBoard(board: Board) {
    console.log('new board on list: ' + board.boardName);
    this.boards.push(board);
    this.boardsChanged.next(this.boards.slice());
  }

  updateBoard(board: Board, index: number) {
    this.boards[index] = board;
    this.boardsChanged.next(this.boards.slice());
  }

  deleteBoard(index: number) {
    this.boards.splice(index, 1);
    this.boardsChanged.next(this.boards.slice());
  }

  deleteBoards() {
    this.boards.splice(0,this.boards.length);

  }

  checkIfBoardNamesUsed(newName: string, actualIndex: number){
    let returnValue = false;
    this.boards.forEach((board, index) => {
      if ( board.boardName === newName && ((index !== actualIndex && index !== -1) || index === -1 )) {
        returnValue = true;
      }
    });
    return returnValue;
  }
}
