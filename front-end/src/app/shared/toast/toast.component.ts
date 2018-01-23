import { Component, OnInit } from '@angular/core';
import {Toast, ToastService} from '../../core/toast.service';
import { styles } from './toast.component.styles';

@Component({
  selector: 'app-toast',
  templateUrl: './toast.component.html'
})
export class ToastComponent implements OnInit {
  private styles = styles;
  private toasts: Toast[] = [];
  constructor(private toastService: ToastService) {
  }

  ngOnInit(): void {
    this.toastService.events
      .map((toast) => this.applyStyle(toast))
      .do((toast) => this.add(toast))
      .delay(4000)
      .map((toast) => this.fadeOut(toast))
      .delay(500)
      .do((toast) => this.remove(toast))
      .subscribe();
  }

  private applyStyle(toast): Toast {
    return{ ...toast,
      style: [styles.toastBase, styles[toast.level]]
    };
  }
  private add(toast) {
    this.toasts.push(toast);
  }

  private remove(toast) {
    this.toasts = this.toasts.filter((t) => t !== toast);
  }

  private fadeOut(toast: Toast) {
    toast.style = [...toast.style, styles.fadeOut];
  }

}
