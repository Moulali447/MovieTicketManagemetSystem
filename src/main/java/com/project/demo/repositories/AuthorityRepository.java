package com.project.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.project.demo.entities.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
	@Query(value = "SELECT * FROM Authority u where u.name IN (:authorities)", nativeQuery = true)
    List<Authority> find(@Param("authorities") List<String> authorities);
	
}
