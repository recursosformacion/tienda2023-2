package rf.com.tienda.servicio;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
public interface  IServicio<T, S> {

	public boolean insert(T t);
	public boolean update(T t);
	public boolean deleteById(S s);
	public List<T> listAll();
	public T leerUno(S s);
}
