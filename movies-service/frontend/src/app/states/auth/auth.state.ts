import {UserModel} from "@shared/models";

export interface AuthState {
    isAuthenticated: boolean;
    user: UserModel | null;
    error: string | null;
    isRegisterSuccess: boolean;
    isLoginSuccess: boolean;
}

export const initialAuthState: AuthState = {
    isAuthenticated: false,
    user: null,
    error: null,
    isRegisterSuccess: false,
    isLoginSuccess: false
}
