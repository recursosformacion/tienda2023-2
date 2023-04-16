package rf.com.tienda.servicio;

import java.util.List;
import java.util.Objects;

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
	public Categoria crear(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	@Override
	public List<Categoria> leerTodos() {
		return (List<Categoria>) categoriaRepository.findAll();
	}

	@Override
	public Categoria actualizar(Categoria categoria, Long id_categoria) throws DomainException {

		Categoria catDB = categoriaRepository.findById(id_categoria).get();

		if (Objects.nonNull(categoria.getCat_nombre())
				&& !"".equalsIgnoreCase(categoria.getCat_nombre())) {
			catDB.setCat_nombre(categoria.getCat_nombre());
		}

		if (Objects.nonNull(categoria.getCat_descripcion())
				&& !"".equalsIgnoreCase(categoria.getCat_descripcion())) {
			catDB.setCat_descripcion(categoria.getCat_descripcion());
		}

		return categoriaRepository.save(catDB);
	}

	@Override
	public void borrar(Long id_categoria) {
		categoriaRepository.deleteById(id_categoria);

	}

	@Override
	public Categoria leerPorId(Long id) {
		categoriaRepository.findById(id);
		return null;
	}

}
