package es.rf.tienda.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import es.rf.tienda.dominio.interfaces.Modelo;

@Service
public abstract class ServicioGral<T extends Modelo, S,R extends JpaRepository<T, S>> implements IServicio<T, S>{

	@Autowired
	protected R cDao;

	public boolean insert(T t) {
		if (t.isValidInsert()) {
			nuevoId(t);
			cDao.save(t);
			return true;
		} else {
			return false;
		}
	}

	public boolean update(T t) {
		if (t.isValidUpdate()) {
			cDao.save(t);
			return true;
		} else {
			return false;
		}
	
	}

	public boolean deleteById(S s) {
		cDao.deleteById(s);
		return true;
	}

	public List<T> listAll() {
		return (List<T>) cDao.findAll();
	}

	public T leerUno(S s) {
		try {
			return (T) cDao.findById(s).get();
			
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	public abstract void nuevoId(T t);
}
