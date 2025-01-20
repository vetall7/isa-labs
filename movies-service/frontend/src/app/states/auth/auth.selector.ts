import {createFeatureSelector, createSelector} from '@ngrx/store';
import {AuthState} from './auth.state';

export const selectAuthState = createFeatureSelector<AuthState>('auth');

export const selectIsAuthenticated = createSelector(
  selectAuthState,
  (auth) => auth.isAuthenticated
);

export const selectUser = createSelector(
  selectAuthState,
  (auth) => auth.user
)

export const selectError = createSelector(
  selectAuthState,
  (auth) => auth.error
);

export const selectIsRegisterSuccess = createSelector(
  selectAuthState,
  (auth) => auth.isRegisterSuccess
);

export const selectIsLoginSuccess = createSelector(
  selectAuthState,
  (auth) => auth.isLoginSuccess
);
