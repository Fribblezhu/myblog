import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';
import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { AppConfigService} from '../app.config';
import { saveToken, removeToken, tokenNotExpired} from './auth-http';

@Injectable()
export  class AuthService {

  private event: Subject<AuthEvent>;
  constructor(private  http: Http,
                private config: AppConfigService) {
    this.event = new Subject<AuthEvent>();
  }
  public login(username: string, password: string): Observable<Response> {
    const body = {
      username,
      password,
    };
    return this.http.post(`${this.config.apiUrl}/api/login`, body).do(
      (resp: Response) => {
        saveToken(resp.json().token);
        this.event.next(new DidLogin());
      }
    );
  }
  public loginOut() {
    removeToken();
    this.event.next(new DidLogout());
  }
  public loggedIn(): boolean {
    return tokenNotExpired();
  }
  get events() {
    return this.event;
  }

  public isAdminRole (roleId: string) {
    return roleId = 'ROLE_ADMIN';
  }
  public hasPermission(roleId: string, resName: string): Observable<boolean> {
    const params = new URLSearchParams();
    params.set('roleId', roleId);
    params.set('resName', resName);
    return this.http.get(`${this.config.apiUrl}/api/has-permission`, {search: params})
      .map( (res) => res.json());
  }

  public hasRole(roleId: string) {
    const params = new URLSearchParams();
    params.set('roleId', roleId);
    return this.http.get(`${this.config.apiUrl}/api/has-role`, {search: params})
      .map((res) => res.json());
  }
}
export class DidLogin {}
export class DidLogout {}
export type AuthEvent = DidLogin | DidLogout;
