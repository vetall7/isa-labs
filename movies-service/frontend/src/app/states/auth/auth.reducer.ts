import {createReducer, on} from '@ngrx/store';
import {initialAuthState} from './auth.state';
import {login, loginFailure, loginSuccess, logout, register, registerFailure, registerSuccess} from './auth.action';

export const authReducer = createReducer(
  initialAuthState,
  on(login, (state, {user}) => ({...state})),
  on(loginSuccess, (state, {user}) => ({...state, user: user})),
  on(loginFailure, (state, {error}) => ({...state, loginError: error})),
  on(logout, (state) => initialAuthState),
  on(register, (state, {user}) => ({...state})),
  on(registerSuccess, (state) => state),
  on(registerFailure, (state, {error}) => ({...state, registerError: error})));
