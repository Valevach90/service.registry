package com.andersen.banking.deposit_impl.testcontainer;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.transaction.Transactional;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@SpringBootTest
@Transactional
@ContextConfiguration(initializers = PostgresContainerInitializer.class)
public @interface IntegrationTestWithPostgres {
}

