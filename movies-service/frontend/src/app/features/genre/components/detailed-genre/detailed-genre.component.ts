import {Component, inject, OnInit} from '@angular/core';
import {ActivatedRoute, RouterLink} from '@angular/router';
import {GenreService} from '@genre/services';
import {GenreWithMovies} from '@genre/models/GenreWithMovies.model';
import {delay, switchMap, tap} from 'rxjs';
import {MatList, MatListItem, MatListSubheaderCssMatStyler} from '@angular/material/list';
import {MatDivider} from '@angular/material/divider';
import {MatFabButton, MatIconButton} from '@angular/material/button';
import {MatIcon} from '@angular/material/icon';
import {MatDialog} from '@angular/material/dialog';
import {EditMovieComponent} from '@movie/components/edit-movie/edit-movie.component';
import {CreateMovieComponent} from '@movie/components/create-movie/create-movie.component';
import {MatProgressSpinner} from '@angular/material/progress-spinner';

@Component({
  selector: 'app-detailed-genre',
  imports: [
    RouterLink,
    MatList,
    MatListItem,
    MatDivider,
    MatFabButton,
    MatIcon,
    MatIconButton,
    MatListSubheaderCssMatStyler,
    MatProgressSpinner
  ],
  standalone: true,
  templateUrl: './detailed-genre.component.html',
  styleUrl: './detailed-genre.component.scss'
})
export class DetailedGenreComponent implements OnInit {
  private readonly route = inject(ActivatedRoute);

  private readonly genreService = inject(GenreService);

  protected genreWithMovies: GenreWithMovies = new GenreWithMovies(); // probably bad practice: storing state inside component

  readonly dialog = inject(MatDialog);

  public ngOnInit() : void {
    const genreName = this.route.snapshot.paramMap.get("genreId");
    if (genreName){
      this.genreService.getGenreDetails(genreName)
        .pipe(
          delay(1000),
          tap(detailedGenre => this.genreWithMovies.detailedGenre = detailedGenre),
          switchMap(() => this.genreService.getGenreMovies(genreName))
        )
        .subscribe(genreMovies => this.genreWithMovies.movies = genreMovies);
    }
  }

  protected onDeleteMovie(movieId: String): void{
    const movies = this.genreWithMovies.movies;
    if (movies) {
      this.genreService.deleteMovie(movieId).subscribe(
        () => {
          this.genreWithMovies.movies = {
            ...this.genreWithMovies.movies,
            movies: movies.movies.filter((movie) => movie.id !== movieId)
          };
        }
      );
    }
  }

  protected editMovie(movieId: String): void {
    this.dialog.open(EditMovieComponent, {
    data: { movieId: movieId, genreId: this.genreWithMovies.detailedGenre?.name }
    });
  }

  protected onCreateMovie(): void {
    this.dialog.open(CreateMovieComponent, {
      data: { genreId: this.genreWithMovies.detailedGenre?.name }
    });
  }
}
