package ru.bwforum.mark.customer.service.api.util;

import lombok.Getter;
import lombok.Setter;
import ru.bwforum.mark.customer.service.api.entity.*;

import java.util.Arrays;
import java.util.Set;

@Getter
@Setter
public class CustomerWrapper {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final Customer customer;

        public Builder() {
            this.customer = new Customer();
        }

        public Builder setId(String customerId) {
            customer.setId(customerId);
            return this;
        }

        public Builder setFirstName(String firstName) {
            customer.setFirstName(firstName);
            return this;
        }

        public Builder setLastName(String lastName) {
            customer.setLastName(lastName);
            return this;
        }

        public Builder setPatronymic(String patronymic) {
            customer.setPatronymic(patronymic);
            return this;
        }

        public Builder setEmail(Set<String> emails) {
            customer.setEmails(emails);
            return this;
        }

        public Builder setPhone(Set<String> phones) {
            customer.setPhones(phones);
            return this;
        }

        public Builder setPost(String post) {
            customer.setPost(post);
            return this;
        }

        public Builder setType(String type) {
            customer.setType(type);
            return this;
        }

        public Builder setStatus(String status) {
            customer.setStatus(Customer.Status.valueOf(status));
            return this;
        }

        public Builder setCompaniesId(Set<String> uniqueCompaniesId) {
            customer.setCompaniesId(uniqueCompaniesId);
            return this;
        }


        public Builder setUserFields(UserFields fields) {
            customer.setUserFields(fields);
            return this;
        }

        public Customer build() {
            return this.customer;
        }
    }
}
