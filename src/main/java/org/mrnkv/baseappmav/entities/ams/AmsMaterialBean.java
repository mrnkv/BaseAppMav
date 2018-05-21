/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mrnkv.baseappmav.entities.ams;

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
@Named(value="amsMaterialBean")
@SessionScoped
public class AmsMaterialBean implements Serializable{
    private List<AmsMaterial> amsMaterials = new ArrayList<>(Arrays.asList(AmsMaterial.values()));
    private AmsMaterial amsMaterial;   
        
    public List<AmsMaterial> getAmsMaterials() {
        return amsMaterials;      
    }   
        
    public AmsMaterial getAmsMaterial() {
        return amsMaterial;       
    }
}
