import {createAction, props} from '@ngrx/store';
import {UserModel} from '@shared/models';

export const login = createAction(
  '[Auth] Login',
  props<{user: UserModel}>()
);

export const logout = createAction(
  '[Auth] Logout'
);
