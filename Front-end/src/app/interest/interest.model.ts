export class Interest {

  public contents: string[];

  constructor(public interestName: string) {
  }

  public setContents (contents: string[]){
    this.contents = contents;
  }

  public getContents (){
    return this.contents;
  }
}
