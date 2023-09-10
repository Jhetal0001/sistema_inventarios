import { Injectable } from '@angular/core';
import { User } from '../models/users.model';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FrontService {

  user$!: User;

  constructor() { }

  selectUser(userSelect: User) {
    this.user$ = userSelect;
  }

}
