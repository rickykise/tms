package ai.fassto.tms.domain.parcel.application.vo;

import ai.fassto.tms.domain.common.vo.enums.TmsResultType;
import ai.fassto.tms.domain.parcel.core.exception.InvoiceNotCollectException;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Builder
public record Boxes(List<Box> boxes) {

    public boolean isInvoiceInFirstBox() {
        return this.boxes.get(0).isInvoice();
    }

    public void collectInvoiceInFirstBox(String invoiceNo) {
        this.boxes.get(0).collectInvoiceNo(invoiceNo);
    }

    public int boxSize() {
        return this.boxes.size();
    }

    public String preCallDisplayProductName() {
        return this.boxes.get(0).displayItemName();
    }

    public String getInvoiceNoBy(int boxIndex) {
        return boxes.get(boxIndex).getInvoiceNo();
    }

    public String getHanjinBoxType(int boxIndex) {
        return boxes.get(boxIndex).hanjinBoxType();
    }

    public String getCjBoxTypeBy(int boxIndex) {
        return boxes.get(boxIndex).getCjBoxType();
    }

    public List<Box> boxesNotHaveInvoice() {
        return this.boxes
                .stream()
                .filter(box -> !box.isInvoice())
                .collect(Collectors.toList());
    }

    public String fistBoxInvoiceMarkItemName() {
        return this.boxes.get(0).invoiceMarkItemName();
    }

    public String firstBoxDisplayItemName() {
        return this.boxes.get(0).displayItemName();
    }

    public int totalItemQty() {
        return this.boxes.stream()
                .mapToInt(Box::totalItemQty)
                .sum();
    }

    public void distributeBoxInvoice() {
        this.boxes.stream()
                .filter(box -> !box.isInvoice())
                .forEach(box -> box.collectInvoiceNo(this.invoiceFindFirst()));
    }

    private String invoiceFindFirst() {
        return this.boxes.stream()
                .filter(Box::isInvoice)
                .findFirst()
                .orElseThrow(() -> new InvoiceNotCollectException(TmsResultType.LOGISTICS_INVOICE_NOT_COLLECT, "이크레모스 주문건은 적어도 한개의 송장번호를 가지고있어야합니다."))
                .getInvoiceNo();
    }

    public Stream<Box> stream() {
        return this.boxes.stream();
    }

    public void insertFare(Fare fare) {
        this.boxes
                .forEach(box -> box.insertBoxFare(fare));
    }
}
