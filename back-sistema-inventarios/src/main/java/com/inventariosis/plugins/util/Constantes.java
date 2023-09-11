package com.inventariosis.plugins.util;

import java.util.HashMap;
import java.util.Map;

public class Constantes {

	public static final Map<String, String> QUERYS = new HashMap<>();

    static {
        QUERYS.put("GET_USUARIOS", "SELECT u FROM UsuariosEntity u");
        QUERYS.put("GET_PRODUCTOS", "SELECT p FROM ProductoEntity p");
        QUERYS.put("GET_CARGOS", "SELECT tc FROM TipoCargoEntity tc");
    }
	
}
