package com.mock.carrental.enums;

import java.util.Objects;

public enum CarStatusEnum {

    FREE,

    RENTED;

    public static Boolean canRent(CarStatusEnum carStatusEnum) {
        return Objects.equals(CarStatusEnum.FREE, carStatusEnum);
    }

    public static Boolean canReturn(CarStatusEnum carStatusEnum) {
        return Objects.equals(CarStatusEnum.RENTED, carStatusEnum);
    }
}
