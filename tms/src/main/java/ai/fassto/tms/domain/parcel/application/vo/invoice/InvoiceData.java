package ai.fassto.tms.domain.parcel.application.vo.invoice;

import ai.fassto.tms.domain.parcel.application.vo.*;
import ai.fassto.tms.domain.parcel.application.vo.enums.InvoiceSizeType;
import ai.fassto.tms.domain.parcel.application.vo.enums.ParcelCompanyType;
import org.apache.commons.lang3.StringUtils;

/**
 * 모든 송장은 http://labelary.com/viewer.html 테스트가 가능하지만 한글은 깨지기때문에
 * 한글 폰트를 확인하기 위해서는 https://www.dobs.co.kr/Index.php?Menu=SolutionZPLConverter 사이트를 참고하는것도 좋습니다.
 * 다만 사이트마다 보여지는 각 값들의 위치가 다르니 꼭 실제 프린터기로 테스트를 해보셔야합니다.
 */
public abstract class InvoiceData {
    // 아직 정의되지 않은 택배의 경우엔 아래의 코드를 리턴하여 NPE 를 방어하고자 함.
    private static final String DEFAULT_ZPL_CODE = "XlhBCl5DRjAsNjAKXkZPMjIwLDUwXkZESU5WT0lDRVRFU1ReRlMKXlha";
    private static final String DEFAULT_DOCUMENT_NAME = "documentName";

    protected boolean isIntegratedInvoice;

    public void markIsIntegratedInvoice(boolean isIntegratedInvoice) {
        this.isIntegratedInvoice = isIntegratedInvoice;
    }

    public abstract String zplInvoiceCode(FmsInfo fmsInfo, DeliveryInfo deliveryInfo, Destination sender, Destination receiver, Memo memo, Box box);


    public String documentName(ParcelCompanyType parcelCompanyType) {
        if (isIntegratedInvoice) {
            return InvoiceSizeType.INTEGRATED_INVOICE.getSize();
        }

        return InvoiceSizeType.findBy(parcelCompanyType.getFmsCode()).getSize();
    }

    protected String nullToEmptyString(String arg) {
        return arg == null ? "" : arg;
    }

    /**
     * 송장 중간 하이픈을 넣어 리턴
     */
    protected String getInvoiceNoWithHyphen(String invoiceNo) {
        final StringBuilder sb = new StringBuilder(invoiceNo);

        sb.insert(4, "-");
        sb.insert(9, "-");

        return sb.toString();
    }

    /**
     * 파스토 송장 양식에 맞게 받는사람 명과 번호를 *로 마스킹처리
     */
    protected String getReceiverWithMasking(String name, String tel1, String tel2) {
        if (StringUtils.isEmpty(name)) {
            name = "*";
        }
        final StringBuilder sb = new StringBuilder(name);

        sb.replace(name.length() - 1, name.length(), "*");
        sb.append(" / ");
        sb.append(getPhoneNumberWithHyphen(tel1, tel2));

        return sb.toString();
    }

    /**
     * 파스토 송장 양식에 맞게 보내는사람을 *로 마스킹 처리
     */
    protected String getSenderWithMasking(String name, String tel1, String tel2) {

        return name + " / " + getPhoneNumberWithHyphen(tel1, tel2);
    }

    /**
     * 공통적으로 사용하는 전화번호 마스킹 메서드
     */
    private String getPhoneNumberWithHyphen(String tel1, String tel2) {
        return tel1 + "-" + tel2 + "-" + "****";
    }
}
