//package com.example.backend.controller;
//
//import com.example.backend.dto.CheckoutDto;
//import com.example.backend.dto.StripeResponse;
//import com.example.backend.repository.AuthRepository;
//import com.example.backend.service.AuthServiceImpl;
//import com.example.backend.service.OrderServiceImpl;
//import com.stripe.exception.StripeException;
//import com.stripe.model.billingportal.Session;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/order")
//public class OrderController {
//
//    @Autowired
//    private AuthServiceImpl authServiceImpl;
//
//    @Autowired
//    private OrderServiceImpl orderServiceImpl;
//
//    //stripe session: checkout api
//    @PostMapping("/create-checkout-session")
//    public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckoutDto> checkoutDto) throws StripeException {
//        Session session = orderServiceImpl.createSession(checkoutDto);
//        StripeResponse stripeRespone = new StripeResponse(session.getId());
//
//        return new ResponseEntity<>(stripeRespone, HttpStatus.OK);
//    }
//
//}
