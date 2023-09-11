import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Cargos } from 'src/app/models/tipo-cargo.model';
import { User } from 'src/app/models/users.model';
import { BackendService } from 'src/app/services/backend.service';
import { UtilsService } from 'src/app/services/utils.service';

@Component({
  selector: 'app-registro-users',
  templateUrl: './registro-users.component.html',
  styleUrls: ['./registro-users.component.scss'],
})
export class RegistroUsersComponent {

  registroUsuarioForm!: FormGroup;
  fechaActual = new Date();
  formularioValidado = false;
  cargos!: Cargos;

  constructor(
    private fb: FormBuilder,
    private modalService: NgbModal,
    private UTIL: UtilsService,
    private backendService: BackendService,
    ) {
      this.registroUsuarioForm = this.fb.group({
        nombre: new FormControl('', [Validators.required]),
        edad: new FormControl('', [Validators.required, Validators.min(18)]),
        cargo: new FormControl('', [Validators.required]),
        fecha: new FormControl('', [Validators.required, this.fechaMaximaValidator])
      })
      this.backendService.getAllCargos().subscribe(cargos => this.cargos = cargos);
    }

    fechaMaximaValidator(control: AbstractControl): { [key: string]: any } | null {
      const fechaSeleccionada = new Date(control.value);
      const fechaActual = new Date();
      if (fechaSeleccionada > fechaActual) {
        return { fechaMaxima: true };
      }
      return null;
    }

    registrarUsuario() {
      this.formularioValidado = true;

      if (this.registroUsuarioForm.valid) {
        let usuario: User = {
          nombre: this.registroUsuarioForm.get('nombre')?.value,
          edad: this.registroUsuarioForm.get('edad')?.value,
          tipoCargo: {
            id: parseInt(this.registroUsuarioForm.get('cargo')?.value),
            nombreCargo: ''
          },
          fechaIngreso: this.registroUsuarioForm.get('fecha')?.value
        }
        this.backendService.addUsuario(usuario).subscribe(() => {this.UTIL.showAlert('Se ha registrado el Usuario','success')}, error => this.UTIL.showAlert('Ocurrio un error al registrar el Usuario:'+error.error.message,'danger'));
        this.registroUsuarioForm.reset();
        this.modalService.dismissAll();
      }
    }
}
