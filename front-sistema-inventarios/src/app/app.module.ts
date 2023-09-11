import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HomeComponent } from './home/home.component';
import { IngresoComponent } from './components/ingreso/ingreso.component';
import { ProductosComponent } from './components/productos/productos.component';
import { ModalProductComponent } from './components/modal-product/modal-product.component';
import { AlertsComponent } from './components/alerts/alerts.component';
import { LoaderComponent } from './components/loader/loader.component';
import { RegistroUsersComponent } from './components/registro-users/registro-users.component';
import { RegistroCargosComponent } from './components/registro-cargos/registro-cargos.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    IngresoComponent,
    ProductosComponent,
    ModalProductComponent,
    AlertsComponent,
    LoaderComponent,
    RegistroUsersComponent,
    RegistroCargosComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
