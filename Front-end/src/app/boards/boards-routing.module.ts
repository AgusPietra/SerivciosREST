import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { BoardsComponent } from './boards.component';
import {BoardItemComponent} from './board-list/board-item/board-item.component';
import {BoardListComponent} from './board-list/board-list.component';

const boardsRoutes: Routes = [
  { path: '', component: BoardsComponent, children: [
    { path: ':id', component: BoardItemComponent }
  ] },
];

@NgModule({
  imports: [
    RouterModule.forChild(boardsRoutes)
  ],
  exports: [RouterModule],
  providers: []
})
export class BoardsRoutingModule {}
