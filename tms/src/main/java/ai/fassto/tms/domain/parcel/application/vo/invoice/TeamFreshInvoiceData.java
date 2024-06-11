package ai.fassto.tms.domain.parcel.application.vo.invoice;

import ai.fassto.tms.common.util.ZebraZpl;
import ai.fassto.tms.domain.parcel.application.vo.*;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

@Getter
public class TeamFreshInvoiceData extends InvoiceData {
    private static final String TEAM_FRESH_KOREAN_NAME = "팀프레시";
    private static final String DAWN = "새벽";

    private final String teamfreshOrdNo;      // 팀프레시 주문번호 (배송등록하면 생김)
    private final String areaCd;              // 권역 코드
    private final String warehouseName;       // 파스토 센터 이름 (tb_wh 테이블의 WH_NM)

    @Builder
    public TeamFreshInvoiceData(String teamfreshOrdNo, String areaCd, String warehouseName) {
        this.teamfreshOrdNo = super.nullToEmptyString(teamfreshOrdNo);
        this.areaCd = super.nullToEmptyString(areaCd);
        this.warehouseName = super.nullToEmptyString(warehouseName);
    }

    @Override
    public String zplInvoiceCode(FmsInfo fmsInfo, DeliveryInfo deliveryInfo, Destination sender, Destination receiver, Memo memo, Box box) {
        return this.makeTeamFreshIntegratedInvoice(fmsInfo, deliveryInfo, sender, receiver, memo, box);
    }

    private String makeTeamFreshIntegratedInvoice(FmsInfo fmsInfo, DeliveryInfo deliveryInfo, Destination sender, Destination receiver, Memo memo, Box box) {
        final ZebraZpl zpl = new ZebraZpl(10.3F, 12.7F, 203)
                .addText(770, 700, 25, true, box.getInvoiceNo())
                .addText(680, 60, 80, true, this.warehouseName + " " + DAWN)
                .addText(630, 800, 25, true, TEAM_FRESH_KOREAN_NAME)
                .addText(620, 70, 60, true, this.areaCd)
                .addText(580, 120, 20, true, receiver.getName())
                .setLineBreakTextList(550, 80, 30, 25, 30, 3, receiver.getAddress().fullAddressWithoutHyphen(), true)
                .addText(450, 120, 20, true, sender.getName())
                .addText(410, 80, 25, true, sender.getAddress().fullAddressWithoutHyphen())
                .setGoodsNameTextList(360, 80, 30, 25, 33, 7, box.displayItemName(), true)
                .addQRCode(170, 950, box.getInvoiceNo())
                .addText(80, 90, 25, true, this.teamfreshOrdNo)
                .addBarcode128(40, 500, 2, 80, true, true, false, box.getInvoiceNo());

        byte[] encodeZplBytes = Base64.encodeBase64(zpl.getZplCode().getBytes(StandardCharsets.UTF_8));
        return new String(encodeZplBytes);
    }
}
