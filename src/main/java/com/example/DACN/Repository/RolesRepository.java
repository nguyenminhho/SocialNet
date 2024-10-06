package com.example.DACN.Repository;

import com.example.DACN.Entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, String> {
//    @Query("Select p from Permission p where p.name = :name")
//    Permission findByName(String name);
}
