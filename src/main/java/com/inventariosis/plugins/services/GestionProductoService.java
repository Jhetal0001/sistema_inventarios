package com.inventariosis.plugins.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventariosis.plugins.manager.ProductoManager;
import com.inventariosis.plugins.models.dto.ProductoDTO;
import com.inventariosis.plugins.models.entity.ProductoEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/producto/")
public class GestionProductoService {

	@Autowired
	private ProductoManager productoManager;

	@GetMapping("/findProducto/{idProducto}")
	@Operation(summary = "Permite obtener un producto por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha obtenido el producto por su {id}", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ProductoEntity.class)) }) })
	private ResponseEntity<ProductoEntity> findProducto(@PathVariable("idProducto") Long idProducto) {
		return productoManager.findProducto(idProducto);
	}

	@GetMapping("/findAllProductos")
	@Operation(summary = "Permite obtener una lista completa de productos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha obtenido la lista de productos", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ProductoEntity.class)) }) })
	private ResponseEntity<List<ProductoEntity>> findAllProductos() {
		return productoManager.finAllProductos();
	}

	@PostMapping("/registrarProducto")
	@Operation(summary = "Permite Registrar un Producto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha registrado el producto exitosamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ProductoEntity.class)) }) })
	private ResponseEntity<String> registrarProducto(@RequestBody ProductoDTO producto) {
		return productoManager.setProducto(producto);
	}

	@PostMapping("/actualizarProducto")
	@Operation(summary = "Permite actualizar un Producto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha actualizado el producto exitosamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ProductoEntity.class)) }) })
	private ResponseEntity<String> actualizarProducto(@RequestBody ProductoDTO producto) {
		return productoManager.updateProducto(producto);
	}

	@DeleteMapping("/eliminarProducto")
	@Operation(summary = "Permite eliminar un Producto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha eliminado el producto exitosamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ProductoEntity.class)) }) })
	private ResponseEntity<String> eliminarProducto(@RequestParam("idProducto") Long idProducto,
			@RequestParam("idUsuario") Long idUsuario) {
		return productoManager.removeProducto(idProducto, idUsuario);
	}

}
