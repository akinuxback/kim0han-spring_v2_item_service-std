package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }
    
    @Test
    public void save() throws Exception{
        
        //given
        Item item = new Item("JPA BOOK", 10000, 10);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(savedItem.getId());
        assertThat(savedItem).isEqualTo(findItem);

    }

    @Test
    public void findAll() throws Exception{

        //given
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);
        Item item3 = new Item("item3", 30000, 30);

        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);

        //when
        List<Item> list = itemRepository.findAll();

        //then
        assertThat(list.size()).isEqualTo(3);
        assertThat(list).contains(item1, item2, item3);

    }

    @Test
    public void updateItem() throws Exception{

        //given
        Item item1 = new Item("item1", 10000, 10);
        Item savedItem = itemRepository.save(item1);
        Long id = savedItem.getId();

        //when
        Item updateParam = new Item("modItem", 1000000, 100);
        itemRepository.update(id, updateParam);

        //then
        Item findItem = itemRepository.findById(id);
        
        assertThat(id).isEqualTo(findItem.getId());
        assertThat(updateParam.getItemName()).isEqualTo(findItem.getItemName());
        assertThat(updateParam.getPrice()).isEqualTo(findItem.getPrice());
        assertThat(updateParam.getQuantity()).isEqualTo(findItem.getQuantity());

    }

}