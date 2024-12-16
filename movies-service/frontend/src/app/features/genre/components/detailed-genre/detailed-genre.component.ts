import {Component, inject, OnInit} from '@angular/core';
import {ActivatedRoute, RouterLink} from '@angular/router';
import {GenreService} from '@genre/services';
import {GenreWithMovies} from '@genre/models/GenreWithMovies.model';
import {switchMap, tap} from 'rxjs';

@Component({
  selector: 'app-detailed-genre',
  imports: [
    RouterLink
  ],
  standalone: true,
  templateUrl: './detailed-genre.component.html',
  styleUrl: './detailed-genre.component.scss'
})
export class DetailedGenreComponent implements OnInit {
  private readonly route = inject(ActivatedRoute);

  private readonly genreService = inject(GenreService);

  protected genreWithMovies: GenreWithMovies = new GenreWithMovies(); // probably bad practice: storing state inside component

  public ngOnInit() : void {
    const genreName = this.route.snapshot.paramMap.get("genreId");
    if (genreName){
      this.genreService.getGenreDetails(genreName)
        .pipe(
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
}
