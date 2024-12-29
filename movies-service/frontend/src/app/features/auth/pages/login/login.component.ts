import {Component, effect, inject, signal} from '@angular/core';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatError, MatFormField, MatLabel} from '@angular/material/form-field';
import {MatInput} from '@angular/material/input';
import {MatIcon} from '@angular/material/icon';
import {MatButton, MatIconButton} from '@angular/material/button';
import {Store} from '@ngrx/store';
import {
  clearError,
  login,
  register,
  setIsLoginSuccess,
  setIsRegisterSuccess
} from '../../../../states/auth/auth.action';
import {UserLoginInfoModel, UserModel} from '@shared/models';
import {selectError, selectIsLoginSuccess} from '../../../../states/auth/auth.selector';
import {toSignal} from '@angular/core/rxjs-interop';
import {Router, RouterLink} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';

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
    MatButton,
    RouterLink
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  private readonly fb = inject(FormBuilder);

  private readonly store = inject(Store);

  private readonly snackBar = inject(MatSnackBar);

  private readonly router = inject(Router);

  hide = signal(true);

  nameError = signal("");

  passwordError = signal("");

  error = toSignal(this.store.select(selectError));

  isLoginSuccess = toSignal(this.store.select(selectIsLoginSuccess));

  protected readonly form = this.fb.group({
    name: ['', [Validators.required, Validators.maxLength(30)]],
    password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(30)]]
  });

  constructor() {
    effect(() => {
      const errorMessage = this.error();

      if (errorMessage) {
        this.openSnackBar(errorMessage);
      }
    });
    effect(() => {
      const isLoginSuccess = this.isLoginSuccess();

      if (isLoginSuccess){
        this.openSnackBar("Logged in successfully");
        this.router.navigate(["/"]);
        this.store.dispatch(setIsLoginSuccess({isLoginSuccess: false}));
      }
    });
  }

  protected openSnackBar(message: string): void {
    this.snackBar.open(message, 'Close', {
      duration: 2000,
    });
  }

  protected onSubmit(): void {
    if (this.form.valid) {
      this.store.dispatch(clearError());
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
