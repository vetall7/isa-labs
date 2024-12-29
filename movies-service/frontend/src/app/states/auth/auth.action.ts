import {createAction, props} from '@ngrx/store';
import {UserLoginInfoModel, UserModel} from '@shared/models';
import {UserRegistrationInfoModel} from "@shared/models/UserRegistrationInfo";

export const login = createAction(
  '[Auth] Login',
  props<{user: UserLoginInfoModel}>()
);

export const loginSuccess = createAction(
  '[Auth] Login Success',
    props<{user: UserModel}>()
);

export const loginFailure = createAction(
  '[Auth] Login Failure',
  props<{error: string}>()
);

export const logout = createAction(
  '[Auth] Logout'
);

export const register = createAction(
  '[Auth] Register',
  props<{user: UserRegistrationInfoModel}>()
);

export const registerSuccess = createAction(
  '[Auth] Register Success'
);

export const registerFailure = createAction(
  '[Auth] Register Failure',
  props<{error: string}>()
);

export const clearError = createAction(
  '[Auth] Clear Error'
);

export const setIsRegisterSuccess = createAction(
  '[Auth] Is Registered',
  props<{isRegistered: boolean}>()
);

export const setIsLoginSuccess = createAction(
  '[Auth] Is Login Success',
  props<{isLoginSuccess: boolean}>()
);
