package rf.com.tienda.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PAISES" )
public class Pais {
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private int id_pais;
	private String pais_nombre;
	
	public int getId_pais() {
		return id_pais;
	}
	public void setId_pais(int id_pais) {
		this.id_pais = id_pais;
	}
	public String getPais_nombre() {
		return pais_nombre;
	}
	public void setPais_nombre(String pais_nombre) {
		this.pais_nombre = pais_nombre;
	}

}
