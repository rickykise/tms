package ai.fassto.tms.domain.parcel.application.vo;

import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class Item {
    private final String code;
    private final int qty;
    private String name;

    @Builder
    public Item(String code, int qty) {
        this.code = code;
        this.qty = qty;
    }

    public void itemNameSetting(String name) {
        this.name = name;
    }

    public boolean isItemName() {
        return StringUtils.isNotBlank(name);
    }
}
