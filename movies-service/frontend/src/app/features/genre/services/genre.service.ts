import {computed, inject, Injectable, signal} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {delay, Observable, tap} from 'rxjs';
import {Genres, Genre, DetailedGenre, Movies} from '@genre/models';

@Injectable({ providedIn: 'root' })
export class GenreService {
  private readonly http = inject(HttpClient);

  private genresSignal= signal<Genres | null>(null);

  public genresSig = computed(() => this.genresSignal());

  public getGenres(): Observable<Genres>{
    return this.http.get<Genres>('/api/genres').pipe(
      delay(1000),
      tap((genres) => {
        this.genresSignal.set(genres);
      })
    );
  }

  public deleteGenre(genre: Genre): Observable<any> {
    return this.http.delete('/api/genres/' + genre.name).pipe(
      tap(() => {
        const currentGenres = this.genresSignal();
        if (currentGenres) {
          const updatedGenres = currentGenres.genres.filter((g) => g.name !== genre.name);
          this.genresSignal.set({ genres: updatedGenres });
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
