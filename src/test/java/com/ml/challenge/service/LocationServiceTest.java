package com.ml.challenge.service;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocationServiceTest {

    private final LocationService service = new LocationService();

    @Test
    void getLocation_WhenDataOk_ThenReturnOk() {

        List<Double> expected = Arrays.asList(-487.2859125D, 1557.014225D);

        List<Double> response = service.getLocation(Arrays.asList(100.0D, 115.5D, 142.7D));

        assertEquals(expected, response);

    }

    @Test
    void getLocation_WhenListIsEmpty_ThenReturnOk() {

        List<Double> response = service.getLocation(Collections.emptyList());

        assertEquals(Collections.emptyList(), response);

    }

    @Test
    void getLocation_WhenListNotEmptyAndLessOfThreeElement_ThenReturnOk() {

        List<Double> response = service.getLocation(Collections.singletonList(0D));

        assertEquals(Collections.emptyList(), response);

    }

}