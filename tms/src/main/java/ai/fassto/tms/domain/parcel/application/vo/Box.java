package ai.fassto.tms.domain.parcel.application.vo;

import ai.fassto.tms.domain.common.vo.consts.CjBoxTypeConstant;
import ai.fassto.tms.domain.common.vo.consts.HanjinBoxTypeConst;
import ai.fassto.tms.domain.common.vo.consts.SoloChainBoxCategoryConstant;
import lombok.Builder;
import lombok.Getter;
import lombok.val;
import org.apache.commons.lang3.StringUtils;

@Getter
public class Box {
    private final String boxID;
    private final String fasstoBoxType;
    private final String fasstoBoxCategory;
    private final float boxWidth;
    private final float boxHeight;
    private final float boxDepth;
    private final int boxWeight;
    private final int productQty;
    private final Items items;
    private Fare fare = new Fare(0, 0, 0, 0, 0);
    private String invoiceNo;

    @Builder
    public Box(String boxID, String invoiceNo, String fasstoBoxType, String fasstoBoxCategory, float boxWidth, float boxHeight, float boxDepth, int boxWeight, int productQty, Items items) {
        this.boxID = boxID;
        this.invoiceNo = invoiceNo;
        this.fasstoBoxType = fasstoBoxType;
        this.fasstoBoxCategory = fasstoBoxCategory;
        this.boxWidth = boxWidth;
        this.boxHeight = boxHeight;
        this.boxDepth = boxDepth;
        this.boxWeight = boxWeight;
        this.productQty = productQty;
        this.items = items;
    }

    public void collectInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public boolean isInvoice() {
        return StringUtils.isNotBlank(this.invoiceNo);
    }

    public String displayItemName() {
        return this.items.displayItemName();
    }

    public String invoiceMarkItemName() {
        return this.items.invoiceMarkItemName();
    }

    public int totalItemQty() {
        return this.items.totalQty();
    }

    public void insertBoxFare(Fare fare) {
        this.fare = fare;
    }

    /**
     * hanjin 박스타입 추출 함수
     */
    public String hanjinBoxType() {
        val sumBoxSize = this.boxHeight + this.boxDepth + this.boxWidth;

        if (sumBoxSize <= 80) {
            if(SoloChainBoxCategoryConstant.FASSTO_POLY_BAG.equalsIgnoreCase(this.fasstoBoxCategory)
                || SoloChainBoxCategoryConstant.CUSTOMER_POLY_BAG.equalsIgnoreCase(this.fasstoBoxCategory)
                ){
                // 파스토폴리백 또는 고객사 폴리백인 경우 (FMS1.5의 경우 완박스 개념이 없으므로 조건은 2개만)
                return HanjinBoxTypeConst.BOX_TYPE_S;   // 폴리백인 경우 S로 리턴
            }
            return HanjinBoxTypeConst.BOX_TYPE_A;
        } else if (sumBoxSize <= 100) {
            return HanjinBoxTypeConst.BOX_TYPE_B;
        } else if (sumBoxSize <= 120) {
            return HanjinBoxTypeConst.BOX_TYPE_C;
        } else if (sumBoxSize <= 160) {
            return HanjinBoxTypeConst.BOX_TYPE_D;
        }
        return HanjinBoxTypeConst.BOX_TYPE_E;
    }

    public String getCjBoxType() {
        final int sumBoxSize = (int) Math.ceil(boxHeight + boxDepth + boxWidth);

        if (sumBoxSize <= 80) {
            return CjBoxTypeConstant.A;
        } else if (sumBoxSize <= 100) {
            return CjBoxTypeConstant.B;
        } else if (sumBoxSize <= 120) {
            return CjBoxTypeConstant.C;
        } else if (sumBoxSize <= 140) {
            return CjBoxTypeConstant.D1;
        } else {
            if (SoloChainBoxCategoryConstant.FASSTO_STYROFOAM.equalsIgnoreCase(fasstoBoxCategory)
                    || SoloChainBoxCategoryConstant.CUSTOMER_STYROFOAM.equalsIgnoreCase(fasstoBoxCategory)) {
                return CjBoxTypeConstant.D2;
            } else {
                if (sumBoxSize <= 160) {
                    return CjBoxTypeConstant.D2;
                } else {
                    return CjBoxTypeConstant.E;
                }
            }
        }
    }
}