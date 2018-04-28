import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {InterestsService} from './interests.service';

@Injectable()
export class InterestsRestService {

  constructor(private httpClient: HttpClient,
              private interestService: InterestsService){}

    public updateContents() {

      for(let interestName of this.interestService.getInterestsNamesList()) {
        console.log('getting content from interests: ' + interestName + ' from server');

        interestName = interestName.replace('#', '%23' );

        this.httpClient.get<string[]>('http://localhost:8080/interests/' + interestName, {
          observe: 'body',
          responseType: 'json'
        }).map(
          (interestContents) => {
            console.log(interestContents);
            return interestContents;
          }
        )
          .subscribe(
            (interestContents: string[]) => {
              this.interestService.setInterestContent(interestName, interestContents);
            },
            (error) => {
            }
          );
      }
    }
}
