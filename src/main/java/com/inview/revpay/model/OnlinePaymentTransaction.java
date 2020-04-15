package com.inview.revpay.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@Entity
public class OnlinePaymentTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String action;
	private Integer transactionStatus;
	private String flwref;
	private String txId;
	private String clientId;
	private String amount;
	private Date createdAt;
	private Date updatedAt;
	private String callbackUrl;
}
