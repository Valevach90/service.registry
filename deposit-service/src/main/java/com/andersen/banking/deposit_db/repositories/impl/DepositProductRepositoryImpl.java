package com.andersen.banking.deposit_db.repositories.impl;

import com.andersen.banking.deposit_api.dto.CurrencyDto;
import com.andersen.banking.deposit_api.dto.DepositProductFilterDto;
import com.andersen.banking.deposit_api.dto.DepositTypeDto;
import com.andersen.banking.deposit_db.entities.Currency;
import com.andersen.banking.deposit_db.entities.DepositProduct;
import com.andersen.banking.deposit_db.entities.DepositType;
import com.andersen.banking.deposit_db.repositories.CurrencyRepository;
import com.andersen.banking.deposit_db.repositories.DepositProductFilterRepository;
import com.andersen.banking.deposit_db.repositories.DepositTypeRepository;
import com.andersen.banking.deposit_impl.mapping.CurrencyMapper;
import com.andersen.banking.deposit_impl.mapping.DepositTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DepositProductRepositoryImpl implements DepositProductFilterRepository {

    @Autowired
    EntityManager em;
    @Autowired//added
    DepositTypeRepository depositTypeRepository;
    @Autowired//added
    CurrencyRepository currencyRepository;

    @Autowired
    DepositTypeMapper depositTypeMapper;

    @Autowired
    CurrencyMapper currencyMapper;

    @Override
    public DepositProductFilterDto getDepositProductFilter() throws IllegalAccessException {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DepositProduct> cq = cb.createQuery(DepositProduct.class);
        Root<DepositProduct> root = cq.from(DepositProduct.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("isActive"), true));

        DepositProductFilterDto depositProductSettingDto = new DepositProductFilterDto();

        Field[] declaredFields = depositProductSettingDto.getClass().getDeclaredFields();
        System.out.println("1");
        for (Field field : declaredFields) {
            System.out.println("2" + field.getName());
            field.setAccessible(true);

            if (field.getName().equals("type")) {
                System.out.println("2.1" + field.getName());
                field.set(depositProductSettingDto, depositTypeRepository.findAll());
            }

            if (field.getName().equals("currency")) {
                field.set(depositProductSettingDto, currencyRepository.findAll());
            }

            if (!Collection.class.isAssignableFrom(field.getType())) {
                System.out.println("3 " + field.getName());
                if (field.getName().startsWith("min")) {
                    System.out.println("4 " + field.getName());
                    CriteriaBuilder cbMin = em.getCriteriaBuilder();
                    CriteriaQuery cqMin = cbMin.createQuery(DepositProduct.class);
                    Root rootMin = cqMin.from(DepositProduct.class);
                    cqMin.select(cbMin.min(rootMin.get(field.getName())));
                    System.out.println("4.1 " + field.getName());
                    List<Long> minValues = em.createQuery(cqMin).getResultList();
                    System.out.println(minValues);
                    field.set(depositProductSettingDto, minValues.get(0));

                } else if (field.getName().startsWith("max")) {
                    System.out.println("5 " + field.getName());
                    CriteriaBuilder cbMax = em.getCriteriaBuilder();
                    CriteriaQuery cqMax = cbMax.createQuery(DepositProduct.class);
                    Root rootMax = cqMax.from(DepositProduct.class);
                    cqMax.select(cbMax.max(rootMax.get(field.getName())));
                    System.out.println("5.1 " + field.getName());
                    List<Long> maxValues = em.createQuery(cqMax).getResultList();
                    field.set(depositProductSettingDto, maxValues.get(0));

                } else {
                    System.out.println("6 " + field.getName());
                    CriteriaBuilder cbBoolean = em.getCriteriaBuilder();
                    CriteriaQuery cqBoolean = cbBoolean.createQuery();
                    Root rootBoolean = cqBoolean.from(DepositProduct.class);
                    /*predicates.add(cb.equal(rootBoolean.get(field.getName()), true));
                    cqBoolean.where(predicates.toArray(new Predicate[0]));*/
                    cqBoolean.select(cbBoolean.equal(rootBoolean.get(field.getName()), true));
                    System.out.println("6.1 " + field.getName());
                    List<DepositProduct> isTrueOption = em.createQuery(cqBoolean).getResultList();
                    System.out.println(isTrueOption);
                    field.set(depositProductSettingDto, true);
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
        System.out.println(declaredFields.toString());
        for (Field field : declaredFields) {

            field.setAccessible(true);

            if (Collection.class.isAssignableFrom(field.getType())) {
                if (field.getName().equals("type")) {
                    List<DepositTypeDto> elementsDto = (List<DepositTypeDto>) field.get(depositProductFilterDto);

                    List<DepositType> elements = elementsDto.stream()
                            .map(depositTypeMapper::toDepositType)
                            .collect(Collectors.toList());

                    System.out.println(elements);
                    for (var element : elements) {
                        predicates.add(cb.equal(root.get(field.getName()), element));
                        System.out.println(element);
                    }
                }
                if (field.getName().equals("currency")) {
                    List<CurrencyDto> elementsDto = (List<CurrencyDto>) field.get(depositProductFilterDto);

                    List<Currency> elements = elementsDto.stream()
                            .map(currencyMapper::toCurrency)
                            .collect(Collectors.toList());

                    System.out.println(elements);
                    for (var element : elements) {
                        predicates.add(cb.equal(root.get(field.getName()), element));
                        System.out.println(element);
                    }
                }
            }

            if (!Collection.class.isAssignableFrom(field.getType())) {
                System.out.println(field.getName());
                if (field.getName().startsWith("min")) {
                    if (field.getType().equals(Integer.class)) {
                        Integer value = (Integer) field.get(depositProductFilterDto);
                        System.out.println(value);
                        predicates.add(cb.gt(root.get(field.getName()), value));
                        System.out.println(field.getName());
                    }
                    if (field.getType().equals(Long.class)) {
                        Long value = (Long) field.get(depositProductFilterDto);
                        System.out.println(value);
                        predicates.add(cb.gt(root.get(field.getName()), value));
                    }


                } else if (field.getName().startsWith("max")) {
                    if (field.getType().equals(Integer.class)) {
                        Integer value = (Integer) field.get(depositProductFilterDto);
                        System.out.println(value);
                        predicates.add(cb.lt(root.get(field.getName()), value));
                        System.out.println(field.getName());
                    }
                    if (field.getType().equals(Long.class)) {
                        Long value = (Long) field.get(depositProductFilterDto);
                        System.out.println(value);
                        predicates.add(cb.lt(root.get(field.getName()), value));
                        System.out.println(field.getName());
                    }
                } else {
                    Boolean value = (Boolean) field.get(depositProductFilterDto);
                    System.out.println(value);
                    predicates.add(cb.equal(root.get(field.getName()), value));
                    System.out.println(field.getName());
                }
            }
        }
        predicates.add(cb.equal(root.get("isActive"), true));
        System.out.println(predicates.toString());

        Predicate[] array = new Predicate[predicates.size()];
        //list.toArray(array); // fill the array
        Predicate predicate
                = cb.and(predicates.toArray(array));
        //cq.where(predicate);
        /*cq.select(root)
                .where(predicates.toArray(new Predicate[]{}));*/
        List<DepositProduct> list = (List<DepositProduct>) em.createQuery(cq).getResultList();
        System.out.println(list);
        Page<DepositProduct> pages = new PageImpl<>(list, pageable, list.size());
        return pages;
    }
}
