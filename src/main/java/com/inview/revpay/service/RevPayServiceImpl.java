package com.inview.revpay.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inview.revpay.dto.TransactionDto;
import com.inview.revpay.model.OnlinePaymentTransaction;
import com.inview.revpay.repository.OrderRepo;

@Service
public class RevPayServiceImpl {

	@Autowired
	OrderRepo revPayRepo;

	public OnlinePaymentTransaction createOrder(TransactionDto walletDto) {
		OnlinePaymentTransaction wallet = new OnlinePaymentTransaction();
		wallet.setClientId(walletDto.getClientID());
		wallet.setAmount(walletDto.getAmount());
		wallet.setTxId(getTxid());
		wallet.setAction("intiated");
		wallet.setTransactionStatus(0);
		wallet.setCreatedAt(new Date());
		return revPayRepo.save(wallet);

	}

	public OnlinePaymentTransaction findByID(String txid) {
		Optional<OnlinePaymentTransaction> wallet = revPayRepo.findByTxId(txid);
		if (wallet.isPresent()) {
			OnlinePaymentTransaction walletData = wallet.get();
			return walletData;
		} else {
			throw new EntityExistsException("There is No Data With the Entered id");
		}
	}

	public String getTxid() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return String.valueOf(timestamp.getTime());
	}
}