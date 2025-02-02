package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable("itemId") long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
        // 리다이렉트로 Model을 다른 url에서도 사용되게끔 함.
    }

    // @PostMapping("/add") // 등록 기능
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam int quantity,
                       Model model) {
        Item item = new Item(itemName, price, quantity);
        itemRepository.save(item);

        model.addAttribute("item", item);
        // model에 item 저장
        return "basic/item";
    }

    // @PostMapping("/add") // 등록 기능
    public String addItemV2(@ModelAttribute("item") Item item, // item에 그대로 set
                       Model model) {
        itemRepository.save(item);
        // model.addAttribute("item", item); : 자동 추가, 생략 가능
        // model에 item 저장
        return "basic/item";
    }

    //@PostMapping("/add") // 등록 기능
    public String addItemV3(@ModelAttribute Item item // item에 그대로 set
                            ) {
        itemRepository.save(item);
        // model.addAttribute("item", item); : 자동 추가, 생략 가능
        // model에 item 저장
        return "basic/item";
    }

    // @PostMapping("/add") // 등록 기능
    public String addItemV4(Item item // 객체의 경우 ModelAttribute 설정 가능
    ) {
        itemRepository.save(item);
        // model.addAttribute("item", item); : 자동 추가, 생략 가능
        // model에 item 저장
        return "basic/item";
    }

    // @PostMapping("/add")
    public String addItemV5(Item item // 객체의 경우 ModelAttribute 설정 가능
    ) {
        itemRepository.save(item);
        // model.addAttribute("item", item); : 자동 추가, 생략 가능
        // model에 item 저장
        return "redirect:/basic/items/"+item.getId();
    }

    @PostMapping("/add")
    public String addItemV6(Item item, // 객체의 경우 ModelAttribute 설정 가능
    RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        // model.addAttribute("item", item); : 자동 추가, 생략 가능
        // model에 item 저장
        redirectAttributes.addAttribute("itemId",savedItem.getId());
        redirectAttributes.addAttribute("status",true);
        // 리다이렉트시 추가할 속성들
        return "redirect:/basic/items/{itemId}"; // itemId 적용
    }

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
