package rf.com.tienda.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rf.com.tienda.dominio.Usuario;
import rf.com.tienda.exception.DomainException;
import rf.com.tienda.servicio.IgenericoService;
import rf.com.tienda.servicio.UsuarioService;



@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private IgenericoService<Usuario,Long> userService;
	
	@GetMapping("/{id}")
	public Optional<Usuario> leerUno(@PathVariable("id") Long id) {		
		return userService.leerPorId(id);
	}
	
	@GetMapping()
	public List<Usuario> leerTodos() {		
		return userService.leerTodos();
	}
	
	@PostMapping
	public Usuario  alta(@RequestBody Usuario c) throws DomainException {
		c.setId_usuario(0l);
		return userService.crear(c);

	}
	
	@PutMapping
	public String[]  modificacion(@RequestBody Usuario c) throws DomainException {
		userService.actualizar(c,c.getId_usuario());
		return new String[] {"200","Registro modificado"};
	}
	
	@DeleteMapping("/{id}")
	public String[]  eliminar(@PathVariable("id")  Long id) {
		userService.borrar(id);
		return new String[] {"200","Registro borrado"};
	}
	

}
