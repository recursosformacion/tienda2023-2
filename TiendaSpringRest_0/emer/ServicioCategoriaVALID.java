package es.rf.tienda.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.rf.tienda.dao.ICategoriaRepo;
import es.rf.tienda.dominio.Categoria;

// esto es un servicio
@Service
public class ServicioCategoriaVALID implements IServicio<Categoria, Integer>{

	@Autowired
	private ICategoriaRepo cDao;

	public boolean insert(Categoria t) {
		if (t.isValidInsert()) {
			cDao.save(t);
			return true;
		} else {
			return false;
		}
	}

	public boolean update(Categoria t) {
		if (t.isValidUpdate()) {
			cDao.save(t);
			return true;
		} else {
			return false;
		}
	
	}

	public boolean deleteById(Integer s) {
		cDao.deleteById(s);
		return true;
	}

	public List<Categoria> listAll() {
		return cDao.findAll();
	}

	public Categoria leerUno(Integer s) {
		try {
			return (Categoria) cDao.findById(s).get();
			
		} catch (NoSuchElementException e) {
			return new Categoria();
		}
	}

}
