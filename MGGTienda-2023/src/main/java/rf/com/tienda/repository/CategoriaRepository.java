package rf.com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rf.com.tienda.dominio.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
}
