package ru.bwforum.mark.customer.service.api.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.bwforum.mark.customer.service.api.dto.CompanyDTO;
import ru.bwforum.mark.customer.service.api.dto.CustomerDTO;
import ru.bwforum.mark.customer.service.api.service.CustomerService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CustomerRestController {

    private final CustomerService<CustomerDTO, CompanyDTO> customerService;


    @GetMapping("/customer/{id}")
    public Mono<ResponseEntity<CustomerDTO>> getCustomerById(@PathVariable(name = "id") String id) {
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ofNullable);
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/customer/company/{id}")
    public Flux<CustomerDTO> getCustomersByCompanyId(@PathVariable(name = "id") String companyId) {
        return customerService.getCustomersByCompanyId(companyId);
    }


    @ResponseStatus(value = HttpStatus.OK, reason = "DELETED")
    @DeleteMapping(value = "/customer/delete/{id}")
    public Mono<Void> deleteCustomer(@PathVariable(name = "id") String id) {
        return customerService
                .deleteCustomerById(id);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "DELETED")
    @DeleteMapping(value = "/customer/delete/all")
    public Mono<Void> deleteAllCustomers() {
        return customerService.deleteAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/customers")
    public Flux<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/customer/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CustomerDTO> addNewCustomer(@RequestBody Mono<CustomerDTO> dto) {
        return dto.flatMap(customerService::addNewCustomer);
    }

    @PatchMapping(value = "/customer/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CustomerDTO>> updateCustomer(@RequestBody Mono<CustomerDTO> dto) {
        return dto.flatMap(customerService::updateCustomer)
                .map(ResponseEntity::ofNullable);
    }


    @PatchMapping(value = "/customer/bind", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CustomerDTO>> bindCompanyWithCustomer(@RequestBody Mono<CompanyDTO> dto) {
        return dto.flatMap(customerService::bindCompanyWithCustomersById)
                .map(ResponseEntity::ofNullable);
    }

    @GetMapping("/customer/unbind/{id}")
    public Mono<ResponseEntity<Void>> unbindCompanyFromCustomerById(@PathVariable(name = "id") String id) {
        return customerService
                .unbindCompany(id)
                .map(ResponseEntity::ofNullable);
    }

}
