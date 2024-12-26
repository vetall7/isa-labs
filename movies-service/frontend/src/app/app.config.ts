import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import {provideHttpClient, withFetch} from '@angular/common/http';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import {provideStore} from '@ngrx/store';
import {authReducer} from './states/auth/auth.reducer';
import {provideEffects} from '@ngrx/effects';
import {AuthEffects} from './states/auth/auth.effect';
export const appConfig: ApplicationConfig = {
  providers: [provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes), provideHttpClient(withFetch()), provideAnimationsAsync(),
    provideStore({
      auth: authReducer
    }),
    provideEffects(AuthEffects)
  ]
};
