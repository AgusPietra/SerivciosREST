import {Component, Input, OnInit} from '@angular/core';
import {Interest} from './interest.model';
import {InterestsService} from './interests.service';
import {InterestsRestService} from './interests.rest.service';

@Component({
  selector: 'app-interest',
  templateUrl: './interest.component.html',
  styleUrls: []
})
export class InterestComponent implements OnInit {

  @Input() interest: Interest;

  constructor(private interestsService: InterestsService,
              private interestsRestService: InterestsRestService) { }

  ngOnInit() {
  }

  onMore() {
    this.interestsService.addInterestCountAsked(this.interest, 5);
    this.interestsRestService.updateContentsSpecificInterest(this.interest);
  }

}
