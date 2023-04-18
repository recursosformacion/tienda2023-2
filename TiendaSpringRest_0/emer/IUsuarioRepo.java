package es.rf.tienda.repository;


import es.rf.tienda.dominio.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepo extends JpaRepository<Usuario, Integer>{

//	public abstract ArrayList<Usuario> findByUser_email(String email);

}
