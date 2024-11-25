import { Routes } from '@angular/router';
import {GenresListComponent} from '@genre/pages/view-genres/genres-list/genres-list.component';
import {CreateGenreComponent} from '@genre/components/create-genre/create-genre.component';
import {EditGenreComponent} from '@genre/components/edit-genre/edit-genre.component';

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
  }
];
