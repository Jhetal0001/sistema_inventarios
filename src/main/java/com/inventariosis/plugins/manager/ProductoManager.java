package com.inventariosis.plugins.manager;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.inventariosis.plugins.models.dto.ProductoDTO;
import com.inventariosis.plugins.models.entity.ProductoEntity;

public interface ProductoManager {

	/**
	 * 
	 * Servicio que permite consultar producto por su id Creado el 9/09/2023 a las
	 * 1:01:21 a. m.
	 * 
	 * @author Jhon Vasquez
	 * @param idProducto
	 * @return
	 */
	ResponseEntity<ProductoEntity> findProducto(Long idProducto);

	/**
	 * 
	 * Servicio que retorna una lista completa de productos Creado el 9/09/2023 a
	 * las 1:34:38 a. m.
	 * 
	 * @author Jhon Vasquez
	 * @return
	 */
	ResponseEntity<List<ProductoEntity>> finAllProductos();

	/**
	 * 
	 * Servicio que permite guardar un nuevo producto Creado el 9/09/2023 a las
	 * 2:23:15 a. m.
	 * 
	 * @author Jhon Vasquez
	 * @param producto
	 * @return
	 */
	ResponseEntity<String> setProducto(ProductoDTO productoDTO);

	/**
	 * 
	 * Servicio que permite actualizar un producto del sistema Creado el 9/09/2023 a
	 * las 4:04:15 a. m.
	 * 
	 * @author Jhon Vasquez
	 * @param productoDTO
	 * @return
	 */
	ResponseEntity<String> updateProducto(ProductoDTO productoDTO);

	/**
	 * Servicio que permite eliminar un producto Creado el 9/09/2023 a las 5:21:01
	 * a. m.
	 * 
	 * @author Jhon Vasquez
	 * @param idProducto
	 * @param idUsuario
	 * @return
	 */
	ResponseEntity<String> removeProducto(Long idProducto, Long idUsuario);

}
