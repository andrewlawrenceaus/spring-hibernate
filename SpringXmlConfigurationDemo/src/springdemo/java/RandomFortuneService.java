import java.util.Random;

public class RandomFortuneService implements FortuneService {

    private final String[] fortunes = new String[]{"Not looking good", "This is your day!", "Could go either way"};

    public RandomFortuneService() {
        System.out.println("Inside the random fortune service constructor");
    }

    @Override
    public String getFortune() {
        Random random = new Random();
        return fortunes[random.nextInt(fortunes.length)];
    }
}
