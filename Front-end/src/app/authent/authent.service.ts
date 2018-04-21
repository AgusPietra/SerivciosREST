import {ActivatedRoute, Router} from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable()
export class AuthentService {

  constructor(private router: Router,
              private route: ActivatedRoute) {}

  signupUser(email: string, username: string, password: string) {
    //TODO
    console.log('sign up with: ' + email + ' ' + username + ' ' + password);
  }

  signinUser(username: string, password: string) {
    //TODO
    console.log('sign in with: ' + username + ' ' + password);
    this.router.navigate(['boards'], {relativeTo: this.route});
  }

  logout () {
    //TODO
    console.log('logging out');
  }

  isAuthenticated() {
    return false; //TODO
  }
}
