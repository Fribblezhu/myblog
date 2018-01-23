import { Component, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-user-layout',
  templateUrl: './user-layout.component.html',
  styleUrls: ['../layout.component.less'],
  encapsulation: ViewEncapsulation.None
})
export class UserLayoutComponent {

  public disabled= false;
  public status: { isopen: boolean } = { isopen: false };

  public toggled(open: boolean): void {}

  public toggleDropdown($event: MouseEvent): void {
    $event.preventDefault();
    $event.stopPropagation();
    this.status.isopen = !this.status.isopen;
  }
}
