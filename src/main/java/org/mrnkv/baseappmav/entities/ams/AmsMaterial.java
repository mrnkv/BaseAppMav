/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mrnkv.baseappmav.entities.ams;

/**
 *
 * @author mrnkv
 */
public enum AmsMaterial {
    STEEL("Сталь"),
    ALUMINIUM("Алюминий");
    private final String material;
    
    AmsMaterial(String material) { 
        this.material = material;
    }
    
    public String getMaterialString(){
        return material;
    }


}
