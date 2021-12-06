import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthServiceService } from '../auth-service/auth-service.service';
import { TokenStorageService } from '../token-storage/token-storage.service';

@Injectable()
export class HttpInterceptorService implements HttpInterceptor {

    constructor(private authenticationService: AuthServiceService, private tokenManager: TokenStorageService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (this.tokenManager.getCurrentToken != null && (req.url.indexOf('authenticate') === -1
            && req.url.indexOf('player/add') === -1 && req.url.indexOf('player/checkUn') === -1
            && req.url.indexOf('game/newGame') == -1)) {
            const authReq = req.clone({
                headers: new HttpHeaders({
                    //'Content-Type': 'application/json',
                    'Authorization': this.tokenManager.getBearerToken()
                })
            });
            return next.handle(authReq);
        } else {
            return next.handle(req);
        }
    }
}