package com.example.demo.backend;

import com.example.demo.ApiKey;
import com.example.demo.entities.OrderCalculator;
import com.example.demo.entities.PaymentBody;
import com.example.demo.entities.PaymentResponse;
import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.sun.deploy.net.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class PaymentController {

    @PostMapping("/api/payment")
    @ResponseBody
    public String startPay(@RequestHeader Map<String, String> headers,
                           @RequestParam(name = "currency") String currency,
                           @RequestParam(name = "name") String name) throws Exception{

        PaymentBody paymentBody = new PaymentBody(currency, name);

        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setCurrency(paymentBody.getCurrency())
                .setAmount(new Long(OrderCalculator.orderAmount()))
                .build();

        System.out.println(headers);

        PaymentIntent intent = PaymentIntent.create(createParams);
        PaymentResponse paymentResponse = new PaymentResponse(ApiKey.PUBLISHABLE_KEY, intent.getClientSecret(), intent.getId());
        return new Gson().toJson(paymentResponse);
    }


}


//        Stripe.apiKey = "sk_test_rdl2xfvV8YIBAEIWaNhvysqm009NbBmOXx";
//
//        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
//                .setCurrency("usd")
//                .setAmount(new Long(1099))
//                .build();
//
//        PaymentIntent intent = PaymentIntent.create(createParams);
//
//        return intent.getClientSecret();