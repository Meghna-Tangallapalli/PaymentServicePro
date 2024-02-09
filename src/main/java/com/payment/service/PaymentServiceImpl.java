package com.payment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.common.PaymentMode;
import com.payment.common.PaymentStatus;
import com.payment.entity.PaymentEntity;
import com.payment.exception.DataNotFoundException;
import com.payment.exception.InvalidDataException;
import com.payment.repository.PaymentRepository;
import com.payment.vo.PaymentVo;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public PaymentVo doPayment(PaymentVo paymentVo) {

		log.info("Saving payment details: {} " + paymentVo);
		PaymentEntity paymentEntity = PaymentEntity.builder()
				.orderId(paymentVo.getOrderId())
				.paymentMode(paymentVo.getPaymentMode())
				.amount(paymentVo.getAmount())
				.paymentStatus(PaymentStatus.SUCCESS)
				.referenceNumber(paymentVo.getReferenceNumber())
				.build();

		PaymentEntity savePayment = paymentRepository.save(paymentEntity);
		log.info("Payment details saved successfully: {} " + paymentEntity);

		PaymentVo paymentResponse = PaymentVo.builder()
				.id(savePayment.getId())
				.orderId(savePayment.getOrderId())
				.paymentMode(savePayment.getPaymentMode())
				.amount(savePayment.getAmount())
				.paymentStatus(savePayment.getPaymentStatus())
				.referenceNumber(savePayment.getReferenceNumber())
				.paymentDate(savePayment.getPaymentDate())
				.build();

		return paymentResponse;
	}

	@Override
	public PaymentVo getByOrderId(long orderId) {
		Optional<PaymentEntity>  paymentOpt = paymentRepository.findByOrderId(Long.valueOf(orderId).toString());

		//PaymentVo paymentVo = new PaymentVo();

		PaymentEntity paymentEntity = paymentOpt.get();
		log.info("Fetching payment details by Order id: {} " + orderId);
		
		PaymentVo paymentVo = null;
		
		if (paymentOpt.isPresent()) {

			/*
			 * paymentVo.setAmount(paymentEntity.getAmount());
			 * paymentVo.setId(paymentEntity.getId());
			 * paymentVo.setOrderId(paymentEntity.getOrderId());
			 * paymentVo.setPaymentDate(paymentEntity.getPaymentDate());
			 * paymentVo.setPaymentMode(paymentEntity.getPaymentMode());
			 * paymentVo.setPaymentStatus(paymentEntity.getPaymentStatus());
			 * paymentVo.setReferenceNumber(paymentEntity.getReferenceNumber());
			 */

			 paymentVo = PaymentVo.builder()
				.id(paymentEntity.getId())
				.amount(paymentEntity.getAmount())
				.orderId(paymentEntity.getOrderId())
				.paymentDate(paymentEntity.getPaymentDate())
				.paymentMode(paymentEntity.getPaymentMode())
				.paymentStatus(paymentEntity.getPaymentStatus())
				.referenceNumber(paymentEntity.getReferenceNumber())
				.build();
			
				log.info(" payment details fetched successfully : {} " + paymentVo);

		}else {
			throw new InvalidDataException("Data not found with id:" + orderId);
		}

		return paymentVo ;
	}

}
