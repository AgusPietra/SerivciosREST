import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {InterestsService} from './interests.service';

@Injectable()
export class InterestsRestService {

  constructor(private httpClient: HttpClient,
              private interestService: InterestsService){}

    public updateContents() {

      for(const interest of this.interestService.getInterests()) {
        console.log('getting content from interests: ' + interest.interestName + ' from server');

        const interestNameEscaped = interest.interestName.replace('#', '%23' );

        this.httpClient.get<string[]>('http://localhost:8080/interests/' + interestNameEscaped, {
          observe: 'body',
          params: new HttpParams().set('count', interest.countAsked.toString()),
          responseType: 'json'
        }).map(
          (interestContents) => {
            return interestContents;
          }
        )
          .subscribe(
            (interestContents: string[]) => {
              this.interestService.setInterestContent(interest.interestName, interestContents);
            },
            (error) => {
            }
          );
      }
    }
}
