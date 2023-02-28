package SpringBoot_Project.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication


@ComponentScan("SpringBoot_Project.web")
public class TheSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheSpringBootApplication.class, args);
	}

}
