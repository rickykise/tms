package ai.fassto.tms.domain.parcel.application.vo.invoice;

import ai.fassto.tms.common.util.ZebraZpl;
import ai.fassto.tms.domain.parcel.application.vo.*;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Getter
@Builder
public class CjInvoiceData extends InvoiceData {
    private static final String CJ = "CJ";
    private static final String HYPHEN = "-";
    private static final String BOX_QTY = "1";
    private static final String CREDIT = "신용";

    private final String endNo;             // 도착지 코드
    private final String subEndNo;          // 도착지 서브코드
    private final String manBranNm;         // 배송집배점명
    private final String cldvEmpNm;         // 배송 sm 명
    private final String cldvEmpClsCd;      // sm 분류코드
    private final String shortAddr;         // 주소 약칭

    @Builder
    public CjInvoiceData(String endNo, String subEndNo, String manBranNm, String cldvEmpNm, String cldvEmpClsCd, String shortAddr) {
        this.endNo = super.nullToEmptyString(endNo);
        this.subEndNo = super.nullToEmptyString(subEndNo);
        this.manBranNm = super.nullToEmptyString(manBranNm);
        this.cldvEmpNm = super.nullToEmptyString(cldvEmpNm);
        this.cldvEmpClsCd = super.nullToEmptyString(cldvEmpClsCd);
        this.shortAddr = super.nullToEmptyString(shortAddr);
    }

    @Override
    public String zplInvoiceCode(FmsInfo fmsInfo, DeliveryInfo deliveryInfo, Destination sender, Destination receiver, Memo memo, Box box) {
        return this.makeCjIntegratedInvoice(fmsInfo, deliveryInfo, sender, receiver, memo, box);
    }

    private String makeCjIntegratedInvoice(FmsInfo fmsInfo, DeliveryInfo deliveryInfo, Destination sender, Destination receiver, Memo memo, Box box) {
        final ZebraZpl zpl = new ZebraZpl(10.3F, 12.7F, 203)
                .addText(770, 700, 25, true, super.getInvoiceNoWithHyphen(box.getInvoiceNo()))
                .addBarcode128(630, 50, 3, 110, true, false, false, this.endNo)
                .addText(660, 300, 120, true, this.endNo + HYPHEN + this.subEndNo)
                .addGraphic(600, 1000, CJ)
                .addText(580, 120, 20, true, super.getReceiverWithMasking(receiver.getName(), receiver.getPhone().getPhoneBy(0), receiver.getPhone().getPhoneBy(1)))
                .setLineBreakTextList(550, 80, 30, 25, 20, 3, receiver.getAddress().fullAddressWithoutHyphen(), true)
                .addBarcode128(500, 630, 3, 50, true, false, true, box.getInvoiceNo())
                .addText(450, 760, 20, true, BOX_QTY)
                .addText(450, 900, 20, true, CREDIT)
                .addText(450, 120, 20, true, super.getSenderWithMasking(sender.getName(), sender.getPhone().getPhoneBy(0), sender.getPhone().getPhoneBy(1)))
                .addText(410, 80, 25, true, sender.getAddress().fullAddressWithoutHyphen())
                .setGoodsNameTextList(360, 80, 30, 25, 35, 6, box.displayItemName(), true)
                .setLineBreakTextList(320, 700, 30, 30, 8, 3, this.shortAddr, true)
                .addText(100, 90, 25, true, this.shortAddr)
                .addText(70, 90, 25, true, this.manBranNm)
                .addText(70, 350, 25, true, this.cldvEmpNm + this.cldvEmpClsCd)
                .addText(40, 90, 25, true, LocalDate.now().toString())
                .addText(40, 400, 25, true, CREDIT)
                .addBarcode128(40, 600, 3, 70, true, true, true, box.getInvoiceNo());

        final byte[] encodeZplBytes = Base64.encodeBase64(zpl.getZplCode().getBytes(StandardCharsets.UTF_8));
        return new String(encodeZplBytes);
    }


}
