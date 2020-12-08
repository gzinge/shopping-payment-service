package com.shopping.payment.model;

import javax.persistence.*;

@Entity
@Table(name = "PAYMENT")
public class Payment {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="common_seq")
    @SequenceGenerator(name="common_seq", sequenceName="common_sequence", allocationSize=20)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne()
    @JoinColumn(name = "USER_ID")
    private User user;

    private Double totalAmount;
    private String paymentStatus;
    private String paymentMethod;

    public Payment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
