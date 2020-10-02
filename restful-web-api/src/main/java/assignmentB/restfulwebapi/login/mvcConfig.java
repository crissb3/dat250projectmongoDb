package assignmentB.restfulwebapi.login;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class mvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/html/polls").setViewName("polls");
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/html/loginScreen").setViewName("loginScreen");
	}

}