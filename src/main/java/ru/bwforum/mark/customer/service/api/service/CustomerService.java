package ru.bwforum.mark.customer.service.api.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService<T, E> {

    Mono<T> getCustomerById(String id);

    Flux<T> getAllCustomers();

    Mono<Void> deleteCustomerById(String id);

    Mono<Void> deleteAll();

    Mono<T> addNewCustomer(T customer);

    Mono<T> updateCustomer(T customer);

    Flux<T> getCustomersByCompanyId(String id);

    Mono<T> bindCompanyWithCustomersById(E dto);

    Mono<Void> unbindCompany(String id);
}
