package com.andersen.banking.deposit_db.repositories;

import com.andersen.banking.deposit_db.entities.DepositProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositProductRepository extends JpaRepository<DepositProduct, Long> {

    @Query("SELECT dp FROM DepositProduct dp " +
            "WHERE (:name IS NULL OR dp.depositName = :name) AND " +
            "(:currency IS NULL  OR dp.currency.name = :currency)")
    Page<DepositProduct> findByDepositNameAndCurrencyName(@Param("name") String depositName,
                                                          @Param("currency") String currencyName,
                                                          Pageable pageable);
}
