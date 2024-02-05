package com.example.jpanext;

import com.example.jpanext.shop.ShopService;
import com.example.jpanext.shop.entity.Item;
import com.example.jpanext.shop.repo.ItemRepository;
import jakarta.persistence.OptimisticLockException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class JpaNextApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private ShopService shopService;

	@Autowired
	private ItemRepository itemRepository;

	@Test
	public void optimisticLock() throws InterruptedException {
		// given
		itemRepository.save(Item.builder()
				.stock(25)
				.build());

		// when
		// 몇번의 동시 요청이 있을것인지
		int threads = 5;
		// 멀티쓰레드를 실행하기 위한 실행자
		ExecutorService executorService
				= Executors.newFixedThreadPool(threads);
		// 결과를 담기위한 리스트
		List<Future<?>> futures = new ArrayList<>();
		for (int i = 0; i < threads; i++) {
			// 여러개의 요청을 보낼 준비
			futures.add(executorService.submit(
					() -> shopService.decreaseStockOpt()
			));
		}

		// then
		Exception result = new Exception();
		try {
			for (Future<?> future: futures)
				future.get();
		} catch (ExecutionException e) {
			result = (Exception) e.getCause();
		}

		System.out.println(result.getClass());
		assertTrue(result instanceof OptimisticLockingFailureException);
		Item item = itemRepository.findById(1L).get();
		assertEquals(15, item.getStock());
	}

	@Test
	public void pessimisticLock() throws InterruptedException {
		// given
		itemRepository.save(Item.builder()
				.stock(55)
				.build());

		// when
		// 몇번의 동시 요청이 있을것인지
		int threads = 5;
		// 멀티쓰레드를 실행하기 위한 실행자
		ExecutorService executorService
				= Executors.newFixedThreadPool(threads);
		// 결과를 담기위한 리스트
		List<Future<?>> futures = new ArrayList<>();
		for (int i = 0; i < threads; i++) {
			// 여러개의 요청을 보낼 준비
			futures.add(executorService.submit(
					() -> shopService.decreaseStockUpdate()
			));
		}

		// then
		Exception result = new Exception();
		try {
			for (Future<?> future: futures)
				future.get();
		} catch (ExecutionException e) {
			result = (Exception) e.getCause();
		}

//		System.out.println(result.getClass());
//		assertTrue(result instanceof OptimisticLockingFailureException);
		Item item = itemRepository.findById(1L).get();
		assertEquals(5, item.getStock());
	}
}