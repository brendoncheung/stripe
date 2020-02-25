package com.example.demo.entities;

import com.google.gson.annotations.SerializedName;

public class PaymentResponse {

    @SerializedName("publishablekey")
    private String mPublicKey;
    @SerializedName("clientSecret")
    private String mClientSecret;
    private String mId;

    public PaymentResponse(String publicKey, String clientSecret, String id) {
        mPublicKey = publicKey;
        mClientSecret = clientSecret;
        mId = id;
    }

    public String getPublicKey() {
        return mPublicKey;
    }

    public String getClientSecret() {
        return mClientSecret;
    }

    public String getId() {
        return mId;
    }
}
