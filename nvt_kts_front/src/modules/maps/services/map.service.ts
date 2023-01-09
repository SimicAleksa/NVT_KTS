import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MapService {

  constructor(private http: HttpClient) { }

  majmun() {
    this.http.get<String>("api/drivers/78").subscribe(
      {next: (response) => {console.log(response);
      }}
    )
  }
}
