package ai.fassto.tms.domain.parcel.application.vo.invoice;

import ai.fassto.tms.common.util.ZebraZpl;
import ai.fassto.tms.domain.parcel.application.vo.*;
import ai.fassto.tms.domain.parcel.application.vo.enums.SoloChainShippingServiceType;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Getter
public class HanjinInvoiceData extends InvoiceData {
    private static final String HANJIN_N_ARRIVE_GUARANTEE = "N 도착";
    private static final String HANJIN = "한진택배";
    private static final String INVOICE_STATIC_START_POINT = "발지 : ";
    private static final String INVOICE_STATIC_RETURN_CODE = "반송코드 : ";
    // 현 시점 기준 송장 하나당 박스수량은 1개인것으로 정책이 정해져있음.
    private static final String BOX_QTY = "1";
    private static final String PAY_CON = "발지신용";

    private final String sTmlNam;             // 집하지 터미널명
    private final String sTmlCod;             // 집하지 터미널코드
    private final String zipCod;              // 정제된 우편번호
    private final String tmlNam;              // 도착지 터미널명
    private final String tmlCod;              // 도착지 터미널코드
    private final String cenNam;              // 도착지 집배점명
    private final String cenCod;              // 도착지 집배점코드
    // 권역구분(1:수도권, 2:강원권역, 3:충청권역, 4:경남권역, 5:경북권역, 6:호남권역, 7:제주권역 (항공료발생), 9:도서지역 (도선료발생))
    private final String domRgn;
    private final String hubCod;              // 허브코드(대분류코드)
    private final String domMid;              // 중분류코드
    private final String grpRnk;              // 소분류코드(배송사원)
    private final String esNam;               // 배송사원명
    private final String esCod;               // 배송사원분류코드

    @Builder
    public HanjinInvoiceData(String sTmlNam, String sTmlCod, String zipCod, String tmlNam, String tmlCod, String cenNam, String cenCod, String domRgn, String hubCod, String domMid, String grpRnk, String esNam, String esCod) {
        this.sTmlNam = super.nullToEmptyString(sTmlNam);
        this.sTmlCod = super.nullToEmptyString(sTmlCod);
        this.zipCod = super.nullToEmptyString(zipCod);
        this.tmlNam = super.nullToEmptyString(tmlNam);
        this.tmlCod = super.nullToEmptyString(tmlCod);
        this.cenNam = super.nullToEmptyString(cenNam);
        this.cenCod = super.nullToEmptyString(cenCod);
        this.domRgn = super.nullToEmptyString(domRgn);
        this.hubCod = super.nullToEmptyString(hubCod);
        this.domMid = super.nullToEmptyString(domMid);
        this.grpRnk = super.nullToEmptyString(grpRnk);
        this.esNam = super.nullToEmptyString(esNam);
        this.esCod = super.nullToEmptyString(esCod);
    }

    @Override
    public String zplInvoiceCode(FmsInfo fmsInfo, DeliveryInfo deliveryInfo, Destination sender, Destination receiver, Memo memo, Box box) {
        return super.isIntegratedInvoice ?
                this.makeHanjinIntegratedInvoice(fmsInfo, deliveryInfo, sender, receiver, memo, box)
                : this.makeHanjinNsInvoice(fmsInfo, deliveryInfo, sender, receiver, memo, box);
    }

    private String makeHanjinIntegratedInvoice(FmsInfo fmsInfo, DeliveryInfo deliveryInfo, Destination sender, Destination receiver, Memo memo, Box box) {
        final ZebraZpl zpl = new ZebraZpl(10.3F, 12.7F, 203)
                .addText(770, 700, 25, true, super.getInvoiceNoWithHyphen(box.getInvoiceNo()))
                .addText(680, 60, 80, true, this.hubCod)
                .addText(680, 180, 80, true, this.tmlCod)
                .addBarcode128(650, 350, 3, 80, true, false, false, this.tmlCod)
                .addText(680, 600, 80, true, this.domMid)
                .addText(680, 680, 80, true, this.esCod)
                .addText(700, 820, 40, true, HANJIN)
                .addText(640, 70, 25, true, LocalDate.now().toString())
                .addText(640, 240, 40, true, this.tmlNam)
                .addText(620, 370, 25, true, this.cenCod)
                .addText(620, 430, 30, true, this.cenNam)
                .addText(630, 660, 25, true, this.sTmlCod)
                .addText(630, 710, 25, true, this.sTmlNam)
                .addText(580, 120, 20, true,
                        super.getReceiverWithMasking(
                                receiver.getName(),
                                receiver.getPhone().getPhoneBy(0),
                                receiver.getPhone().getPhoneBy(1)
                        ))
                .setLineBreakTextList(550, 80, 30, 25, 20, 3, receiver.getAddress().fullAddressWithoutHyphen(), true)
                .addBarcode128(500, 630, 3, 80, true, false, true, box.getInvoiceNo())
                .addText(480, 700, 20, true, super.getInvoiceNoWithHyphen(box.getInvoiceNo()))
                .addText(450, 760, 20, true, BOX_QTY)
                .addText(450, 900, 20, true, PAY_CON)
                .addText(450, 120, 20, true, super.getSenderWithMasking(sender.getName(), sender.getPhone().getPhoneBy(0), sender.getPhone().getPhoneBy(1)))
                .addText(410, 80, 25, true, sender.getAddress().fullAddressWithoutHyphen())
                .setGoodsNameTextList(360, 80, 30, 25, 35, 6, box.displayItemName(), true)
                .addGraphicBox(130, 500, 60, 190, 4)
                .addText(150, 340, 30, true, this.domRgn)
                .addText(320, 700, 70, true, this.grpRnk + this.esNam)
                .addText(80, 90, 25, true, INVOICE_STATIC_START_POINT + this.sTmlCod + this.sTmlNam)
                .setLineBreakTextList(100, 530, 30, 20, 22, 2, memo.getShipRequestMemo(), true)
                .addText(30, 400, 25, true, PAY_CON)
                .addText(30, 600, 30, true, INVOICE_STATIC_RETURN_CODE + this.sTmlCod + this.sTmlNam);

        this.addFieldAccordingToServiceLevel(zpl, deliveryInfo.getDeliveryType());
        final byte[] encodeZplBytes = Base64.encodeBase64(zpl.getZplCode().getBytes(StandardCharsets.UTF_8));
        return new String(encodeZplBytes);
    }

    // TODO: NS 송장 양식이 개발된 이후에 사용가능.
    private String makeHanjinNsInvoice(FmsInfo fmsInfo, DeliveryInfo deliveryInfo, Destination sender, Destination receiver, Memo memo, Box box) {
        return this.makeHanjinIntegratedInvoice(fmsInfo, deliveryInfo, sender, receiver, memo, box);
    }

    private void addFieldAccordingToServiceLevel(ZebraZpl zpl, SoloChainShippingServiceType serviceType) {
        if (serviceType == SoloChainShippingServiceType.HANJIN_GUARANTEE) {
            zpl.addText(650, 830, 50, true, HANJIN_N_ARRIVE_GUARANTEE);
            zpl.addText(70, 380, 50, true, HANJIN_N_ARRIVE_GUARANTEE);
        }
    }
}
