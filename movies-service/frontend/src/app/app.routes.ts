import { Routes } from '@angular/router';
import {GenresListComponent} from '@genre/pages/view-genres/genres-list/genres-list.component';
import {CreateGenreComponent} from '@genre/components/create-genre/create-genre.component';
import {EditGenreComponent} from '@genre/components/edit-genre/edit-genre.component';
import {DetailedGenreComponent} from '@genre/components/detailed-genre/detailed-genre.component';
import {CreateMovieComponent} from '@movie/components/create-movie/create-movie.component';
import {EditMovieComponent} from '@movie/components/edit-movie/edit-movie.component';
import {DetailedMovieComponent} from '@movie/components/detailed-movie/detailed-movie.component';
import {RegistrationComponent} from './features/auth/pages/registration/registration.component';
import {LoginComponent} from './features/auth/pages/login/login.component';

export const routes: Routes = [
  {
    component: GenresListComponent,
    path: "genres"
  },
  {
    component: CreateGenreComponent,
    path: "genres/create"
  },
  {
    component: EditGenreComponent,
    path: "genres/edit/:genreId"
  },
  {
    component: DetailedGenreComponent,
    path: "genres/:genreId"
  },
  {
    component: CreateMovieComponent,
    path: "genres/:genreId/movies/create"
  },
  {
    component: EditMovieComponent,
    path: "genres/:genreId/movies/edit/:movieId"
  },
  {
    component: DetailedMovieComponent,
    path: "genres/:genreId/movies/:movieId"
  },
  {
    component: RegistrationComponent,
    path: "auth/registration"
  },
  {
    component: LoginComponent,
    path: "auth/login"
  }
];
