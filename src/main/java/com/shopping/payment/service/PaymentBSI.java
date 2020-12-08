package com.shopping.payment.service;

import com.shopping.payment.model.Payment;

import java.util.List;

public interface PaymentBSI {

    public int createPayment(Payment payment) throws Exception;

    public Payment getPaymentById(Long paymentId) throws Exception;

    public String getPaymentStatusByOrderId(Long orderId) throws Exception;

    public List<Payment> getAllPaymentsForUser(Long userId) throws Exception;

    public int removePaymentById(Long paymentId) throws Exception;

    public int removeAllPayment() throws Exception;
}
