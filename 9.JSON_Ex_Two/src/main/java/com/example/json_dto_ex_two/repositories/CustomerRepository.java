package com.example.json_dto_ex_two.repositories;

import com.example.json_dto_ex_two.entities.Customer;
import com.example.json_dto_ex_two.entities.dtoToQueries.CustomerFiveDTO;
import com.example.json_dto_ex_two.entities.dtoToQueries.CustomerOneDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    @Query("select new com.example.json_dto_ex_two.entities.dtoToQueries.CustomerOneDTO (" +
            "c.id,c.name,c.birthDate,c.isYoungDriver) from Customer c" +
            " order by c.birthDate")
    List<CustomerOneDTO> getAllCustomersOrderByBirthDate();


  /*  @Query("select new com.example.json_dto_ex_two.entities.dtoToQueries.CustomerFiveDTO(" +
            " c.fullName , boughtCarsCount, spentMoney) from Customer c " +
            "join Sale s on c.id=s.customer.id " +
            "join Car c2 on c2.id= s.car.id " +
            "join Part p on p.id=c2.id " +
            "GROUP BY c.name")

    List<CustomerFiveDTO> getAllCustomersWithTheirCarsAndPrice();*/


}
