package com.example.demo;

import com.example.demo.client.controller.AndroidPaymentController;
import com.example.demo.entities.OrderCalculator;
import com.example.demo.entities.PaymentBody;
import com.example.demo.entities.PaymentResponse;
import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static spark.Spark.*;

@SpringBootApplication
public class StripeApplication {

	public static final AndroidPaymentController mAndroidDevice = new AndroidPaymentController();

	public static void main(String[] args) {

		Stripe.apiKey = ApiKey.SECRET_KEY;

		ConfigurableApplicationContext context = SpringApplication.run(StripeApplication.class, args);

		mAndroidDevice.requestPaymentIntent("USD", "Item", new AndroidPaymentController.Listener() {
			@Override
			public void onClientKeysReturned(String key) {
				mAndroidDevice.processPayment(key);
			}
		});


	}
}
