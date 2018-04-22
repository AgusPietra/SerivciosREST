import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { BoardsComponent } from './boards.component';
import {BoardDisplayComponent} from './board-display/board-display.component';
import {BoardEditComponent} from './board-edit/board-edit.component';

const boardsRoutes: Routes = [
  { path: '', component: BoardsComponent, children: [
    { path: 'new', component: BoardEditComponent },
    { path: ':id', component: BoardDisplayComponent },
    { path: ':id/edit', component: BoardEditComponent }
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
