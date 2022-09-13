package com.andersen.banking.meeting_api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;

/** Interface that presents basic endpoints for working with CardProduct entity. */
@Tag(name = "Card product controller", description = "Endpoints to work with Card product")
@RequestMapping(value = "/api/v1/products")
public interface CardProductController {

}
