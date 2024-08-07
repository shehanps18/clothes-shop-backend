package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.Cart;
import org.example.dto.ItemRequest;
import org.example.entity.CartEntity;
import org.example.entity.CartItemEntity;
import org.example.entity.ProductEntity;
import org.example.entity.UserEntity;
import org.example.exceptions.UserNotFoundException;
import org.example.repository.CartRepository;
import org.example.repository.ProductRepository;
import org.example.repository.UserRepository;
import org.example.service.CartService;
import org.modelmapper.ModelMapper;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    final UserRepository userRepository;
    final ProductRepository productRepository;
    final CartRepository cartRepository;
    final ModelMapper mapper;
    private final ModelMapper modelMapper;

    @Override
    public Cart addItem(ItemRequest itemRequest, String Username) {
        int productId = itemRequest.getProductId();
        int quantity = itemRequest.getQuantity();

//        fetch User
        UserEntity user = this.userRepository.findByemail(Username)
                .orElseThrow(() -> new UserNotFoundException("User name not found"));
//        fetch product
        ProductEntity productEntity = this.productRepository.findById((long) productId)
                .orElseThrow(() -> new UserNotFoundException("Product Not found"));
//checking product stock
        if(!productEntity.isStock()){
            throw new UserNotFoundException("Product out of stock");
        }
//        create cart item with productId and quantity
        CartItemEntity cartItem = new CartItemEntity();
        cartItem.setProduct(productEntity);
        cartItem.setQuantity(quantity);
        double totalePrice = productEntity.getProduct_prise() * productEntity.getProduct_qty();
        cartItem.setTotalPrice(totalePrice);

//        getting cart from user
        CartEntity cart = user.getCart();
        if (cart == null) {
            cart = new CartEntity();
            cart.setUser(user); // Ensure the Cart is associated with the User
            user.setCart(cart); // Ensure the User is associated with the Cart
        }

        cartItem.setCart(cart); // Set the Cart in CartItem
        Set<CartItemEntity> items = cart.getItems();
        /*if(cart==null){
             cart = new CartEntity();
        }
        cartItem.setCart(cart);
        Set<CartItemEntity> items = cart.getItems();*/

//        checking item is available in cartItem
//        if cart item available increase qty else
//        add new product in cartitem
        AtomicReference<Boolean> flag = new AtomicReference<>(false);
        Set<CartItemEntity> newProduct = items.stream().peek((i) -> {
            if (Objects.equals(i.getProduct().getId(), productEntity.getId())) {
                i.setQuantity(quantity);
                i.setTotalPrice(totalePrice);
                flag.set(true);

            }
        }).collect(Collectors.toSet());

        if(flag.get()){
            items.clear();
            items.addAll(newProduct);

        }else {
            cartItem.setCart(cart);
            items.add(cartItem);
        }
        cart.setItems(items);
        CartEntity cart1 = this.cartRepository.save(cart);
        return this.mapper.map(cart1,Cart.class);
    }
//    create methode for getting cart

    public Cart getCartAll(String email){
//        find user
        UserEntity user = this.userRepository.findByemail(email).orElseThrow(
                () -> new OpenApiResourceNotFoundException("User not found"));
//        find cart
        CartEntity cart = this.cartRepository.findByUser(user).orElseThrow(
                () -> new OpenApiResourceNotFoundException("cart not found"));
        return this.modelMapper.map(cart,Cart.class);
    }
}
