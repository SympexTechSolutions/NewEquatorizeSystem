package com.attendance.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.attendance.auth.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{

	  	Optional<Role> findByName(String name);

	    @Query(value = "SELECT * FROM roles WHERE name = ?1", nativeQuery = true)
	    Optional<Role> findRoleByNameNative(String name);
}
