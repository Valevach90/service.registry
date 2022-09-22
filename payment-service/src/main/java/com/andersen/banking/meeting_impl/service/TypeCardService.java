package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_db.entities.TypeCard;
import java.util.UUID;

public interface TypeCardService {

    /**
     * This method returns TypeCard entity by given id.
     *
     * @param id
     * @return
     */
    TypeCard findById(UUID id);

    /**
     * This method returns TypeCard entity by Payment system and type name.
     *
     * @param ps
     * @param tn
     * @return
     */
    TypeCard findByPaymentSystemAndTypeName(String ps, String tn);

    /**
     * This method updates and returns updated TypeCard entity.
     *
     * @param typeCard
     * @return
     */
    TypeCard update(TypeCard typeCard);
}
