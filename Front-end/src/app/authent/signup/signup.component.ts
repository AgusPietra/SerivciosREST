import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

import { AuthentService } from '../authent.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private authService: AuthentService) { }

  ngOnInit() {
  }

  onSignup(form: NgForm) {
    const email = form.value.email;
    const username = form.value.username;
    const password = form.value.password;
    this.authService.signupUser(email, username, password);
  }

}
