package com.andersen.banking.meeting_impl.generators;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class CustomPageImpl<T> extends PageImpl<T> {

    private boolean last;
    private boolean first;
    private int totalPages;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CustomPageImpl(@JsonProperty("content") List<T> content, @JsonProperty("number") int number,
                          @JsonProperty("size") int size, @JsonProperty("totalElements") Long totalElements,
                          @JsonProperty("pageable") JsonNode pageable, @JsonProperty("last") boolean last,
                          @JsonProperty("totalPages") int totalPages,
                          @JsonProperty("sort") JsonNode sort,
                          @JsonProperty("first") boolean first, @JsonProperty("numberOfElements") int numberOfElements) {
        super(content, PageRequest.of(number, size), totalElements);
    }

    public CustomPageImpl(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public CustomPageImpl(List<T> content) {
        super(new ArrayList<T>());
    }
}
