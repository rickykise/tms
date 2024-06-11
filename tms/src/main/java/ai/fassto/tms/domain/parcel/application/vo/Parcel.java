package ai.fassto.tms.domain.parcel.application.vo;

import ai.fassto.tms.domain.parcel.application.vo.invoice.InvoiceData;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Parcel {
    private final FmsInfo fmsInfo;
    private final DeliveryInfo deliveryInfo;
    private final Destination sender;
    private final Destination receiver;
    private final Memo memo;
    private final Boxes boxes;
    private InvoiceData invoiceData;

    @Builder
    public Parcel(FmsInfo fmsInfo, DeliveryInfo deliveryInfo, Destination sender, Destination receiver, Memo memo, Boxes boxes) {
        this.fmsInfo = fmsInfo;
        this.deliveryInfo = deliveryInfo;
        this.sender = sender;
        this.receiver = receiver;
        this.memo = memo;
        this.boxes = boxes;
    }

    public boolean isPreCallInvoice() {
        return this.boxes.isInvoiceInFirstBox();
    }

    public void collectPreCallInvoice(String invoiceNo) {
        this.boxes.collectInvoiceInFirstBox(invoiceNo);
    }

    public String getPreCallInvoice() {
        return boxes.getInvoiceNoBy(0);
    }

    public String getEcremmoceInvoice() {
        return boxes.getInvoiceNoBy(0);
    }

    public String customerUseNoBy(int boxIndex) {
        return this.fmsInfo.warehouseCode() + this.fmsInfo.customerCode() + this.boxes.getInvoiceNoBy(boxIndex);
    }

    public String customerUseNoBy(Box box) {
        return this.fmsInfo.warehouseCode() + this.fmsInfo.customerCode() + box.getInvoiceNo();
    }

    public String getHanjinBoxTypeBy(int boxIndex) {
        return this.boxes.getHanjinBoxType(boxIndex);
    }

    public void updateContractCode(String contractCode) {
        this.deliveryInfo.updateContractCode(contractCode);
    }

    public void receiveInvoiceData(InvoiceData invoiceData) {
        if (this.invoiceData == null) {
            this.invoiceData = invoiceData;
        }
    }

    public String zplInvoiceCode(Box box) {
        return this.invoiceData.zplInvoiceCode(this.fmsInfo, this.deliveryInfo, this.sender, this.receiver, this.memo, box);
    }

    public String documentName() {
        return this.invoiceData.documentName(this.deliveryInfo.getParcelCompanyType());
    }

    public void distributeEcremmoceInvoice() {
        this.boxes.distributeBoxInvoice();
    }

    public void senderAddressConvertToCheckedAddress(Address address) {
        this.sender.convertAddressToCheckedAddress(address);
    }

    public void receiverAddressConvertToCheckedAddress(Address address) {
        this.receiver.convertAddressToCheckedAddress(address);
    }

    public void insertFare(Fare fare) {
        this.boxes.insertFare(fare);
    }

    public boolean isHanjinGuaranteeOrder() {
        return this.deliveryInfo.isHanjinGuaranteeOrder();
    }
}
