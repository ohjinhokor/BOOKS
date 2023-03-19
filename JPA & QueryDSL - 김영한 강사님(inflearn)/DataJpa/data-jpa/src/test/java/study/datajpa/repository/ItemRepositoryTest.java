package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.datajpa.entity.Item;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void save(){

        // 이런식으로 id에 값을 넣어주면 persist가 아니라 merge가 호출됨을 알 수 있음
        // merge는 잘 사용하지 않음. 결국 생각해야할건 왠만하면 Dirty Checking으로 데이터를 변경하자는 것!
        Item item = new Item(1L);
        itemRepository.save(item);

    }
}