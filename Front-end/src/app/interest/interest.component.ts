import {Component, Input, OnInit} from '@angular/core';
import {Interest} from './interest.model';

@Component({
  selector: 'app-interest',
  templateUrl: './interest.component.html',
  styleUrls: []
})
export class InterestComponent implements OnInit {

  @Input() interest: Interest;

  constructor() { }

  ngOnInit() {
  }


}
