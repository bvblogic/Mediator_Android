package com.enterprayz.controller.beans.enums;

/**
 * Created by con on 19.04.16.
 */
public enum StandType {
    MAGAZINE("Магазины"),
    ZONE("Зоны"),
    STAND("Стенды"),
    TEST("Другое");

    private final String value;

    StandType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static StandType getType(String type) {
        switch (type) {
            case "Магазины": {
                return MAGAZINE;
            }
            case "Зоны": {
                return ZONE;
            }
            case "Стенды": {
                return STAND;
            }
            default: {
                return TEST;
            }
        }
    }

}
