package com.example.jpanext.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shop")
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;
    private final ParentService parentService;
    private final EntityManagerService emService;

    @GetMapping("create-order")
    public String createOrder() {
        shopService.createOrder();
        return "done";
    }

    @GetMapping("propagation")
    public void propagation() {
        parentService.none();
    }

    @GetMapping("identity")
    public void identity(){
        shopService.testIdentity();
    }

    @GetMapping("dirty-check")
    public void dirtyCheck() {
        shopService.testDirtyChecking();
    }

    @GetMapping("test-em")
    public void testEm() {
        emService.save();
        emService.find();
    }
}
