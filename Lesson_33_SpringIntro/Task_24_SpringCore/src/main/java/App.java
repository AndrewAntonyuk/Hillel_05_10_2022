import com.av.config.Config;
import com.av.utils.Menu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

        applicationContext.getBean(Menu.class).run();
    }
}
