package com.eteration.bootcamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eteration.bootcamp.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	// to entity class query
	@Query("Select c from Customer c where c.name=?1")
	Customer findByCustomerName(String name);

	// to entity class query
	@Query("Select c from #{#entityName} c where c.surname=?1")
	Customer findByCustomerSurname(@Param("surname") String lastname);

	// to rdbms query
	@Query(value = "Select * from customers c where c.address_id=:addressId", nativeQuery = true)
	Customer findByCustomerAddressId(@Param("addressId") Long id);
}
