package ai.fassto.tms.domain.parcel.application.vo.invoice;

import ai.fassto.tms.common.util.ZebraZpl;
import ai.fassto.tms.domain.parcel.application.vo.*;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Getter
public class LotteInvoiceData extends InvoiceData {
    private static final String BOX_TYPE_CD = "1";
    private static final String BOX_QTY = "1";
    private static final String ORDER_NO = "주문번호";
    private static final String INVOICE_STR = "운송장번호: ";
    private static final String SENDER_STR = "보내는분: ";
    private static final String RECEIVER_STR = "받는분: ";
    private static final String PRICE_CREDIT = "운임: (신용)";
    private static final String CREDIT_STR = "(신)";
    private static final String NORMAL_RELEASE = "※일반출고※";
    private static final String TEL_EMOJI = " ☎ ";

    private final String conFlg;
    private final String tmlCd;             // 터미널 코드
    private final String tmlNm;             // 터미널 명
    private final String cityGunGu;         // 시군구
    private final String eupMuinDong;       // 읍면동
    private final String filtCd;            // 도착지 코드
    private final String brnshpNm;          // 배달영업소
    private final String brnclctCd;         // 집하점 코드
    private final String brnclctNm;         // 집하점 명
    private final String brnclctTelNo;      // 집하점 전화번호
    private final String empNm;             // 집배송사원명

    @Builder
    public LotteInvoiceData(String conFlg, String tmlCd, String tmlNm, String cityGunGu, String eupMuinDong, String filtCd, String brnshpNm, String brnclctCd, String brnclctNm, String brnclctTelNo, String empNm) {
        this.conFlg = super.nullToEmptyString(conFlg);
        this.tmlCd = super.nullToEmptyString(tmlCd);
        this.tmlNm = super.nullToEmptyString(tmlNm);
        this.cityGunGu = super.nullToEmptyString(cityGunGu);
        this.eupMuinDong = super.nullToEmptyString(eupMuinDong);
        this.filtCd = super.nullToEmptyString(filtCd);
        this.brnshpNm = super.nullToEmptyString(brnshpNm);
        this.brnclctCd = super.nullToEmptyString(brnclctCd);
        this.brnclctNm = super.nullToEmptyString(brnclctNm);
        this.brnclctTelNo = super.nullToEmptyString(brnclctTelNo);
        this.empNm = super.nullToEmptyString(empNm);
    }

    @Override
    public String zplInvoiceCode(FmsInfo fmsInfo, DeliveryInfo deliveryInfo, Destination sender, Destination receiver, Memo memo, Box box) {
        final ZebraZpl zpl = new ZebraZpl(10.5F, 20.3F, 203)
                .addText(740, 80, 60, true, this.tmlNm)
                .addText(680, 50, 60, true, this.cityGunGu + " " + this.eupMuinDong)
                .addBarcode39(670, 450, 3, 110, true, false, this.filtCd)
                .addText(610, 40, 25, true, fmsInfo.orderNo())
                .setGoodsNameTextList(570, 40, 30, 25, 36, 9, box.displayItemName(), true)
                .addText(275, 40, 20, true, INVOICE_STR + super.getInvoiceNoWithHyphen(box.getInvoiceNo()))
                .addText(250, 40, 20, true, SENDER_STR + sender.getName())
                .addText(225, 40, 20, true, RECEIVER_STR + super.getReceiverWithMasking(receiver.getName(), receiver.getPhone().getPhoneBy(0), receiver.getPhone().getPhoneBy(1)))
                .setLineBreakTextList(200, 40, 25, 20, 30, 2, receiver.getAddress().fullAddressWithoutHyphen(), true)
                .addText(150, 40, 20, true, PRICE_CREDIT)
                .addBarcode2of5(40, 130, 2, 90, true, false, box.getInvoiceNo())
                .addText(100, 400, 50, true, this.brnshpNm)
                .addText(60, 400, 50, true, this.empNm)
                .addText(760, 850, 25, true, super.getInvoiceNoWithHyphen(box.getInvoiceNo()))
                .addText(760, 1300, 25, true, LocalDate.now().toString())
                .addText(720, 770, 30, true, super.getReceiverWithMasking(receiver.getName(), receiver.getPhone().getPhoneBy(0), receiver.getPhone().getPhoneBy(1)))
                .setLineBreakTextList(690, 770, 30, 30, 25, 3, receiver.getAddress().fullAddressWithoutHyphen(), true)
                .addText(560, 770, 30, true, super.getSenderWithMasking(sender.getName(), sender.getPhone().getPhoneBy(0), sender.getPhone().getPhoneBy(1)))
                .addBarcode2of5(440, 1200, 3, 110, true, false, box.getInvoiceNo())
                .addText(400, 1200, 25, true, NORMAL_RELEASE + super.getInvoiceNoWithHyphen(box.getInvoiceNo()))
                .addText(370, 1200, 25, true, this.brnclctNm + TEL_EMOJI + this.brnclctTelNo)
                .addText(430, 820, 30, true, super.getInvoiceNoWithHyphen(box.getInvoiceNo()))
                .addText(380, 770, 25, true, CREDIT_STR)
                .addText(380, 1050, 25, true, BOX_QTY)
                .addText(330, 770, 30, true, super.getReceiverWithMasking(receiver.getName(), receiver.getPhone().getPhoneBy(0), receiver.getPhone().getPhoneBy(1)))
                .setLineBreakTextList(300, 770, 30, 30, 25, 4, receiver.getAddress().fullAddressWithoutHyphen(), true)
                .addText(140, 770, 25, true, super.getSenderWithMasking(sender.getName(), sender.getPhone().getPhoneBy(0), sender.getPhone().getPhoneBy(1)))
                .addText(110, 770, 25, true, sender.getAddress().fullAddressWithoutHyphen())
                .addText(70, 770, 25, true, box.invoiceMarkItemName())
                .addText(30, 770, 25, true, memo.getShipRequestMemo());

        byte[] encodeZplBytes = Base64.encodeBase64(zpl.getZplCode().getBytes(StandardCharsets.UTF_8));
        return new String(encodeZplBytes);
    }
}
