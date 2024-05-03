package br.com.jjdev.APIREST.rest.dto;

import br.com.jjdev.APIREST.domain.category.Category;

import java.math.BigDecimal;

public record ProductDTO(
        String name,
        Category category,
        BigDecimal price
) {
}
