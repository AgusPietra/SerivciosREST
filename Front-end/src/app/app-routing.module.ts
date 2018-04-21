import { NgModule } from '@angular/core';
import { Routes, RouterModule, PreloadAllModules } from '@angular/router';


const appRoutes: Routes = [
  { path: '', redirectTo: 'signin', pathMatch: 'full' },
  { path: 'boards', loadChildren: './boards/boards.module#BoardsModule'}
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes, {preloadingStrategy: PreloadAllModules} )
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
