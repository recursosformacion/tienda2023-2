package rf.com.tienda.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import rf.com.tienda.dominio.Categoria;
import rf.com.tienda.servicio.CategoriaService;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoriaControllerTest {
	
	@MockBean
    private CategoriaService service;

    @Autowired
    private MockMvc mockMvc;

	
	@Test
	@DisplayName("GET /categorias ")
	public void getAllCategorias() throws Exception 
	{
		mockMvc.perform(
	  			get("/categorias")
	  			.accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$").exists());
//	      .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id_categoria",is(21)));
	}
	
	@Test
	public void createCategoria() throws Exception 
	{
		mockMvc.perform( MockMvcRequestBuilders
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
