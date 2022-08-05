package com.andersen.banking.service.transfer.testcontainer;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.transaction.Transactional;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@SpringBootTest
@Transactional
@ContextConfiguration(initializers = PostgresContainerInitializer.class)
public @interface IntegrationTestWithPostgres {
}
