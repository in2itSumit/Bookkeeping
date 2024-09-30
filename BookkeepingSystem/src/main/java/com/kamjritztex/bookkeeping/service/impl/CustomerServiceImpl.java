package com.kamjritztex.bookkeeping.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kamjritztex.bookkeeping.dto.AccountResponseDto;
import com.kamjritztex.bookkeeping.dto.AddressResponseDto;
import com.kamjritztex.bookkeeping.dto.CustomerDto;
import com.kamjritztex.bookkeeping.dto.CustomerResponseDto;
import com.kamjritztex.bookkeeping.dto.UpdateCustomerDto;
import com.kamjritztex.bookkeeping.entity.Account;
import com.kamjritztex.bookkeeping.entity.Address;
import com.kamjritztex.bookkeeping.entity.Customer;
import com.kamjritztex.bookkeeping.exception.CustomerNotFoundException;
import com.kamjritztex.bookkeeping.repository.AccountRepository;
import com.kamjritztex.bookkeeping.repository.AddressRepository;
import com.kamjritztex.bookkeeping.repository.CustomerRepository;
import com.kamjritztex.bookkeeping.repository.RoleRepository;
import com.kamjritztex.bookkeeping.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	Random random = new Random();
	
	
	
	@Autowired
	ModelMapper mapper;
	

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public Optional<CustomerResponseDto> getCustomerById(String id) {
		
		Customer customer = null;
		try
		{
			customer = customerRepository.findByIdAndStatus(id, true).get();
		}
		catch (Exception e) {
			throw new CustomerNotFoundException("Customer with ID = "+id+" not found");
		}
		AddressResponseDto addressResponseDto = mapper.map(addressRepository.findById(customer.getAddressId()).get(), AddressResponseDto.class);
		AccountResponseDto accountResponseDto = mapper.map(accountRepository.findById(customer.getAccountId()).get(), AccountResponseDto.class);
		CustomerResponseDto customerResponseDto = mapper.map(customer, CustomerResponseDto.class);
		customerResponseDto.setAccount(accountResponseDto);
		customerResponseDto.setAddress(addressResponseDto);
		
		return Optional.of(customerResponseDto);
		
	}

	@Override
	public Optional<CustomerResponseDto> getCustomerByEmail(String email) {
		Customer customer = null;
		try
		{
			customer = customerRepository.findByEmailAndStatus(email, true).get();
		}
		catch (Exception e) {
			throw new CustomerNotFoundException("Customer with email = "+email+" not found");
		}
		AddressResponseDto addressResponseDto = mapper.map(addressRepository.findById(customer.getAddressId()).get(), AddressResponseDto.class);
		AccountResponseDto accountResponseDto = mapper.map(accountRepository.findById(customer.getAccountId()).get(), AccountResponseDto.class);
		CustomerResponseDto customerResponseDto = mapper.map(customer, CustomerResponseDto.class);
		customerResponseDto.setAccount(accountResponseDto);
		customerResponseDto.setAddress(addressResponseDto);
		
		return Optional.of(customerResponseDto);
	}

	@Override
	public List<CustomerResponseDto> getAllCustomers() {
		List<Customer> allCustomer = customerRepository.findAll();
		List<CustomerResponseDto> allCustomerResponse = new ArrayList<>();
		
		for(Customer customer : allCustomer)
		{
			CustomerResponseDto customerResponseDto = mapper.map(customer, CustomerResponseDto.class);
			AccountResponseDto accountResponseDto = mapper.map(accountRepository.findById(customer.getAccountId()).get(), AccountResponseDto.class);
			AddressResponseDto addressResponseDto = mapper.map(addressRepository.findById(customer.getAddressId()).get(), AddressResponseDto.class);
			customerResponseDto.setAccount(accountResponseDto);
			customerResponseDto.setAddress(addressResponseDto);
			
			allCustomerResponse.add(customerResponseDto);
		}
		return allCustomerResponse;
	}

	@Override
	public String deleteCustomer(String id, String updatedBy) {
		
		Customer existingCustomer = null;
		try
		{
			existingCustomer = customerRepository.findByIdAndStatus(id, true).get();
		}
		catch (Exception e) {
			throw new CustomerNotFoundException("No customer with ID = "+id);
		}
		Address address = addressRepository.findById(existingCustomer.getAddressId()).get();
		address.setUpdatedBy(updatedBy);
		address.setUpdatedAt(LocalDateTime.now());
		addressRepository.save(address);
		
		Account account = accountRepository.findById(existingCustomer.getAccountId()).get();
		account.setUpdatedBy(updatedBy);
		account.setUpdatedAt(LocalDateTime.now());
		accountRepository.save(account);
		
		existingCustomer.setUpdatedAt(LocalDateTime.now());
		existingCustomer.setUpdatedBy(updatedBy);
		existingCustomer.setStatus(false);
		customerRepository.save(existingCustomer);		
		
		return "Success";
	}

	@Override
	public CustomerResponseDto createCustomer(CustomerDto customerDto) {
		
		Address address = new Address();
		address.setArea(customerDto.getAddress().getArea());
		address.setCountry(customerDto.getAddress().getCountry());
		address.setDistrict(customerDto.getAddress().getDistrict());
		address.setHouseNo(customerDto.getAddress().getHouseNo());
		address.setPinCode(customerDto.getAddress().getPinCode());
		address.setState(customerDto.getAddress().getState());
		address.setStreetNo(customerDto.getAddress().getStreetNo());
		address.setCreatedAt(LocalDateTime.now());
		address.setCreatedBy(customerDto.getCreatedBy());
		Address savedAddress = addressRepository.save(address);
		
		Account account = new Account();
		account.setBalance(customerDto.getAccount().getBalance());
		account.setCreatedAt(LocalDateTime.now());
		account.setCreatedBy(customerDto.getCreatedBy());
		account.setName(customerDto.getAccount().getName());
		account.setType(customerDto.getAccount().getType());
		Account savedAccount = accountRepository.save(account);
		
		Customer customer = new Customer();
		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setEmail(customerDto.getEmail());
		customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
		customer.setGender(customerDto.getGender());
		customer.setRoles(Set.of(roleRepository.findByName("CUSTOMER").get()));
		customer.setCreatedAt(LocalDateTime.now());
		customer.setCreatedBy(customerDto.getCreatedBy());
		customer.setDateOfBirth(customerDto.getDateOfBirth());
		customer.setStatus(true);
		customer.setAddressId(savedAddress.getId());
		customer.setContact(customerDto.getContact());
		customer.setAccountId(savedAccount.getId());
		Customer savedCustomer = customerRepository.save(customer);

		//Making Response Object
		
		CustomerResponseDto customerResponse = new CustomerResponseDto();
		customerResponse.setAccount(mapper.map(savedAccount, AccountResponseDto.class));
		customerResponse.setAddress(mapper.map(savedAddress, AddressResponseDto.class));
		customerResponse.setContact(savedCustomer.getContact());
		customerResponse.setDateOfBirth(savedCustomer.getDateOfBirth());
		customerResponse.setEmail(savedCustomer.getEmail());
		customerResponse.setFirstName(savedCustomer.getFirstName());
		customerResponse.setGender(savedCustomer.getGender());
		customerResponse.setId(savedCustomer.getId());
		customerResponse.setLastName(savedCustomer.getLastName());
		customerResponse.setPassword(savedCustomer.getPassword());
		customerResponse.setRoles(savedCustomer.getRoles());
		
		
		return customerResponse;
	}

	@Override
	public CustomerResponseDto updateCustomer(UpdateCustomerDto updateDto) {
		
		Customer existingCustomer = null;
		
		try
		{
			existingCustomer = customerRepository.findByIdAndStatus(updateDto.getId(),true).get();
		}
		catch (Exception e) {
			throw new CustomerNotFoundException("No customer with ID = "+updateDto.getId());
		}
		
		if(existingCustomer != null)
		{
			if(updateDto.getContact()!=null)
				existingCustomer.setContact(updateDto.getContact());
			if(updateDto.getDateOfBirth()!=null)
				existingCustomer.setDateOfBirth(updateDto.getDateOfBirth());
			if(updateDto.getFirstName()!=null)
				existingCustomer.setFirstName(updateDto.getFirstName());
			if(updateDto.getGender()!=null)
				existingCustomer.setGender(updateDto.getGender());
			if(updateDto.getLastName()!=null)
				existingCustomer.setLastName(updateDto.getLastName());
			if(updateDto.getPassword()!=null)
				existingCustomer.setPassword(updateDto.getPassword());
			existingCustomer.setUpdatedAt(LocalDateTime.now());
			existingCustomer.setUpdatedBy(updateDto.getUpdatedBy());
			
			if(updateDto.getAddress() != null)
			{
				Address existingAddress = addressRepository.findById(existingCustomer.getAddressId()).get();
				
				if(updateDto.getAddress().getHouseNo() != null)
					existingAddress.setHouseNo(updateDto.getAddress().getHouseNo());
				if(updateDto.getAddress().getArea() != null)
					existingAddress.setArea(updateDto.getAddress().getArea());
				if(updateDto.getAddress().getCountry() != null)
					existingAddress.setCountry(updateDto.getAddress().getCountry());
				if(updateDto.getAddress().getDistrict() != null)
					existingAddress.setDistrict(updateDto.getAddress().getDistrict());
				if(updateDto.getAddress().getPinCode() != 0)
					existingAddress.setPinCode(updateDto.getAddress().getPinCode());
				if(updateDto.getAddress().getState() != null)
					existingAddress.setState(updateDto.getAddress().getState());
				if(updateDto.getAddress().getStreetNo() != null)
					existingAddress.setStreetNo(updateDto.getAddress().getStreetNo());
				existingAddress.setUpdatedAt(LocalDateTime.now());
				existingAddress.setUpdatedBy(updateDto.getUpdatedBy());
				
				Address updatedAddress = addressRepository.save(existingAddress);
				existingCustomer.setAddressId(updatedAddress.getId());
			}				
		}
		Customer updatedCustomer = customerRepository.save(existingCustomer);	
		CustomerResponseDto customerResponse =  mapper.map(updatedCustomer, CustomerResponseDto.class);
		
		customerResponse.setAccount(mapper.map(accountRepository.findById(existingCustomer.getAccountId()).get(), AccountResponseDto.class));
		customerResponse.setAddress(mapper.map(addressRepository.findById(existingCustomer.getAddressId()).get(), AddressResponseDto.class));
		
		return customerResponse;
	}
}
