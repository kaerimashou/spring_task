package sobes.bean_scope_example;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Person {
    private String name;
    private String surname;

    public Person() {
        name = "Aliaksandar";
        surname = "Ikonnikov";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @PostConstruct
    public void init() throws Exception {
        System.out.println("INIT BEAN PERSON");
    }

    @PreDestroy
    public void destroy() throws Exception {
        System.out.println("DESTROY BEAN PERSON");
    }

}
