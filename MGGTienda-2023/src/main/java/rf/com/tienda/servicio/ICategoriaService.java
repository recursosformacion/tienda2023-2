package rf.com.tienda.servicio;

import java.util.List;

import rf.com.tienda.dominio.Categoria;
import rf.com.tienda.exception.DomainException;

public interface ICategoriaService {

	Categoria crear(Categoria categoria)  throws DomainException ;
	
	List<Categoria> leerTodos();
	Categoria leerPorId(Long id);
	
	Categoria actualizar(Categoria categoria,Long id_categoria)  throws DomainException ;
	
	void borrar(Long id_categoria);
}
