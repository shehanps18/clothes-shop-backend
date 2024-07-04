package org.example.service;

import org.example.dto.Cart;
import org.example.dto.ItemRequest;
import org.example.entity.CartEntity;

public interface CartService {
    Cart addItem(ItemRequest itemRequest, String Username);
    Cart getCartAll(String email);
}
