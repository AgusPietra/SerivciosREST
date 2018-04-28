import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {InterestsService} from './interests.service';

@Injectable()
export class InterestsRestService {

  constructor(private httpClient: HttpClient,
              private interestService: InterestsService){}

    public updateContents() {

      for(const interestName of this.interestService.getInterestsNamesList()) {
        console.log('getting content from interests: ' + interestName + ' from server');

        const interestNameEscaped = interestName.replace('#', '%23' );

        this.httpClient.get<string[]>('http://localhost:8080/interests/' + interestNameEscaped, {
          observe: 'body',
          responseType: 'json'
        }).map(
          (interestContents) => {
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
