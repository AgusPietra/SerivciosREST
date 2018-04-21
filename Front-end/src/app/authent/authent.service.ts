import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';

@Injectable()
export class AuthentService {

  constructor(private httpClient: HttpClient) {}

  signupUser(email: string, username: string, password: string) {
    console.log('sign up with: ' + email + ' ' + username + ' ' + password);

    //TODO, hacer metodo real de sign up, de momento solo registra el usuario, y no hace ningún chequeo de si existe.
    return this.httpClient.post('http://localhost:8080/users/' + username, '', {
      observe: 'body',
      params: new HttpParams()
    });
  }

  signinUser(username: string, password: string) {
    //TODO
    console.log('sign in with: ' + username + ' ' + password);
    return true;
  }

  logout () {
    //TODO
    console.log('logging out');
  }

  isAuthenticated() {
    return false; //TODO
  }
}
