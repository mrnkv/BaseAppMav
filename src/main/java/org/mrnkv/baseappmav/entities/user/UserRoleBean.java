/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mrnkv.baseappmav.entities.user;

import java.io.Serializable;   
import java.util.ArrayList;    
import java.util.Arrays;       
import java.util.List;         
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;     
/**
 *
 * @author mrnkv
 */
@Named("userRoleBean")  
@SessionScoped
public class UserRoleBean implements Serializable{

    private List<RoleName> roleNames = new ArrayList<>(Arrays.asList(RoleName.values()));
    private RoleName roleName;   
        
    public List<RoleName> getRoleNames() {
        return roleNames;      
    }   
        
    public void setRoleNames(List<RoleName> roleNames) {
        this.roleNames = roleNames;     
    }
    
    public RoleName getRoleName() {
        return roleName;       
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

}

