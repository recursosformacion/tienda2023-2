package rf.com.tienda.dominio;

import java.io.Serializable;

import rf.com.tienda.exception.DomainException;
import rf.com.tienda.util.Validator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import rf.com.tienda.dominio.Categoria;
import rf.com.tienda.dominio.interfaces.Modelo;

import javax.persistence.Transient;

import org.springframework.util.StringUtils;




@SuppressWarnings("serial")
@Entity
@Table( name="CATEGORIAS" )
public class Categoria implements Serializable, Modelo {
	@Transient
	int LONG_MAX_NOMBRE = 50;
	@Transient
	int LONG_MIN_NOMBRE = 5;
	@Transient
	int LONG_MAX_DESCRIPCION = 200;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id_categoria; // identificador categoria

	@Column(nullable = false, length = 50)
	private String cat_nombre; // nombre de la categoria

	@Column(nullable = true, length = 200)
	private String cat_descripcion; // descripcion de la categoria

	public Categoria() {
	}

	/**
	 * Getter para identificador de categoria
	 * 
	 * @return Integer con el id de la categoria
	 */
	public long getId_categoria() {
		return id_categoria;
	}

	/**
	 * Setter para identificador de categoria
	 * 
	 */
	public void setId_categoria(long id_categoria) {
		this.id_categoria = id_categoria;
	}

	/**
	 * Getter para el nombre de categoria
	 * 
	 * @return cadena con el nombre de la categoria
	 */
	public String getCat_nombre() {
		return cat_nombre;
	}

	/**
	 * Setter para el nombre de categoria
	 * @throws DomainException 
	 * 
	 */
	public void setCat_nombre(String cat_nombre) throws DomainException {
		if (Validator.cumpleLongitud(cat_nombre, LONG_MIN_NOMBRE, LONG_MAX_NOMBRE)) {
			this.cat_nombre = cat_nombre;
		} else {
			System.out.println("Error longitud");
			throw new DomainException("Error longitud");
		}
	}

	/**
	 * Getter para la descripcion de categoria
	 * 
	 * @return cadena con la descripcion de la categoria
	 */
	public String getCat_descripcion() {
		return cat_descripcion;
	}

	/**
	 * setter para la descripcion de categoria
	 * 
	 */
	public void setCat_descripcion(String cat_descripcion) {
		 if (cat_descripcion.length() <= LONG_MAX_DESCRIPCION) {
			 this.cat_descripcion = cat_descripcion;
		    } else {
		    	this.cat_descripcion =  cat_descripcion.substring(0, LONG_MAX_DESCRIPCION);
		    }
	
	}
 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cat_descripcion == null) ? 0 : cat_descripcion.hashCode());
		result = prime * result + ((cat_nombre == null) ? 0 : cat_nombre.hashCode());
		result = prime * result + (int) id_categoria;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (cat_descripcion == null) {
			if (other.cat_descripcion != null)
				return false;
		} else if (!cat_descripcion.equals(other.cat_descripcion))
			return false;
		if (cat_nombre == null) {
			if (other.cat_nombre != null)
				return false;
		} else if (!cat_nombre.equals(other.cat_nombre))
			return false;
		if (id_categoria != other.id_categoria)
			return false;
		return true;
	}

	
	@Override
	public boolean isValidInsert() {
		boolean result = !Validator.isVacio(cat_nombre);
		System.out.println(Validator.isVacio(cat_nombre));

		return result;

	}

	
	@Override
	public boolean isValidUpdate() {
		boolean result = !Validator.isVacio(cat_nombre) &&
				id_categoria > 0;
		System.out.println(Validator.isVacio(cat_nombre));
		System.out.println(id_categoria > 0);
		return result;
		
	}

}