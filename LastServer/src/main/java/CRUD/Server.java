package CRUD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import java.util.Collections;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class Server
{
	public static void main(String[] args)
	{
	SpringApplication app = new SpringApplication(Server.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", "8083"));
		app.run(args);
	}

}
