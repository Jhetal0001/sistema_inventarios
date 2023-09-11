package com.inventariosis.plugins.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventariosis.plugins.manager.TipoCargoManager;
import com.inventariosis.plugins.models.dto.TipoCargoDTO;
import com.inventariosis.plugins.models.entity.TipoCargoEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/cargos/")
public class GestionCargosService {

	@Autowired
	private TipoCargoManager tipoCargoManager;

	@GetMapping("/getCargos")
	@Operation(summary = "Permite obtener los Cargos registrados")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha obtenido todos los cargos", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TipoCargoEntity.class)) }) })
	private ResponseEntity<List<TipoCargoEntity>> findCargos() {
		return tipoCargoManager.getCargos();
	}

	@PostMapping("/registrarCargo")
	@Operation(summary = "Permite Registrar un Cargo")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha registrado el Cargo exitosamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TipoCargoDTO.class)) }) })
	private ResponseEntity<?> registrarCargo(@RequestBody TipoCargoDTO cargo) {
		return tipoCargoManager.setCargo(cargo);
	}
}
