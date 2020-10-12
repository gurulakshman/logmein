package com.logmein;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/**
 * This is main application class for spring boot application.
 * @author Guru
 *
 */
@SpringBootApplication
public class ShoppingApplication extends SpringBootServletInitializer {
	public static void main(String str[]) {
		SpringApplication.run(ShoppingApplication.class, str);
	}
}
