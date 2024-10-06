package com.example.DACN.Service;

import com.example.DACN.Dto.Request.PermissionRequest;
import com.example.DACN.Dto.Request.RoleRequest;
import com.example.DACN.Dto.Response.PermissionResponse;
import com.example.DACN.Dto.Response.RoleResponse;
import com.example.DACN.Entity.Permission;
import com.example.DACN.Entity.Roles;
import com.example.DACN.Mapper.PermissionMapper;
import com.example.DACN.Mapper.RoleMapper;
import com.example.DACN.Repository.PermissionRepository;
import com.example.DACN.Repository.RolesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RolesService {
    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleMapper roleMapper;
    public RoleResponse addRole(RoleRequest roleRequest) throws Exception{
        var roles = roleMapper.toRoles(roleRequest);
        if((rolesRepository.findById(roles.getName())).isPresent()){
            throw  new Exception("Roles exits");
        }
        var permissions = permissionRepository.findAllById(roleRequest.getPermissions());
        roles.setPermissions(new HashSet<>(permissions));
        return roleMapper.toRoleResponse(rolesRepository.save(roles));
    }

    public RoleResponse updatePerInRole(String id, List<String> permission) throws Exception{
        Roles roles = rolesRepository.findById(id).get();
        var permissions = permissionRepository.findAllById(permission);

        if ((roles.getPermissions() == null)) {
            permissions.forEach(permission1 -> roles.setPermissions(new HashSet<>(permissions)));
        } else {
            permissions.forEach(permission1 -> roles.getPermissions().add(permission1));
        }

        return roleMapper.toRoleResponse(rolesRepository.save(roles));
    }

    public List<RoleResponse> getAllRole(){
        return rolesRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }

    public Boolean removeRole(String name){
        rolesRepository.deleteById(name);
        return true;
    }


}
