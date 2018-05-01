import {Injectable} from '@angular/core';

@Injectable()
export class TranslatorService {

  private languages = ['Spanish', 'English'];

  private language = 'English';

  public getLanguages(): string[] {
    return this.languages.slice();
  }

  public setLanguage(language: string){
    this.language = language;
  }

  public setLanguageIndex(index: number){
    this.language = this.languages[index];
  }

  private dictionary: {[key: string]: TranslatorSet} = {
    'English' : {
      languange: 'English',
      values: {
        'Social Media Aggregator' : 'Social Media Aggregator',
        'Challenge' : 'Challenge',
        'Email': 'E-mail',
        'User name' : 'User name',
        'Password' : 'Password',
        'Sign Up': 'Sign Up',
        'Sign In': 'Sign In',
        'Language': 'Language',
        'Spanish': 'Spanish',
        'English': 'English',
        'New board': 'New board',
        'Interests': 'Interests',
        'Save': 'Save',
        'Cancel': 'Cancel',
        'Name': 'Name',
        'Add interest': 'Add interest',
        'Edit': 'Edit',
        'Delete': 'Delete',
        'User name not available': 'User name not available',
        'Unknown user name': 'Unknown user name',
        'Password error': 'Password error',
        'Can\'t connect': 'Can\'t establish connection to server',
        'Refresh': 'Refresh',
        'More': 'More',
      }
    },
    'Spanish' : {
      languange: 'Spanish',
      values: {
        'Social Media Aggregator' : 'Agregador de contenidos de redes sociales',
        'Challenge' : 'Desafío',
        'Email': 'Correo electrónico',
        'User name' : 'Nombre de usuario',
        'Password' : 'Contraseña',
        'Sign Up': 'Registrarse',
        'Sign In': 'Entrar',
        'Language': 'Idioma',
        'Spanish': 'Español',
        'English': 'Inglés',
        'New board': 'Nuevo tablero',
        'Interests': 'Intereses',
        'Save': 'Guardar',
        'Cancel': 'Cancelar',
        'Name': 'Nombre',
        'Add interest': 'Agregar interés',
        'Edit': 'Editar',
        'Delete': 'Borrar',
        'User name not available': 'Nombre de usuario no disponible',
        'Unknown user name': 'Nombre de usuario desconocido',
        'Password error': 'Contraseña incorrecta',
        'Can\'t connect': 'No hay conexión con el servidor',
        'Refresh': 'Refrescar',
        'More': 'Más',
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

