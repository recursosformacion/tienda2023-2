package rf.com.tienda.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rf.com.tienda.dominio.Categoria;
import rf.com.tienda.exception.DomainException;
import rf.com.tienda.servicio.CategoriaService;


@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("/{id}")
	public Categoria leerUno(@PathVariable("id") Long id) {		
		return categoriaService.leerPorId(id);
	}
	
	@GetMapping()
	public List<Categoria> leerTodos() {		
		return categoriaService.leerTodos();
	}
	
	@PostMapping
	public Categoria  alta(@RequestBody Categoria c) {
		c.setId_categoria(0l);
		return categoriaService.crear(c);

	}
	
	@PutMapping
	public String[]  modificacion(@RequestBody Categoria c) throws DomainException {
		categoriaService.actualizar(c,c.getId_categoria());
		return new String[] {"200","Registro modificado"};
	}
	
	@DeleteMapping("/{id}")
	public String[]  eliminar(@PathVariable("id")  Long id) {
		categoriaService.borrar(id);
		return new String[] {"200","Registro borrado"};
	}
	

}
