package com.inventariosis.plugins.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.inventariosis.plugins.dao.ProductoDao;
import com.inventariosis.plugins.models.entity.ProductoEntity;
import com.inventariosis.plugins.util.Constantes;

@Repository
public class ProductoDaoImpl implements ProductoDao {

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * 
	 * @see
	 * com.inventariosis.plugins.dao.ProductoDao#findProductoById(java.lang.Long)
	 */
	@Override
	public ProductoEntity findProductoById(Long idProducto) {
		ProductoEntity producto = entityManager.find(ProductoEntity.class, idProducto);
		if (producto == null) {
			System.out.println("No se encontró ningún producto con ID " + idProducto);
		}
		return producto;

	}

	/*
	 * 
	 * @see com.inventariosis.plugins.dao.ProductoDao#findAllProductos()
	 */
	@Override
	public List<ProductoEntity> findAllProductos() {
		String jpqlQuery = Constantes.QUERYS.get("GET_PRODUCTOS");
		TypedQuery<ProductoEntity> query = entityManager.createQuery(jpqlQuery, ProductoEntity.class);
		return query.getResultList();
	}

	/*
	 * 
	 * @see
	 * com.inventariosis.plugins.dao.ProductoDao#registrarProducto(com.inventariosis
	 * .plugins.models.entity.ProductoEntity)
	 */
	@Override
	public ResponseEntity<?> registrarProducto(ProductoEntity producto) {
		try {
			entityManager.persist(producto);
			return ResponseEntity.ok("Registro Exitoso");
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			String mensajeError = "Ya existe un producto con el mismo nombre.";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeError);
		}
	}

	/*
	 * 
	 * @see
	 * com.inventariosis.plugins.dao.ProductoDao#updateProducto(com.inventariosis.
	 * plugins.models.entity.ProductoEntity)
	 */
	@Override
	public void updateProducto(ProductoEntity producto) {
		entityManager.merge(producto);
	}

	/*
	 * 
	 * @see
	 * com.inventariosis.plugins.dao.ProductoDao#eliminarProducto(com.inventariosis.
	 * plugins.models.entity.ProductoEntity)
	 */
	@Override
	public void eliminarProducto(ProductoEntity producto) {
		entityManager.remove(producto);
	}
}
