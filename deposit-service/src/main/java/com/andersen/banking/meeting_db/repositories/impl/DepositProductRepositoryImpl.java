package com.andersen.banking.meeting_db.repositories.impl;

import com.andersen.banking.meeting_api.dto.DepositProductFilterDto;
import com.andersen.banking.meeting_db.entities.DepositProduct;
import com.andersen.banking.meeting_db.entities.DepositType;
import com.andersen.banking.meeting_db.repositories.CurrencyRepository;
import com.andersen.banking.meeting_db.repositories.DepositProductFilterRepository;
import com.andersen.banking.meeting_db.repositories.DepositTypeRepository;
import com.andersen.banking.meeting_impl.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class DepositProductRepositoryImpl implements DepositProductFilterRepository {

    private static final String IS_ACTIVE_PRODUCT_FIELD_NAME = "isActive";
    private static final String TYPE_OF_PRODUCT_FIELD_NAME = "type";
    private static final String CURRENCY_FIELD_NAME = "currency";
    private static final String MIN_VALUE_START_FIELD_NAME = "min";
    private static final String MAX_VALUE_START_FIELD_NAME = "max";

    @Autowired
    EntityManager em;
    @Autowired
    DepositTypeRepository depositTypeRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    @Override
    public DepositProductFilterDto getDepositProductAvailableSettings() throws IllegalAccessException {

        log.info("Getting deposit products available settings");

        DepositProductFilterDto depositProductSettingDto = new DepositProductFilterDto();

        Field[] declaredFields = depositProductSettingDto.getClass().getDeclaredFields();

        for (Field field : declaredFields) {
            field.setAccessible(true);

            log.info("Getting deposit products available settings for field: {}", field.getName());

            CriteriaBuilder cbuilder = em.getCriteriaBuilder();
            CriteriaQuery cquery = cbuilder.createQuery();
            Root<DepositProduct> root = cquery.from(DepositProduct.class);

            if (Collection.class.isAssignableFrom(field.getType())) {

                Predicate predicate = cbuilder.isTrue(root.get(IS_ACTIVE_PRODUCT_FIELD_NAME).as(Boolean.class));
                cquery.select(root).where(predicate);

                List<String> namesOfElements = new ArrayList<>();

                for (DepositProduct product : (List<DepositProduct>) em.createQuery(cquery).getResultList()) {

                    if (field.getName().equals(TYPE_OF_PRODUCT_FIELD_NAME)) {
                        namesOfElements.add(product.getType().getName());
                    }
                    if (field.getName().equals(CURRENCY_FIELD_NAME)) {
                        namesOfElements.add(product.getCurrency().getName());
                    }
                }
                log.info("Available values for field {} are {}", field.getName(), namesOfElements);
                field.set(depositProductSettingDto, namesOfElements.stream().distinct().collect(Collectors.toList()));
            } else {
                if (field.getName().startsWith(MIN_VALUE_START_FIELD_NAME)) {
                    cquery.select(cbuilder.min(root.get(field.getName())));

                } else if (field.getName().startsWith(MAX_VALUE_START_FIELD_NAME)) {
                    cquery.select(cbuilder.max(root.get(field.getName())));

                } else if (field.getType().isAssignableFrom(Boolean.class)) {
                    cquery.select(cbuilder.isTrue(root.get(field.getName())));
                }
                var values = em.createQuery(cquery).getResultList();

                log.info("Available value for field {} is {}", field.getName(), values.get(0));
                field.set(depositProductSettingDto, values.get(0));
            }
        }
        log.info("Get deposit products available settings: {}", depositProductSettingDto);
        return depositProductSettingDto;
    }

    @Override
    public Page<DepositProduct> getDepositProductsByFilter(DepositProductFilterDto filter, Pageable pageable) throws IllegalAccessException {

        log.info("Getting deposit products using filter: {}", filter);

        CriteriaBuilder cbuilder = em.getCriteriaBuilder();
        CriteriaQuery cquery = cbuilder.createQuery();
        Root<DepositProduct> root = cquery.from(DepositProduct.class);

        List<Predicate> predicates = new ArrayList<>();

        Field[] declaredFields = filter.getClass().getDeclaredFields();

        for (Field field : declaredFields) {
            field.setAccessible(true);

            log.info("Creating predicate for field {} with value {}", field.getName(), field.get(filter));

            if (Objects.nonNull(field.get(filter))) {
                if (Collection.class.isAssignableFrom(field.getType())) {

                    List<String> elementsNames = (List<String>) field.get(filter);

                    List<Predicate> orPredicates = new ArrayList<>();

                    for (String elementName : elementsNames) {

                       if (field.getName().equals(TYPE_OF_PRODUCT_FIELD_NAME)) {
                           orPredicates.add(cbuilder.equal(root.get(field.getName()), depositTypeRepository.findByName(elementName)
                                       .orElseThrow(() -> new NotFoundException(DepositType.class, elementName))));
                       }
                       if (field.getName().equals(CURRENCY_FIELD_NAME)) {
                          orPredicates.add(cbuilder.equal(root.get(field.getName()), currencyRepository.findByName(elementName)
                                      .orElseThrow(() -> new NotFoundException(Currency.class, elementName))));
                       }
                    }
                    predicates.add(cbuilder.or(orPredicates.toArray(new Predicate[]{})));
                } else {
                    if (field.getName().startsWith(MIN_VALUE_START_FIELD_NAME)) {

                        if (field.getType().equals(Integer.class)) {
                            Integer value = (Integer) field.get(filter);
                            predicates.add(cbuilder.lessThanOrEqualTo(root.get(field.getName()), value));
                        }
                        if (field.getType().equals(Long.class)) {
                            Long value = (Long) field.get(filter);
                            predicates.add(cbuilder.lessThanOrEqualTo(root.get(field.getName()), value));
                        }
                    } else if (field.getName().startsWith(MAX_VALUE_START_FIELD_NAME)) {
                        if (field.getType().equals(Integer.class)) {
                            Integer value = (Integer) field.get(filter);
                            predicates.add(cbuilder.greaterThanOrEqualTo(root.get(field.getName()), value));
                        }
                        if (field.getType().equals(Long.class)) {
                            Long value = (Long) field.get(filter);
                            predicates.add(cbuilder.greaterThanOrEqualTo(root.get(field.getName()), value));
                        }
                    } else if (field.getType().isAssignableFrom(Boolean.class)){
                        Boolean value = (Boolean) field.get(filter);
                        predicates.add(value ? cbuilder.isTrue(root.get(field.getName()).as(Boolean.class))
                                             : cbuilder.isFalse(root.get(field.getName()).as(Boolean.class)));
                    }
                }
            }
        }
        predicates.add(cbuilder.isTrue(root.get(IS_ACTIVE_PRODUCT_FIELD_NAME).as(Boolean.class)));

        cquery.select(root).where(predicates.toArray(new Predicate[]{}));

        List<DepositProduct> list = em.createQuery(cquery).getResultList();

        log.info("Found {} deposit products: {}", list.size(), list);
        return new PageImpl<>(list, pageable, list.size());
    }
}
