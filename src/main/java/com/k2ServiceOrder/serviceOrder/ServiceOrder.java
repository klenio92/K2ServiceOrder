package com.k2ServiceOrder.serviceOrder;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "serviceId")
public class ServiceOrder {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;
    private Long budgetId;
    //@NotBlank
    private String costumerName;
    //@NotBlank
    private String costumerId;
    //@NotBlank
    private String costumerNumber; //zap
    private String costumerEmail;
    private String costumerAddress;
    //@NotBlank
    private String carType;
    //@NotBlank
    private String carPlate;
    private String description;
    private String techReport;
    private String note;
    //@NotBlank
    private String price;
    //private OffsetDateTime dateTime;

    public ServiceOrder(ServiceOrderRequestDTO data) {
        this.serviceId = data.serviceId();
        this.budgetId = data.budgetId();
        this.costumerName = data.costumerName();
        this.costumerId = data.costumerId();
        this.costumerNumber = data.costumerNumber();
        this.costumerEmail = data.costumerEmail();
        this.costumerAddress = data.costumerAddress();
        this.carType = data.carType();
        this.carPlate = data.carPlate();
        this.description = data.description();
        this.techReport = data.techReport();
        this.note = data.note();
        this.price = data.price();
        //this.dateTime = data.dateTime();
    }
}