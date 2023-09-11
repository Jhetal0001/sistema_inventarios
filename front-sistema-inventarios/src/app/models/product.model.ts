import { User } from "./users.model"

export interface Products extends Array<Product> {}

export interface Product {
  id?: number
  nombreProducto: string
  cantidad: number
  fechaIngreso?: Date
  usuarioRegistra: User
  fechaModifica?: Date
  usuarioModifica: User
}
