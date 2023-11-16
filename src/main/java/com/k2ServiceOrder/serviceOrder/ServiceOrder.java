package com.k2ServiceOrder.serviceOrder;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "serviceorder")
@Entity(name = "serviceorder")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "serviceId")
public class ServiceOrder {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;
    private Long budgetId;
    private String costumerName;
    private Long costumerId;
    private String costumerEmail;
    private String costumerAddress;
    private String carType;
    private String carPlate;
    private String description;
    private String techReport;
    private String note;
    private Integer price;

    public ServiceOrder(ServiceOrderRequestDTO data) {
        this.serviceId = data.serviceId();
        this.budgetId = data.budgetId();
        this.costumerName = data.costumerName();
        this.costumerId = data.costumerId();
        this.costumerEmail = data.costumerEmail();
        this.costumerAddress = data.costumerAddress();
        this.carType = data.carType();
        this.carPlate = data.carPlate();
        this.description = data.description();
        this.techReport = data.techReport();
        this.note = data.note();
        this.price = data.price();
    }
}