/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mrnkv.baseappmav.entities.ams;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.mrnkv.baseappmav.entities.umg.*;

/**
 *
 * @author mrnkv
 */
@Entity
public class Ams implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    private Float height;
    private String project;
    private int buildYear;
    
    @Enumerated(EnumType.STRING)
    private AmsMaterial material;

    @Enumerated(EnumType.STRING)
    private AmsType type;
    
    @ManyToOne(fetch = FetchType.LAZY)
    Umg umg;
    
    @OneToMany(mappedBy = "ams")
    List<AmsMes> meses;

    public int getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(int buildYear) {
        this.buildYear = buildYear;
    }

    
    
    public List<AmsMes> getMeses() {
        return meses;
    }

    public void setMeses(List<AmsMes> meses) {
        this.meses = meses;
    }

    public AmsMaterial getMaterial() {
        return material;
    }

    public void setMaterial(AmsMaterial material) {
        this.material = material;
    }

    public AmsType getType() {
        return type;
    }

    public void setType(AmsType type) {
        this.type = type;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Umg getUmg() {
        return umg;
    }

    public void setUmg(Umg umg) {
        this.umg = umg;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ams)) {
            return false;
        }
        Ams other = (Ams) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return name;
    }
    
}
