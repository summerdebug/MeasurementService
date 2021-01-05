package com.utilities.monitoring.controller;

import com.utilities.monitoring.dto.MeasurementDto;
import com.utilities.monitoring.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/measurement")
public class MeasurementController {

    private final MeasurementService measurementService;

    @Autowired
    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    /**
     * POST request to URL: http://localhost:8080/api/measurement
     * Media Type: application/json
     * Body: {"userId":1,"type":"COLD_WATER","value":20}
     * Check URL in browser: http://localhost:8080/api/measurement/1
     */
    @PostMapping
    public ResponseEntity<String> save(@RequestBody MeasurementDto dto) {
        Long generatedId = measurementService.save(dto);
        return ok("Measurement saved successfully with id: " + generatedId);
    }

    /**
     * URL http://localhost:8080/api/measurement/1
     */
    @GetMapping(path = "/{user_id}")
    public ResponseEntity<List<MeasurementDto>> findByUserId(@PathVariable(name = "user_id") Long userId) {
        return ok(measurementService.findByUserId(userId));
    }
}
