package ai.fassto.tms.domain.parcel.application.vo;


import lombok.Builder;
import lombok.Getter;

@Getter
public class Phone {
    private static final String HYPHEN = "-";
    private String number;
    private final String numberEtc;

    @Builder
    public Phone(String number, String numberEtc) {
        this.number = number;
        this.numberEtc = numberEtc;
    }

    public String getPhoneBy(int index) {
        try {
            return this.deleteSymbolAndAddHyphen(number).split(HYPHEN)[index];
        } catch (Exception e) {
            return "";
        }
    }

    public String getPhoneEtcBy(int index) {
        try {
            return this.deleteSymbolAndAddHyphen(numberEtc).split(HYPHEN)[index];
        } catch (Exception e) {
            return "";
        }
    }

    private String deleteSymbolAndAddHyphen(String src) {
        try {
            src = src.replaceAll("[^0-9]", "");

            if (src.length() == 8) {
                return src.replaceFirst("^([0-9]{4})([0-9]{4})$", "$1-$2");
            } else if (src.length() == 12) {
                return src.replaceFirst("(^[0-9]{4})([0-9]{4})([0-9]{4})$", "$1-$2-$3");
            }
            return src.replaceFirst("(^02|[0-9]{3})([0-9]{3,4})([0-9]{4})$", "$1-$2-$3");
        } catch (Exception e) {
            return "";
        }
    }

    public void senderPhoneNumberSetting(String number) {
        this.number = number;
    }

}
