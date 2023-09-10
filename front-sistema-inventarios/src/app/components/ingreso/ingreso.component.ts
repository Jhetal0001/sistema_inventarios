import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User, Users } from 'src/app/models/users.model';
import { BackendService } from 'src/app/services/backend.service';
import { FrontService } from 'src/app/services/front.service';

@Component({
  selector: 'app-ingreso',
  templateUrl: './ingreso.component.html',
  styleUrls: ['./ingreso.component.scss']
})
export class IngresoComponent {

  usuarios!: Users;
  selectedUser!: string;

  constructor(private backService: BackendService, private frontService: FrontService, private router: Router,) {
     this.backService.getAllUsers().subscribe((item: Users) => {this.usuarios = item});
  }

  selectUser() {
    const user = this.usuarios.find((elemento: User) => elemento.id === parseInt(this.selectedUser));
    if(user) {
      this.frontService.selectUser(user);
      sessionStorage.setItem('user', JSON.stringify(user));
      this.router.navigate(['home/productos']);
    }
  }

  seleccionarUsuario(event: any) {
    this.selectedUser = event.target.value;
  }

}
