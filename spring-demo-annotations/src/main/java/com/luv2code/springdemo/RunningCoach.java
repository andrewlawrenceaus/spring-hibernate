package com.luv2code.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class RunningCoach implements Coach {

    private FortuneService fortuneService;

    @Autowired
    public RunningCoach(@Qualifier("happyFortuneService") FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "45 minute tempo run";
    }

    @Override
    public String getDailyFortune() {
        return null;
    }
}
