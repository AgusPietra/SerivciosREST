import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {TranslatorPipe} from './translator.pipe';

@NgModule({
  imports: [],
  declarations: [ TranslatorPipe ],
  exports: [
    CommonModule,
    TranslatorPipe
  ]
})
export class SharedModule { }
