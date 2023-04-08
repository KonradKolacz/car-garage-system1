package com.example.test04system1.repository;

import com.example.test04system1.domain.Garage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface GarageRepository extends JpaRepository<Garage, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Garage> findWithLockingById(Long id);


}
