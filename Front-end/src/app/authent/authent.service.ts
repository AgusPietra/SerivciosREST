import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ServerResponse} from '../shared/server-response.model';
import {User} from './user.model';


@Injectable()
export class AuthentService {

  private authenticated: boolean;

  private authenticatedUserName: string;

  constructor(private httpClient: HttpClient) {
    this.authenticated = false;
  }

  signupUser(user: User) {
    // console.log('sign up with: ' + user.email + ' ' + user.userName+ ' ' + user.password);
    //TODO, hacer metodo real de sign up, con seguridad
    return this.httpClient.post<ServerResponse>('http://localhost:8080/users', user, {
      observe: 'body',
      params: new HttpParams()
    });
  }

  signinUser(user: User) {
    // console.log('sign in with: ' + user.email + ' ' + user.userName+ ' ' + user.password);
    //TODO, hacer metodo real de sign in, con seguridad, por ejemplo con oauth2
    return this.httpClient.post<ServerResponse>('http://localhost:8080/users/' + user.userName, user, {
      observe: 'body',
      params: new HttpParams()
    });
  }

  logout () {
    //TODO
    console.log('logging out');
  }

  isAuthenticated() {
    return this.authenticated;
  }

  setAuthenticated(userName: string) {
    this.authenticated = true;
    this.authenticatedUserName = userName;
  }
}
