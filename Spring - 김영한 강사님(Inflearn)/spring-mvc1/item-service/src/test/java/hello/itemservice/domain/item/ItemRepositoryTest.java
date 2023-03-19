package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item itemA = new Item("iteamA", 100000, 10);
        //when
        Item findItemById = itemRepository.findById(itemA.getId());
        //then
        Item findItem = itemRepository.findById(itemA.getId());
        Assertions.assertThat(findItem).isEqualTo(findItemById);
    }
}
