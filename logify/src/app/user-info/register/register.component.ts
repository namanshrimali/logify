import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup, FormControl, FormGroupDirective, NgForm } from '@angular/forms';
import { AuthService } from 'src/app/service/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.sass']
})
export class RegisterComponent implements OnInit {
  ngOnInit(): void {
    this.hide = true;
  }
  constructor(private formBuilder: FormBuilder, private authService:AuthService, private _snackBar: MatSnackBar, private router: Router) { }
  areIconsDisabled=true;
  isUserNameTaken = null;
  timeout = null;
  hide = true;
  userInfo = this.formBuilder.group({
    name: ['', Validators.compose([
      Validators.required,
      Validators.minLength(5),
      Validators.maxLength(40),
      Validators.pattern('[-A-Za-z ]*'),
    ])],
    username: ['', Validators.compose([
      Validators.required,
      Validators.minLength(5),
      Validators.maxLength(20),
      Validators.pattern('[-A-Za-z0-9]*'),
    ])],
    password: ['', Validators.compose([
      Validators.minLength(8),
      Validators.maxLength(20),
      Validators.required
    ])]});
  

    checkIfAvailable() {
      if(this.userInfo.get('username').valid) {
        clearTimeout(this.timeout);
        this.timeout = setTimeout(() => {
          this.authService.isUserNameUnavailable(this.userInfo.get('username').value).subscribe(res=> {
            this.isUserNameTaken = res
            this.areIconsDisabled = false;
          });
        }, 1000);
      } 
    }
    registerUser() {
      this.authService.registerUser(this.userInfo.value).subscribe(res=> {
        this._snackBar.open("You've been successfully registered. Please continue to login", "OK", {
          duration: 4000,
        });
        this.router.navigate(['user']);
      })
    }
}
