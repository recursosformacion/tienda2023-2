package rf.com.tienda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication  //(exclude = {DataSourceAutoConfiguration.class })
@ComponentScan({"rf.com.tienda"})
@EnableJpaRepositories(basePackages = {"rf.com.tienda.repository"})
public class MggTienda2023Application {

	public static void main(String[] args) {
		SpringApplication.run(MggTienda2023Application.class, args);
	}

}
