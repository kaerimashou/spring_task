package sobes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sobes.bean_scope_example.Person;


@Configuration
@ComponentScan("sobes.bean_scope_example")
public class SpringConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    @Autowired
    public SpringConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Person personSingleton() {
        return new Person();
    }

    @Bean
    @Scope(value =ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Person personPrototype(){
        return new Person();
    }
}
