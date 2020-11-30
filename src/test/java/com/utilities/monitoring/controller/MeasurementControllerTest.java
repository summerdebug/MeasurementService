package com.utilities.monitoring.controller;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.OK;

import com.utilities.monitoring.dto.MeasurementDto;
import com.utilities.monitoring.model.UtilityType;
import com.utilities.monitoring.repository.MeasurementRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MeasurementControllerTest {

    static final String URL = "/api/measurement";
    private static final long USER_ID = 2L;
    private static final int MEASUREMENT_VALUE = 20;
    public static final UtilityType TYPE = UtilityType.HOT_WATER;

    @Mock
    private MeasurementController measurementController;

    @Autowired
    private TestRestTemplate rest;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        MockMvcBuilders.standaloneSetup(measurementController).build();
    }

    @Test
    public void shouldReturnSavedMeasurement() throws Exception {
        // Arrange
        final HttpEntity<String> entity = getMeasurementDtoHttpEntity(USER_ID, MEASUREMENT_VALUE, TYPE);
        final ParameterizedTypeReference<List<MeasurementDto>> listType =
                new ParameterizedTypeReference<List<MeasurementDto>>() {
                };

        // Act
        final ResponseEntity<String> savedResponse = rest.postForEntity(URL, entity, String.class);
        final ResponseEntity<List<MeasurementDto>> foundResponse = rest
                .exchange(URL + "/" + USER_ID, GET, null, listType);

        // Verify
        assertEquals(OK.value(), savedResponse.getStatusCode().value());
        final String savedMeasurement = requireNonNull(savedResponse.getBody());

        assertEquals(OK.value(), foundResponse.getStatusCode().value());
        final List<MeasurementDto> foundMeasurements = requireNonNull(foundResponse.getBody());
        assertEquals(1, foundMeasurements.size());
        MeasurementDto foundMeasurement = requireNonNull(foundMeasurements.get(0));
        assertEquals(TYPE, foundMeasurement.getType());
        assertEquals(MEASUREMENT_VALUE, (int) foundMeasurement.getValue());
        assertEquals(USER_ID, (long) foundMeasurement.getUserId());
    }

    private HttpEntity<String> getMeasurementDtoHttpEntity(Long userId, Integer value, UtilityType type)
            throws JsonProcessingException {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final MeasurementDto dto = MeasurementDto.builder().value(value).type(type).userId(USER_ID).build();
        return new HttpEntity<>(objectMapper.writeValueAsString(dto), headers);
    }
}
