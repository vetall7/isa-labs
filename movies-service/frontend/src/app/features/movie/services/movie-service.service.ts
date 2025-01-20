import {inject, Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {DetailedMovie} from '@movie/models/DetailedMovie.module';

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  private readonly http = inject(HttpClient);

  constructor() { }

  public createMovie(movie: DetailedMovie, movieId: String): Observable<any> {
    return this.http.put('/api/movies/' + movieId , movie);
  }

  public getDetailedMovie(movieId: String): Observable<DetailedMovie> {
    return this.http.get<DetailedMovie>('/api/movies/' + movieId);
  }

  public updateMovie(movie: DetailedMovie, movieId: String): Observable<any> {
    return this.http.patch('/api/movies/' + movieId, movie);
  }
}
