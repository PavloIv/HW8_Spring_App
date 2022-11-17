package com.ip.hw8.repository;

import com.ip.hw8.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<Producer,Long> {

}
