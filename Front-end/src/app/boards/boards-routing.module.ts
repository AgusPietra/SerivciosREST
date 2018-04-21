import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { BoardsComponent } from './boards.component';
import {BoardItemComponent} from './board-item/board-item.component';

const boardsRoutes: Routes = [
  { path: '', component: BoardsComponent, children: [
    { path: '', component: BoardsComponent },
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
