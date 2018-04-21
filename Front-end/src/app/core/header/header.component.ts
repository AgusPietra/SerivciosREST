import { Component } from '@angular/core';

import { AuthentService } from '../../authent/authent.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent {
  constructor(private authentService: AuthentService) {
  }

  onLogout() {
    this.authentService.logout();
  }
}
