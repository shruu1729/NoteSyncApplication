import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8083';

  constructor(private http: HttpClient) { }

  signUp(userData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/registerNewUser`, userData);
  }

  login(credentials: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/authenticate`, credentials).pipe(
      tap((response: any) => {
      })
    );
  }

  logout(): void {
    localStorage.removeItem('jwtToken');
    localStorage.clear();
    console.log("dvdavbdsb", localStorage.getItem('jwtToken'));

  }

  isAuthenticated(): boolean {
    return !!localStorage.getItem('jwtToken');
  }
}
