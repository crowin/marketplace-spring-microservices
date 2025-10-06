package org.github.crowin.marketservice.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceUtils {

    public static double scalePrice(Double price) {
        return BigDecimal.valueOf(price).setScale(2, RoundingMode.CEILING).doubleValue();
    }
}
