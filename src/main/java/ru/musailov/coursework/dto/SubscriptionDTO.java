package ru.musailov.coursework.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import ru.musailov.coursework.models.SubscriptionType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SubscriptionDTO {

    private int clientId;


    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date expirationDate;

    public SubscriptionDTO(int clientId, SubscriptionType subscriptionType, Date expirationDate) {
        this.clientId = clientId;
        this.subscriptionType = subscriptionType;
        this.expirationDate = expirationDate;
    }
}
