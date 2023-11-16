package com.k2ServiceOrder.serviceOrder;

public record ServiceOrderRequestDTO(Long serviceId, Long budgetId, String costumerName, Long costumerId,
                                     String costumerEmail, String costumerAddress, String carType, String carPlate,
                                     String description, String techReport, String note, Integer price) {
}