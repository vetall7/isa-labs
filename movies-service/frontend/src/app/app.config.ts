import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import {provideHttpClient, withFetch, withInterceptors} from '@angular/common/http';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import {provideStore} from '@ngrx/store';
import {authReducer} from './states/auth/auth.reducer';
import {provideEffects} from '@ngrx/effects';
import {AuthEffects} from './states/auth/auth.effect';
import {JWT_OPTIONS, JwtHelperService} from '@auth0/angular-jwt';
import {authTokenInterceptor} from './features/auth/interceptors/auth-token';
export const appConfig: ApplicationConfig = {
  providers: [provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes), provideHttpClient(withFetch(), withInterceptors([authTokenInterceptor])), provideAnimationsAsync(),
    provideStore({
      auth: authReducer
    }),
    provideEffects(AuthEffects),
    JwtHelperService,
    { provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
  ]
};
