import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../../service/auth.service';
import { CookieService } from 'ngx-cookie-service';
import { JWTPayload } from '../model/jwt-data';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass']
})
export class LoginComponent implements OnInit {

  constructor(private formBuilder: FormBuilder, private authService: AuthService,  private _snackBar: MatSnackBar) { }
  hide=false;
  credentialsInvalid=false;
  registerUser = this.formBuilder.group({
    username: ['', Validators.compose([
      Validators.required,
      Validators.minLength(5),
      Validators.maxLength(40),
      Validators.pattern('[-A-Za-z0-9]*'),
    ])],
    password: ['', Validators.compose([
      Validators.minLength(8),
      Validators.maxLength(20),
      Validators.required
    ])],
  });
  ngOnInit(): void {
    this.authService.isUserLoggedIn.next(false);
    this.hide = true;
  }
  submit() {
    this.authService.logInUser(this.registerUser.value).subscribe(res=> {
      this.authService.isUserLoggedIn.next(true);
      localStorage.setItem('logify_user', res.token);
      this.authService.routeBasedOnRoles();
    },
    err=> {
      this._snackBar.open("Username or password provided is invalid. Please try again", "OK", {
        duration: 10000,
      });
    });
  }

  parseJwt(token): JWTPayload {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
      return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    return JSON.parse(jsonPayload);
  };
}
