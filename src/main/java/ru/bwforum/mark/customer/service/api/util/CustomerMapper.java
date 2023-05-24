package ru.bwforum.mark.customer.service.api.util;


import ru.bwforum.mark.customer.service.api.dto.CustomerDTO;
import ru.bwforum.mark.customer.service.api.entity.Customer;

public class CustomerMapper {

    public static CustomerDTO mapToCustomerDto(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .patronymic(customer.getPatronymic())
                .post(customer.getPost())
                .emails(customer.getEmails())
                .phones(customer.getPhones())
                .status(String.valueOf(customer.getStatus()))
                .type(customer.getType())
                .uniqueCompaniesId(customer.getCompaniesId())
                .userFields(customer.getUserFields())
                .build();

    }


    public static Customer mapToCustomerFromDto(CustomerDTO dto) {
        return CustomerWrapper.builder()
                .setId(dto.getId())
                .setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName())
                .setPatronymic(dto.getPatronymic())
                .setPost(dto.getPost())
                .setStatus(dto.getStatus())
                .setEmail(dto.getEmails())
                .setPhone(dto.getPhones())
                .setType(dto.getType())
                .setCompaniesId(dto.getUniqueCompaniesId())
                .setUserFields(dto.getUserFields())
                .build();

    }

}
