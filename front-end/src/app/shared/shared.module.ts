import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { LoadingBarComponent } from './loadingbar/loading-bar.component';
import { ToastComponent } from './toast/toast.component';
import { StylesDirective } from './directives';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
  ],
  declarations: [
    LoadingBarComponent,
    ToastComponent,
    StylesDirective,
  ],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    LoadingBarComponent,
    ToastComponent,
    StylesDirective,
  ],
  providers: []
})
export class SharedModule {
}
