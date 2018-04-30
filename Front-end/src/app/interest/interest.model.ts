export class Interest {

  public contents: string[];
  public countAsked: number;

  constructor(public interestName: string) {
    this.countAsked = 5;
  }

  public setContents (contents: string[]){
    this.contents = contents;
  }

  public getContents (){
    return this.contents;
  }
}
