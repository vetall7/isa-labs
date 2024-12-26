import {createAction, props} from '@ngrx/store';
import {UserModel} from '@shared/models';

export const login = createAction(
  '[Auth] Login',
  props<{user: UserModel}>()
);

export const logout = createAction(
  '[Auth] Logout'
);

export const register = createAction(
  '[Auth] Register',
  props<{user: UserModel}>()
);

export const registerSuccess = createAction(
  '[Auth] Register Success'
);

export const registerFailure = createAction(
  '[Auth] Register Failure',
  props<{error: string}>()
);
