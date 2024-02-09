package com.payment.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.payment.common.PaymentMode;
import com.payment.common.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment_service")
@Builder
public class PaymentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String orderId;

	@Enumerated(EnumType.STRING)
	private PaymentMode paymentMode;
	private long referenceNumber;
	
	@CreationTimestamp
	private LocalDateTime paymentDate;

	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	private long amount;

}
