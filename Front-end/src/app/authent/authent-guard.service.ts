import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, ActivatedRoute, Router} from '@angular/router';
import { Injectable } from '@angular/core';

import { AuthentService } from './authent.service';

@Injectable()
export class AuthentGuard implements CanActivate {

  constructor(private authentService: AuthentService,
              private router: Router,
              private route: ActivatedRoute) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.authentService.isAuthenticated()) {
      return true;
    }

    this.router.navigate(['/signin']);
  }
}
