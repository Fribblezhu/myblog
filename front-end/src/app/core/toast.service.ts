import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';

export interface Toast {
  msg: string ;
  level: string ;
  style: any[];
}

export interface ToastEvent {
  msg: string;
  level: string;
}

@Injectable()
export class ToastService {
  private toastEvents: Subject<ToastEvent> = new Subject<Toast>();
  get events() {
    return this. toastEvents;
  }
  public succsess(msg: string) {
    this.publish({msg, level: 'success'});
  }
  public warning(msg: string) {
    this.publish({msg, level: 'warning'});
  }
  public error(msg: string) {
    this.publish({msg, level: 'error'});
  }
  private publish(toast: ToastEvent) {
    this.toastEvents.next(toast);
  }
}
