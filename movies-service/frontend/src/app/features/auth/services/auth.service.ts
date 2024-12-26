import {inject, Injectable} from '@angular/core';
import {UserModel} from '@shared/models';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly http = inject(HttpClient);

  public register(user: UserModel): Observable<void> {
    return this.http.post<void>('/api/auth/register', user);
  }
}
