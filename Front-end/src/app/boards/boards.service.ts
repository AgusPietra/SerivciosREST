import {Board} from './board.model';
import {Subject} from 'rxjs/Subject';

export class BoardsService {
  private boards: Board[] = [
    new Board (
      'Test board',
      [
        '@Pepe',
        '#Chess',
        '#Cheese',
        '@Pipo'
      ]
    ),
    new Board (
      'Another board',
      [
        '@Domi',
        '@Dante',
        '#all'
      ],
    )
  ];

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

}
