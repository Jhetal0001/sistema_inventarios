package com.inventariosis.plugins.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventariosis.plugins.manager.UsuarioManager;
import com.inventariosis.plugins.models.dto.UsuarioDTO;
import com.inventariosis.plugins.models.entity.UsuariosEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/usuarios/")
public class GestionUsuariosService {

	@Autowired
	private UsuarioManager usuarioManager;

	@GetMapping("/getUsuarios")
	@Operation(summary = "Permite obtener los usuarios registrados")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha obtenido todos los usuarios", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UsuariosEntity.class)) }) })
	private ResponseEntity<List<UsuariosEntity>> findProducto() {
		return usuarioManager.getUsuarios();
	}

	@PostMapping("/registrarUsuario")
	@Operation(summary = "Permite Registrar un Usuario")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha registrado el Usuario exitosamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTO.class)) }) })
	private ResponseEntity<?> registrarUsuario(@RequestBody UsuarioDTO usuario) {
		return usuarioManager.setUsuario(usuario);
	}
}
