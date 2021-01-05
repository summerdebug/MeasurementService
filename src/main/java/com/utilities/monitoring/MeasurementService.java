package com.utilities.monitoring;

import com.utilities.monitoring.model.Measurement;
import com.utilities.monitoring.model.User;
import com.utilities.monitoring.model.UtilityType;
import com.utilities.monitoring.repository.MeasurementRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

import com.utilities.monitoring.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MeasurementService {

    public static void main(String[] args) {
        SpringApplication.run(MeasurementService.class, args);
    }

    // Initiate database
    @Bean
    public CommandLineRunner init(MeasurementRepository measurementRepo, UserRepository userRepo) {
        return args -> {
            Stream.of("Andrew", "Kate", "Tom", "John", "Bill").forEach(name -> {
                User user = new User(name, name.toLowerCase() + "@dummymail.com");
                userRepo.save(user);
            });
            userRepo.findAll().forEach(System.out::println);

            Optional<User> userOptional = userRepo.findById(1L);
            if (!userOptional.isPresent()) {
                throw new RuntimeException("User with userId = 1 not found");
            }
            User user = userOptional.get();

            Measurement measurement = new Measurement(user, UtilityType.WATER, 15, LocalDateTime.now());
            measurementRepo.save(measurement);
        };
    }
}
