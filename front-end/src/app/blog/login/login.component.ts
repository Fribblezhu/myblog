import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../core/auth.service';
import { ToastService } from '../../core/toast.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.less']
})
export class LoginComponent implements OnInit {
  private handing = false;
  private form: FormGroup;

  ngOnInit(): void {
    this.form = this.fb.group({
      username: new FormControl('', Validators.required),
      password: new FormControl('', [Validators.required, Validators.minLength(6)])
    });
  }

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private router: Router,
              private toastService: ToastService) {}
  private focus() {
    this.handing = true;
  }
  private blur() {
    this.handing = false;
  }
  private login() {
    const user = this.form.value;
    if (!this.form.valid) {
      if ( user.username === null || user.username === '') {
        this.toastService.warning('用户名不能为空!');
      }
      if ( user.password == null || user.password === '' || user.password.length < 6) {
        this.toastService.warning('密码不能为空,且不能少于6位');
      }
      return;
    }
    this.authService.login(user.username, user.password).subscribe(
      (res) => {
        this.router.navigate(['/user']);
      },
      (error) => {
        this.handError(error);
      }
    );
  }
  private register() {
    // todo
    this.toastService.error('抱歉,该功能还未上线!');
  }
  private findPassword() {
    // todo
    this.toastService.error('抱歉,该功能还未上线!');
  }
  private handError(error) {
    switch (error.status) {
      case 401:
        this.toastService.error('用户名或密码错误!');
        break;
      case 403:
        this.toastService.error('系统错误,请重试!');
        break;
    }
  }
}
