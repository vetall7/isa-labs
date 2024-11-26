import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable, tap} from 'rxjs';
import {Genres, Genre, DetailedGenre, Movies} from '@genre/models';
import {GenreWithMovies} from '@genre/models/GenreWithMovies.model';

@Injectable({ providedIn: 'root' })
export class GenreService {
  private readonly http = inject(HttpClient);

  private readonly genresSubject = new BehaviorSubject<Genres | null>(null);
  public readonly genres$ = this.genresSubject.asObservable();

  public getGenres(): Observable<Genres>{
    return this.http.get<Genres>('/api/genres').pipe(
      tap((genres) => {
        this.genresSubject.next(genres);
      })
    );
  }

  public deleteGenre(genre: Genre): Observable<any> {
    return this.http.delete('/api/genres/' + genre.name).pipe(
      tap(() => {
        const currentGenres = this.genresSubject.getValue();
        if (currentGenres) {
          const updatedGenres = currentGenres.genres.filter((g) => g.name !== genre.name);
          this.genresSubject.next({ genres: updatedGenres });
        }
      })
    );
  }

  public createGenre(genre: DetailedGenre): Observable<any>{
    return this.http.put('/api/genres/' + genre.name, {description: genre.description});
  }

  public getGenreDetails(genreId: String): Observable<DetailedGenre> {
    return this.http.get<DetailedGenre>('/api/genres/' + genreId);
  }

  public updateGenre(genre: DetailedGenre): Observable<any> {
    return this.http.patch('/api/genres/' + genre.name, {description: genre.description});
  }

  public getGenreMovies(genreId: String): Observable<Movies> {
    return this.http.get<Movies>('/api/genres/' + genreId + "/movies");
  }

  public deleteMovie(movieId: String): Observable<any>{
    return this.http.delete('api/movies/' + movieId);
  }
}
