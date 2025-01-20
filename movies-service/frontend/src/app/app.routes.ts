import { Routes } from '@angular/router';
import {GenresListComponent} from '@genre/pages/genres-list/genres-list.component';
import {DetailedGenreComponent} from '@genre/components/detailed-genre/detailed-genre.component';
import {RegistrationComponent} from './features/auth/pages/registration/registration.component';
import {LoginComponent} from './features/auth/pages/login/login.component';
import {AuthGuard} from '@shared/guards/AuthGuard';

export const routes: Routes = [
  {
    component: GenresListComponent,
    path: "genres",
    canActivate: [AuthGuard]
  },
  {
    component: DetailedGenreComponent,
    path: "genres/:genreId",
    canActivate: [AuthGuard]
  },
  {
    component: RegistrationComponent,
    path: "auth/registration"
  },
  {
    component: LoginComponent,
    path: "auth/login"
  },
  {
    path: '',
    redirectTo: 'genres',
    pathMatch: 'full'
  },
];
