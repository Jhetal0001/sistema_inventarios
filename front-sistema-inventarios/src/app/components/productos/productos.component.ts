import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs';
import { Product, Products } from 'src/app/models/product.model';
import { User } from 'src/app/models/users.model';
import { BackendService } from 'src/app/services/backend.service';
import { FrontService } from 'src/app/services/front.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalProductComponent } from '../modal-product/modal-product.component';
import { UtilsService } from 'src/app/services/utils.service';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.scss'],
})
export class ProductosComponent implements OnInit {
  user?: User;
  totalProducts: number = 0;
  filtro: any = '';
  listaFiltrada?: Products;
  listProducts?: Products;
  filtroFecha: string = '';

  constructor(
    private frontService: FrontService,
    private backService: BackendService,
    private modalService: NgbModal,
    private UTIL: UtilsService
  ) {
    this.user = JSON.parse(sessionStorage.getItem('user') as string);
  }

  gestionarProducto(item: Product | boolean, accion: string) {
    const modalRef = this.modalService.open(ModalProductComponent, {
      centered: true,
    });
    switch (accion) {
      case 'agregar':
        modalRef.componentInstance.addProduct = item;
        break;
      case 'eliminar':
        modalRef.componentInstance.deleteProduct = item;
        break;
      case 'modifica':
        modalRef.componentInstance.updateProduct = item;
        break;
      default:
        this.UTIL.showAlert('ha ocurrido un error','danger');
    }
  }

  aplicarFiltro(filtro: string) {
    switch (filtro) {
      case 'producto':
        this.listaFiltrada = this.listProducts?.filter((item) => {
          return item.nombreProducto
            .toLowerCase()
            .includes(this.filtro.toLowerCase());
        });
        break;
      case 'cantidad':
        if (this.filtro !== '') {
          this.listaFiltrada = this.listProducts?.filter((item) => {
            return item.cantidad === parseInt(this.filtro);
          });
        } else {
          this.listaFiltrada = this.listProducts;
        }
        break;
      case 'fechaIngreso':
        if (this.filtroFecha && this.filtroFecha !== '') {
          if (this.filtroFecha) {
            this.listaFiltrada = this.listProducts?.filter((item) => {
              const fechaItem = new Date(item.fechaIngreso as Date);

              const fechaItemString = fechaItem.toLocaleDateString();
              const filtroFechaString = this.formatoFecha(this.filtroFecha);
              return fechaItemString === filtroFechaString;
            });
          }
        }
        break;
      case 'usuarioIngresa':
        this.listaFiltrada = this.listProducts?.filter((item) => {
          return item.usuarioRegistra.nombre
            .toLowerCase()
            .includes(this.filtro.toLowerCase());
        });
        break;
      case 'fechaModifica':
        if (this.filtroFecha && this.filtroFecha !== '') {
          if (this.filtroFecha) {
            this.listaFiltrada = this.listProducts?.filter((item) => {
              const fechaItem = new Date(item.fechaModifica as Date);

              const fechaItemString = fechaItem.toLocaleDateString();
              const filtroFechaString = this.formatoFecha(this.filtroFecha);
              return fechaItemString === filtroFechaString;
            });
          }
        }
        break;
      case 'usuarioModifica':
        this.listaFiltrada = this.listProducts?.filter((item) => {
          return item.usuarioModifica.nombre
            .toLowerCase()
            .includes(this.filtro.toLowerCase());
        });
        break;
      default:
        break;
    }
  }

  formatoFecha(fechaConvertir: string) {
    const fecha = new Date(fechaConvertir);

    fecha.setDate(fecha.getDate() + 1);
    const dia = fecha.getDate();
    const mes = fecha.getMonth() + 1;
    const anio = fecha.getFullYear();

    const fechaFormateada = `${dia}/${mes}/${anio}`;

    return fechaFormateada;
  }

  ngOnInit(): void {
    this.backService.getAllProducts().subscribe((products: Products) => {
      this.listProducts = products;
      this.listaFiltrada = this.listProducts;
      products.forEach((item) => {
        this.totalProducts += item.cantidad;
      });
    });
    this.backService.vehiculosSubject$.subscribe((products: Products) => {
      this.listProducts = products;
      this.listaFiltrada = this.listProducts;
    });
  }
}
