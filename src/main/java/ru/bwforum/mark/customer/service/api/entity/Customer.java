package ru.bwforum.mark.customer.service.api.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customers")
public class Customer {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String post;
    private Set<String> emails;
    private Set<String> phones;
    private String type;
    private Status status;
    private Set<String> companiesId;
    private UserFields userFields;

    public enum Status {
        BLACK, GREEN, RED
    }


}
