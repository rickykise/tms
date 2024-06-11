package ai.fassto.tms.externalservice.parcel.hanjin.vo.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum DomRgnType {
    CAPITAL("1", "수도권"), GANGWON("2", "강원권역"), CHUNGCHEONG("3", "충청권역"), GYEONGSANGNAMDO("4", "경남권역"), GYEONGSANGBUKDO("5", "경북권역"), HONAM("6", "호남권역"), JEJU("7", "제주권역"), OTHER("9", "도서지역");

    private final String type;
    private final String means;

    DomRgnType(String type, String means) {
        this.type = type;
        this.means = means;
    }

    public static DomRgnType findBy(String targetType) {
        return Arrays.stream(values())
                .filter(domRgn -> domRgn.type.equals(targetType))
                .findFirst()
                .orElseGet(() -> CAPITAL);
    }
}
