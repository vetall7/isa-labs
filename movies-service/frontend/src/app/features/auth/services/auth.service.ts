import {inject, Injectable} from '@angular/core';
import {UserLoginInfoModel, UserModel} from '@shared/models';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {v4 as uuidv4} from 'uuid';
import {UserRegistrationInfoModel} from "@shared/models/UserRegistrationInfo";
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly http = inject(HttpClient);

  public register(user: UserRegistrationInfoModel): Observable<void> {
    return this.http.put<void>('/api/users/registration/' + uuidv4(), user);
  }

  login(user: UserLoginInfoModel): Observable<string> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const body = { name: user.name, password: user.password };

    return this.http.post(`/api/users/login`, body, { headers, responseType: 'text' });
  }
}
