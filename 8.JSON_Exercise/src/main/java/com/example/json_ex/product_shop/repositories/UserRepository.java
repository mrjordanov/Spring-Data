package com.example.json_ex.product_shop.repositories;


import com.example.json_ex.product_shop.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("select u from User u Join u.sellingItems p where p.buyer is not null ")
    List<User> findAllBySellingItemsNotNullOrderByLastNameAscFirstNameAsc();

    @Query("select u from User u Join u.sellingItems p where p.buyer is not null order by u.sellingItems.size DESC, u.lastName ASC ")
    List<User> findAllWithSoldProductsOrderByCount();
}
