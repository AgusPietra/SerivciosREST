import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';

import { SigninComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
import { AuthentRoutingModule } from './authent-routing.module';

@NgModule({
  imports: [
    FormsModule,
    AuthentRoutingModule
  ],
  declarations: [
    SigninComponent,
    SignupComponent
  ]
})
export class AuthentModule { }
