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

@Slf4j
@Service
@RequiredArgsConstructor
public class ShopService {
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

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
}
