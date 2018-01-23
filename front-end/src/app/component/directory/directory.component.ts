import { Component, OnInit, HostListener } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../core/auth.service';
import { Directory } from './directory.model';

@Component({
  selector: 'app-directory',
  templateUrl: 'directory.component.html',
  styleUrls: ['./directory.component.less']
})

export class DirectoryComponent implements OnInit {

  private loggedIn: boolean;
  private isMenuHidden = true;
  private files: Directory[] = [];
  public constructor(
    private router: Router,
    private authService: AuthService) {
  }

  public ngOnInit(): void {
    this.loggedIn = this.authService.loggedIn();
    this.authService.events.subscribe(() => {
      this.loggedIn = this.authService.loggedIn();
    });
    this.files = [{id: '1', name: '文件夹', items: [{id: '1', name: 'java', items: []}, {id: '2', name: 'python', items: []}]},
      {id: '2', name: '视屏夹', items: []}];
  }

  private toggleMenu(e: Event) {
    this.isMenuHidden = !this.isMenuHidden;
    e.stopPropagation();
  }

  @HostListener('document:click') private hideMenu() {
    this.isMenuHidden = true;
  }

}
