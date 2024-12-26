import {UserModel} from "@shared/models";

export interface AuthState {
    isAuthenticated: boolean;
    user: UserModel | null;
    registerError: string | null;
    loginError: string | null;
}

export const initialAuthState: AuthState = {
    isAuthenticated: false,
    user: null,
    registerError: null,
    loginError: null
}
