import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { IngresoComponent } from './components/ingreso/ingreso.component';
import { ProductosComponent } from './components/productos/productos.component';

const routes: Routes = [
  {
    path: 'home', component: HomeComponent,
    children: [
      { path: '', component: IngresoComponent },
      { path: 'productos', component: ProductosComponent },
    ],

  },
  { path: '**', pathMatch: 'full', redirectTo: 'home'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule {routes: any}
