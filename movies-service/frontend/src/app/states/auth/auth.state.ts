import {UserModel} from "@shared/models";

export interface AuthState {
    isAuthenticated: boolean;
    user: UserModel | null;
}

export const initialAuthState: AuthState = {
    isAuthenticated: false,
    user: null
}
