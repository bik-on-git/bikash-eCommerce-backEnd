//package com.example.backend.service;
//
//import com.example.backend.dto.CheckoutDto;
//import com.stripe.Stripe;
//import com.stripe.exception.StripeException;
//import com.stripe.model.billingportal.Session;
//import com.stripe.param.checkout.SessionCreateParams;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class OrderServiceImpl {
//
//    @Value("${BASE_URL}")
//    private String baseUrl;
//
//    @Value("${STRIPE_SECRET_KEY}")
//    private String apiKey;
//
//    //2 api: success/fail
//    public Session createSession(List<CheckoutDto> checkoutDto) throws StripeException {
//
//        String successURL = baseUrl + "payment/success";
//        String failURL = baseUrl + "payment/failed";
//
//        Stripe.apiKey = apiKey;
//
//        List<SessionCreateParams.LineItem> sessionItemList = new ArrayList<>();
//
//        for(CheckoutDto checkoutItemDto: checkoutDto){
//            sessionItemList.add(createSessionLineItem(checkoutItemDto));
//        }
//
//        SessionCreateParams params = SessionCreateParams.builder()
//                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
//                .setMode(SessionCreateParams.Mode.PAYMENT)
//                .setCancelUrl(failURL)
//                .setSuccessUrl(successURL)
//                .addAllLineItem(sessionItemList)
//                .build();
//
//        return Session.create((Map<String, Object>) params);
//
//    }
//
//    private SessionCreateParams.LineItem createSessionLineItem(CheckoutDto checkoutItemDto) {
//        return SessionCreateParams.LineItem.builder()
//                .setPriceData(createPriceData(checkoutItemDto))
//                .setQuantity(Long.parseLong(String.valueOf(checkoutItemDto.getQuantity())))
//                .build();
//    }
//
//    private SessionCreateParams.LineItem.PriceData createPriceData(CheckoutDto checkoutItemDto) {
//        return SessionCreateParams.LineItem.PriceData.builder()
//                .setCurrency("usd")
//                .setUnitAmount((long)checkoutItemDto.getPrice() * 100)
//                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
//                        .setName(checkoutItemDto.getProductName())
//                        .build())
//                .build();
//    }
//
//
//}
