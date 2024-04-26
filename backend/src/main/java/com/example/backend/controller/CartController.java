package com.example.backend.controller;

import com.example.backend.dto.CartDto;
import com.example.backend.dto.CartListDto;
import com.example.backend.entity.User;
import com.example.backend.service.AuthServiceImpl;
import com.example.backend.service.CartServiceImpl;
import com.example.backend.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartServiceImpl cartServiceImpl;

   @Autowired
   private AuthServiceImpl authServiceImpl;

   @Autowired
   private ProductServiceImpl productServiceImpl;

    //3 apis

    //post : add to cart api
    //RequestBody: cartDto
    //RequestParam: to check which user, adding the cart item
    @PostMapping("/add")
    public ResponseEntity<String> addCart(@RequestBody CartDto cartDto, @RequestParam("token") String token){
        //authenticate Token
        authServiceImpl.authenticate(token);

        //find the User
        User user = authServiceImpl.getUser(token);

        //get the Product: can be done in cartService too
        //add to Cart
       cartServiceImpl.addToCart(cartDto, user);
        return ResponseEntity.ok("item added to cart");

    }

    //get all cart items for a user
    @GetMapping("/all")
    public ResponseEntity<CartListDto> getAllCartItems(@RequestParam("token") String token){

        //authenticate the token
        authServiceImpl.authenticate(token);

        //get the User
        User user = authServiceImpl.getUser(token);

        //get cart items
        CartListDto cartListDto = cartServiceImpl.listAllCartItems(user);

        return ResponseEntity.ok(cartListDto);

    }

    //delete a cart item for a user
    @DeleteMapping("/delete/{carItemId}")
    public ResponseEntity<String> deleteCartItem(@PathVariable("cartItemId") Integer itemId, @RequestParam("token") String token){

        //authenticate the token
        authServiceImpl.authenticate(token);

        //get the User
        User user = authServiceImpl.getUser(token);

        //get cart items
         cartServiceImpl.deleteFromCart(itemId, user);

        return ResponseEntity.ok("item has been deleted");

    }


}
