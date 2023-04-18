package rf.com.tienda.servicio;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rf.com.tienda.dominio.Categoria;
import rf.com.tienda.exception.DomainException;
import rf.com.tienda.repository.CategoriaRepository;

@Service
public class CategoriaService implements ICategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public boolean insert(Categoria categoria) {
		return categoriaRepository.save(categoria) != null;
	}
	@Override
	public List<Categoria> listAll() {
		return (List<Categoria>) categoriaRepository.findAll();
	}

	@Override
	public boolean update(Categoria categoria, Long id_categoria) throws DomainException {

		Categoria catDB = categoriaRepository.findById(id_categoria).get();

		if (Objects.nonNull(categoria.getCat_nombre())
				&& !"".equalsIgnoreCase(categoria.getCat_nombre())) {
			catDB.setCat_nombre(categoria.getCat_nombre());
		}

		if (Objects.nonNull(categoria.getCat_descripcion())
				&& !"".equalsIgnoreCase(categoria.getCat_descripcion())) {
			catDB.setCat_descripcion(categoria.getCat_descripcion());
		}

		return categoriaRepository.save(catDB) != null;
	}

	@Override
	public boolean deleteById(Long id_categoria) {
		 categoriaRepository.deleteById(id_categoria);
		 return true;

	}

	@Override
	public Optional<Categoria> leerUno(Long id) {
		return categoriaRepository.findById(id);
		
	}

}
