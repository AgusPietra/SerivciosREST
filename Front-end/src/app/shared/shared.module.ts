import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {TranslatorPipe} from './translator.pipe';
import {DropdownDirective} from './dropdown.directive';

@NgModule({
  imports: [],
  declarations: [
    TranslatorPipe,
    DropdownDirective ],
  exports: [
    CommonModule,
    TranslatorPipe,
    DropdownDirective
  ]
})
export class SharedModule { }
