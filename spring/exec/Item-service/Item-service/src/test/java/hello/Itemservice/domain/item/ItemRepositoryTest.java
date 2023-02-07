package hello.Itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //g
        Item item = new Item("itemA", 10000, 10);
        //w
        Item saveItem = itemRepository.save(item);

        //t
        Item findItem = itemRepository.findById(item.getId());
        Assertions.assertThat(findItem).isEqualTo(saveItem);

    }

    @Test
    void findAll() {
        //g
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 20000, 101);
        itemRepository.save(item1);
        itemRepository.save(item2);
        //w
        List<Item> result = itemRepository.findAll();
        //t
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result).contains(item1, item2);
    }

    @Test
    void updateItem() {
        //g
        Item item1 = new Item("itemA", 10000, 10);
        Item saveItem = itemRepository.save(item1);
        Long itemId = saveItem.getId();
        //w
        Item updateitem = new Item("item2", 20000, 30);
        itemRepository.update(itemId, updateitem);

        //t
        Item findItem = itemRepository.findById(itemId);
        Assertions.assertThat(findItem.getItemName()).isEqualTo(updateitem.getItemName());
        Assertions.assertThat(findItem.getPrice()).isEqualTo(updateitem.getPrice());
        Assertions.assertThat(findItem.getQuantity()).isEqualTo(updateitem.getQuantity());
    }

}