import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

import { AuthentService } from '../authent.service';
import {ActivatedRoute, Router} from '@angular/router';
import {User} from '../user.model';
import {ServerResponse} from '../../shared/server-response.model';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {

  private notRegisteredUserName: Boolean;
  private invalidPassword: Boolean;

  constructor(private authService: AuthentService,
              private router: Router,
              private route: ActivatedRoute) {
    this.notRegisteredUserName = false;
    this.invalidPassword = false;
  }

  username: string;

  ngOnInit() {
  }

  onSignin(form: NgForm) {
    this.authService.signinUser(new User(form.value.username, form.value.password, form.value.email))
      .subscribe(
        (data: ServerResponse) => {
          console.log(data.code);
          if ( +data.code === 0 ) {
            this.authService.setAuthenticated(form.value.username);
            this.router.navigate(['../boards'], {relativeTo: this.route});
          }
          if ( +data.code === -1 ){
            this.notRegisteredUserName = true;
          }
          if ( +data.code === -2 ){
            this.invalidPassword = true;
          }
        },
        (error) => {
          console.log('error msg: ');
          console.log(error);
        }
      );
  }


  getUsername() {
    return this.username;
  }

  onUsernameChange(){
    this.notRegisteredUserName = false;
    this.invalidPassword = false;
  }

  onPasswordChange(){
    this.invalidPassword = false;
  }

}
