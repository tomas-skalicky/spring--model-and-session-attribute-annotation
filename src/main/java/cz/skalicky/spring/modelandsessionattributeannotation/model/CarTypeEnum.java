package cz.skalicky.spring.modelandsessionattributeannotation.model;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import cz.skalicky.spring.modelandsessionattributeannotation.support.EnumWithId;

public enum CarTypeEnum implements EnumWithId<Integer> {

    // @formatter:off
    //           id     label
    SEDAN       (1,     "Sedan"),
    LIFTBACK    (2,     "Liftback"),
    COMBI       (3,     "Combi"),
    COUPE       (4,     "Coupé"),
    HATCHBACK   (5,     "Hatchback");
    // @formatter:on

    private static final Map<Integer, CarTypeEnum> idToEnumValue;

    static {
        idToEnumValue = Arrays.stream(values()).collect(Collectors.toMap(v -> v.id, v -> v));
    }

    private final int id;
    private final String label;

    private CarTypeEnum(final int id, final String label) {
        this.id = id;
        this.label = label;
    }

    @Override
    @JsonValue
    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static CarTypeEnum getById(final int id) {
        if (idToEnumValue.containsKey(id)) {
            return idToEnumValue.get(id);
        } else {
            throw new IllegalArgumentException(String.format("Not support id [%d]", id));
        }
    }

}
