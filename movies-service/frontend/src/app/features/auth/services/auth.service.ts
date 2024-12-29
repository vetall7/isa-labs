import {inject, Injectable} from '@angular/core';
import {UserLoginInfoModel, UserModel} from '@shared/models';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {v4 as uuidv4} from 'uuid';
import {UserRegistrationInfoModel} from "@shared/models/UserRegistrationInfo";
import {LocalStorageService} from '@shared/utils/local-storage.service';
import {JwtTokenService} from '@shared/utils/jwt-token.service';
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly http = inject(HttpClient);

  private readonly jwtTokenService = inject(JwtTokenService);
  private readonly localStorage = inject(LocalStorageService);

  public register(user: UserRegistrationInfoModel): Observable<void> {
    return this.http.put<void>('/api/users/registration/' + uuidv4(), user);
  }

  login(user: UserLoginInfoModel): Observable<string> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const body = { name: user.name, password: user.password };

    return this.http.post(`/api/users/login`, body, { headers, responseType: 'text' });
  }

  public getRole(token: string): "USER" | "ADMIN"  {
    const decodedToken = this.jwtTokenService.decodeToken(token);

    return decodedToken.role;
  }

  public setToken(token: string): void {
    this.localStorage.setItem('token', token);
  }

  public logout(): void {
    this.localStorage.removeItem('token');
  }

  public isAuthenticated(): boolean {
    return !!this.localStorage.getItem('token');
  }
}
