import {inject, Injectable} from '@angular/core';
import {AuthService} from '../../features/auth/services/auth.service';
import {Actions, createEffect, ofType} from '@ngrx/effects';
import {
  login,
  loginFailure,
  loginSuccess, logout,
  register,
  registerSuccess
} from './auth.action';
import {catchError, map, of, switchMap, tap} from 'rxjs';
import {JwtTokenService} from '@shared/utils/jwt-token.service';
import {LocalStorageService} from '@shared/utils/local-storage.service';
import {HttpErrorResponse} from '@angular/common/http';

@Injectable()
export class AuthEffects {
  private readonly actions$ = inject(Actions);
  private readonly authService = inject(AuthService);

  register$ = createEffect(() =>
    this.actions$.pipe(
      ofType(register),
      switchMap(({ user }) =>
        this.authService.register(user).pipe(
          map(() => registerSuccess()),
          catchError((error: HttpErrorResponse) => {
              const errorMessage = error.error?.message || 'An unknown error occurred';
              return of(loginFailure({error: errorMessage}));
            }
          )
        )
      )
    )
  );

  login$ = createEffect(() =>
    this.actions$.pipe(
      ofType(login),
      switchMap(({ user }) =>
        this.authService.login(user).pipe(
          map((jwtToken) => {
            this.authService.setToken(jwtToken);
            const userRole  = this.authService.getRole(jwtToken);
            return loginSuccess({
              user: {
                name: user.name,
                email: null,
                role: userRole,
              },
            });
          }),
          catchError((error: HttpErrorResponse) =>
          {
              const errorMessage = error.error?.message || 'An unknown error occurred';
              return of(loginFailure({ error: errorMessage }));
            })
        )
      )
    )
  );

  logout$ = createEffect(() =>
    this.actions$.pipe(
      ofType(logout),
      tap(() => this.authService.logout())
    ),
    { dispatch: false }
  );
}
