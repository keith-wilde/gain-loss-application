package com.kwilde.gainloss.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class ObjectUtils {

    public static BigDecimal convertToBigDecimal(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Object cannot be null");
        }
        if (obj instanceof BigDecimal) {
            return (BigDecimal) obj;
        }
        if (obj instanceof String) {
            try {
                return new BigDecimal((String) obj);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid string format for BigDecimal", e);
            }
        }
        if (obj instanceof Number) {
            return BigDecimal.valueOf(((Number) obj).doubleValue());
        }
        throw new IllegalArgumentException("Unsupported object type");
    }

    public static LocalDate convertToLocalDate(Object localDateObject) {
        Objects.requireNonNull(localDateObject);
        return LocalDate.parse(String.valueOf(localDateObject));

    }

}
