package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository     // Component Scan 의 대상이 됨
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>();   //static 사용함
    //지금 같은 멀티 쓰레드 환경에서는 HashMap 사용 X
    //쓸 거면 ConcurrentHashMap 사용하기
    private static Long sequence = 0L;      // static
    //실무에서는 Long으로 하면 동시에 접속할 시 값이 꼬일 수 있으니 사용 X

    //아이템 저장
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    //아이템 조회
    public Item findById(Long id) {
        return store.get(id);
    }

    //전체 목록 조회
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    //item의 id와 item의 파라미터를 넣으면 수정 되도록 함
    //원래는 itemName, price, quantity만 빼내서 DTO 클래스를 만드는 게 나음
    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
