public class TrackCoach implements Coach {

    private FortuneService fortuneService;

    public TrackCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    public TrackCoach() {
    }

    public String getDailyWorkout() {
        return "Run a hard 5k";
    }

    @Override
    public String getDailyFortune() {
        return "Just do it: " + fortuneService.getFortune();
    }

    public FortuneService getFortuneService(){
        return fortuneService;
    }

    // add an init method
    public void doMyStartupStuff(){
        System.out.println("TrackCoach: Inside method doMyStartupStuff");
    }


    // add a destroy method
    public void doMyCleanupStuffYoYo(){
        System.out.println("TrackCoach: Inside method MyCleanupStuffYoYo");
    }
}
