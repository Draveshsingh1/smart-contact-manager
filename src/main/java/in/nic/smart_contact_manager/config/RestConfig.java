package in.nic.smart_contact_manager.config;

import in.nic.smart_contact_manager.entities.Contact;
import in.nic.smart_contact_manager.entities.User;
import in.nic.smart_contact_manager.projection.UserProjection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RestConfig {
    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer(){
        return  new RepositoryRestConfigurer() {
            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

                //config.setBasePath(AppConstant.REST_BASE_URL);
                config.setDefaultPageSize(AppConstant.PAGE_SIZE);
                config.exposeIdsFor(User.class, Contact.class);
                config.getProjectionConfiguration().addProjection(UserProjection.class);


                //config.setDefaultMediaType(MediaType.APPLICATION_JSON);
                //config.useHalAsDefaultJsonMediaType(false); // this disables HAL: Hypertext Application Language
            }
        };
    }
}
