import {createReducer, on} from '@ngrx/store';
import {initialAuthState} from './auth.state';
import {login, logout} from './auth.action';

export const authReducer = createReducer(
  initialAuthState,
  on(login, (state, {user}) => ({...state, isAuthenticated: true, user: user})),
  on(logout, (state) => initialAuthState));
