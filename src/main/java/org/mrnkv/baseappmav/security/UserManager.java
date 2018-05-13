/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mrnkv.baseappmav.security;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author mrnkv
 */
@Named(value = "userManager")
@SessionScoped
public class UserManager implements Serializable{

    /**
     * Creates a new instance of UserManager
     */
    
    public static String getUserName() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext externalContext = fc.getExternalContext();
        if (externalContext.getUserPrincipal() == null){
            return null;
        }else{
            String userName = externalContext.getUserPrincipal().getName();
            return userName;
        }
    }
    
    public UserManager() {
    }
    
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/faces/main.xhtml";   
    }
    
}
