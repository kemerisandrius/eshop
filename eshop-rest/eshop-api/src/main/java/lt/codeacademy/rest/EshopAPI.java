package lt.codeacademy.rest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EshopAPI implements CommandLineRunner {

	private final MessageBean messageBean;

	public EshopAPI(MessageBean messageBean) {
		this.messageBean = messageBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(EshopAPI.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(messageBean.getMessage());
	}
}
