package rf.com.tienda.servicio;

import java.util.List;

import rf.com.tienda.dominio.Categoria;
import rf.com.tienda.exception.DomainException;

public interface IgenericoService<S,T> {

	S crear(S categoria)  throws DomainException ;
	
	List<S> leerTodos();
	
	S leerPorId(T id);
	
	S actualizar(S categoria,T id)  throws DomainException ;
	
	void borrar(T id);
}
