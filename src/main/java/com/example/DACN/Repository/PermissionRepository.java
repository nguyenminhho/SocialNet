package com.example.DACN.Repository;

import com.example.DACN.Entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {
//    @Query("Select p from Permission p where p.name = :name")
//    Permission findByName(String name);
}
