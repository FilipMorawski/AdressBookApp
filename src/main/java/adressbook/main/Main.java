package adressbook.main;

import adressbook.domain.Controller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(adressbook.main.MyConfig.class);
        Controller controller = context.getBean("controller", Controller.class);
        controller.start();
    }
}
