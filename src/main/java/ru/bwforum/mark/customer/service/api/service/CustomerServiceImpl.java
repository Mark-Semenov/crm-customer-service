package ru.bwforum.mark.customer.service.api.service;

import com.thoughtworks.xstream.converters.reflection.MissingFieldException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.bwforum.mark.customer.service.api.dto.CompanyDTO;
import ru.bwforum.mark.customer.service.api.dto.CustomerDTO;
import ru.bwforum.mark.customer.service.api.entity.Customer;
import ru.bwforum.mark.customer.service.api.repository.CustomerRepository;
import ru.bwforum.mark.customer.service.api.util.CustomerMapper;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService<CustomerDTO, CompanyDTO> {

    private final CustomerRepository customerRepository;
    private final RabbitTemplate rabbit;

    @Override
    public Mono<CustomerDTO> getCustomerById(String customerId) {
        return customerRepository
                .findById(customerId)
                .map(CustomerMapper::mapToCustomerDto)
                .onErrorComplete(NoSuchElementException.class)
                .log();
    }

    @Override
    public Flux<CustomerDTO> getAllCustomers() {
        return customerRepository
                .findAll()
                .map(CustomerMapper::mapToCustomerDto)
                .log();
    }


    @Override
    public Flux<CustomerDTO> getCustomersByCompanyId(String companyId) {
        return customerRepository
                .getCustomerByCompaniesIdContains(companyId)
                .map(CustomerMapper::mapToCustomerDto)
                .log();
    }

    @Override
    public Mono<CustomerDTO> bindCompanyWithCustomersById(CompanyDTO dto) {
        return customerRepository
                .findAllById(dto.getUniqueCustomersId())
                .doOnNext(item -> item.getCompaniesId().add(dto.getId()))
                .flatMap(customerRepository::save)
                .reduce((one, two) -> two)
                .map(CustomerMapper::mapToCustomerDto)
                .log();
    }

    @Override
    public Mono<Void> unbindCompany(String companyId) {
        return customerRepository
                .findAll()
                .filter(item -> item.getCompaniesId().contains(companyId))
                .doOnNext(item -> item.getCompaniesId().remove(companyId))
                .flatMap(customerRepository::save)
                .then()
                .log();
    }

    @Override
    public Mono<Void> deleteAll() {
        return customerRepository
                .findAll()
                .map(CustomerMapper::mapToCustomerDto)
                .doOnNext(item -> item.setDelete(true))
                .doOnNext(rabbit::convertAndSend)
                .map(CustomerDTO::getId)
                .flatMap(customerRepository::deleteById)
                .then()
                .log();
    }


    @Override
    public Mono<Void> deleteCustomerById(String customerId) {
        return customerRepository
                .findById(customerId)
                .map(CustomerMapper::mapToCustomerDto)
                .doOnNext(item -> item.setDelete(true))
                .doOnNext(rabbit::convertAndSend)
                .map(CustomerDTO::getId)
                .flatMap(customerRepository::deleteById)
                .log();
    }

    @Override
    public Mono<CustomerDTO> addNewCustomer(CustomerDTO dto) {
        Customer customer = CustomerMapper.mapToCustomerFromDto(dto);
        return customerRepository
                .save(customer)
                .map(CustomerMapper::mapToCustomerDto)
                .doOnNext(rabbit::convertAndSend)
                .log();
    }

    @Override
    public Mono<CustomerDTO> updateCustomer(CustomerDTO dto) {
        if (dto.getId() == null) {
            throw new MissingFieldException("String", "ID");
        }
        Customer updated = CustomerMapper.mapToCustomerFromDto(dto);
        return customerRepository
                .findById(dto.getId())
                .map(item -> updated)
                .flatMap(customerRepository::save)
                .map(CustomerMapper::mapToCustomerDto)
                .doOnNext(rabbit::convertAndSend)
                .log();
    }

    private void sendToDeleteId(CustomerDTO dto, Customer item) {
        for (String id : item.getCompaniesId()) {
            if (!dto.getUniqueCompaniesId().contains(id)) {
                CompanyDTO build = CompanyDTO.builder()
                        .id(id)
                        .isDelete(true)
                        .build();
                rabbit.convertAndSend(build);
            }
        }
    }
}
