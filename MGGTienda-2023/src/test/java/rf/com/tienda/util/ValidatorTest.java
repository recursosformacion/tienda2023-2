package rf.com.tienda.util;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class ValidatorTest {

	final String NOT_ALFANUMERIC = "@ # % {c";
	final String ALFANUMERIC = "ABCabc";

	final String CODIGO_PRODUCTO_OK = "AB123";
	final String CODIGO_PRODUCTO_NO1 = "A123B";
	final String CODIGO_PRODUCTO_NO2 = "Ab123";
	final String CODIGO_PRODUCTO_NO3 = "A0123";
	final String CODIGO_PRODUCTO_FORMATO_ERR_NUM = "12345";
	final String CODIGO_PRODUCTO_FORMATO_ERR_ALF = "ABCDE";
	final String CODIGO_PRODUCTO_FORMATO_ERR_LON = "AB345678";

	String STRING_NULA;
	final String STRING_VACIA = "";

	final String CORREO_ELECTRONICO_CORRECTO = "migarcia@recursosformacion.com";
	final String CORREO_ELECTRONICO_ERRONEO_1 = "migarcia.recursosformacion.com";
	final String CORREO_ELECTRONICO_ERRONEO_2 = "migarcia@recursosformacion";
	final String CORREO_ELECTRONICO_ERRONEO_3 = "@recursosformacion.com";
	final String CORREO_ELECTRONICO_ERRONEO_4 = "migarcia@";

	final String NUMERO_DNI_OK = "12.345.678-Z";
	final String NUMERO_DNI_ERROR_LETRA = "12.345.678-Ñ";
	final String NUMERO_DNI_ERROR_FORM_CORTO = "12.2.678-A";
	final String NUMERO_DNI_ERROR_FORM_CORTO2 = "122.678-A";
	final String NUMERO_DNI_ERROR_FORM_LARGO = "123.456.678-A";
	final String NUMERO_DNI_ERROR_FORM_ERR = "12345678A";
	final String NUMERO_DNI_ERROR_FORM_ERR2 = "12.345.678.A";

	final int NUMERO_INT = 0;
	final int NUMERO_INT_NEGATIVO = -90000000;
	final int NUMERO_INT_POSITIVO = 800000000;

	final double NUMERO_DOUBLE = 0.0;
	final double NUMERO_DOUBLE_NEGATIVO = -1764.8889;
	final double NUMERO_DOUBLE_POSITIVO = 86594.6442;

	final String CADENA1 = "a";
	final String CADENA5 = "Ansde";
	final String CADENA20 = "asmdjfoeirksndvieqaz";
	final String CADENA30 = "qazxswedcvfrbnhyujm,kiol.-pasd";
	final String CADENA50 = "poiuytrsdfghjkl�mnbvcxzZXCVBNM�LKJHGFDSAQWERTY";

	final String PHONENUMBER_OK = "1134567890";
	final String PHONENUMBER_ERROR_FORM_CORTO = "11345";
	final String PHONENUMBER_ERROR_FORM_LARGO = "145454487524575147874517811345";

	final String FECHA_OK = "01/07/2022";
	final String FECHA_OK_1 = "26/12/9999";
	final String FECHA_OK_2 = "08/01/2022";
	final String FECHA_OK_3 = "09/05/2021";

	final String FECHA_ERROR = "2/30/2022";
	final String FECHA_ERROR_1 = "2022/2/22";
	final String FECHA_ERROR_2 = "31/2/2022";
	final String FECHA_ERROR_3 = "2/9993/9";

	final LocalDate AHORA = LocalDate.now();
	final LocalDate MANIANA = LocalDate.now().plusDays(1);;
	final LocalDate AYER = LocalDate.now().minusDays(2);

	final String CONTRASENIA_OK = "qasLOASD#~@1!!!";
	final String CONTRASENIA_ERROR = "12345";
	final String CONTRASENIA_ERROR_1 = "123456789123456789123456789";
	final String CONTRASENIA_ERROR_2 = "}}¬¬¬||SD";
	

	@Test
	void testIsAlfanumeric() {
		assertAll(() -> assertTrue(Validator.isAlfanumeric(ALFANUMERIC)),
				() -> assertFalse(Validator.isAlfanumeric(NOT_ALFANUMERIC)));
	}

	@Test
	void testIsVacio() {
		assertAll(() -> assertTrue(Validator.isVacio(STRING_NULA)), () -> assertTrue(Validator.isVacio(STRING_VACIA)));
	}

	@Test
	void testCumplePhoneNumber() {
		assertAll(() -> assertTrue(Validator.cumplePhoneNumber(PHONENUMBER_OK)),
				() -> assertFalse(Validator.cumplePhoneNumber(PHONENUMBER_ERROR_FORM_CORTO)),
				() -> assertFalse(Validator.cumplePhoneNumber(PHONENUMBER_ERROR_FORM_LARGO)));
	}

	@Test
	void testIsEmailValido() {
		assertAll(() -> assertTrue(Validator.isEmailValido(CORREO_ELECTRONICO_CORRECTO)),
				() -> assertFalse(Validator.isEmailValido(CORREO_ELECTRONICO_ERRONEO_1)),
				() -> assertFalse(Validator.isEmailValido(CORREO_ELECTRONICO_ERRONEO_2)),
				() -> assertFalse(Validator.isEmailValido(CORREO_ELECTRONICO_ERRONEO_3)),
				() -> assertFalse(Validator.isEmailValido(CORREO_ELECTRONICO_ERRONEO_4)));
	}

	@Test
	void testCumpleDNI() {
		assertAll(() -> assertTrue(Validator.cumpleDNI(NUMERO_DNI_OK)),
				() -> assertFalse(Validator.cumpleDNI(NUMERO_DNI_ERROR_FORM_CORTO)),
				() -> assertFalse(Validator.cumpleDNI(NUMERO_DNI_ERROR_FORM_CORTO2)),
				() -> assertFalse(Validator.cumpleDNI(NUMERO_DNI_ERROR_FORM_ERR)),
				() -> assertFalse(Validator.cumpleDNI(NUMERO_DNI_ERROR_FORM_ERR2)),
				() -> assertFalse(Validator.cumpleDNI(NUMERO_DNI_ERROR_FORM_LARGO)),
				() -> assertFalse(Validator.cumpleDNI(NUMERO_DNI_ERROR_LETRA)));

	}

	@Test
	void testCumpleRangoIntIntInt() {
		assertAll(() -> assertFalse(Validator.cumpleRango(NUMERO_INT, NUMERO_INT, NUMERO_INT)),
				() -> assertFalse(Validator.cumpleRango(NUMERO_INT_POSITIVO, NUMERO_INT_NEGATIVO, NUMERO_INT)),
				() -> assertFalse(Validator.cumpleRango(NUMERO_INT_POSITIVO, NUMERO_INT, NUMERO_INT_NEGATIVO)),
				() -> assertFalse(Validator.cumpleRango(NUMERO_INT_NEGATIVO, NUMERO_INT_POSITIVO, NUMERO_INT)),
				() -> assertFalse(Validator.cumpleRango(NUMERO_INT_NEGATIVO, NUMERO_INT, NUMERO_INT_POSITIVO)),
				() -> assertTrue(Validator.cumpleRango(NUMERO_INT, NUMERO_INT_NEGATIVO, NUMERO_INT_POSITIVO)));

	}

	@Test
	void testCumpleRangoDoubleIntInt() {

		assertAll(() -> assertFalse(Validator.cumpleRango(NUMERO_DOUBLE_NEGATIVO, NUMERO_INT, NUMERO_INT)),
				() -> assertFalse(Validator.cumpleRango(NUMERO_DOUBLE_POSITIVO, NUMERO_INT_NEGATIVO, NUMERO_INT)),
				() -> assertFalse(Validator.cumpleRango(NUMERO_DOUBLE, NUMERO_INT, NUMERO_INT_NEGATIVO)),
				() -> assertFalse(Validator.cumpleRango(NUMERO_DOUBLE_NEGATIVO, NUMERO_INT_POSITIVO, NUMERO_INT)),
				() -> assertFalse(Validator.cumpleRango(NUMERO_DOUBLE_NEGATIVO, NUMERO_INT, NUMERO_INT_POSITIVO)),
				() -> assertTrue(Validator.cumpleRango(NUMERO_DOUBLE, NUMERO_INT_NEGATIVO, NUMERO_INT_POSITIVO)));
	}

	@Test
	void testCumpleLongitudMin() {

		assertAll(() -> assertTrue(Validator.cumpleLongitudMin(CADENA1, 1)),
				() -> assertTrue(Validator.cumpleLongitudMin(CADENA20, 20)),
				() -> assertTrue(Validator.cumpleLongitudMin(CADENA30, 24)),
				() -> assertTrue(Validator.cumpleLongitudMin(CADENA5, 2)),
				() -> assertTrue(Validator.cumpleLongitudMin(CADENA50, 40)),
				() -> assertFalse(Validator.cumpleLongitudMin(CADENA1, 3)),
				() -> assertFalse(Validator.cumpleLongitudMin(CADENA20, 40)),
				() -> assertFalse(Validator.cumpleLongitudMin(CADENA30, 32)),
				() -> assertFalse(Validator.cumpleLongitudMin(CADENA5, 9)),
				() -> assertFalse(Validator.cumpleLongitudMin(CADENA50, 100)));
	}

	@Test
	void testCumpleLongitudMax() {
		assertAll(() -> assertTrue(Validator.cumpleLongitudMax(CADENA1, 1)),
				() -> assertTrue(Validator.cumpleLongitudMax(CADENA20, 20)),
				() -> assertTrue(Validator.cumpleLongitudMax(CADENA30, 50)),
				() -> assertTrue(Validator.cumpleLongitudMax(CADENA5, 50)),
				() -> assertTrue(Validator.cumpleLongitudMax(CADENA50, 100)),
				() -> assertFalse(Validator.cumpleLongitudMax(CADENA1, 0)),
				() -> assertFalse(Validator.cumpleLongitudMax(CADENA20, 10)),
				() -> assertFalse(Validator.cumpleLongitudMax(CADENA30, 1)),
				() -> assertFalse(Validator.cumpleLongitudMax(CADENA5, 3)),
				() -> assertFalse(Validator.cumpleLongitudMax(CADENA50, 1)));
	}

	@Test
	void testCumpleLongitud() {
		assertAll(() -> assertTrue(Validator.cumpleLongitud(CADENA1, 1, 1)),
				() -> assertTrue(Validator.cumpleLongitud(CADENA20, 20, 30)),
				() -> assertTrue(Validator.cumpleLongitud(CADENA30, 30, 100)),
				() -> assertTrue(Validator.cumpleLongitud(CADENA5, 3, 5)),
				() -> assertTrue(Validator.cumpleLongitud(CADENA50, 30, 100)),
				() -> assertFalse(Validator.cumpleLongitud(CADENA1, 0, 0)),
				() -> assertFalse(Validator.cumpleLongitud(CADENA20, 10, 11)),
				() -> assertFalse(Validator.cumpleLongitud(CADENA30, 50, 100)),
				() -> assertFalse(Validator.cumpleLongitud(CADENA5, 5, 1)),
				() -> assertFalse(Validator.cumpleLongitud(CADENA50, 100, 1)));
	}

	@Test
	void testValDateMin() {

		assertAll(() -> assertTrue(Validator.valDateMin(AHORA, AHORA)),
				() -> assertTrue(Validator.valDateMin(MANIANA, AHORA)),
				() -> assertTrue(Validator.valDateMin(AHORA, AYER)),
				() -> assertTrue(Validator.valDateMin(MANIANA, AYER)),

				() -> assertFalse(Validator.valDateMin(AHORA, MANIANA)),
				() -> assertFalse(Validator.valDateMin(AYER, AHORA)),
				() -> assertFalse(Validator.valDateMin(AYER, MANIANA)));
	}

	@Test
	void testValDateMax() {

		assertAll(() -> assertTrue(Validator.valDateMax(AHORA, AHORA)),
				() -> assertTrue(Validator.valDateMax(AYER, MANIANA)),
				() -> assertTrue(Validator.valDateMax(AHORA, MANIANA)),
				() -> assertTrue(Validator.valDateMax(AYER, AHORA)),

				() -> assertFalse(Validator.valDateMax(MANIANA, AHORA)),
				() -> assertFalse(Validator.valDateMax(AHORA, AYER)),
				() -> assertFalse(Validator.valDateMax(MANIANA, AYER)));
	}

	@Test
	void testEsFechaValida() {
		assertAll(() -> assertTrue(Validator.esFechaValida(FECHA_OK)),
				() -> assertTrue(Validator.esFechaValida(FECHA_OK_1)),
				() -> assertTrue(Validator.esFechaValida(FECHA_OK_2)),
				() -> assertTrue(Validator.esFechaValida(FECHA_OK_3)),
				() -> assertFalse(Validator.esFechaValida(FECHA_ERROR)),
				() -> assertFalse(Validator.esFechaValida(FECHA_ERROR_1)),
				() -> assertFalse(Validator.esFechaValida(FECHA_ERROR_2)),
				() -> assertFalse(Validator.esFechaValida(FECHA_ERROR_3)));
	}

	@Test
	void testEsPasswordValida() {
		assertAll(() -> assertTrue(Validator.esPasswordValida(CONTRASENIA_OK)),
				() -> assertFalse(Validator.esPasswordValida(CONTRASENIA_ERROR)),
				() -> assertFalse(Validator.esPasswordValida(CONTRASENIA_ERROR_1)),
				() -> assertFalse(Validator.esPasswordValida(CONTRASENIA_ERROR_2)));
	}
}
