package rf.com.tienda.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;



/********************************************************************************************
 * NOMBRE: Validator.java
 * 
 * DESCRIPCION: 
 * 			Clase auxiliar para validar los datos que sean 
 * 			introducidos en la aplicacion.
 * 
 *  @version	30/01/2023 
 *  @author 	Miguel Garcia
 *  
 * ******************************************************************************************/
public class Validator {
	
	private static final String ALFANUMERIC_PATTERN = "^[0-9a-zA-Z\\s]+$";
	
	private static final String PASSWORD_PATTERN = 
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
	/**
	 * Patron para validar el email, evitando puntos finales antes de la @ y que solo contenga
	 * caracteres alfanumericos		 
	 */
	private final static String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
	/**
	 * Permite verificar que un DNI cumple con el Patron XX.XXX.XXX-L
	 */
	private final static String DNI_PATTERN = "\\d{2}\\.\\d{3}\\.\\d{3}-[a-zA-Z]";

		
	/**
	 * Permite validar un telefono, el cual debe contener de 10 a 20 digitos
	 * y donde los espacios estan permitidos
	 */
	private final static String PHONE_PATTERN =  "[\\d ]{10,20}";
	
	/**
	 * Orden de las letras con las cuales se comprobara la validez del DNI
	 */
	private final static String LETRA_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";
	
	/**
	 * Longitud que debe tener todo DNI pasado a la aplicacion.
	 */
	private final static int LONGITUD_DNI = 12;

	/* ***************************************************************************************
	 * NOMBRE: isAlfanumeric                                                                 *
	 * 
	 * DESCRIPCION: *//**
	 * 		Permite verificar que el texto pasado solo contiene caracters alfanumericos
	 * 
	 * @param texto String a verificar que solo tenga caracteres alfanumericos
	 * 
	 * @return  true, si cumple solo contiene caracters alfanumericos. <br> 
	 * 			false en caso contrario
	 * FECHA: Enero 2023
	 * 
	 * AUTOR: Miguel Garcia - Barcelona
	 * "^[a-zA-Z0-9-_\"]*$"
	 * **************************************************************************************/
	public static boolean isAlfanumeric(String texto){
		return !isVacio(texto) && texto.matches(ALFANUMERIC_PATTERN);
		
	}
	
	public static boolean isVacio( String prueba ){
		return prueba == null || prueba.equalsIgnoreCase("");
	}
	
	/* ***************************************************************************************
	 * NOMBRE: cumplePhoneNumber                                                                 *
	 * 
	 * DESCRIPCION: *//**
	 * 		El phone number debe tener un total de entre 10 y 20, contando digitos y espacios.
	 * 		Minimo aceptable son 10 digitos.
	 * 
	 * @param phoneNumber String con el Numero de telefono de entre 10 y 20 digitos. 
	 * 		Puede tener espacios, pero siempre con 10 digitos como Minimo.
	 * 
	 * @return true, si cumple todas las condiciones
	 *
	 * FECHA: Enero 2023
	 * AUTOR: Miguel Garcia
	 * 
	 * **************************************************************************************/
	public static boolean cumplePhoneNumber(String phoneNumber){
		return !isVacio(phoneNumber) && phoneNumber.matches(PHONE_PATTERN);
	}

	/* ***************************************************************************************
	 * NOMBRE: isEmailValido                                                                 *
	 * 
	 * DESCRIPCION: *//**
	 * 			Comprueba si el email tiene un formato que pueda ser valido.
	 * 
	 * @param email String a comprobar
	 * 
	 * @return true, en caso que el formato sea valido
	 * FECHA: Enero 2023
	 * 
	 * AUTOR: Miguel Garcia
	 * 
	 * **************************************************************************************/
	public static boolean isEmailValido(String email){
		return !isVacio(email) && email.matches(EMAIL_PATTERN);
	}

	/* ***************************************************************************************
	 * NOMBRE: cumpleDNI                                                                 *
	 * 
	 * DESCRIPCION: *//**
	 * 			Esta funcion verifica que el DNI cumple el siguiente formato: xx.xxx.xxx-L <br>
	 * 			El DNI ha de tener longitud 12
	 * 
	 * @param dni String con DNI a ser validado
	 * 
	 * @return true, si el DNI cumple el estandar nacional.
	 * FECHA: Enero 2023
	 * AUTOR: Miguel Garcia
	 * 
	 * **************************************************************************************/
	public static boolean cumpleDNI(String dni){
		if (dni ==null) {
			   return false;
			}
			
		// si es un NIE se hacen las operaciones necesarias para poder calcular luego la letra correcta	
		
			if (dni.startsWith("X")) {
				dni = dni.replaceFirst("X", "0");
			} else if (dni.startsWith("Y")) {
				dni = dni.replaceFirst("Y", "1");
			} else if (dni.startsWith("Z")) {
				dni = dni.replaceFirst("Z", "2");
			}
			
			if (dni.length() != LONGITUD_DNI) {
				return false;
			}
			
			if (!dni.matches(DNI_PATTERN) ) {
				return false;
			}
			
			String dniNumerico = dni.substring(0, dni.length() - 2).replace(".", "");
			int valorNumerico = Integer.parseInt(dniNumerico);
			
			Character letraDNI = Character.toUpperCase(dni.charAt(dni.length() -1));
			
			if  (LETRA_DNI.charAt(valorNumerico % 23) == letraDNI) {
				return true;
			} else {
				return false;
			}
	}
	

