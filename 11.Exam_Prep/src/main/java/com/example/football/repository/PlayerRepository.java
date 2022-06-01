package com.example.football.repository;

import com.example.football.models.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {


    Optional<Player> findByEmail(String email);

    @Query("select p from Player p where  p.birthDate>=:birthDate1 and p.birthDate<=:birthDate2" +
            " order by p.stat.shooting desc ,p.stat.passing desc , p.stat.endurance desc, p.lastName")
    List<Player> findAllByBirthDateAfterAndBirthDateBeforeOrderByStat(LocalDate birthDate1, LocalDate birthDate2);

   List<Player> findAllByBirthDateBetweenOrderByStatShootingDescStatPassingDescStatEnduranceDescLastNameAsc(LocalDate birthDate, LocalDate birthDate2);
}
