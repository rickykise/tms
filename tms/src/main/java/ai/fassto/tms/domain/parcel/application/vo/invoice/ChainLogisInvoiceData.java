package ai.fassto.tms.domain.parcel.application.vo.invoice;


import ai.fassto.tms.common.util.ZebraZpl;
import ai.fassto.tms.domain.parcel.application.vo.*;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

@Getter
public class ChainLogisInvoiceData extends InvoiceData {
    public static final String PARCEL_NAME = "체인로지스";
    private final String dongGroup;         // 두발히어로 내부 지역 그룹(ex. 동남11역삼) 빈값일 수 있음.

    @Builder
    public ChainLogisInvoiceData(String dongGroup) {
        this.dongGroup = super.nullToEmptyString(dongGroup);
    }

    @Override
    public String zplInvoiceCode(FmsInfo fmsInfo, DeliveryInfo deliveryInfo, Destination sender, Destination receiver, Memo memo, Box box) {
        final ZebraZpl zpl = new ZebraZpl(10.3F, 12.7F, 203)
                .addText(630, 900, 20, true, PARCEL_NAME)
                .addText(580, 120, 20, true, super.getReceiverWithMasking(receiver.getName(), receiver.getPhone().getPhoneBy(0), receiver.getPhone().getPhoneBy(1)))
                .setLineBreakTextList(550, 80, 30, 25, 20, 3, receiver.getAddress().fullAddressWithoutHyphen(), true)
                .addBarcode128(500, 630, 3, 60, true, true, true, box.getInvoiceNo())
                .addText(450, 120, 20, true, super.getSenderWithMasking(sender.getName(), sender.getPhone().getPhoneBy(0), sender.getPhone().getPhoneBy(1)))
                .addText(410, 80, 25, true, sender.getAddress().fullAddressWithoutHyphen())
                .setGoodsNameTextList(360, 80, 30, 25, 35, 6, box.displayItemName(), true)
                .addBarcode128(40, 630, 3, 80, true, true, true, box.getInvoiceNo());

        byte[] encodeZplBytes = Base64.encodeBase64(zpl.getZplCode().getBytes(StandardCharsets.UTF_8));
        return new String(encodeZplBytes);
    }
}
