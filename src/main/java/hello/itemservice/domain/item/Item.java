package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data  모든게 다 들어가기 때문에 쓰면 위험할 수 있음, 필요한 것만 골라서 쓰기
@Getter
@Setter
public class Item {

    private Long id;
    private String itemName;
    private Integer price;      // null 값이 들어갈 수도 있으므로 Integer, int로 하면 값 필수
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
