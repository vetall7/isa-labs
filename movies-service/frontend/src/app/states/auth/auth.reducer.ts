import {createReducer, on} from '@ngrx/store';
import {initialAuthState} from './auth.state';
import {login, logout, register, registerFailure, registerSuccess} from './auth.action';

export const authReducer = createReducer(
  initialAuthState,
  on(login, (state, {user}) => ({...state, isAuthenticated: true, user: user})),
  on(logout, (state) => initialAuthState),
  on(register, (state, {user}) => ({...state})),
  on(registerSuccess, (state) => state),
  on(registerFailure, (state, registerError) => ({...state, error: registerError})));
