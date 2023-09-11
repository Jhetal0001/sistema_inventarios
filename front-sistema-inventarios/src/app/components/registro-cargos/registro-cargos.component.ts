import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Cargos, TipoCargo } from 'src/app/models/tipo-cargo.model';
import { BackendService } from 'src/app/services/backend.service';
import { UtilsService } from 'src/app/services/utils.service';

@Component({
  selector: 'app-registro-cargos',
  templateUrl: './registro-cargos.component.html',
  styleUrls: ['./registro-cargos.component.scss']
})
export class RegistroCargosComponent implements OnInit{

  registroCargoForm!: FormGroup;
  fechaActual = new Date();
  formularioValidado = false;
  listCargos?: Cargos;
  listaFiltrada?: Cargos;
  filtroCargo!: '';

  constructor(
    private fb: FormBuilder,
    private modalService: NgbModal,
    private UTIL: UtilsService,
    private backendService: BackendService
    ) {
      this.registroCargoForm = this.fb.group({
        nombre: new FormControl('', [Validators.required])
      })
    }

    registrarCargo() {
      this.formularioValidado = true;

      if (this.registroCargoForm.valid) {
        let cargo: TipoCargo = {
          nombreCargo: this.registroCargoForm.get('nombre')?.value
        }
        this.backendService.addCargo(cargo).subscribe(() => {this.UTIL.showAlert('Se ha registrado el Cargo','success')}, error => this.UTIL.showAlert('Ocurrio un error al registrar el Cargo:'+error.error.message,'danger'));
        this.registroCargoForm.reset();
        this.modalService.dismissAll();
      }
    }


  aplicarFiltro(filtro: string) {
    switch (filtro) {
      case 'nombre':
        this.listaFiltrada = this.listCargos?.filter((item) => {
          return item.nombreCargo
            .toLowerCase()
            .includes(this.filtroCargo.toLowerCase());
        });
        break;
    default:
        break;
    }
  }

  ordenarAscendente(column: string) {
    switch (column) {
      case 'nombre':
        this.listaFiltrada = this.listaFiltrada?.slice().sort((a, b) => {
          const cargoA = (a.nombreCargo as string).toLowerCase();
          const cargoB = (b.nombreCargo as string).toLowerCase();
          if (cargoA < cargoB) {
            return -1;
          }
          if (cargoA > cargoB) {
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
      case 'nombre':
        this.listaFiltrada = this.listaFiltrada?.slice().sort((a, b) => {
          const cargoA = (a.nombreCargo as string).toLowerCase();
          const cargoB = (b.nombreCargo as string).toLowerCase();
          if (cargoA < cargoB) {
            return 1;
          }
          if (cargoA > cargoB) {
            return -1;
          }
          return 0;
        });
        break;
      default:
        break;
    }
  }

  ngOnInit(): void {
    this.backendService.getAllCargos().subscribe((cargos: Cargos) =>
      {this.listCargos = cargos
      this.listaFiltrada = this.listCargos;}
    );

  }
}
