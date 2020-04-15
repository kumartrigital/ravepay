package com.inview.revpay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inview.revpay.model.OnlinePaymentTransaction;

@Repository
public interface OrderRepo extends JpaRepository<OnlinePaymentTransaction, Long> {

	Optional<OnlinePaymentTransaction> findByTxId(String txid);
}
