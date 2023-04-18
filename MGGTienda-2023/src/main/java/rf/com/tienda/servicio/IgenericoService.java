package rf.com.tienda.servicio;

import java.util.List;
import java.util.Optional;

import rf.com.tienda.dominio.Usuario;
import rf.com.tienda.exception.DomainException;

public interface IgenericoService<S,T> {

	S crear(S categoria)  throws DomainException ;
	
	List<S> leerTodos();
	
	Optional<S> leerPorId(T id);
	
	S actualizar(S categoria,T id)  throws DomainException ;
	
	void borrar(T id);
}
