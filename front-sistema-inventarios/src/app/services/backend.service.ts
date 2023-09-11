import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject, catchError, tap, throwError } from 'rxjs';
import { Product, Products } from '../models/product.model';
import { Cargos, TipoCargo } from '../models/tipo-cargo.model';
import { User, Users } from '../models/users.model';

@Injectable({
  providedIn: 'root',
})
export class BackendService {
  private urlBack = 'http://localhost:8080/backend/api';

  private vehiculosSubject = new Subject<Products>();
  vehiculosSubject$ = this.vehiculosSubject.asObservable();

  private cargoSubject = new Subject<Cargos>();
  cargoSubject$ = this.cargoSubject.asObservable();

  private usuarioSubject = new Subject<Users>();
  usuarioSubject$ = this.usuarioSubject.asObservable();

  constructor(private http: HttpClient) {}

  getAllUsers(): Observable<any> {
    return this.http.get(`${this.urlBack}/usuarios/getUsuarios`);
  }
  getAllProducts(): Observable<any> {
    return this.http.get(`${this.urlBack}/producto/findAllProductos`);
  }
  getAllCargos(): Observable<any> {
    return this.http.get(`${this.urlBack}/cargos/getCargos`);
  }
  updateProduct(item: Product): Observable<any> {
    return this.http.post(`${this.urlBack}/producto/actualizarProducto`, item
    ).pipe(
      tap((response) => {
        const vehiculosI = response as Products;
        if (vehiculosI) {
          this.vehiculosSubject.next(vehiculosI);
        }
      }),
      catchError((error) => {
        console.error('Error al registrar producto', error);
        return throwError(error);
      })
    );
  }
  addProduct(item: Product): Observable<any> {
    return this.http.post(`${this.urlBack}/producto/registrarProducto`, item)
      .pipe(
        tap((response) => {
          const vehiculosI = response as Products;
          if (vehiculosI) {
            this.vehiculosSubject.next(vehiculosI);
          }
        }),
        catchError((error) => {
          console.error('Error al registrar producto', error);
          return throwError(error);
        })
      );
  }
  deleteProduct(idUser: number, item: Product): Observable<any> {
    return this.http.delete(`${this.urlBack}/producto/eliminarProducto?idProducto=${item.id}&idUsuario=${idUser}`
    ).pipe(
      tap((response) => {
        const vehiculosI = response as Products;
        if (vehiculosI) {
          this.vehiculosSubject.next(vehiculosI);
        }
      }),
      catchError((error) => {
        console.error('Error al registrar producto', error);
        return throwError(error);
      })
    );
  }
  addUsuario(item: User): Observable<any> {
    return this.http.post(`${this.urlBack}/usuarios/registrarUsuario`, item)
      .pipe(
        tap((response) => {
          const usuariosI = response as Users;
          if (usuariosI) {
            this.usuarioSubject.next(usuariosI);
          }
        }),
        catchError((error) => {
          console.error('Error al registrar el Usuario', error);
          return throwError(error);
        })
      );
  }
  addCargo(item: TipoCargo): Observable<any> {
    return this.http.post(`${this.urlBack}/cargos/registrarCargo`, item)
      .pipe(
        tap((response) => {
          const cargosI = response as Cargos;
          if (cargosI) {
            this.cargoSubject.next(cargosI);
          }
        }),
        catchError((error) => {
          console.error('Error al registrar el cargo', error);
          return throwError(error);
        })
      );
  }
}
