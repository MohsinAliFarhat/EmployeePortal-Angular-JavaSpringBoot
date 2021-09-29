import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class BackendCommunicationService {

  private baseUrl: String = 'http://localhost:8080'
  constructor(private http: HttpClient) { }


  async getAllEmployees(page=0,pageSize=5) {
    const httpOptions:any = {
      params: {
        page: page,
        pageSize:pageSize
      }
    }
    return this.http.get(this.baseUrl + `/employees`,httpOptions).toPromise();
  }

  async addEmployee(body) {
    return this.http.post(this.baseUrl + `/employees`, body).toPromise();
  }

  async deleteEmployee(id) {
    return this.http.delete(this.baseUrl + `/employees/${id}`).toPromise()
  }

  async updateEmployee(id, body) {
    return this.http.put(this.baseUrl + `/employees/${id}`, body).toPromise()
  }
  async getSingleEmployee(id) {
    return this.http.get(this.baseUrl + `/employees/${id}`).toPromise();
  }
}