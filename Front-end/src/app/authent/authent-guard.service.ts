import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';

import { AuthentService } from './authent.service';

@Injectable()
export class AuthentGuard implements CanActivate {

  constructor(private authentService: AuthentService) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.authentService.isAuthenticated();
  }
}
