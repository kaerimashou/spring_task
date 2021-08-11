package sobes.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.util.Assert;
import sobes.bean_scope_example.Person;
import sobes.config.SpringConfig;

@SpringBootTest(properties = "spring.main.lazy-initialization=true",classes = {SpringConfig.class})
class DemoApplicationTests {

//    Autowired through property
    @Autowired
    private ApplicationContext context;

//    Autowired through constructor
//    @Autowired
//    public DemoApplicationTests(ApplicationContext context){
//        this.context=context;
//    }
//
//    Autowired through setter
//    @Autowired
//    public void setContext(ApplicationContext context) {
//        this.context = context;
//    }

    @Test
    public void singletonTest(){
        Person firstPerson = (Person) context.getBean("personSingleton");
        Person secondPerson= (Person) context.getBean("personSingleton");

        firstPerson.setName("Alex");
        Assert.isTrue(firstPerson.getName().equals(secondPerson.getName()));
    }

    @Test
    public void prototypeTest(){
        Person firstPerson = (Person) context.getBean("personPrototype");
        Person secondPerson= (Person) context.getBean("personPrototype");

        firstPerson.setName("Alex");
        Assert.isTrue(!firstPerson.getName().equals(secondPerson.getName()));
    }
}
