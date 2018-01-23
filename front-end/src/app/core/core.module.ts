import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { SkipSelf, Optional } from '@angular/core';
import { Http, RequestOptions, HttpModule } from '@angular/http';
import { LoadingBarService } from './loading-bar.service';
import { PublicPageGuard} from './public-page.guard';
import { AuthService } from './auth.service';
import { AuthHttp } from './auth-http';
import { AuthConfig } from './auth-http';
import { ToastService } from './toast.service';
import { UserPageGuard } from './user-page.guard';

export function authHttpServiceFactory(http: Http, options: RequestOptions) {
  return new AuthHttp(new AuthConfig(), http, options);
}
@NgModule({
  imports: [
    CommonModule,
    HttpModule,
  ],
  exports: [
  ],
  providers: [
    {
      provide: AuthHttp,
      useFactory: authHttpServiceFactory,
      deps: [Http, RequestOptions]
    },
    LoadingBarService,
    PublicPageGuard,
    AuthService,
    ToastService,
    UserPageGuard,
  ]
})
export class CoreModule {

  constructor( @Optional() @SkipSelf() parentModule: CoreModule) {
    if (parentModule) {
      throw new Error(
        'CoreModule is already loaded. Import it in the AppModule only');
    }
  }

}
