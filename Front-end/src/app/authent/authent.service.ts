import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ServerResponse} from '../shared/server-response.model';
import {User} from './user.model';


@Injectable()
export class AuthentService {

  constructor(private httpClient: HttpClient) {}

  signupUser(user: User) {
    console.log('sign up with: ' + user.email + ' ' + user.userName+ ' ' + user.password);

    //TODO, hacer metodo real de sign up, de momento solo registra el usuario, y no hace ningún chequeo de si existe.
    return this.httpClient.post<ServerResponse>('http://localhost:8080/users/' + user.userName, user, {
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
