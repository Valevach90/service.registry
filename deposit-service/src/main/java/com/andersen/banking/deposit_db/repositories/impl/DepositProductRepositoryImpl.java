package com.andersen.banking.deposit_db.repositories.impl;

import com.andersen.banking.deposit_api.dto.DepositProductFilterDto;
import com.andersen.banking.deposit_db.entities.DepositProduct;
import com.andersen.banking.deposit_db.repositories.CurrencyRepository;
import com.andersen.banking.deposit_db.repositories.DepositProductFilterRepository;
import com.andersen.banking.deposit_db.repositories.DepositTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DepositProductRepositoryImpl implements DepositProductFilterRepository {

    EntityManager em;

    DepositTypeRepository depositTypeRepository;

    CurrencyRepository currencyRepository;

    @Override
    public DepositProductFilterDto getDepositProductFilter() throws IllegalAccessException {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DepositProduct> cq = cb.createQuery(DepositProduct.class);
        Root<DepositProduct> root = cq.from(DepositProduct.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("isActive"), true));

        DepositProductFilterDto depositProductSettingDto = new DepositProductFilterDto();

        Field[] declaredFields = depositProductSettingDto.getClass().getDeclaredFields();

        for (Field field : declaredFields) {

            field.setAccessible(true);

            if (field.getName().equals("type")) {
                field.set(depositProductSettingDto, depositTypeRepository.findAll());
            }

            if (field.getName().equals("currency")) {
                field.set(depositProductSettingDto, currencyRepository.findAll());
            }

            if (field.getType().isPrimitive()) {

                if (field.getName().startsWith("min")) {

                    CriteriaQuery<Long> cqMin = cb.createQuery(Long.class);
                    Root<DepositProduct> rootMin = cq.from(DepositProduct.class);
                    cqMin.select(cb.min(rootMin.get(field.getName())));

                    List<Long> minValues = em.createQuery(cqMin).getResultList();
                    field.set(depositProductSettingDto, minValues.get(0));

                } else if (field.getName().startsWith("max")) {

                    CriteriaQuery<Long> cqMax = cb.createQuery(Long.class);
                    Root<DepositProduct> rootMax = cq.from(DepositProduct.class);
                    cqMax.select(cb.max(rootMax.get(field.getName())));

                    List<Long> maxValues = em.createQuery(cqMax).getResultList();
                    field.set(depositProductSettingDto, maxValues.get(0));

                } else {
                    CriteriaQuery<Boolean> cqBoolean = cb.createQuery(Boolean.class);
                    Root<DepositProduct> rootBoolean = cq.from(DepositProduct.class);
                    predicates.add(cb.equal(rootBoolean.get(field.getName()), true));

                    List<Boolean> isTrueOption = em.createQuery(cqBoolean).getResultList();
                    field.set(depositProductSettingDto, isTrueOption.get(0));
                }
            }
        }
        return depositProductSettingDto;
    }

    @Override
    public Page<DepositProduct> getDepositProductsByFilter(DepositProductFilterDto depositProductFilterDto, Pageable pageable) throws IllegalAccessException {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DepositProduct> cq = cb.createQuery(DepositProduct.class);

        Root<DepositProduct> root = cq.from(DepositProduct.class);
        List<Predicate> predicates = new ArrayList<>();

        Field[] declaredFields = depositProductFilterDto.getClass().getDeclaredFields();

        for (Field field : declaredFields) {

            field.setAccessible(true);

            if (field.getType().isArray()) {

                Object[] elements = (Object[]) field.get(depositProductFilterDto);

                for (var element :  elements) {
                    predicates.add(cb.equal(root.get(field.getName()), element));
                }
            }

            if (field.getType().isPrimitive()) {

                if (field.getName().startsWith("min")) {
                    Long value = (Long) field.get(depositProductFilterDto);
                    predicates.add(cb.gt(root.get(field.getName()), value));

                } else if (field.getName().startsWith("max")) {
                    Long value = (Long) field.get(depositProductFilterDto);
                    predicates.add(cb.lt(root.get(field.getName()), value));

                } else {
                    Boolean value = (Boolean) field.get(depositProductFilterDto);
                    predicates.add(cb.equal(root.get(field.getName()), value));
                }
            }
        }
        predicates.add(cb.equal(root.get("isActive"), true));

        cq.where(predicates.toArray(new Predicate[0]));

        List<DepositProduct> list = em.createQuery(cq).getResultList();
        Page<DepositProduct> pages = new PageImpl<>(list, pageable, list.size());
        return pages;
    }
}
