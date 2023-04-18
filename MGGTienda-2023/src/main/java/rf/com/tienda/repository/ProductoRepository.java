package rf.com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rf.com.tienda.dominio.Producto;

public interface ProductoRepository  extends JpaRepository<Producto, String> {

}
