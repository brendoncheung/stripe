package com.example.demo.entities;

import com.google.gson.annotations.SerializedName;

public class PaymentBody {

    @SerializedName("currency")
    String mCurrency;

    @SerializedName("name")
    String mName;

    public PaymentBody(String currency, String name) {
        mCurrency = currency;
        mName = name;
    }

    @Override
    public String toString() {
        return "PaymentBody{" +
                "mCurrency='" + mCurrency + '\'' +
                ", mName='" + mName + '\'' +
                '}';
    }

    public String getCurrency() {
        return mCurrency;
    }

    public String getName() {
        return mName;
    }
}




