import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class NoteService {
  private apiUrl = 'http://localhost:8083';


  constructor(private http: HttpClient) { }

  giveheader(): any {
    let token = localStorage.getItem('jwtToken');
    let headers = { Authorization: `Bearer ${token}` };
    return headers;

  }

  getRecentNotes(): Observable<any[]> {
    let headers = this.giveheader();
    return this.http.get<any[]>(`${this.apiUrl}/notes`, { headers });
  }

  getNoteDetails(id: number): Observable<any> {
    let headers = this.giveheader()
    return this.http.get<any>(`${this.apiUrl}/notes/${id}`, { headers });
  }

  updateNote(id: number, updatedNote: any): Observable<any> {
    let headers = this.giveheader()
    return this.http.put<any>(`${this.apiUrl}/notes/${id}`, updatedNote, { headers });
  }

  deleteNote(id: number): Observable<any> {
    let headers = this.giveheader()
    return this.http.delete<any>(`${this.apiUrl}/notes/${id}`, { headers });
  }
  addNote(newNote: any): Observable<any> {
    let headers = this.giveheader()
    return this.http.post(`${this.apiUrl}/notes`, newNote, { headers });
  }
}