import { Component } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Product } from 'src/app/models/product.model';
import { User } from 'src/app/models/users.model';
import { BackendService } from 'src/app/services/backend.service';
import { UtilsService } from 'src/app/services/utils.service';

@Component({
  selector: 'app-modal-product',
  templateUrl: './modal-product.component.html',
  styleUrls: ['./modal-product.component.scss'],
})
export class ModalProductComponent {
  addProduct?: boolean;
  updateProduct?: Product;
  deleteProduct?: Product;

  addProductForm!: FormGroup;
  idUser!: User;

  constructor(
    private fb: FormBuilder,
    public activeModal: NgbActiveModal,
    private backendService: BackendService,
    private UTIL: UtilsService
  ) {
    this.addProductForm = this.fb.group({
      nombre: new FormControl('', [Validators.required]),
      cantidad: new FormControl('', [Validators.required]),
    });
    this.idUser = JSON.parse(sessionStorage.getItem('user') as string);
  }

  closeModal() {
    this.activeModal.close('confirm');
  }

  getionarProducts(accion: string) {
    switch (accion) {
      case 'eliminar':
        this.backendService
          .deleteProduct(this.idUser.id as number, this.deleteProduct as Product)
          .subscribe(
            () => this.UTIL.showAlert('Se ha eliminado el producto', 'success'),
            () =>
              this.UTIL.showAlert(
                'Este producto solo puede ser eliminado por la persona que lo registró',
                'warning'
              )
          );
        this.activeModal.dismiss('confirmar');
        break;
      case 'agregar':
        let newProduct = {
          cantidad: this.addProductForm.get('cantidad')?.value,
          nombreProducto: this.addProductForm.get('nombre')?.value,
          usuarioModifica: this.idUser,
          usuarioRegistra: this.idUser,
        };
        this.backendService.addProduct(newProduct).subscribe(
          () => this.UTIL.showAlert('Registro Exitoso', 'success'),
          (error) => {
            this.UTIL.showAlert(
              'Ya existe un producto con el mismo nombre: ' + error,
              'warning'
            );
          }
        );
        this.addProductForm.reset();
        this.activeModal.dismiss('confirmar');
        break;
      case 'modificar':
        this.updateProduct!.usuarioModifica = this.idUser;
        this.backendService
          .updateProduct(this.updateProduct as Product)
          .subscribe(
            () => this.UTIL.showAlert('Se ha guardado exitosamente', 'success'),
            (error) =>
              this.UTIL.showAlert(
                'Ha ocurrido un error al guardar' + error,
                'danger'
              )
          );
        this.activeModal.dismiss('confirmar');
        break;
      default:
        this.UTIL.showAlert('Ups, ha ocurrido un error', 'warning');
    }
  }
}
