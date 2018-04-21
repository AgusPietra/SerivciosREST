import {Component, Input, OnInit} from '@angular/core';
import {Board} from '../../board.model';

@Component({
  selector: 'app-board-item',
  templateUrl: './board-item.component.html',
  styleUrls: ['./board-item.component.css']
})
export class BoardItemComponent implements OnInit {

  @Input() board: Board;
  @Input() index: number;

  ngOnInit() {
  }

}
