import { NgModule } from '@angular/core';
import {SharedModule} from '../../shared/shared.module';
import {MineComponent} from './mine.component';
@NgModule({
  imports: [
    SharedModule,
  ],
  declarations: [
    MineComponent
  ],
  exports: [
    MineComponent
  ]
})
export class MineModule {
}
