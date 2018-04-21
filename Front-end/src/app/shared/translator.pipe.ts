import { TranslatorService } from './translator.service';
import {Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'translate',
  pure: false
})
export class TranslatorPipe implements PipeTransform {

  rValue: string;

  constructor(private translatorService: TranslatorService) {}

  transform(value: any, args?: any): any {

    this.rValue = this.translatorService.translate(value);
    return this.rValue === undefined ? 'DICT_ERROR' : this.rValue;
  }


}
