package com.todolist.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.todolist.entities.Item;
import com.todolist.repositories.ItemRepository;

@RunWith(SpringJUnit4ClassRunner.class)
class ItemServiceTest {
	
	private static long curItemIndex = 1;
	
	@Mock
	ItemRepository itemRepository;
	
	public ItemServiceTest() {
		
		Map<Long, Item> itemsById = new HashMap<>();

		MockitoAnnotations.initMocks(this);
		
		Mockito.doAnswer(new Answer() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				
				Item item = (Item) invocation.getArgument(0);

				
				if (item.getId() == null) {
					item.setId(curItemIndex++);
				}
				itemsById.put(item.getId(), item);
				
				return item;
			}
		}).when(itemRepository).save(Mockito.any(Item.class));
		
		Mockito.doAnswer(new Answer() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				
				Long id = (Long) invocation.getArgument(0);
				
				return Optional.of(itemsById.get(id));
			}
		}).when(itemRepository).findById(Mockito.any(Long.class));
	}
	
	@Test
	void whenGetItem_thenReturnItem() {

		ItemService itemService = new ItemService(itemRepository);
		
		Item item = new Item("to do", false);
		item = itemService.saveItem(item);
		
		Item savedItem = itemService.getItem(item.getId());
		
		assertThat(savedItem.getValue()).isEqualTo(item.getValue());
	}

}
