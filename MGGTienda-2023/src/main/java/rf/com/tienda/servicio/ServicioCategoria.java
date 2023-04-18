package rf.com.tienda.servicio;


import org.springframework.stereotype.Service;


import rf.com.tienda.dominio.Categoria;
import rf.com.tienda.repository.CategoriaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// esto es un servicio
//Service
public class ServicioCategoria extends ServicioGral<Categoria, Integer, CategoriaRepository>{

	@PersistenceContext
    private EntityManager em;
	
	public void nuevoId(Categoria t) {
//		//long maxId = em.createQuery("Select NVL(max(id_categoria),0) FROM categorias").getSingleResult();
//		@Query(value = "Select NVL(max(id_categoria),0) FROM categorias")
//		int getMaxTransactionId();
//		t.setId_categoria(getMaxTransactionId);
		return ;
		
	}
}
