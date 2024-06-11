package ai.fassto.tms.domain.parcel.application.vo.invoice;

import ai.fassto.tms.common.util.ZebraZpl;
import ai.fassto.tms.domain.parcel.application.vo.*;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class EcremmoceInvoiceData extends InvoiceData {
    private static final String FMS = "FMS";
    private static final String INVOICE_STATIC_ORDER_ID = "Order ID : ";
    private static final String INVOICE_STATIC_PLATFORM = "PLATFORM :";
    private static final String INVOICE_STATIC_TRACKING_NO = "Tracking No : ";
    private static final String INVOICE_STATIC_LOGISTIC_CENTER = "LogisticCenter : ";
    private static final String INVOICE_STATIC_ITEMS = "Items :";
    private final String platform;        // 이크레모스 쇼핑몰(SHOPEE, LAZADA, RAKUTEN, QOO10)
    private final String logiCenter;      // 크레모스 배송센터(YSL(용성), DRA(두라))

    @Builder
    public EcremmoceInvoiceData(String platform, String logiCenter) {
        this.platform = super.nullToEmptyString(platform);
        this.logiCenter = super.nullToEmptyString(logiCenter);
    }

    @Override
    public String zplInvoiceCode(FmsInfo fmsInfo, DeliveryInfo deliveryInfo, Destination sender, Destination receiver, Memo memo, Box box) {
        final ZebraZpl zpl = new ZebraZpl(10.3F, 10.3F, 203)
                .addGraphicBox(30, 750, 740, 560, 2)
                .addGraphicBox(30, 480, 740, 230, 1)
                .addText(30, 100, 20, false, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .addText(500, 100, 20, false, FMS)
                .addText(40, 230, 30, false, INVOICE_STATIC_ORDER_ID + fmsInfo.orderNo())
                .addText(600, 210, 20, false, INVOICE_STATIC_PLATFORM)
                .addText(600, 230, 20, false, this.platform)
                .addBarcode128(70, 350, 3, 80, false, false, false, box.getInvoiceNo())
                .addText(200, 390, 25, false, INVOICE_STATIC_TRACKING_NO + box.getInvoiceNo())
                .addText(260, 440, 25, false, INVOICE_STATIC_LOGISTIC_CENTER + this.logiCenter)
                .addText(60, 500, 20, false, INVOICE_STATIC_ITEMS)
                .setGoodsNameTextList(70, 530, 30, 25, 33, 7, box.displayItemName(), false);

        final byte[] encodeZplBytes = Base64.encodeBase64(zpl.getZplCode().getBytes(StandardCharsets.UTF_8));
        return new String(encodeZplBytes);
    }
}
