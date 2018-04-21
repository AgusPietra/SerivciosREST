import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { BoardsComponent } from './boards.component';
import { BoardItemComponent } from './board-item/board-item.component';

import { SharedModule } from '../shared/shared.module';
import {BoardsRoutingModule} from './boards-routing.module';

@NgModule({
  declarations: [
    BoardsComponent,
    BoardItemComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    BoardsRoutingModule,
    SharedModule
  ]
})
export class BoardsModule {}
