package rf.com.tienda.servicio;

import java.util.List;
import java.util.Optional;

import rf.com.tienda.dominio.Categoria;
import rf.com.tienda.exception.DomainException;

public interface ICategoriaService {

	boolean insert(Categoria categoria)  throws DomainException ;
	
	List<Categoria> listAll();
	
	Optional<Categoria> leerUno(Long id);
	
	boolean update(Categoria categoria,Long id_categoria)  throws DomainException ;
	
	boolean deleteById(Long id_categoria);
}
