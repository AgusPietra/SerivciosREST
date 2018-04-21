import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

import { AuthentService } from '../authent.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private authService: AuthentService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
  }

  onSignup(form: NgForm) {
    const email = form.value.email;
    const username = form.value.username;
    const password = form.value.password;
    this.authService.signupUser(email, username, password)
      .subscribe(
        (response) => {
          console.log(response);
          this.router.navigate(['../boards'], {relativeTo: this.route});
        }
      );
    // this.dataStorageService.storeRecipes()
    //   .subscribe(
    //     (response) => {
    //       console.log(response);
    //     }
    //   );
  }

}
