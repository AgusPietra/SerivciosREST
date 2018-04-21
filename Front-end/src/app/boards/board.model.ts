export class Board {
  public name: string;
  public followedUsers: string[];
  public followedInterests: string[];

  constructor(name: string, followedUsers: string[], followedInterests: string[]) {
    this.name = name;
    this.followedUsers = followedUsers;
    this.followedInterests = followedInterests;
  }
}
