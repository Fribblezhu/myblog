import {
  Directive,
  ElementRef,
  Input,
  Renderer,
  OnChanges,
  SimpleChanges
} from '@angular/core';
import { css } from 'aphrodite';

@Directive({
  selector: '[appStyles]'
})
export class StylesDirective implements OnChanges {

  @Input('appStyles')
  private appStyles: any[];

  constructor(private el: ElementRef, private renderer: Renderer) {
  }

  public ngOnChanges(changes: SimpleChanges): void {
    if (!changes['appStyles'].isFirstChange()) {
      const prevClassName = css(changes['appStyles'].previousValue);
      this.renderer.setElementClass(this.el.nativeElement, prevClassName, false);
    }
    const className = css(this.appStyles);
    this.renderer.setElementClass(this.el.nativeElement, className, true);
  }

}
