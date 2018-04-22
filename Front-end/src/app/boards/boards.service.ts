import {Board} from './board.model';

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

  setBoards(boards: Board[]) {
    this.boards = boards;
  }

  getBoards() {
    return this.boards.slice();
  }

  getBoard(index: number){
    return this.boards[index];
  }
}
