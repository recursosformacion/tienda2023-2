package es.rf.tienda.controller;

import java.util.ArrayList;
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


import es.rf.tienda.dominio.Usuario;
import es.rf.tienda.repository.IUsuarioRepo;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private IUsuarioRepo cDao;
	
	@GetMapping("/{id}")
	public Usuario leerUno(@PathVariable("id") int id) {
		return cDao.findById(id).get() ;
	}
	
	@GetMapping()
	public List<Usuario> leerTodos() {		
		return cDao.findAll();
	}
	
	@PostMapping
	public String[]  alta(@RequestBody Usuario c) {
		c.setId_usuario(0);
		cDao.save(c);
		return new String[] {"200","Registro Salvado"};
	}
	
	@PutMapping
	public String[]  modificacion(@RequestBody Usuario c) {
		cDao.save(c);
		return new String[] {"200","Registro modificado"};
	}
	
	@DeleteMapping("/{id}")
	public String[]  eliminar(@PathVariable("id")  Integer id) {
		cDao.deleteById(id);
		return new String[] {"200","Registro borrado"};
	}
	

}
