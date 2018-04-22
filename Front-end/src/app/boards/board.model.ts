export class Board {
  public name: string;
  public interests: string[];
  public followedInterests: string[];

  constructor(name: string, interests: string[]) {
    this.name = name;
    this.interests = interests;
  }
}
