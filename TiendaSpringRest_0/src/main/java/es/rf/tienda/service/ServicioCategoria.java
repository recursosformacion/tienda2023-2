package es.rf.tienda.service;


import org.springframework.stereotype.Service;

import es.rf.tienda.dao.ICategoriaRepo;
import es.rf.tienda.dominio.Categoria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

// esto es un servicio
@Service
public class ServicioCategoria extends ServicioGral<Categoria, Integer, ICategoriaRepo>{

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
