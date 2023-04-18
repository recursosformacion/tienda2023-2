package es.rf.tienda.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.rf.tienda.dominio.*;
import es.rf.tienda.exception.*;
import es.rf.tienda.service.*;


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
@CrossOrigin
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private IServicio<Categoria,Integer> cDao;

	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> leerUno(@PathVariable("id") String ids) throws ControllerException {
		String mensaje ="";
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (ids != null) {
			try {
				int id = Integer.parseInt(ids);
				Categoria cat = (Categoria) cDao.leerUno(id);

				if (cat != null) {
					map.put("status", 1);
					map.put("data", cat);
					return new ResponseEntity<>(map, HttpStatus.OK);
				} else {
					mensaje =  "No existen datos";
				}
			} catch (NumberFormatException nfe) {
				mensaje = "Formato erroneo";
			}
		} else {
			mensaje="Formato erroneo";
		}
		throw new ControllerException(mensaje);

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
	@CrossOrigin
	@GetMapping({"","/"})
	public ResponseEntity<Map<String, Object>> leerTodos() throws ControllerException {

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Categoria> cat = cDao.listAll();
		if (!cat.isEmpty()) {
			map.put("status", 1);
			map.put("data", cat);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			throw new ControllerException("No existen datos");
//			map.clear();
//			map.put("status", 0);
//			map.put("message", "No existen datos");
//			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> alta(@RequestBody Categoria c) throws DomainException, ControllerException {		//ID,NOMBRE,DESCRIPCION

//		throw new DomainException("Mensaje de pruebas");
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		c.setId_categoria(0);
		if (cDao.insert(c)) {
			map.put("status", 1);
			map.put("message", "Registro salvado");
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			throw new ControllerException("Error al hacer la insercion");
		}
	}

	@PutMapping
	public ResponseEntity<Map<String, Object>> modificacion(@RequestBody Categoria c) throws ControllerException {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (cDao.update(c)) {
			map.put("status", 1);
			map.put("message", "Error al actualizar");
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			throw new ControllerException("Error al hacer la modificacion");

		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> eliminar(@PathVariable("id") String ids) throws ControllerException {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (ids != null) {
			try {
				int id = Integer.parseInt(ids);
				Categoria cat = cDao.leerUno(id);
				cDao.deleteById(cat.getId_categoria());
				map.put("status", 1);
				map.put("message", "Registro borrado");
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			} catch (Exception ex) {
				throw new ControllerException("Error al borrar");

			}
		}
		throw new ControllerException("No existe registro al borrar");
	}

	@GetMapping("/error")
	public ResponseEntity<Map<String, Object>> error() throws DomainException, ControllerException {		

		throw new DomainException("Mensaje de pruebas");
	}
}
