package com.example.backend.service;

import com.example.backend.dto.CartDto;
import com.example.backend.dto.CartItemDto;
import com.example.backend.dto.CartListDto;
import com.example.backend.entity.Cart;
import com.example.backend.entity.Product;
import com.example.backend.entity.User;
import com.example.backend.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl {

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Autowired
    private CartRepository cartRepository;

    public void addToCart(CartDto cartDto, User user) {

        //validate if the product id is valid

        Product product = productServiceImpl.findProductById(cartDto.getProductId());

        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setUser(user);
        cart.setQuantity(cartDto.getQuantity());
        cart.setCreatedDate(new Date());

        //save the cart
        cartRepository.save(cart);
    }


    public CartListDto listAllCartItems(User user) {

        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);

        //change to cartDto
        List<CartItemDto> dtoCartList = new ArrayList<>();
        double totalCost = 0;

        for(Cart cart: cartList ){
            CartItemDto cartItemDto = new CartItemDto(cart);
            totalCost += cartItemDto.getQuantity() * cart.getProduct().getPrice();
            dtoCartList.add(cartItemDto);
        }

        CartListDto cartListDto = new CartListDto();
        cartListDto.setTotalCost(totalCost);
        cartListDto.setCartItems(dtoCartList);

        return cartListDto;

    }


    public void deleteFromCart(Integer cartItemId, User user) {
        //check the
        Optional<Cart> optionalId = cartRepository.findById(cartItemId);
        if(optionalId.isEmpty()){
            throw new RuntimeException("cart item id is invalid" + cartItemId)
;        }

        Cart cart = optionalId.get();

        if(cart.getUser() != user){
            throw new RuntimeException("cart item doesn't belong to user" + cartItemId );
        }
        cartRepository.delete(cart);

    }
}
