import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifecycleDemoApp {

    public static void main(String[] args) {

        // load the spring configuration file
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("beanLifecycle-applicationContext.xml");
        // retrieve bean from spring container
        TrackCoach theCoach = context.getBean("myCoach", TrackCoach.class);
        TrackCoach anotherCoach = context.getBean("myCoach", TrackCoach.class);

        System.out.println(theCoach.getFortuneService());
        System.out.println(anotherCoach.getFortuneService());
        context.close();
    }
}
