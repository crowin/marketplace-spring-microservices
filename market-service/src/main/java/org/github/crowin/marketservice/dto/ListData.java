package org.github.crowin.marketservice.dto;

import java.util.List;

/*
Basic List Data DTO
 */
public record ListData<T>(
    List<T> items,
    Integer current,
    Integer totalPages,
    Long totalItems) {}
