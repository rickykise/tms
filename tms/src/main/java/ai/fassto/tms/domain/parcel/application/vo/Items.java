package ai.fassto.tms.domain.parcel.application.vo;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Items {
    private final List<Item> items;

    public Items(List<Item> items) {
        this.items = items;
    }

    public List<Item> itemsNotHaveName() {
        return this.items
                .stream()
                .filter(item -> !item.isItemName())
                .collect(Collectors.toList());
    }

    public String invoiceMarkItemName() {
        if (this.items.size() == 1) {
            return this.items.get(0).getName();
        }

        return this.items.get(0).getName() + "외 " + Integer.toString(this.items.size() - 1) + "건";
    }

    public String displayItemName() {
        StringBuilder displayItemName = new StringBuilder("총 " + this.items.size() + "건");

        for (Item item : this.items) {
            displayItemName.append("|").append(item.getName()).append("[").append(item.getQty()).append("EA]");
        }

        return displayItemName.length() > 400 ? displayItemName.substring(0, 400) : displayItemName.toString();
    }

    public int totalQty() {
        return this.items
                .stream()
                .mapToInt(Item::getQty)
                .sum();
    }
}
