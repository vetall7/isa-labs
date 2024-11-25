import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {BehaviorSubject, Observable, tap} from 'rxjs';
import {Genres} from '../models/genres.model';
import {Genre} from '@genre/models/genre.model';
import {DetailedGenre} from '@genre/models/DetailedGenre.model';

@Injectable({ providedIn: 'root' })
export class GenreService {
  private readonly http = inject(HttpClient);

  private readonly genresSubject = new BehaviorSubject<Genres | null>(null);
  public readonly genres$ = this.genresSubject.asObservable();

  public getGenres(): Observable<Genres>{
    return this.http.get<Genres>('/api/genres').pipe(
      tap((genres) => {
        this.genresSubject.next(genres);
        console.log(this.genresSubject.getValue());
      })
    );
  }

  public deleteGenre(genre: Genre): Observable<any> {
    return this.http.delete('/api/genres/' + genre.name).pipe(
      tap(() => {
        const currentGenres = this.genresSubject.getValue();
        console.log(currentGenres);
        if (currentGenres) {
          const updatedGenres = currentGenres.genres.filter((g) => g.name !== genre.name);
          this.genresSubject.next({ genres: updatedGenres });
        }
      })
    );
  }

  public createGenre(genre: DetailedGenre): Observable<any>{
    return this.http.put('api/genres/' + genre.name, genre);
  }

  public getGenreDetails(genreId: String): Observable<DetailedGenre> {
    return this.http.get<DetailedGenre>('/api/genres/' + genreId);
  }

  public updateGenre(genre: DetailedGenre): Observable<any> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.patch('api/genres/' + genre.name, genre.description, {headers});
  }
}
