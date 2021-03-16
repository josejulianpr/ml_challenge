package com.ml.challenge.enums;

import org.junit.jupiter.api.Test;

import static com.ml.challenge.enums.Ship.KENOBI;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ShipTest {

    @Test
    void isShip_WhenDataOk_ThenReturnOk() {

        assertEquals(KENOBI.name(), Ship.isShip(KENOBI.name()));
    }

    @Test
    void isShip_InputIsNull_ThenReturnOk() {

        assertEquals("", Ship.isShip(null));
    }

    @Test
    void isShip_ShipIsInvalid_ThenReturnOk() {

        assertEquals("", Ship.isShip("Anakin"));
    }
}