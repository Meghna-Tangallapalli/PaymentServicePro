package com.payment.service;

import org.springframework.stereotype.Component;

import com.payment.vo.PaymentVo;

@Component
public interface PaymentService {

	PaymentVo doPayment(PaymentVo paymentVo);

	PaymentVo getByOrderId(long orderId);
	

}
