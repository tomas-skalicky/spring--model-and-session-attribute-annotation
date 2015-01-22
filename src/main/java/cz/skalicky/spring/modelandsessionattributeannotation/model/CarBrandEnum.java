package cz.skalicky.spring.modelandsessionattributeannotation.model;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import cz.skalicky.spring.modelandsessionattributeannotation.support.EnumWithId;

public enum CarBrandEnum implements EnumWithId<Integer> {

    // @formatter:off
    //       id
    SKODA   (1),
    BMW     (2),
    FORD    (3);
    // @formatter:on

    private static final Map<Integer, CarBrandEnum> idToEnumValue;

    static {
        idToEnumValue = Arrays.stream(values()).collect(Collectors.toMap(v -> v.id, v -> v));
    }

    private final int id;

    private CarBrandEnum(final int id) {
        this.id = id;
    }

    @Override
    @JsonValue
    public Integer getId() {
        return id;
    }

    @JsonCreator
    public static CarBrandEnum getById(final int id) {
        if (idToEnumValue.containsKey(id)) {
            return idToEnumValue.get(id);
        } else {
            throw new IllegalArgumentException(String.format("Not support id [%d]", id));
        }
    }

}
