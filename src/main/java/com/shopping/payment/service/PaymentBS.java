package com.shopping.payment.service;

import com.shopping.payment.model.Payment;
import com.shopping.payment.repository.PaymentRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentBS implements PaymentBSI{

    Logger logger = LoggerFactory.getLogger(PaymentBS.class);

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public int createPayment(Payment payment) throws Exception {

        if(payment != null && payment.getOrder() != null && payment.getOrder().getId() != null) {
            Payment newPayment = new Payment();
            newPayment.setOrder(payment.getOrder());
            newPayment.setPaymentStatus(payment.getPaymentStatus());
            newPayment.setPaymentMethod(payment.getPaymentMethod());
            newPayment.setUser(payment.getUser());
            newPayment.setTotalAmount(payment.getOrder().getOrderCost());
            paymentRepository.save(newPayment);
            logger.info("------------Payment Id : "+newPayment.getId());
            return 1;
        }
        return 0;
    }

    @Override
    public Payment getPaymentById(Long paymentId) throws Exception {
        if(paymentId != null) {
            Optional<Payment> opt = paymentRepository.findById(paymentId);
            if(opt.isPresent()){
                return opt.get();
            }
        }
        return null;
    }

    @Override
    public String getPaymentStatusByOrderId(Long orderId) throws Exception {
        if(orderId != null){

            List<Payment> payments = paymentRepository.findAll().stream()
                                    .filter(payment -> orderId.equals(payment.getOrder() != null ? payment.getOrder().getId() : null))
                                    .collect(Collectors.toList());

            if(payments != null && payments.size() > 0) {
                return payments.get(0).getPaymentStatus();
            }
        }
        return null;
    }

    @Override
    public List<Payment> getAllPaymentsForUser(Long userId) throws Exception {
        List<Payment> payments = null;
        if(userId != null){
            payments = paymentRepository.findAll().stream()
                    .filter(payment -> userId.equals(payment.getUser() != null ? payment.getUser().getId() : null))
                    .collect(Collectors.toList());

            if(payments != null && payments.size() > 0) {
                return payments;
            }
        }
        return null;
    }

    @Override
    public int removePaymentById(Long paymentId) throws Exception {
        if(paymentId != null) {
            paymentRepository.deleteById(paymentId);
            return 1;
        }
        return 0;
    }

    @Override
    public int removeAllPayment() throws Exception {
        paymentRepository.deleteAll();
        return 1;
    }
}
