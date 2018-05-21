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
public enum AmsType {
    /*
    MAST,
    TOWER;
    */
    
    MAST("Мачта"),
    TOWER("Башня");
    
    private final String type;
    
    AmsType(String type) { 
        this.type = type;
    }
    
    public String getTypeString(){
        return type;
    }

}
