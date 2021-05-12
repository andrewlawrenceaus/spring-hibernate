public class SwimCoach implements Coach {

    private FortuneService fortuneService;

    public SwimCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    public String getDailyWorkout() {
        return "Swim 4x300m hard";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }
}
