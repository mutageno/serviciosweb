import { Component, OnInit } from '@angular/core';
import { Http, Headers, URLSearchParams } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/of';
import { Observable } from 'rxjs/Observable';

interface Info {
  _id?: string;
  name?: string;
  email?: string;
  password?: string;
  __v?: number;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Cliente de una API REST hecha en JavaScript';
  datos: Observable<Info[]> = Observable.of([]);
  respuesta: Info = {};
  constructor(private http: Http) { }
  ngOnInit() {
    this.refrescar();
  }
  refrescar() {
    console.log('Refrescando datos...');
    this.datos = this.http.get('http://localhost:3000/users')
      .map(response => response.json() as Info[]);
  }
  metodoPost() {
    console.log('Creando un usuario nuevo...');
    const parametros = new URLSearchParams();
    parametros.append('name', 'nuevo');
    parametros.append('email', 'nuevo@xyz.com');
    parametros.append('password', 'nuevopassword');
    this.http.post('http://localhost:3000/users', parametros)
      .map(response => response.json() as Info)
      .subscribe(
      data => this.respuesta = data,
      error => console.log(error),
      () => console.log('Observable completado')
      );
  }
}
