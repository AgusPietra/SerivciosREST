export class Board {
  public userName: string;
  public boardName: string;
  public interests: string[];

  constructor(userName: string, boardName: string, interests: string[]) {
    this.userName = userName;
    this.boardName = boardName;
    this.interests = interests;
  }
}
