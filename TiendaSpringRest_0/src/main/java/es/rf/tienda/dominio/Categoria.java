package es.rf.tienda.dominio;

import java.io.Serializable;

import es.rf.tienda.dominio.interfaces.Modelo;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.util.Validator;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(schema = "ALUMNO_MGG", name = "CATEGORIAS")
public class Categoria implements Serializable, Modelo {
	@Transient
	int LONG_MAX_NOMBRE = 50;
	@Transient
	int LONG_MIN_NOMBRE = 5;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_categoria; // identificador categoria

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
	public int getId_categoria() {
		return id_categoria;
	}

	/**
	 * Setter para identificador de categoria
	 * 
	 */
	public void setId_categoria(int id_categoria) {
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
		this.cat_descripcion =  StringUtils.truncate(cat_descripcion,200);
	}
 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cat_descripcion == null) ? 0 : cat_descripcion.hashCode());
		result = prime * result + ((cat_nombre == null) ? 0 : cat_nombre.hashCode());
		result = prime * result + id_categoria;
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
