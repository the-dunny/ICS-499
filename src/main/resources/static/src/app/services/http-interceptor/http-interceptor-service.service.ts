import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthServiceService } from '../auth-service/auth-service.service';
import { TokenStorageService } from '../token-storage/token-storage.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

const TOKEN_HEADER = 'Authorization';

@Injectable()
export class HttpInterceptorService implements HttpInterceptor {

    constructor(private tokenManager: TokenStorageService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let authReq = req;
        const token = this.tokenManager.getCurrentToken();
        if(token != null){
            authReq = req.clone({headers: req.headers.set(TOKEN_HEADER, 'Bearer ' + token) });
        }
        return next.handle(authReq);
    }
}

export const authInterceptorProviders = [
    { provide: HTTP_INTERCEPTORS, useClass: HttpInterceptorService, multi: true }
  ];