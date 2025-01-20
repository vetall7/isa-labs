import {HttpEvent, HttpHandler, HttpHandlerFn, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {inject} from '@angular/core';
import {LocalStorageService} from '@shared/utils/local-storage.service';

export function authTokenInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>> {
  const localStorage = inject(LocalStorageService);

  const token = localStorage.getItem('token');
  if (token) {
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
  }
  return next(req);
}
