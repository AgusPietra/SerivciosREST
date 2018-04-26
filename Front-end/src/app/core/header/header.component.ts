import {Component, OnInit} from '@angular/core';

import { AuthentService } from '../../authent/authent.service';
import {TranslatorService} from '../../shared/translator.service';
import {BoardsService} from '../../boards/boards.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {
  constructor(private authentService: AuthentService,
              private translatorService: TranslatorService,
              private boardsService: BoardsService) {
  }

  languages: string[];

  onLogout() {
    this.authentService.logout();
  }

  ngOnInit (){
    this.languages = this.translatorService.getLanguages();
    this.boardsService.deleteBoards();
    console.log(this.languages);
  }

  onSetLanguage(index: number){
    // console.log(language);
    this.translatorService.setLanguageIndex(index);
  }
}
