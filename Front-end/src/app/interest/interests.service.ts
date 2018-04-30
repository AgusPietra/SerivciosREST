import { Injectable } from '@angular/core';
import {Interest} from './interest.model';
import {Subject} from 'rxjs/Subject';

@Injectable()
export class InterestsService {

  // private interestNamesList: string[];
  private interests: Interest[];

  constructor() {
    this.interests = new Array<Interest>();
    // this.interestNamesList = new Array<string>();
  }

  interestsChanged = new Subject<Interest[]>();

  setInterestsNamesList(interestNamesList: string[]) {
    // this.interestNamesList = interestNamesList;
    this.interests.splice(0, this.interests.length);

    for (const interestName of interestNamesList) {
      // let interest = new Interest(interestName);
      // this.interestList[interest.interestName] = interest;
      this.interests.push(new Interest(interestName));
    }
    this.interestsChanged.next(this.interests.slice());
    console.log('Interests changed, qty: ' + this.interests.length);
  }

  // getInterestsNamesList() {
  //   return this.interestNamesList.slice();
  // }

  getInterestContent(index: number) {
    return this.interests[index];
  }

  getInterests() {
    return this.interests;
  }

  setInterestContent(interestName: string, interestContents: string []) {
    for(const interest of this.interests) {
      if (interest.interestName === interestName) {
        interest.setContents(interestContents);
        this.interestsChanged.next(this.interests.slice());
        break;
      }
    }
  }
}
