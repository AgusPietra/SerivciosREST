import { TranslatorService } from './translator.service';
import {Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'translate',
  pure: false
})
export class TranslatorPipe implements PipeTransform {

  constructor(private translatorService: TranslatorService) {}

  transform(value: any, args?: any): any {
    return this.translatorService.translate(value);
  }


}
