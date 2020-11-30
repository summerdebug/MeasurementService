package com.utilities.monitoring.dto;

import com.utilities.monitoring.model.UtilityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
public class MeasurementDto {
    Long userId;
    UtilityType type;
    Integer value;
    String date;
}
