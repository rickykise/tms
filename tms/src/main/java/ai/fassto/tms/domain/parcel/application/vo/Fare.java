package ai.fassto.tms.domain.parcel.application.vo;


import lombok.Builder;
import lombok.Getter;

@Getter
public class Fare {
    private final int basicFare;
    private final int dealFare;
    private final int airFare;
    private final int shipFare;
    private final int sumFare;

    @Builder
    public Fare(int basicFare, int dealFare, int airFare, int shipFare, int sumFare) {
        this.basicFare = basicFare;
        this.dealFare = dealFare;
        this.airFare = airFare;
        this.shipFare = shipFare;
        this.sumFare = sumFare;
    }
}
