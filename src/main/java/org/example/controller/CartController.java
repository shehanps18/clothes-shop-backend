package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.Cart;
import org.example.dto.ItemRequest;
import org.example.entity.CartEntity;
import org.example.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartController {
    final CartService cartService;
//    @PostMapping("/add")
//    public ResponseEntity<Cart> addItemToCart(@RequestBody ItemRequest itemRequest, Principal principal) {
//        if (principal == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//        Cart cart = cartService.addItem(itemRequest, principal.getName());
//        return ResponseEntity.ok(cart);
//    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addCart(@RequestBody ItemRequest itemRequest, Principal principal){
        String email= principal.getName();
        System.out.print(email);
        Cart addItem = this.cartService.addItem(itemRequest, principal.getName());
        return new ResponseEntity<Cart>(addItem, HttpStatus.OK);
    }
//      create methode for getting cart
    @GetMapping("/all")
    public ResponseEntity<Cart> getAllCart(Principal principal){
        Cart getCartAll = this.cartService.getCartAll(principal.getName());
        return new ResponseEntity<Cart>(getCartAll,HttpStatus.OK);
    }
}
