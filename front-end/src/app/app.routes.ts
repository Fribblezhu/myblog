import {Routes} from '@angular/router';
import {PublicLayoutComponent} from './layout/public/public-layout.component';
import {HomeComponent} from './component/home/home.component';
import {PublicPageGuard} from './core/public-page.guard';
import {LoginComponent} from './blog/login/login.component';
import {UserLayoutComponent} from './layout/user/user-layout.component';
import {MineComponent} from './user/mine/mine.component';
import {UserPageGuard} from './core/user-page.guard';

export const ROUTES: Routes = [
  {
    path: '',
    component: PublicLayoutComponent,
    data: {
      title: 'Home'
    },
    children: [
      {
        path: '',
        component: HomeComponent,
        canActivate: [PublicPageGuard]
      },
      {
        path: 'login',
        component: LoginComponent,
        canActivate: [PublicPageGuard]
      }
    ]
  },
  {
    path: 'user',
    component: UserLayoutComponent,
    children: [
      {
        path: '',
        component: MineComponent,
        canActivate: [UserPageGuard]
      }
    ]
  }
];
