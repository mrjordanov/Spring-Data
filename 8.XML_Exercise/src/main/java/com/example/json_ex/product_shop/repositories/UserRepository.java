package com.example.json_ex.product_shop.repositories;


import com.example.json_ex.product_shop.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select u from User u where (SELECT  count (p) from Product p where p.seller=u and p.buyer is not null )>0 " +
            "order by u.lastName, u.firstName")
    List<User> findAllWithSoldProducts();

   @Query("select u from User u" +
            " where (SELECT  count (p) from Product p where p.seller=u and p.buyer is not null )>0")
    List<User> findAllWithSoldProductsOrderByCount();
}
