import { NgModule } from '@angular/core';

import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { AppRoutingModule } from '../app-routing.module';
import { AuthentService } from '../authent/authent.service';
import {SharedModule} from '../shared/shared.module';
import {TranslatorService} from '../shared/translator.service';
import {BoardsService} from '../boards/boards.service';
import {BoardsRestService} from '../boards/boards-rest.service';
import {InterestsService} from '../interest/interests.service';
import {InterestsRestService} from '../interest/interests.rest.service';

@NgModule({
  declarations: [
    HeaderComponent,
    HomeComponent
  ],
  imports: [
    AppRoutingModule,
    SharedModule
  ],
  exports: [
    AppRoutingModule,
    HeaderComponent
  ],
  providers: [
    AuthentService,
    TranslatorService,
    BoardsService,
    BoardsRestService,
    InterestsService,
    InterestsRestService,
  ]
})
export class CoreModule {}
