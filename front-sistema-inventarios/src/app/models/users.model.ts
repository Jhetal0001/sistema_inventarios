import { TipoCargo } from "./tipo-cargo.model"

export interface Users extends Array<User> {}

export interface User {
  id: number
  nombre: string
  edad: number
  tipoCargo: TipoCargo
  fechaIngreso?: Date
}
