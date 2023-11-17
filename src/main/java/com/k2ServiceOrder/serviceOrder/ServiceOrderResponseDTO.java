package com.k2ServiceOrder.serviceOrder;

import java.time.OffsetDateTime;

public record ServiceOrderResponseDTO(Long serviceId, Long budgetId, String costumerName, String costumerId,
                                      String costumerEmail, String costumerAddress, String carType, String carPlate,
                                      String description, String techReport, String note,
                                      Integer price) //,OffsetDateTime dateTime)
{
    public ServiceOrderResponseDTO(ServiceOrder serviceOrder) {
        this(serviceOrder.getServiceId(), serviceOrder.getBudgetId(), serviceOrder.getCostumerName(), serviceOrder.getCostumerId(),
                serviceOrder.getCostumerEmail(), serviceOrder.getCostumerAddress(), serviceOrder.getCarType(), serviceOrder.getCarPlate(),
                serviceOrder.getDescription(), serviceOrder.getTechReport(), serviceOrder.getNote(), serviceOrder.getPrice());//, serviceOrder.getDateTime());
    }
}