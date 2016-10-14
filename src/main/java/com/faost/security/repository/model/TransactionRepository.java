package com.faost.security.repository.model;

import com.faost.security.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by faos7 on 12.11.16.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
