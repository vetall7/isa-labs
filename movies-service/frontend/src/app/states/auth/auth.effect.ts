import {inject, Injectable} from '@angular/core';
import {AuthService} from '../../features/auth/services/auth.service';
import {Actions, createEffect, ofType} from '@ngrx/effects';
import {login, loginFailure, loginSuccess, register, registerFailure, registerSuccess} from './auth.action';
import {catchError, map, of, switchMap, tap} from 'rxjs';
import {JwtTokenService} from '@shared/utils/jwt-token.service';
import {LocalStorageService} from '@shared/utils/local-storage.service';

@Injectable()
export class AuthEffects {
  private readonly actions$ = inject(Actions);
  private readonly authService = inject(AuthService);
  private readonly jwtTokenService = inject(JwtTokenService);
  private readonly localStorage = inject(LocalStorageService);

  register$ = createEffect(() => this.actions$.pipe(
    ofType(register),
    switchMap(({user}) => this.authService.register(user).pipe(
      map(() => registerSuccess()),
      catchError((error) => of(registerFailure({error})))
    ))
  ));

  login$ = createEffect(() => this.actions$.pipe(
    tap(({user}) => console.log('Logging in user:', user)),
    ofType(login),
    switchMap(({user}) => this.authService.login(user).pipe(
      map((jwtToken) => {
        const token = this.jwtTokenService.decodeToken(jwtToken);
        console.log('Token:', token);
        this.localStorage.setItem('token', jwtToken);
        return loginSuccess({
          user: {
            name: user.name,
            email: null,
            role: token
          }
        })
      }),
      catchError((error) => {
        console.log('Error:', error);
        return of(loginFailure({error}));
      }
    ))
  )));
}
