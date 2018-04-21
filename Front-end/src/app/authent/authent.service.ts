import { Router } from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable()
export class AuthentService {

  constructor(private router: Router) {}

  signupUser(email: string, username: string, password: string) {
    //TODO
    console.log('sign up with: ' + email + ' ' + username + ' ' + password);
  }

  signinUser(username: string, password: string) {
    //TODO
    console.log('sign in with: ' + username + ' ' + password);
  }

  logout () {
    //TODO
    console.log('logging out');
  }

  isAuthenticated() {
    false; //TODO
  }
}
