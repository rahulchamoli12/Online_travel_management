package com.masai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication

public class OnlineTripManagement2Application {

	public static void main(String[] args) {
		SpringApplication.run(OnlineTripManagement2Application.class, args);
	}
	
}
