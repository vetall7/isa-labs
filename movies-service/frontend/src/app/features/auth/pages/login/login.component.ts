import {Component, inject, signal} from '@angular/core';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatError, MatFormField, MatLabel} from '@angular/material/form-field';
import {MatInput} from '@angular/material/input';
import {MatIcon} from '@angular/material/icon';
import {MatButton, MatIconButton} from '@angular/material/button';
import {Store} from '@ngrx/store';
import {login, register} from '../../../../states/auth/auth.action';
import {UserLoginInfoModel, UserModel} from '@shared/models';
import {selectRegisterError} from '../../../../states/auth/auth.selector';
import {toSignal} from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-login',
  imports: [
    ReactiveFormsModule,
    MatFormField,
    MatInput,
    MatError,
    MatLabel,
    MatIcon,
    MatIconButton,
    MatButton
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  private readonly fb = inject(FormBuilder);

  private readonly store = inject(Store);

  hide = signal(true);

  nameError = signal("");

  passwordError = signal("");

  error = toSignal(this.store.select(selectRegisterError));

  protected readonly form = this.fb.group({
    name: ['', [Validators.required, Validators.maxLength(30)]],
    password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(30)]]
  });

  protected onSubmit(): void {
    if (this.form.valid) {
      this.store.dispatch(login({ user: this.form.value as UserLoginInfoModel }));
    }
  }

  onHide(event: MouseEvent) {
    this.hide.set(!this.hide());
    event.stopPropagation();
  }

  protected updateNameError(): void {
    if (this.form.get('name')?.hasError('required')) {
      this.nameError.set('Name is required');
    }else if (this.form.get('name')?.hasError('maxLength')) {
      this.nameError.set('Name is too long');
    }
  }

  protected updatePasswordError() : void {
    if (this.form.get('password')?.hasError('required')) {
      this.passwordError.set('Password is required');
    }else if (this.form.get('password')?.hasError('minLength')) {
      this.passwordError.set('Password is too short');
    }else if (this.form.get('password')?.hasError('maxLength')) {
      this.passwordError.set('Password is too long');
    }
  }
}
