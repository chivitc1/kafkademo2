package chinv.nal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chi.nguyen
 */
@SpringBootApplication
@Slf4j
public class MainApp implements CommandLineRunner
{
	public static void main(String[] args)
	{
		SpringApplication.run(MainApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		log.info("RUNNER");
	}
}
