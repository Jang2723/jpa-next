package com.example.jpanext.shop;

import com.example.jpanext.shop.entity.Customer;
import com.example.jpanext.shop.entity.Item;
import com.example.jpanext.shop.entity.Order;
import com.example.jpanext.shop.entity.OrderItem;
import com.example.jpanext.shop.repo.CustomerRepository;
import com.example.jpanext.shop.repo.ItemRepository;
import com.example.jpanext.shop.repo.OrderItemRepository;
import com.example.jpanext.shop.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShopService {
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    // 나를 호출한 메서드가 트랜잭션이면 그 일부로 실행되고
    // 아니라면 내가 직접 트랜잭션을 만든다.
    @Transactional()
    public void createOrder() {
        // 고객 정보 회수
        Customer customer = customerRepository
                .findById(1L)
                .orElseThrow();
        // 고객의 새 주문 생성
        Order newOrder = orderRepository.save(Order.builder()
                .customer(customer)
                .build());
        // 구매할 물품 회수
        Item item  = itemRepository
                .findById(2L)
                .orElseThrow();
        // 주문정보 물품 추가
        orderItemRepository.save(OrderItem.builder()
                        .order(newOrder)
                        .item(item)
                        .count(10)
                        .build());
        if (!(item.getStock() < 10)) {
            item.setStock(item.getStock() - 10);
            itemRepository.save(item);
        }else throw new IllegalStateException();
    }

    @Transactional
    public void testIdentity() {
        Item item = Item.builder().build();
        Long id = itemRepository.save(item).getId();

        Item a = itemRepository.findById(id).get();
        Item b = itemRepository.findById(id).get();

        log.info("is same object: {}", a == b);
    }

    @Transactional
    public void testDirtyChecking() {
        itemRepository.findAll().stream()
                .forEach(item -> item.setStock(100));
    }

}
