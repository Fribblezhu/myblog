import { Injectable } from '@angular/core';

/** 保存全局配置的服务 */
@Injectable()
export class AppConfigService {
  /** Api服务的基础地址 */
  public apiUrl: string;
  constructor() {
    const appConfig = window['appConfig'] || {};
    this.apiUrl = appConfig.apiUrl ||
      (location.protocol + '//' + location.host);
  }

}
