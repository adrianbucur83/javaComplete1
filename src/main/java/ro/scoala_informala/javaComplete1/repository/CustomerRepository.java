package ro.scoala_informala.javaComplete1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.scoala_informala.javaComplete1.model.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // findAll

    Page<Customer> findAll(Pageable pageable);

    List<Customer> findByPhoneNumber(String phoneNumber);
    List<Customer> findByPhoneNumberIn(List<String> phoneNumbers);
    void deleteByName(String name);

    @Query(nativeQuery = true, value = "SELECT * FROM customers")
    List<Customer> myCustomQuery();

    @Query(nativeQuery = false, value = "SELECT Customer c")
    List<Customer> myCustomHqlQuery();

}
