package com.utilities.monitoring.service;

import com.utilities.monitoring.dto.MeasurementDto;
import com.utilities.monitoring.exception.UserNotFoundException;
import com.utilities.monitoring.model.Measurement;
import com.utilities.monitoring.model.User;
import com.utilities.monitoring.repository.MeasurementRepository;
import com.utilities.monitoring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MeasurementServiceImpl implements MeasurementService {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final UserRepository userRepo;
    private final MeasurementRepository measurementRepo;

    @Autowired
    public MeasurementServiceImpl(MeasurementRepository measurementRepo,
                                  UserRepository userRepo) {
        this.measurementRepo = measurementRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Long save(MeasurementDto dto) {
        Long userId = dto.getUserId();
        Optional<User> userOptional = userRepo.findById(userId);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(userId);
        }
        User user = userOptional.get();
        LocalDateTime timestamp = LocalDateTime.now();
        // TODO Validate that value is not less than previously received values
        Measurement measurement = new Measurement(user, dto.getType(), dto.getValue(), timestamp);
        Measurement saved = measurementRepo.save(measurement);
        return saved.getId();
    }

    @Override
    public List<MeasurementDto> findByUserId(Long userId) {
        if (userRepo.findById(userId).isEmpty()) {
            throw new UserNotFoundException(userId);
        }
        List<Measurement> measurements = measurementRepo.findByUserId(userId);
        return measurements.stream().map(m ->
                MeasurementDto.builder()
                        .userId(m.getUser().getId())
                        .type(m.getType())
                        .value(m.getValue())
                        .date(m.getTimestamp().format(DATE_FORMAT))
                        .build()).collect(Collectors.toList());
    }
}
