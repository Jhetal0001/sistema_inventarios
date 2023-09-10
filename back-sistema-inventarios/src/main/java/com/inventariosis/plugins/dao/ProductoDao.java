package com.inventariosis.plugins.dao;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.inventariosis.plugins.models.entity.ProductoEntity;

public interface ProductoDao {

	/**
	 * 
	 * Método que retorna un producto por su Id Creado el 9/09/2023 a las 12:58:23
	 * a. m.
	 * 
	 * @author Jhon Vasquez
	 * @param idProducto
	 * @return ProductoEntity
	 */
	ProductoEntity findProductoById(Long idProducto);

	/**
	 * 
	 * Método que retorna una lista completa de los productos Creado el 9/09/2023 a
	 * las 1:31:44 a. m.
	 * 
	 * @author Jhon Vasquez
	 * @return List<ProductoEntity>
	 */
	List<ProductoEntity> findAllProductos();

	/**
	 * 
	 * Método que permite guardar un producto nuevo Creado el 9/09/2023 a las
	 * 2:16:19 a. m.
	 * 
	 * @author Jhon Vasquez
	 * @param producto
	 */
	ResponseEntity<?> registrarProducto(ProductoEntity producto);

	/**
	 * 
	 * Método que permite modificar un producto en el sistema Creado el 9/09/2023 a
	 * las 4:03:34 a. m.
	 * 
	 * @author Jhon Vasquez
	 * @param producto
	 */
	void updateProducto(ProductoEntity producto);

	/**
	 * 
	 * Método que permite eliminar un producto registrado
	 * Creado el 9/09/2023 a las 5:09:38 a. m.
	 * @author Jhon Vasquez
	 * @param producto
	 */
	void eliminarProducto(ProductoEntity producto);

}
