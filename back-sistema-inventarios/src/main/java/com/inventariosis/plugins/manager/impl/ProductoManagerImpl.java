package com.inventariosis.plugins.manager.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.inventariosis.plugins.dao.ProductoDao;
import com.inventariosis.plugins.dao.UsuariosDao;
import com.inventariosis.plugins.manager.ProductoManager;
import com.inventariosis.plugins.models.dto.ProductoDTO;
import com.inventariosis.plugins.models.entity.ProductoEntity;
import com.inventariosis.plugins.models.entity.UsuariosEntity;

@Service
public class ProductoManagerImpl implements ProductoManager {

	@Autowired
	private ProductoDao productoDao;
	@Autowired
	private UsuariosDao usuariosDao;

	/*
	 * 
	 * @see
	 * com.inventariosis.plugins.manager.ProductoManager#findProducto(java.lang.
	 * Long)
	 */
	@Override
	public ResponseEntity<ProductoEntity> findProducto(Long idProducto) {
		try {
			ProductoEntity product = productoDao.findProductoById(idProducto);
			return ResponseEntity.ok(product);
		} catch (NoResultException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	/*
	 * 
	 * @see com.inventariosis.plugins.manager.ProductoManager#finAllProductos()
	 */
	@Override
	public ResponseEntity<List<ProductoEntity>> finAllProductos() {
		try {
			List<ProductoEntity> productos = productoDao.findAllProductos();
			return ResponseEntity.ok(productos);
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	/*
	 * 
	 * @see com.inventariosis.plugins.manager.ProductoManager#setProducto(com.
	 * inventariosis.plugins.models.entity.ProductoEntity)
	 */
	@Override
	@Transactional
	public ResponseEntity<?> setProducto(ProductoDTO productoDTO) {
			ProductoEntity producto = new ProductoEntity();
			UsuariosEntity usuarioRegistra = usuariosDao.findUsuarioById(productoDTO.getUsuarioRegistra().getId());
			producto.setNombreProducto(productoDTO.getNombreProducto());
			producto.setCantidad(productoDTO.getCantidad());
			producto.setUsuarioRegistra(usuarioRegistra);
			producto.setUsuarioModifica(usuarioRegistra);
			productoDao.registrarProducto(producto);		
			return this.finAllProductos();
	}

	/*
	 * 
	 * @see com.inventariosis.plugins.manager.ProductoManager#updateProducto(com.
	 * inventariosis.plugins.models.dto.ProductoDTO)
	 */
	@Override
	@Transactional
	public ResponseEntity<?> updateProducto(ProductoDTO productoDTO) {
		try {
			ProductoEntity producto = productoDao.findProductoById(productoDTO.getId());
			UsuariosEntity usuarioModifica = usuariosDao.findUsuarioById(productoDTO.getUsuarioModifica().getId());

			producto.setNombreProducto(productoDTO.getNombreProducto());
			producto.setCantidad(productoDTO.getCantidad());
			producto.setUsuarioModifica(usuarioModifica);
			productoDao.updateProducto(producto);
			return this.finAllProductos();
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			String mensajeError = "Ya existe un producto con el mismo nombre.";
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensajeError);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar el producto");
		}
	}

	/*
	 * 
	 * @see
	 * com.inventariosis.plugins.manager.ProductoManager#removeProducto(java.lang.
	 * Long, java.lang.Long)
	 */
	@Override
	@Transactional
	public ResponseEntity<?> removeProducto(Long idProducto, Long idUsuario) {
		try {
			ProductoEntity producto = productoDao.findProductoById(idProducto);
			if (producto.getUsuarioRegistra().getId().equals(idUsuario)) {
				productoDao.eliminarProducto(producto);
				return this.finAllProductos();
			} else {
				String mensaje = "Solo el usuario que registró el producto puede eliminarlo";
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al Eliminar el producto");
		}
	}
}
