package ru.bwforum.mark.customer.service.api.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {

    private String id;
    private Set<String> uniqueCustomersId = new HashSet<>();
    private boolean isDelete;

}
