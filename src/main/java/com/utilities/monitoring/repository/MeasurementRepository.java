package com.utilities.monitoring.repository;

import com.utilities.monitoring.model.Measurement;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends CrudRepository<Measurement, Long> {

    List<Measurement> findByUserId(@Param("userId") Long userId);
}
