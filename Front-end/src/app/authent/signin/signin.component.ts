import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

import { AuthentService } from '../authent.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {

  constructor(private authService: AuthentService) { }

  username: string;

  ngOnInit() {
  }

  onSignin(form: NgForm) {
    this.username = form.value.username;
    const password = form.value.password;
    this.authService.signinUser(this.username, password);
  }

  getUsername() {
    return this.username;
  }

}
