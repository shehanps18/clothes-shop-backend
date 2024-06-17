package org.example.service;

import org.example.dto.Cart;
import org.example.dto.ItemRequest;

public interface CartService {
    Cart addItem(ItemRequest itemRequest, String Username);
}
