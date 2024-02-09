package com.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payment.service.PaymentService;
import com.payment.vo.PaymentVo;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/doPayment")
	public ResponseEntity<PaymentVo> doPayment(@RequestBody PaymentVo paymentVo){
		PaymentVo result = paymentService.doPayment(paymentVo);
		return new ResponseEntity<PaymentVo>(result, HttpStatus.OK);
		
	}
	
	@GetMapping("/getByOrderId")
	public ResponseEntity<PaymentVo> getPaymentByOrderId( @RequestParam long orderId){
		PaymentVo result = paymentService.getByOrderId(orderId);
		return new ResponseEntity<PaymentVo>(result, HttpStatus.OK);
		
	}
}
