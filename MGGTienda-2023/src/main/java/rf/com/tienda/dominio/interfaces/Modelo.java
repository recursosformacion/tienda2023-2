package rf.com.tienda.dominio.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Transient;

public interface Modelo {
	
	@Transient
	@JsonIgnore
	public boolean isValidInsert();
	@Transient
	@JsonIgnore
	public boolean isValidUpdate();

}
