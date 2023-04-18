package rf.com.tienda.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import rf.com.tienda.dominio.Categoria;

@WebMvcTest(CategoriaController.class)
public class CategoriaControllerTest1 {
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void getAllCategorias() throws Exception 
	{
	  mvc.perform(MockMvcRequestBuilders
	  			.get("/categorias")
	  			.accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.categorias").exists())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.categorias[*].id_categoria").isNotEmpty());
	}
	@Test
	public void createCategoria() throws Exception 
	{
	  mvc.perform( MockMvcRequestBuilders
		      .post("/categorias")
		      .content(asJsonString(new Categoria(1, "un nombre", "una descripcion")))
		      .contentType(MediaType.APPLICATION_JSON)
		      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isCreated())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.id_categoria").exists());
	}
	 
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
