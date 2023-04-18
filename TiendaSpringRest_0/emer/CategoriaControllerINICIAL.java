package es.rf.tienda.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.dto.Mensajes;
import es.rf.tienda.service.ServicioCategoria;
import jakarta.annotation.Resource;

/*
 * Posibilidad de usar responseEntity
 * https://www.baeldung.com/spring-response-entity
 * 
 * Uso de ExceptionHandler
 * https://www.baeldung.com/exception-handling-for-rest-with-spring
 * 
 * En servicioGral, probar 
 * private JpaRepository<T, ID> cRepository;
 */


@RestController
@RequestMapping("/categorias")
public class CategoriaControllerINICIAL {

	@Autowired
	private ServicioCategoria cDao;

	@GetMapping("/{id}")
	public Mensajes leerUno(@PathVariable("id") String ids) {
		if (ids != null) {
			try {
				int id = Integer.parseInt(ids);

				Categoria c = (Categoria) cDao.leerUno(id);
				if (c == null) {
					return mensaje("500", "Categoria no existe", null);
				} else {
					return mensaje("200", "", c);
				}

			} catch (NumberFormatException nfe) {
				return mensaje("500", "Codigo erroneo", null);
			}
		} else {
			return mensaje("500", "Peticion erronea", null);
		}

	}

//	@GetMapping("/{id}")
//	public Categoria leerUno(@PathVariable("id") int id) {
//		System.out.println(id);
//			
//			Categoria c = (Categoria) cDao.leerUno(id) ;
//			
//			return c;
//		
//	}

	@GetMapping()
	public Mensajes leerTodos() {
		return mensajeList("200", "", cDao.listAll());
	}

	@PostMapping
	public String[] alta(@RequestBody Categoria c) {
		c.setId_categoria(0);
		if (cDao.insert(c)) {
			return new String[] { "200", "Registro Salvado" };
		} else {
			return new String[] { "500", "Registro No valido" };
		}
	}

	@PutMapping
	public String[] modificacion(@RequestBody Categoria c) {
		if (cDao.update(c)) {
			return new String[] { "200", "Registro Salvado" };
		} else {
			return new String[] { "500", "Registro No valido" };
		}
	}

	@DeleteMapping("/{id}")
	public String[] eliminar(@PathVariable("id") String ids) {
		if (ids != null) {
			try {
				int id = Integer.parseInt(ids);
				cDao.deleteById(id);
				return new String[] { "200", "Registro borrado" };
			} catch (NumberFormatException nfe) {
				return new String[] { "500", "Registro erroneo" };
			}
		}
		return new String[] { "500", "Falta registro" };
	}

	public Mensajes mensaje(String error, String mensaje, Categoria respuesta) {
		Mensajes retorno = new Mensajes();
		retorno.codigo = error;
		retorno.mensaje = mensaje;
		retorno.categoria = respuesta;
		return retorno;
	}

	public Mensajes mensajeList(String error, String mensaje, List<Categoria> respuesta) {
		Mensajes retorno = new Mensajes();
		retorno.codigo = error;
		retorno.mensaje = mensaje;
		retorno.lista = respuesta;
		return retorno;
	}
}
