package bean;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        DatabaseInit databaseInit = applicationContext.getBean(DatabaseInit.class);

        for(String s : applicationContext.getBeanDefinitionNames()) {
            System.out.println(s);
        }
        databaseInit.destroy();
    }
}
