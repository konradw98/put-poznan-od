package put.poznan.ochronadanych;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class OchronaDanychApplication {

	public static void main(String[] args) {
		SpringApplication.run(OchronaDanychApplication.class, args);
	}

}
