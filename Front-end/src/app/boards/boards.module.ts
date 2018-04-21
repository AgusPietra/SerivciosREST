import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { BoardsComponent } from './boards.component';
import { BoardItemComponent } from './board-list/board-item/board-item.component';

import { SharedModule } from '../shared/shared.module';
import {BoardsRoutingModule} from './boards-routing.module';
import { BoardListComponent } from './board-list/board-list.component';

@NgModule({
  declarations: [
    BoardsComponent,
    BoardItemComponent,
    BoardListComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    BoardsRoutingModule,
    SharedModule
  ]
})
export class BoardsModule {}
