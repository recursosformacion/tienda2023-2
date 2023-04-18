package rf.com.tienda.dominio;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import rf.com.tienda.exception.DomainException;
import rf.com.tienda.util.ErrorMessages;
import rf.com.tienda.util.Rutinas;
import rf.com.tienda.util.Validator;



@SuppressWarnings("serial")
@Entity
@Table(name = "Productos")
public class Producto implements Serializable {

	@Id
	private String id_producto; // C�digo de producto
	private String pro_descripcion; // Descripcion corta
	private String pro_desLarga; // Explicacion
	private Double pro_precio;
	private int pro_stock;
	private LocalDate pro_fecRepos;
	private LocalDate pro_fecActi;
	private LocalDate pro_fecDesacti;
	private String pro_uniVenta;
	private Double pro_cantXUniVenta;
	private String pro_uniUltNivel;
	private int id_pais;
	private String pro_usoRecomendado;

	@ManyToOne(targetEntity = Categoria.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria")
	private int id_categoria;

	private int pro_stkReservado;
	private int pro_nStkAlto;
	private int pro_nStkBajo;
	private char pro_stat;

	/**
	 * Longitud codigo de producto
	 */
	public static final int ID_PRODUCTO_LONG = 5;
	/**
	 * Longitud descripcion
	 * 
	 */
	public static final int PRO_DESCRIPCION_LONG_MIN = 5;
	public static final int PRO_DESCRIPCION_LONG_MAX = 100;

	/**
	 * Longitud descripcion larga
	 * 
	 */
	public static final int PRO_DESLARGA_LONG_MIN = 5;
	public static final int PRO_DESLARGA_LONG_MAX = 2000;

	/**
	 * Filtro precio
	 * 
	 */
	public static final int PRO_PRECIO_VAL_MIN = 0;
	public static final int PRO_PRECIO_VAL_MAX = 100;

	/**
	 * Filtro precio
	 * 
	 */
	public static final int PRO_UNIVENTA_LONG_MIN = 0;
	public static final int PRO_UNIVENTA_LONG_MAX = 10;

	/**
	 * Longitud uso recomendado
	 * 
	 */
	public static final int PRO_USORECOMENDADO_LONG_MIN = 5;
	public static final int PRO_USORECOMENDADO_LONG_MAX = 2000;

	/**
	 * GETTERS de campos
	 * 
	 */

	public String getId_producto() {
		return id_producto;
	}

	public String getPro_descripcion() {
		return pro_descripcion;
	}

	public String getPro_desLarga() {
		return pro_desLarga;
	}

	public Double getPro_precio() {
		return pro_precio;
	}

	public int getPro_stock() {
		return pro_stock;
	}

	public LocalDate getPro_fecRepos() {
		return pro_fecRepos;
	}

	public LocalDate getPro_fecActi() {
		return pro_fecActi;
	}

	public LocalDate getPro_fecDesacti() {
		return pro_fecDesacti;
	}

	public String getPro_uniVenta() {
		return pro_uniVenta;
	}

	public Double getPro_cantXUniVenta() {
		return pro_cantXUniVenta;
	}

	public String getPro_uniUltNivel() {
		return pro_uniUltNivel;
	}

	public int getId_pais() {
		return id_pais;
	}

	public String getPro_usoRecomendado() {
		return pro_usoRecomendado;
	}

	public int getId_categoria() {
		return id_categoria;
	}

	public int getPro_stkReservado() {
		return pro_stkReservado;
	}

	public int getPro_nStkAlto() {
		return pro_nStkAlto;
	}

	public int getPro_nStkBajo() {
		return pro_nStkBajo;
	}

	public char getPro_stat() {
		return pro_stat;
	}

	/**
	 * SETTERS
	 * 
	 * @throws DomainException
	 *
	 */

	public void setId_producto(String id_producto) throws DomainException {
		if (Validator.cumpleLongitud(id_producto, ID_PRODUCTO_LONG, ID_PRODUCTO_LONG))
			if (Validator.isAlfanumeric(id_producto))
				this.id_producto = id_producto;
			else
				throw new DomainException(ErrorMessages.PROERR_001);
		else
			throw new DomainException(ErrorMessages.PROERR_002);

	}

	public void setPro_descripcion(String pro_descripcion) throws DomainException {
		if (Validator.cumpleLongitud(pro_descripcion, PRO_DESCRIPCION_LONG_MIN, PRO_DESCRIPCION_LONG_MAX))
			if (Validator.isAlfanumeric(pro_descripcion))
				this.pro_descripcion = pro_descripcion;
			else
				throw new DomainException(
						Rutinas.mensajes(ErrorMessages.PROERR_004, new String[] { "Descripcion", "Alfanumerico" }));
		else
			throw new DomainException(Rutinas.mensajes(ErrorMessages.PROERR_003,
					new String[] { "Descripcion", "" + PRO_DESCRIPCION_LONG_MIN, "" + PRO_DESCRIPCION_LONG_MAX }));

	}

	public void setPro_desLarga(String pro_desLarga) throws DomainException {
		if (Validator.cumpleLongitud(pro_desLarga, PRO_DESLARGA_LONG_MIN, PRO_DESLARGA_LONG_MAX))
			if (Validator.isAlfanumeric(pro_desLarga))
				this.pro_desLarga = pro_desLarga;
			else
				throw new DomainException(Rutinas.mensajes(ErrorMessages.PROERR_004,
						new String[] { "Descripcion larga", "Alfanumerico" }));
		else
			throw new DomainException(Rutinas.mensajes(ErrorMessages.PROERR_003,
					new String[] { "Descripcion larga", "" + PRO_DESLARGA_LONG_MIN, "" + PRO_DESLARGA_LONG_MAX }));
	}

	public void setPro_precio(double i) throws DomainException {
		if (Validator.cumpleRango(i, PRO_PRECIO_VAL_MIN, PRO_PRECIO_VAL_MAX))
			this.pro_precio = i;
		else
			throw new DomainException(Rutinas.mensajes(ErrorMessages.PROERR_005,
					new String[] { "Precio", "" + PRO_PRECIO_VAL_MIN, "" + PRO_PRECIO_VAL_MAX }));
	}

	public void setPro_stock(int pro_stock) {
		this.pro_stock = pro_stock;
	}

	public void setPro_fecRepos(String pro_fecRepos) throws DomainException {
		if (Validator.esFechaValida(pro_fecRepos))
			try {
				setPro_fecRepos(LocalDate.parse(pro_fecRepos));
			} catch (DomainException e) {
				throw new DomainException(
						Rutinas.mensajes(ErrorMessages.PROERR_006, new String[] { "Fecha reposicion" }));
			}
		else
			throw new DomainException(Rutinas.mensajes(ErrorMessages.PROERR_006, new String[] { "Fecha reposicion" }));

	}

	public void setPro_fecRepos(LocalDate pro_fecRepos) throws DomainException {
		LocalDate aux = LocalDate.now();
		aux.plusDays((long)aux.getDayOfYear() +1);
		if (Validator.valDateMin(pro_fecRepos, aux))
			this.pro_fecRepos = pro_fecRepos;
		else
			throw new DomainException(Rutinas.mensajes(ErrorMessages.PROERR_007, new String[] { "Fecha reposicion" }));

	}

	public void setPro_fecActi(String pro_fecActi) throws DomainException {
		if (Validator.esFechaValida(pro_fecActi))
			setPro_fecActi(LocalDate.parse(pro_fecActi));
		else
			throw new DomainException(Rutinas.mensajes(ErrorMessages.PROERR_006, new String[] { "Fecha Activacion" }));

	}

	public void setPro_fecActi(LocalDate pro_fecActi) throws DomainException {
		LocalDate aux = LocalDate.now();
		aux.plusDays((long)aux.getDayOfYear() +1);
		if (Validator.valDateMin(pro_fecActi, aux))
			this.pro_fecActi = pro_fecActi;
		else
			throw new DomainException(Rutinas.mensajes(ErrorMessages.PROERR_007, new String[] { "Fecha activacion" }));
	}

	public void setPro_fecDesacti(String pro_fecDesacti) throws DomainException {
		if (Validator.esFechaValida(pro_fecDesacti))
			this.setPro_fecDesacti(LocalDate.parse(pro_fecDesacti));
		else
			throw new DomainException(
					Rutinas.mensajes(ErrorMessages.PROERR_006, new String[] { "Fecha Desactivacion" }));

	}

	public void setPro_fecDesacti(LocalDate pro_fecDesacti) throws DomainException {
		LocalDate aux = LocalDate.now();
		aux.plusDays((long)aux.getDayOfYear() +1);
		if (Validator.valDateMin(pro_fecDesacti, aux))
			this.pro_fecDesacti = pro_fecDesacti;
		else
			throw new DomainException(
					Rutinas.mensajes(ErrorMessages.PROERR_007, new String[] { "Fecha Desactivacion" }));
	}

	public void setPro_uniVenta(String pro_uniVenta) {
		this.pro_uniVenta = pro_uniVenta;
	}

	public void setPro_cantXUniVenta(Double pro_cantXUniVenta) {
		this.pro_cantXUniVenta = pro_cantXUniVenta;
	}

	public void setPro_uniUltNivel(String pro_uniUltNivel) {
		this.pro_uniUltNivel = pro_uniUltNivel;
	}

	public void setId_pais(int id_pais) {
		this.id_pais = id_pais;
	}

	public void setPro_usoRecomendado(String pro_usoRecomendado) {
		this.pro_usoRecomendado = pro_usoRecomendado;
	}

	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}

	public void setPro_stkReservado(int pro_stkReservado) {
		this.pro_stkReservado = pro_stkReservado;
	}

	public void setPro_nStkAlto(int pro_nStkAlto) {
		this.pro_nStkAlto = pro_nStkAlto;
	}

	public void setPro_nStkBajo(int pro_nStkBajo) {
		this.pro_nStkBajo = pro_nStkBajo;
	}

	public void setPro_stat(char pro_stat) {
		this.pro_stat = pro_stat;
	}

}