	/** ***************************************************************************************
	 * NOMBRE: cumpleRango                                                                 *
	 * 
	 * DESCRIPCION: *//**
	 * 		Comprueba que un Numero se necuentra entre 2 valores
	 * 
	 * @param valor (int)/(double) Numero a comprobar
	 * @param valorMinimo (int) Numero valor aceptable
	 * @param valorMaximo (int) Numero valor aceptable
	 * 
	 * @return true si valorMinimo < valor < valorMaximo
	 * FECHA: Enero 2023
	 * AUTOR: Miguel Garcia 
	 * 
	 * **************************************************************************************/
	public static boolean cumpleRango(
			int valor, 
			int valorMinimo,
			int valorMaximo){
		
		return (valorMinimo < valor) && (valor < valorMaximo);
		
	}
	public static boolean cumpleRango(
			double valor, 
			int valorMinimo,
			int valorMaximo){
		return (valorMinimo < valor) && (valor < valorMaximo);
		
	}


	/* ***************************************************************************************
	 * NOMBRE: cumpleLongitudMin                                                                 *
	 * 
	 * DESCRIPCION: *//**
	 * 		Comprobar que el texto pasado tiene una longitud de al menos x caracteres, siendo
	 * 		x el entero pasado como parametro
	 * 
	 * @param texto String texto a comprobar
	 * @param longitudMinima int que indique longitud Minima.
	 * 
	 * @return cierto, si la longitud del texto es mayor o igual que longitudMinima
	 * FECHA: Enero 2023
	 * AUTOR: Miguel Garcia
	 * 
	 * **************************************************************************************/
	public static boolean cumpleLongitudMin(
			String texto, 
			int longitudMinima){
		return texto.length() >= longitudMinima;

		
	}


	/* ***************************************************************************************
	 * NOMBRE: cumpleLongitudMax                                                                 *
	 * 
	 * DESCRIPCION: *//**
	 * 		Comprobar que el texto pasado tiene una longitud de, como mucho, x caracteres, siendo
	 * 		x el entero pasado como parametro
	 * 
	 * @param texto String con el texto a comprobar
	 * @param longitudMaxima int con la longitud maxima del texto
	 * 
	 * @return true, si el texto es menor o igual que la longitud maxima.
	 * FECHA: Enero 2023 
	 * AUTOR: Miguel Garcia 
	 * 
	 * **************************************************************************************/
	public static boolean cumpleLongitudMax(
			String texto, 
			int longitudMaxima){
		return texto.length() <= longitudMaxima;
		
	}


	/****************************************************************************************
	 * NOMBRE: cumpleLongitud                                                                 *
	 * 
	 * DESCRIPCION: *//**
	 * 		Comprobar que la longitud de un texto se encuentra entre unos valores maximos y Minimos 
	 * 
	 * @param texto String con el texto que a validar
	 * @param longitudMinima (int) Minima longitud del texto
	 * @param longitudMaxima (int) maxima longitud valida para el texo
	 * 
	 * @return true, si la longitud del texto cumple: longitudMinima
	 *               <= longitudTexto <=longitudMaxima
	 * FECHA: Enero 2023 
	 * AUTOR: Miguel Garcia - Barcelona
	 * 
	 * **************************************************************************************/
	public static boolean cumpleLongitud(
			String texto, 
			int longitudMinima, 
			int longitudMaxima){

		return (cumpleLongitudMin(texto, longitudMinima) && cumpleLongitudMax(texto, longitudMaxima));

		

	}
	/**
	 * Valida una fecha calendar con Minimo min
	 * @param fecha
	 * @param min
	 * @return
	 */
	
	public static boolean valDateMin(LocalDate fecha, LocalDate min){
		if (fecha != null && min != null) {

			return fecha.compareTo(min) >= 0;

		}
		return false;
		
	}
	
	/**
	 * Valida una fecha calendar con maximo max
	 * @param fecha
	 * @param max
	 * @return
	 */
	public static boolean valDateMax(LocalDate fecha, LocalDate max){
		if (fecha != null && max != null) {
			return fecha.compareTo(max) <= 0;
		}
		return false;
		
	}	
	
	/**
	 * esFechaValida
	 * Recibe una string con formato fecha dd/mm/aaaa y comprueba el formato
	 * @param fecha
	 * @return
	 */
	public static boolean esFechaValida(String fecha){
		if (isVacio(fecha)) {
			return false;
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Optional<LocalDate> date = Optional.empty();
        try {
            date = Optional.of(LocalDate.parse(fecha, formatter));
            if(date.isPresent()) {
                return true;
            }
        } catch (DateTimeParseException e) {
        }   
        System.out.println(fecha);
        return false;
		
		
	}
	
	/**
	 * Nombre esPasswordValida
	 * Descripcion Comprueba que la cadena recibida cumpla con lasnormas de contraseaa
	 * @param password string con la contraseaa introducida
	 * @return true si cumple con las especificaciones
	 */
	public static boolean esPasswordValida(String password){
		return !isVacio(password) && password.matches(PASSWORD_PATTERN);

	}
}
