package com.ml.challenge.service;

import com.google.common.primitives.Doubles;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.ml.challenge.enums.Ship.*;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

@Service
public class LocationService {

    /***
     *
     * @param distances distance to the transmitter as it is received on each satellite
     * @return the 'x' and 'y' coordinates of the sender of the message
     */
    public List<Double> getLocation(List<Double> distances) {

        if (isNotEmpty(distances) && distances.size() == 3) {
            double x1 = KENOBI.getX();
            double y1 = KENOBI.getY();
            double x2 = SKYWALKER.getX();
            double y2 = SKYWALKER.getY();
            double x3 = SATO.getX();
            double y3 = SATO.getY();
            double r1 = distances.get(0);
            double r2 = distances.get(1);
            double r3 = distances.get(2);

            //Solve the system of equations
            // r1^2 = (x - x1)^2 + (y - y1)^2
            // r2^2 = (x - x2)^2 + (y - y2)^2
            // r3^2 = (x - x3)^2 + (y - y3)^2
            // to simplify the resolution we group the operations in variables
            double a = 2 * x2 - 2 * x1;
            double b = 2 * y2 - 2 * y1;
            double c = Math.pow(r1, 2) - Math.pow(r2, 2) - Math.pow(x1, 2) + Math.pow(x2, 2) - Math.pow(y1, 2) + Math.pow(y2, 2);
            double d = 2 * x3 - 2 * x2;
            double e = 2 * y3 - 2 * y2;
            double f = Math.pow(r2, 2) - Math.pow(r3, 2) - Math.pow(x2, 2) + Math.pow(x3, 2) - Math.pow(y2, 2) + Math.pow(y3, 2);
            double x = (c * e - f * b) / (e * a - b * d);
            double y = (c * d - a * f) / (b * d - a * e);

            return Doubles.asList(x, y);
        }
        return Collections.emptyList();
    }

}
