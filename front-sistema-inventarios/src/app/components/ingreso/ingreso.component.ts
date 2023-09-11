import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { User, Users } from 'src/app/models/users.model';
import { BackendService } from 'src/app/services/backend.service';
import { FrontService } from 'src/app/services/front.service';
import { RegistroUsersComponent } from '../registro-users/registro-users.component';
import { RegistroCargosComponent } from '../registro-cargos/registro-cargos.component';

@Component({
  selector: 'app-ingreso',
  templateUrl: './ingreso.component.html',
  styleUrls: ['./ingreso.component.scss'],
})
export class IngresoComponent {
  usuarios!: Users;
  selectedUser!: string;

  constructor(
    private backService: BackendService,
    private frontService: FrontService,
    private router: Router,
    private modalService: NgbModal
  ) {
    this.backService.getAllUsers().subscribe((item: Users) => {
      this.usuarios = item;
    });
  }

  selectUser() {
    const user = this.usuarios.find(
      (elemento: User) => elemento.id === parseInt(this.selectedUser)
    );
    if (user) {
      this.frontService.selectUser(user);
      sessionStorage.setItem('user', JSON.stringify(user));
      this.router.navigate(['home/productos']);
    }
  }

  seleccionarUsuario(event: any) {
    this.selectedUser = event.target.value;
  }

  registrarUsuario() {
    this.modalService.open(RegistroUsersComponent, { centered: true });
  }

  registrarCargos() {
    this.modalService.open(RegistroCargosComponent, { centered: true });
  }

  ngOnInit(): void {
    this.backService.usuarioSubject$.subscribe((users: Users) => {
      this.usuarios = users;
    });
  }
}
