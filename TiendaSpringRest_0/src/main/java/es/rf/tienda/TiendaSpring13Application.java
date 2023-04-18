package es.rf.tienda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication  //(exclude= {DataSourceAutoConfiguration.class})
public class TiendaSpring13Application {

	public static void main(String[] args) {
		SpringApplication.run(TiendaSpring13Application.class, args);
	}

}
