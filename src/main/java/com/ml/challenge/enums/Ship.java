package com.ml.challenge.enums;

import lombok.Getter;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Getter
public enum Ship {
    KENOBI(-500, -200),
    SKYWALKER(100, -100),
    SATO(500, 100);

    private final double x;
    private final double y;

    Ship(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static String isShip(String value) {

        try {
            if (isBlank(value))
                return "";
            return Ship.valueOf(value.toUpperCase()).name();
        } catch (Exception e) {
            return "";
        }

    }
}
