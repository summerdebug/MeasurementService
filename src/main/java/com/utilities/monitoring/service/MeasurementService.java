package com.utilities.monitoring.service;

import com.utilities.monitoring.dto.MeasurementDto;
import com.utilities.monitoring.exception.UserNotFoundException;

import java.util.List;

public interface MeasurementService {

    Long save(MeasurementDto dto);

    List<MeasurementDto> findByUserId(Long userId);
}
