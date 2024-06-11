package ai.fassto.tms.domain.parcel.application.vo;


import lombok.Builder;
import lombok.Getter;

@Getter
public class Destination {
    private String name;
    private final Phone phone;
    private Address address;

    @Builder
    public Destination(String name, Phone phone, Address address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    void convertAddressToCheckedAddress(Address address) {
        this.address = address;
    }

    public void senderNameSetting(String name) {
        this.name = name;
    }
}
