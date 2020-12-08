package com.shopping.payment.api;

import com.shopping.payment.model.Payment;
import com.shopping.payment.model.PaymentWrapper;
import com.shopping.payment.service.PaymentBS;
import com.shopping.payment.service.PaymentBSI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentRestController {

    Logger logger = LoggerFactory.getLogger(PaymentRestController.class);

    @Autowired
    private PaymentBSI paymentBS;

    @PostMapping("/create")
    public ResponseEntity<Object> createPayment(@RequestBody Payment payment) throws Exception {
        try {
            int i = paymentBS.createPayment(payment);
        }catch (Exception ex) {
            logger.error("Exception occurred while creating payment", ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>("Payment Created Successfully", HttpStatus.OK);
    }

    @GetMapping("/getPayment/paymentId/{paymentId}")
    public ResponseEntity<Object> getPaymentById(@PathVariable("paymentId") Long paymentId) throws Exception {
        Payment payment = null;
        try {
            payment = paymentBS.getPaymentById(paymentId);
        }catch (Exception ex) {
            logger.error("Exception occurred while getting payment by id : "+ paymentId, ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(payment, HttpStatus.OK);
    }

    @GetMapping("/getPaymentStatus/orderId/{orderId}")
    public ResponseEntity<Object> getPaymentStatusByOrderId(@PathVariable("orderId") Long orderId) throws Exception {
        String paymentStatus = null;
        try {
            paymentStatus = paymentBS.getPaymentStatusByOrderId(orderId);
        }catch (Exception ex) {
            logger.error("Exception occurred while creating payment by order id : "+orderId, ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(paymentStatus, HttpStatus.OK);
    }

    @GetMapping("/getAllPayments/userId/{userId}")
    public ResponseEntity<Object> getAllPaymentsForUser(@PathVariable("userId") Long userId) throws Exception {
        PaymentWrapper wrapper = new PaymentWrapper();
        try {
            wrapper.setPaymentList(paymentBS.getAllPaymentsForUser(userId));
        }catch (Exception ex) {
            logger.error("Exception occurred while getting all payments : ", ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(wrapper, HttpStatus.OK);
    }

    @DeleteMapping("/deletePayment/paymentId/{paymentId}")
    public ResponseEntity<Object> removePaymentById(@PathVariable("paymentId") Long paymentId) throws Exception {
        try {
          int ret = paymentBS.removePaymentById(paymentId);
        }catch (Exception ex) {
            logger.error("Exception occurred while removing payment by id: "+ paymentId, ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>("Payment Removed Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deletePayment/all")
    public ResponseEntity<Object> removeAllPayment() throws Exception {
        try {
            int ret = paymentBS.removeAllPayment();
        }catch (Exception ex) {
            logger.error("Exception occurred while removing all payments.", ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(" All Payments Removed Successfully", HttpStatus.OK);
    }

}
