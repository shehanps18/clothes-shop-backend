package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.Cart;
import org.example.dto.ItemRequest;
import org.example.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("cart")
public class CartController {
    final CartService cartService;
    @PostMapping("add-cart")
    public ResponseEntity<Cart> addCart(@RequestBody ItemRequest itemRequest, Principal principal){
        Cart addItem = this.cartService.addItem(itemRequest, principal.getName());
        return new ResponseEntity<Cart>(addItem, HttpStatus.OK);
    }
}
