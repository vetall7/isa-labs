import {Component, effect, inject, signal} from '@angular/core';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatError, MatFormField, MatLabel} from '@angular/material/form-field';
import {MatInput} from '@angular/material/input';
import {MatIcon} from '@angular/material/icon';
import {MatButton, MatIconButton} from '@angular/material/button';
import {Store} from '@ngrx/store';
import {clearError, register, setIsRegisterSuccess} from '../../../../states/auth/auth.action';
import {UserModel} from '@shared/models';
import {selectAuthState, selectError, selectIsRegisterSuccess} from '../../../../states/auth/auth.selector';
import {toSignal} from '@angular/core/rxjs-interop';
import {UserRegistrationInfoModel} from "@shared/models/UserRegistrationInfo";
import {Router, RouterLink} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-registration',
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
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.scss'
})
export class RegistrationComponent {
  private readonly fb = inject(FormBuilder);

  private readonly store = inject(Store);

  private readonly snackBar = inject(MatSnackBar);

  private readonly router = inject(Router);

  hide = signal(true);

  nameError = signal("");

  emailError = signal("");

  passwordError = signal("");

  error = toSignal(this.store.select(selectError));

  isRegisterSuccess = toSignal(this.store.select(selectIsRegisterSuccess));

  constructor() {
    effect(() => {
      const errorMessage = this.error();

      if (errorMessage) {
        this.openSnackBar(errorMessage);
      }
    });
    effect(() => {
      const isRegisterSuccess = this.isRegisterSuccess();

      if (isRegisterSuccess){
        this.openSnackBar("Account created successfully");
        this.router.navigate(["/auth/login"]);
        this.store.dispatch(setIsRegisterSuccess({isRegistered: false}));
      }
    });
  }
  protected readonly form = this.fb.group({
    name: ['', [Validators.required, Validators.maxLength(30)]],
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(30)]]
  });

  protected onSubmit(): void {
    if (this.form.valid) {
      this.store.dispatch(clearError());
      this.store.dispatch(register({ user: this.form.value as UserRegistrationInfoModel }));
    }
  }

  protected openSnackBar(message: string): void {
    this.snackBar.open(message, 'Close', {
      duration: 2000,
    });
  }

  protected onHide(event: MouseEvent): void {
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

  protected updateEmailError(): void {
    if (this.form.get('email')?.hasError('required')) {
      this.emailError.set('Email is required');
    }else if (this.form.get('email')?.hasError('email')) {
      this.emailError.set('Email is invalid');
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
