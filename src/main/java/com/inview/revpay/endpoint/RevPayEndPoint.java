package com.inview.revpay.endpoint;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inview.revpay.dto.TransactionDto;
import com.inview.revpay.model.ApiResponse;
import com.inview.revpay.model.OnlinePaymentTransaction;
import com.inview.revpay.repository.OrderRepo;
import com.inview.revpay.service.RevPayServiceImpl;

@RestController
@RequestMapping(value = "/api/v1/revpay")
public class RevPayEndPoint {

	@Autowired
	RevPayServiceImpl revPayServiceImpl;

	@Autowired
	OrderRepo revPayRepo;

	@PostMapping("/createorder")
	public ResponseEntity<?> createOrder(@RequestBody TransactionDto WalletDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		OnlinePaymentTransaction onlinePaymentTransaction = revPayServiceImpl.createOrder(WalletDto);
		map.put("response", onlinePaymentTransaction);
		return new ResponseEntity<>(new ApiResponse(new Date(), "/createorder", 200, map), HttpStatus.OK);
	}

	@PutMapping("/orderlock")
	public ResponseEntity<?> orderLock(@RequestParam String flwref, @RequestParam String txid,
			@RequestParam String status) {
		Map<String, Object> map = new HashMap<String, Object>();
		OnlinePaymentTransaction wallet = revPayServiceImpl.findByID(txid);
		wallet.setAction("charged");
		wallet.setFlwref(flwref);
		wallet.setTransactionStatus(1);
		wallet.setUpdatedAt(new Date());
		revPayRepo.save(wallet);
		if (status.equals("successful")) {
			map.put("result", "successful");
		} else {
			map.put("result", "failed");
		}
		return new ResponseEntity<>(new ApiResponse(new Date(), "/orderlock", 200, map), HttpStatus.OK);
	}

	@PutMapping("/orderCompleted")
	public ResponseEntity<?> orderCompleted(@RequestBody String txid) {
		Map<String, Object> map = new HashMap<String, Object>();
		OnlinePaymentTransaction wallet = revPayServiceImpl.findByID(txid);
		wallet.setAction("Completed");
		// wallet.setFlwref(flwref);
		wallet.setTransactionStatus(2);
		wallet.setUpdatedAt(new Date());
		revPayRepo.save(wallet);
		/*
		 * if (status.equals("successful")) { map.put("result", "successful"); } else {
		 * map.put("result", "failed"); }
		 */
		return new ResponseEntity<>(new ApiResponse(new Date(), "/orderlock", 200, map), HttpStatus.OK);
	}

}
