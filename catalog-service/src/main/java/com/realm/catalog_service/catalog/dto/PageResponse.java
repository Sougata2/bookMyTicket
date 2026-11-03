package com.realm.catalog_service.catalog.dto;

import java.util.List;

public record PageResponse<T>(List<T> items, int page, int size, long totalElement, int totalPages) {
}
