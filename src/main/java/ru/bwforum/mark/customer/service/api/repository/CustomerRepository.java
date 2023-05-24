package ru.bwforum.mark.customer.service.api.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.bwforum.mark.customer.service.api.dto.CustomerDTO;
import ru.bwforum.mark.customer.service.api.entity.Customer;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
    Flux<Customer> getCustomerByCompaniesIdContains(String companyId);
}
