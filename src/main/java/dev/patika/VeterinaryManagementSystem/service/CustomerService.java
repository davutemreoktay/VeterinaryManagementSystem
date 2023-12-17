package dev.patika.VeterinaryManagementSystem.service;

import dev.patika.VeterinaryManagementSystem.dto.request.CustomerRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.CustomerResponse;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import dev.patika.VeterinaryManagementSystem.mapper.CustomerMapper;
import dev.patika.VeterinaryManagementSystem.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public List<CustomerResponse> findAll() {
        return customerMapper.asOutput(customerRepository.findAll());
    }

    public CustomerResponse getById(Long id) {
        return customerMapper.asOutput(customerRepository.findById(id).orElseThrow(()
                -> new RuntimeException(id + " nolu müşteri bulunamadı.")));
    }

    public CustomerResponse create(CustomerRequest request) {
        Optional<Customer> isCustomerExist = customerRepository.findByName(request.getName());

        if (isCustomerExist.isEmpty()) {
            Customer customerSaved = customerRepository.save(customerMapper.asEntity(request));
            return customerMapper.asOutput(customerSaved);
        }
        throw new RuntimeException("Bu müşteri daha önce sisteme kayıt olmuştur !!!");
    }

    public CustomerResponse update(Long id, CustomerRequest request) {
        Optional<Customer> customerFromDb = customerRepository.findById(id);
        Optional<Customer> isCustomerExist = customerRepository.findByName(request.getName());

        if (customerFromDb.isEmpty()) {
            throw new RuntimeException(id + " nolu müşteri bulunamadı.");
        }

        if (isCustomerExist.isPresent()) {
            throw new RuntimeException("Bu müşteri daha önce sisteme kayıt olmuştur !!!");
        }
        Customer customer = customerFromDb.get();
        customerMapper.update(customer, request);
        return customerMapper.asOutput(customerRepository.save(customer));
    }

    public void deleteById(Long id) {
        Optional<Customer> customerFromDb = customerRepository.findById(id);

        if (customerFromDb.isPresent()) {
            customerRepository.delete(customerFromDb.get());
        } else {
            throw new RuntimeException(id + " nolu müşteri bulunamadı.");
        }
    }

}
