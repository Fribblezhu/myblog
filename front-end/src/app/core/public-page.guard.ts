import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable()
export class PublicPageGuard implements CanActivate {

  constructor(private router: Router) {
  }

  public canActivate() {
    return true;
  }

}
