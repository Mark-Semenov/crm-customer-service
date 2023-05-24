package ru.bwforum.mark.customer.service.api.dto;

import lombok.*;
import ru.bwforum.mark.customer.service.api.entity.UserFields;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Set<String> emails = new HashSet<>();
    private Set<String> phones = new HashSet<>();
    private String post;
    private String type;
    private String status;
    private Set<String> uniqueCompaniesId = new HashSet<>();
    private UserFields userFields;
    private boolean isDelete;
}
