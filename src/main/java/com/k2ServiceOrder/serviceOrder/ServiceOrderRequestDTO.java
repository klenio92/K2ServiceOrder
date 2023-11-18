package com.k2ServiceOrder.serviceOrder;

import java.time.OffsetDateTime;

public record ServiceOrderRequestDTO(Long serviceId, Long budgetId, String costumerName, String costumerId,
                                     String costumerNumber, String costumerEmail, String costumerAddress,
                                     String carType, String carPlate, String description, String techReport,
                                     String note, String price)//, OffsetDateTime dateTime)
{
}