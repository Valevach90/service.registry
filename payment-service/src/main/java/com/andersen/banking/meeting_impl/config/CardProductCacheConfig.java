package com.andersen.banking.meeting_impl.config;

import com.andersen.banking.meeting_db.entities.CardProduct;
import com.andersen.banking.meeting_db.repository.CardProductRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class CardProductCacheConfig {

    private final CardProductRepository cardProductRepository;

    private final CacheManager cacheManager;

    @PostConstruct
    private void cacheAllCardProductsIntoMemory(){
        log.info("Initializing all card products into cache memory:");
        List<CardProduct> allCardProducts = cardProductRepository.findAll();
        allCardProducts.forEach(cardProduct -> {
            cacheManager.getCache("cardProductCache").put(cardProduct.getId(), cardProduct);
        });
        log.info("All {} card products was cached", allCardProducts.size());
    }
}
