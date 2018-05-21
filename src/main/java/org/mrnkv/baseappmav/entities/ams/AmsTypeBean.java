/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mrnkv.baseappmav.entities.ams;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author mrnkv
 */
@Named(value="amsTypeBean")
@SessionScoped
public class AmsTypeBean implements Serializable{
    private final List<AmsType> amsTypes = new ArrayList<>(Arrays.asList(AmsType.values()));
    private static final Map<AmsType, String> typeToString = new HashMap<>();
    private AmsType amsType;
    private String amsTypeString;
    
    public List<AmsType> getAmsTypes() {
        return amsTypes;      
    }   
        
    public AmsType getAmsType() {
        return amsType;       
    }
}
