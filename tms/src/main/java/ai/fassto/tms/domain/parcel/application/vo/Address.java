package ai.fassto.tms.domain.parcel.application.vo;


import lombok.Builder;
import lombok.Getter;

@Getter
public class Address {
    private final String zipCode;
    private final String address;
    private final String addressDetail;

    @Builder
    public Address(String zipCode, String address, String addressDetail) {
        this.zipCode = zipCode;
        this.address = address;
        this.addressDetail = addressDetail;
    }

    public String fullAddress() {
        return this.address + (this.addressDetail == null ? "" : this.addressDetail);
    }

    /**
     * zpl 코드로 송장 출력시 ~ 기호는 명령어의 시작으로 인식하여 인쇄가 되지않아
     * ~ 를 - 로 대체하여 출력하는 방향으로 기능 작성
     */
    public String fullAddressWithoutHyphen() {
        final String fullAddress = this.address + (this.addressDetail == null ? "" : this.addressDetail);

        return fullAddress.replace("~", "-");
    }
}
