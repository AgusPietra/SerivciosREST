import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

import { AuthentService } from '../authent.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {

  constructor(private authService: AuthentService,
              private router: Router,
              private route: ActivatedRoute) { }

  username: string;

  ngOnInit() {
  }

  onSignin(form: NgForm) {
    this.username = form.value.username;
    const password = form.value.password;
    if (this.authService.signinUser(this.username, password)) {
      this.router.navigate(['../boards'], {relativeTo: this.route});
    }
  }

  getUsername() {
    return this.username;
  }

}
