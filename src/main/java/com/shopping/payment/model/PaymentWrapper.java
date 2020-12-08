package com.shopping.payment.model;

import java.util.List;

public class PaymentWrapper {

    private List<Payment> paymentList;

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }
}
