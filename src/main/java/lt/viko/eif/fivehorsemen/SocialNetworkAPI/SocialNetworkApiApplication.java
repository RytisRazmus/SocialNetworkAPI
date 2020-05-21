package lt.viko.eif.fivehorsemen.SocialNetworkAPI;

import lt.viko.eif.fivehorsemen.SocialNetworkAPI.api.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackageClasses = UserController.class)
public class SocialNetworkApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialNetworkApiApplication.class, args);
	}

}
