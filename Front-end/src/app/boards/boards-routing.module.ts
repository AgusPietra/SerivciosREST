import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { BoardsComponent } from './boards.component';
import {BoardDisplayComponent} from './board-display/board-display.component';
import {BoardEditComponent} from './board-edit/board-edit.component';
import {AuthentGuard} from '../authent/authent-guard.service';

const boardsRoutes: Routes = [
  { path: '', component: BoardsComponent, canActivate: [AuthentGuard], children: [
    { path: 'new', component: BoardEditComponent, canActivate: [AuthentGuard] },
    { path: ':id', component: BoardDisplayComponent, canActivate: [AuthentGuard] },
    { path: ':id/edit', component: BoardEditComponent, canActivate: [AuthentGuard] }
  ] },
];

@NgModule({
  imports: [
    RouterModule.forChild(boardsRoutes)
  ],
  exports: [RouterModule],
  providers: [AuthentGuard]
})
export class BoardsRoutingModule {}
