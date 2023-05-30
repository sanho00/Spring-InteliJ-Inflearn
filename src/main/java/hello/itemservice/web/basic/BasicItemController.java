package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
//final이 붙은 객체의 생성자를 만들어줌
public class BasicItemController {

    private final ItemRepository itemRepository;

/*    @Autowired
    public BasicItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    */

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    //아이템 상세
    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    //상품 등록 폼 보여주기
    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    //상품 등록
    /*@PostMapping("/add")*/
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);
        //저장된 상품을 상세 화면에서 볼 수 있도록 item 값 넘겨줌

        return "basic/item";
    }

  /*  @PostMapping("/add")*/
    public String addItemV2(@ModelAttribute("item") Item item) {
        //ModelAttribute 에 지정해주는 name ("item")은 뷰(html)에서 지정한 name 과 같아야 함

        /*Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);*/

        itemRepository.save(item);

        /*model.addAttribute("item", item); @ModelAttribute 가 자동 추가 해주기 때문에 생략 가능
        * Model model 도 자동 추가 해주기 때문에 생략 가능*/
        //저장된 상품을 상세 화면에서 볼 수 있도록 item 값 넘겨줌

        return "basic/item";
    }

    /*@PostMapping("/add")*/
    public String addItemV3(@ModelAttribute Item item) {
        //@ModelAttribute 에 name 을 따로 지정하지 않으면 클래스명에서 첫 글자를 소문자로 바꾸어 이름 자동 지정

        itemRepository.save(item);
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV4(Item item) {
        //내가 만든 임의의 객체일 경우 @ModelAttribute 생략 가능

        itemRepository.save(item);
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV5(@ModelAttribute Item item) {

        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String addItemV6(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);     //저장된 결과 가져 오기
        redirectAttributes.addAttribute("itemId", savedItem.getId()); //저장된 ID 넣기
        redirectAttributes.addAttribute("status", true);    // 저장 해서 넘어온 것 true
        return "redirect:/basic/items/{itemId}";
        //redirectAttributes에 넣은 itemId와 {itemId}가 치환됨
        // ?status=true 치환된 나머지는 이렇게 쿼리파라미터 형식으로 들어감
    }

    // 상품 정보 수정 폼 조회
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    //상품 정보 수정 처리
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }


    //테스트용 데이터 추가
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

}
