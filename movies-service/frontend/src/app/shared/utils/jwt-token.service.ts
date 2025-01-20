import {inject, Injectable} from '@angular/core';
import {JwtHelperService} from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class JwtTokenService {
  private readonly jwtHelper = inject(JwtHelperService);

  public decodeToken(token: string): any {
    return this.jwtHelper.decodeToken(token);
  }


  public isTokenExpired(token: string): boolean {
    return this.jwtHelper.isTokenExpired(token);
  }
}
