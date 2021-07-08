package com.eteration.bootcamp;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.eteration.bootcamp.model.Address;
import com.eteration.bootcamp.model.Customer;
import com.eteration.bootcamp.model.Order;
import com.eteration.bootcamp.model.Product;
import com.eteration.bootcamp.repository.AddressRepository;
import com.eteration.bootcamp.repository.CustomerRepository;
import com.eteration.bootcamp.repository.OrderRepository;
import com.eteration.bootcamp.repository.ProductRepository;

@SpringBootApplication
@EnableJpaRepositories
public class BootcampJpaDemoApplication implements CommandLineRunner{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BootcampJpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Customer customer1 = new Customer();
		customer1.setName("Yakup");
		customer1.setSurname("Arslan");
		
		Address address1 = new Address();
		address1.setCity("Istanbul");
		address1.setDistrict("Silivri");
		address1.setName("Ev adresim");
		address1.setCustomer(customer1);
		
		customer1.setAddress(address1);
		
		customerRepository.save(customer1);
		
		Product product1 = new Product();
		product1.setName("Mobile Phone");
		productRepository.save(product1);

		Product product2 = new Product();
		product2.setName("Television");
		productRepository.save(product2);
		
		Order order1 = new Order();
		order1.setCustomer(customer1);
		
		Set<Product> orderedProducts = new HashSet<>();
		orderedProducts.add(product1);
		orderedProducts.add(product2);
		
		order1.setOrderedProducts(orderedProducts);
		
		orderRepository.save(order1);
		
		Customer customer2 = new Customer();
		customer2.setName("Furkan");
		customer2.setSurname("Sahin");
		customerRepository.save(customer2);
		
		System.out.println(customerRepository.findByCustomerName("Furkan").toString());
		System.out.println(addressRepository.findByCityContaining("anb").get(0).getName());
		System.out.println(customerRepository.findByCustomerSurname("Sahin"));
		System.out.println(customerRepository.findByCustomerAddressId(Long.valueOf(2)));
		
		
	}

}
