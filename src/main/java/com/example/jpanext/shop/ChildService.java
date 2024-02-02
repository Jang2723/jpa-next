package com.example.jpanext.shop;

import com.example.jpanext.shop.entity.Customer;
import com.example.jpanext.shop.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChildService {
    private final CustomerRepository customerRepository;

    // 나를 호출한 메서드가 트랜잭션 이면 그 일부로 실행되고,
    // 아리나면 트랜잭션 없이 실행된다.
    @Transactional(propagation = Propagation.SUPPORTS)
    public void supports() {
        customerRepository.save(Customer.builder()
                .name("Child Supports").build());
        throw new RuntimeException("child throw");
    }

    // 나를 호출한 메서드가 트랜잭션 이어야 한다.
    // 없으면 예외가 발생한다.
    @Transactional(propagation = Propagation.MANDATORY)
    public void mandatory() {
        customerRepository.save(Customer.builder()
                .name("Child Mandatory").build());
        throw new RuntimeException("child throw");
    }
}
