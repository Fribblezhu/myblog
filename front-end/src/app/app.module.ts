import { NgModule, ApplicationRef } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, PreloadAllModules } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpModule, Http } from '@angular/http';
import { ROUTES } from './app.routes';
import { AppComponent } from './app.component';
import { AppConfigService } from './app.config';
import { HeaderModule } from './component/header/header.module';
import { CoreModule} from './core/core.module';
import { PublicLayoutComponent } from './layout/public/public-layout.component';
import {
  removeNgStyles,
  createInputTransfer,
  createNewHosts
} from '@angularclass/hmr';
import { SharedModule } from './shared/shared.module';
import { HomeModule } from './component/home/home.module';
import { LoginModule } from './blog/login/login.module';
import { MineModule} from './user/mine/mine.module';
import { UserLayoutComponent } from './layout/user/user-layout.component';
import { DirectoryModule } from './component/directory/directory.module';

@NgModule({
  bootstrap: [AppComponent],
  declarations: [
    AppComponent,
    PublicLayoutComponent,
    UserLayoutComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(ROUTES, {
      useHash: true,
      preloadingStrategy: PreloadAllModules
    }),
    HttpModule,
    HeaderModule,
    SharedModule,
    HomeModule,
    LoginModule,
    CoreModule,
    MineModule,
    DirectoryModule
  ],
  providers: [
    AppConfigService
  ]
})
export class AppModule {
  constructor(public appRef: ApplicationRef) {
  }

  public hmrOnInit(store) {
    if (!store || !store.state) {
      return;
    }
    console.log('HMR store', store);
    console.log('store.state.data:', store.state.data);
    // inject AppStore here and update it
    // this.AppStore.update(store.state)
    if ('restoreInputValues' in store) {
      store.restoreInputValues();
    }
    // change detection
    this.appRef.tick();
    delete store.state;
    delete store.restoreInputValues;
  }

  public hmrOnDestroy(store) {
    const cmpLocation = this.appRef.components.map((cmp) => cmp.location.nativeElement);
    // recreate elements
    store.disposeOldHosts = createNewHosts(cmpLocation);
    // inject your AppStore and grab state then set it on store
    // var appState = this.AppStore.get()
    store.state = { data: 'yolo' };
    // store.state = Object.assign({}, appState)
    // save input values
    store.restoreInputValues = createInputTransfer();
    // remove styles
    removeNgStyles();
  }

  public hmrAfterDestroy(store) {
    // display new elements
    store.disposeOldHosts();
    delete store.disposeOldHosts;
    // anything you need done the component is removed
  }

}
