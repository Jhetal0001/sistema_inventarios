import { Component, OnInit } from '@angular/core';
import { Product, Products } from 'src/app/models/product.model';
import { User } from 'src/app/models/users.model';
import { BackendService } from 'src/app/services/backend.service';
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

  filtroIdProducto: any = '';
  filtroProducto: any = '';
  filtroCantidad: any = '';
  filtroFechaIngreso: string = '';
  filtroUsuarioIngresa: any = '';
  filtroFechaModifica: any = '';
  filtroUsuarioModifica: any = '';

  listaFiltrada?: Products;
  listProducts?: Products;

  constructor(
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
        this.UTIL.showAlert('ha ocurrido un error', 'danger');
    }
  }

  aplicarFiltro(filtro: string) {
    switch (filtro) {
      case 'idProducto':
        if (this.filtroIdProducto !== '') {
          this.listaFiltrada = this.listProducts?.filter((item) => {
            return item.id === parseInt(this.filtroIdProducto);
          });
        } else {
          this.listaFiltrada = this.listProducts;
        }
        break;
      case 'producto':
        this.listaFiltrada = this.listProducts?.filter((item) => {
          return item.nombreProducto
            .toLowerCase()
            .includes(this.filtroProducto.toLowerCase());
        });
        break;
      case 'cantidad':
        if (this.filtroCantidad !== '') {
          this.listaFiltrada = this.listProducts?.filter((item) => {
            return item.cantidad === parseInt(this.filtroCantidad);
          });
        } else {
          this.listaFiltrada = this.listProducts;
        }
        break;
      case 'fechaIngreso':
        if (this.filtroFechaIngreso && this.filtroFechaIngreso !== '') {
          if (this.filtroFechaIngreso) {
            this.listaFiltrada = this.listProducts?.filter((item) => {
              const fechaItem = new Date(item.fechaIngreso as Date);

              const fechaItemString = fechaItem.toLocaleDateString();
              const filtroFechaString = this.formatoFecha(
                this.filtroFechaIngreso
              );
              return fechaItemString === filtroFechaString;
            });
          }
        } else {
          this.listaFiltrada = this.listProducts;
        }
        break;
      case 'usuarioIngresa':
        this.listaFiltrada = this.listProducts?.filter((item) => {
          return item.usuarioRegistra.nombre
            .toLowerCase()
            .includes(this.filtroUsuarioIngresa.toLowerCase());
        });
        break;
      case 'fechaModifica':
        if (this.filtroFechaModifica && this.filtroFechaModifica !== '') {
          if (this.filtroFechaModifica) {
            this.listaFiltrada = this.listProducts?.filter((item) => {
              const fechaItem = new Date(item.fechaModifica as Date);

              const fechaItemString = fechaItem.toLocaleDateString();
              const filtroFechaString = this.formatoFecha(
                this.filtroFechaModifica
              );
              return fechaItemString === filtroFechaString;
            });
          }
        } else {
          this.listaFiltrada = this.listProducts;
        }
        break;
      case 'usuarioModifica':
        this.listaFiltrada = this.listProducts?.filter((item) => {
          return item.usuarioModifica.nombre
            .toLowerCase()
            .includes(this.filtroUsuarioModifica.toLowerCase());
        });
        break;
      default:
        break;
    }
    this.sumTotalProducts(this.listaFiltrada as Products);
  }

  ordenarAscendente(column: string) {
    switch (column) {
      case 'idProducto':
        this.listaFiltrada = this.listaFiltrada
          ?.slice()
          .sort((a, b) => (a.id as number) - (b.id as number));
        break;
      case 'producto':
        this.listaFiltrada = this.listaFiltrada?.slice().sort((a, b) => {
          const productoA = (a.nombreProducto as string).toLowerCase();
          const productoB = (b.nombreProducto as string).toLowerCase();
          if (productoA < productoB) {
            return -1;
          }
          if (productoA > productoB) {
            return 1;
          }
          return 0;
        });
        break;
      case 'cantidad':
        this.listaFiltrada = this.listaFiltrada
          ?.slice()
          .sort((a, b) => (a.cantidad as number) - (b.cantidad as number));
        break;
      case 'fechaIngreso':
        this.listaFiltrada = this.listaFiltrada?.slice().sort((a, b) => {
          const fechaA = new Date(a.fechaIngreso as Date);
          const fechaB = new Date(b.fechaIngreso as Date);
          if (fechaA < fechaB) {
            return -1;
          }
          if (fechaA > fechaB) {
            return 1;
          }
          return 0;
        });
        break;
      case 'usuarioIngresa':
        this.listaFiltrada = this.listaFiltrada?.slice().sort((a, b) => {
          const productoA = (a.usuarioRegistra.nombre as string).toLowerCase();
          const productoB = (b.usuarioRegistra.nombre as string).toLowerCase();
          if (productoA < productoB) {
            return -1;
          }
          if (productoA > productoB) {
            return 1;
          }
          return 0;
        });
        break;
      case 'fechaModifica':
        this.listaFiltrada = this.listaFiltrada?.slice().sort((a, b) => {
          const fechaA = new Date(a.fechaModifica as Date);
          const fechaB = new Date(b.fechaModifica as Date);
          if (fechaA < fechaB) {
            return -1;
          }
          if (fechaA > fechaB) {
            return 1;
          }
          return 0;
        });
        break;
      case 'usuarioModifica':
        this.listaFiltrada = this.listaFiltrada?.slice().sort((a, b) => {
          const productoA = (a.usuarioModifica.nombre as string).toLowerCase();
          const productoB = (b.usuarioModifica.nombre as string).toLowerCase();
          if (productoA < productoB) {
            return -1;
          }
          if (productoA > productoB) {
            return 1;
          }
          return 0;
        });
        break;
      default:
        break;
    }
  }
  ordenarDescendente(column: string) {
    switch (column) {
      case 'idProducto':
        this.listaFiltrada = this.listaFiltrada
          ?.slice()
          .sort((a, b) => (b.id as number) - (a.id as number));
        break;
      case 'producto':
        this.listaFiltrada = this.listaFiltrada?.slice().sort((a, b) => {
          const productoA = (a.nombreProducto as string).toLowerCase();
          const productoB = (b.nombreProducto as string).toLowerCase();
          if (productoA < productoB) {
            return 1;
          }
          if (productoA > productoB) {
            return -1;
          }
          return 0;
        });
        break;
      case 'cantidad':
        this.listaFiltrada = this.listaFiltrada
          ?.slice()
          .sort((a, b) => (b.cantidad as number) - (a.cantidad as number));
        break;
      case 'fechaIngreso':
        this.listaFiltrada = this.listaFiltrada?.slice().sort((a, b) => {
          const fechaA = new Date(a.fechaIngreso as Date);
          const fechaB = new Date(b.fechaIngreso as Date);
          if (fechaA < fechaB) {
            return 1;
          }
          if (fechaA > fechaB) {
            return -1;
          }
          return 0;
        });
        break;
      case 'usuarioIngresa':
        this.listaFiltrada = this.listaFiltrada?.slice().sort((a, b) => {
          const productoA = (a.usuarioRegistra.nombre as string).toLowerCase();
          const productoB = (b.usuarioRegistra.nombre as string).toLowerCase();
          if (productoA < productoB) {
            return 1;
          }
          if (productoA > productoB) {
            return -1;
          }
          return 0;
        });
        break;
      case 'fechaModifica':
        this.listaFiltrada = this.listaFiltrada?.slice().sort((a, b) => {
          const fechaA = new Date(a.fechaModifica as Date);
          const fechaB = new Date(b.fechaModifica as Date);
          if (fechaA < fechaB) {
            return 1;
          }
          if (fechaA > fechaB) {
            return -1;
          }
          return 0;
        });
        break;
      case 'usuarioModifica':
        this.listaFiltrada = this.listaFiltrada?.slice().sort((a, b) => {
          const productoA = (a.usuarioModifica.nombre as string).toLowerCase();
          const productoB = (b.usuarioModifica.nombre as string).toLowerCase();
          if (productoA < productoB) {
            return 1;
          }
          if (productoA > productoB) {
            return -1;
          }
          return 0;
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

  sumTotalProducts(products: Products) {
    this.totalProducts = 0;
    products.forEach((item) => {
      this.totalProducts += item.cantidad;
    });
  }

  ngOnInit(): void {
    this.backService.getAllProducts().subscribe((products: Products) => {
      this.listProducts = products;
      this.listaFiltrada = this.listProducts;
      this.sumTotalProducts(this.listaFiltrada);
    });
    this.backService.vehiculosSubject$.subscribe((products: Products) => {
      this.listProducts = products;
      this.listaFiltrada = this.listProducts;
      this.sumTotalProducts(this.listaFiltrada);
    });
  }
}
