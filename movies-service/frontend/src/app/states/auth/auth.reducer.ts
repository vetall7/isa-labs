import {createReducer, on} from '@ngrx/store';
import {initialAuthState} from './auth.state';
import {
  clearError,
  login,
  loginFailure,
  loginSuccess,
  logout,
  register,
  registerFailure,
  registerSuccess, setIsLoginSuccess, setIsRegisterSuccess
} from './auth.action';

export const authReducer = createReducer(
  initialAuthState,
  on(login, (state, {user}) => ({...state})),
  on(loginSuccess, (state, {user}) => ({...state, user: user, isLoginSuccess: true, isAuthenticated: true})),
  on(loginFailure, (state, {error}) => ({...state, error: error})),
  on(logout, (state) => initialAuthState),
  on(register, (state, {user}) => ({...state})),
  on(registerSuccess, (state) => ({...state, isRegisterSuccess: true})),
  on(registerFailure, (state, {error}) => ({...state, error: error})),
  on(clearError, (state) => ({...state, error: null})),
  on(setIsRegisterSuccess, (state, {isRegistered}) => ({...state, isRegisterSuccess: isRegistered})),
  on(setIsLoginSuccess, (state, {isLoginSuccess}) => ({...state, isLoginSuccess: isLoginSuccess})));
