import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Genres} from '../models/genres.model';

@Injectable()
export class GenreService {

  private readonly http = inject(HttpClient);

  public getGenres(): Observable<Genres>{
    return this.http.get<Genres>('/api/genres');
  }
}
