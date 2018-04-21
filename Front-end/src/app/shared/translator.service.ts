import {Injectable} from '@angular/core';

@Injectable()
export class TranslatorService {

  public languages = ['ES', 'EN'];

  public language = 'ES';

  private dictionary: {[key: string]: TranslatorSet} = {
    'EN' : {
      languange: 'EN',
      values: {
        'Welcome' : 'Welcome to the Social Media Aggregator',
        'Email': 'E-mail',
        'User name' : 'User name',
        'Password' : 'Password'

      }
    },
    'ES' : {
      languange: 'ES',
      values: {
        'Welcome' : 'Bienvenido al visualizador de contenidos'
        'Email': 'Correo electrónico',
        'User Name' : 'Nombre de usuario',
        'Password' : 'Contraseña'
      }
    }
  };

  constructor() { }

  translate(key: string): string {
    if ( this.dictionary[this.language] != null) {
      return this.dictionary[this.language].values[key];
    }
  }
}

export class TranslatorSet {
  public languange: string;
  public values: {[key: string]: string} = {};
}

