package AssignmentB.restfulwebapi;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import AssignmentB.restfulwebapi.entity.Poll;
import AssignmentB.restfulwebapi.entity.User;

@Configuration
public class SpringDataRestConfig implements RepositoryRestConfigurer {

	@Override
	  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
	    config.exposeIdsFor(User.class,Poll.class);
	  }

}
