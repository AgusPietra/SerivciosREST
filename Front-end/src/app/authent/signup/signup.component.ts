import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

import { AuthentService } from '../authent.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ServerResponse} from '../../shared/server-response.model';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  private userAlreadyTaken: Boolean;

  constructor(private authService: AuthentService,
              private router: Router,
              private route: ActivatedRoute) {
    this.userAlreadyTaken = false;
  }

  ngOnInit() {
  }

  onSignup(form: NgForm) {
    const email = form.value.email;
    const username = form.value.username;
    const password = form.value.password;
    this.authService.signupUser(email, username, password)
      .subscribe(
        (data: ServerResponse) => {
          if ( +data.code === 0 ) {
            this.router.navigate(['../boards'], {relativeTo: this.route});
          }
          else {
            this.userAlreadyTaken = true;
          }
        },
        (error) => {
          console.log('error msg: ');
          console.log(error);
        }
      );
    // this.dataStorageService.storeRecipes()
    //   .subscribe(
    //     (response) => {
    //       console.log(response);
    //     }
    //   );
  }

  onUsernameChange() {
    this.userAlreadyTaken = false;
  }

}
