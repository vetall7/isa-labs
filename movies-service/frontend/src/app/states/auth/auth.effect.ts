import {inject, Injectable} from '@angular/core';
import {AuthService} from '../../features/auth/services/auth.service';
import {Actions, createEffect, ofType} from '@ngrx/effects';
import {register, registerFailure, registerSuccess} from './auth.action';
import {catchError, map, of, switchMap, tap} from 'rxjs';

@Injectable()
export class AuthEffects {
  private readonly actions$ = inject(Actions);
  private readonly authService = inject(AuthService);

  register$ = createEffect(() => this.actions$.pipe(
    tap(({user}) => console.log('Registering user:', user)),
    ofType(register),
    switchMap(({user}) => this.authService.register(user).pipe(
      map(() => registerSuccess()),
      catchError((error) => of(registerFailure({error})))
    ))
  ));
}
