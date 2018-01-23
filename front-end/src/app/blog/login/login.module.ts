import { NgModule } from '@angular/core';
import { LoginComponent } from './login.component';
import { SharedModule } from '../../shared/shared.module';
import { InputTextModule } from 'primeng/primeng';

@NgModule({
  imports: [
    SharedModule,
    InputTextModule,
  ],
  declarations: [
    LoginComponent,
  ],
  exports: [
    LoginComponent,
  ]
})
export class LoginModule {
}
