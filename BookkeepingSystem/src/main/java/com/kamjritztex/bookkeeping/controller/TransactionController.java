//package com.kamjritztex.bookkeeping.controller;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.kamjritztex.bookkeeping.dto.TransactionDto;
//import com.kamjritztex.bookkeeping.dto.UpdateCustomerDto;
//import com.kamjritztex.bookkeeping.entity.Customer;
//import com.kamjritztex.bookkeeping.entity.Transaction;
//import com.kamjritztex.bookkeeping.exception.CustomerNotFoundException;
//import com.kamjritztex.bookkeeping.response.dto.CreateCustomerResponseDto;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import jakarta.validation.Valid;
//
//@RestController
//@RequestMapping("bookkeeping/transaction")
//public class TransactionController {
//
//
//	@Autowired
//	private Transaction customerService;
//
//	@Operation(summary = "Do Transaction ")
//	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Do Transaction ", content = {
//	@Content(mediaType = "application/json", schema = @Schema(implementation = Transaction.class)) }) })
//	@PostMapping("/do-transaction ")
//	public ResponseEntity<CreateCustomerResponseDto> doTransaction(
//			@Valid @RequestBody TransactionDto transactionDto) {
//
//		return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(transactionDto));
//	}
//
//	@Operation(summary = "Get employee by ID  ")
//	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Customer found", content = {
//			@Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }) })
//	@GetMapping("get-by-id/{id}")
//	public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
//		Optional<Customer> customer = customerService.getCustomerById(id);
//		return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//	}
//	
//
//
//	@GetMapping("/get-by-email/{email}")
//	public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
//		Optional<Customer> customer = customerService.getCustomerByEmail(email);
//		return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//	}
//
//	@Operation(summary = "Get customer  by CustomerID  ")
//	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Customer found", content = {
//			@Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }) })
//	@GetMapping("/customer-id/{employeeId}")
//	public ResponseEntity<Customer> getCustomerByCustomerId(@PathVariable String customerId) {
//		Optional<Customer> customer = customerService.getCustomerById(customerId);
//		return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//	}
//
//	@GetMapping
//	public ResponseEntity<List<Customer>> getAllCustomer() {
//		List<Customer> customer = customerService.getAllCustomers();
//		return ResponseEntity.ok(customer);
//	}
//
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
//		customerService.deleteCustomer(id);
//		return ResponseEntity.noContent().build();
//	}
//
//	@PutMapping("/{id}")
//	public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @RequestParam String firstName,
//			@RequestParam String lastName, @RequestParam String username, @RequestParam String email,
//			@RequestParam String password) {
//		Customer updatedCustomer = customerService.updateCustomer(id, firstName, lastName, username, email, password);
//		return ResponseEntity.ok(updatedCustomer);
//	}
//	
//	
//	@PutMapping("customer/{customerID}")
//    public ResponseEntity<UpdateCustomerDto> updateCustomer(
//            @PathVariable String customerID,
//            @RequestBody UpdateCustomerDto customerDto) {
//        try {
//            UpdateCustomerDto updatedCustomerDto = customerService.updateCustomer(customerID, customerDto);
//            return new ResponseEntity<>(updatedCustomerDto, HttpStatus.OK);
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        } catch (CustomerNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//}
