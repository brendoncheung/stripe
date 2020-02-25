package com.example.demo.client.controller;

import com.example.demo.ApiKey;
import com.example.demo.entities.OrderCalculator;
import com.example.demo.entities.PaymentBody;
import com.example.demo.entities.PaymentResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;

import static spark.Spark.post;


public class AndroidPaymentController {

    public interface Listener {
        void onClientKeysReturned(String key);
    }

    private final OkHttpClient mHttpClient = new OkHttpClient();

    public void requestPaymentIntent(String currency, String name, Listener listener) {

        // this android device make a call to the server

        MediaType mediaType = MediaType.get("application/x-www-form-urlencoded");
        String postBody = "currency=" + currency + "&" + "name=" + name;

        RequestBody requestBody = RequestBody.create(postBody, mediaType);

        Request request = new Request.Builder()
                .url("http://localhost:8080/api/payment")
                .post(requestBody)
                .build();

        mHttpClient.newCall(request)
                .enqueue(new Callback() {

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        Gson gson = new Gson();
                        Type type = new TypeToken<Map<String, String>>(){}.getType();
                        PaymentResponse responseMap = gson.fromJson(
                                Objects.requireNonNull(response.body()).string(),
                                PaymentResponse.class);

                        listener.onClientKeysReturned(responseMap.getClientSecret());
                    }

                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }
                });
    }

    public void processPayment(String clientSecret) {



    }

}




//
//        post("/testing", (request, response) -> {
//            PaymentBody paymentBody = new Gson().fromJson(request.body(), PaymentBody.class);
//
//            PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
//                    .setCurrency(paymentBody.getCurrency())
//                    .setAmount(new Long(OrderCalculator.orderAmount()))
//                    .build();
//
//            PaymentIntent intent = PaymentIntent.create(createParams);
//
//            PaymentResponse paymentResponse = new PaymentResponse(ApiKey.PUBLISHABLE_KEY, intent.getClientSecret(), intent.getId());
