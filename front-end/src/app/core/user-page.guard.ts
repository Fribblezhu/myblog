import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import {AuthService} from './auth.service';

@Injectable()
export class UserPageGuard implements CanActivate {

  constructor(private router: Router,
              private authService: AuthService) {
  }

  public canActivate(): Observable<boolean> | boolean {
    if (!this.authService.loggedIn()) {
      this.router.navigate(['/login']);
      return false;
    }
    return true;
  }

}
