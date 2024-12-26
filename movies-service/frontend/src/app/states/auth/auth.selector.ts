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

export const selectRegisterError = createSelector(
  selectAuthState,
  (auth) => auth.registerError
);
