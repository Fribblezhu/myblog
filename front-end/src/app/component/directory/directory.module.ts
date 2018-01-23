import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MenuModule } from 'primeng/primeng';
import { SharedModule } from '../../shared/shared.module';
import { DirectoryComponent } from './directory.component';
import { NAV_DROPDOWN_DIRECTIVES } from '../../shared/directives/nav-dropdown.directive';

@NgModule({
  imports: [
    SharedModule,
    RouterModule,
    MenuModule,
  ],
  declarations: [
    DirectoryComponent,
    NAV_DROPDOWN_DIRECTIVES,
  ],
  exports: [
    DirectoryComponent,
  ]
})
export class DirectoryModule {
}
