import { Component, Inject } from '@angular/core';
import { AuthService } from './service/auth.service';
import { DOCUMENT } from '@angular/common';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})
export class AppComponent {
  title = 'logify';
  isUserLoggedIn: boolean = false;
  constructor(private authService: AuthService, @Inject(DOCUMENT) private document: Document) {
  }
  ngOnInit() {
    this.authService.isUserLoggedIn.subscribe(userStatus => {
      this.isUserLoggedIn = userStatus;
    });

    if(this.authService.isTokenValid()) {
      this.authService.routeBasedOnRoles();
    }
  }
  logout() {
    this.authService.logoutUser();
  }
}
