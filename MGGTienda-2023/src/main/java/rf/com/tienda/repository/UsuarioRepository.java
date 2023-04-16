package rf.com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rf.com.tienda.dominio.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
