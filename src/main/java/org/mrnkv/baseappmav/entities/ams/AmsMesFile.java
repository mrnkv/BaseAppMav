/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mrnkv.baseappmav.entities.ams;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author mrnkv
 */
@Entity
public class AmsMesFile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String fileName;
    
    @ManyToOne(fetch = FetchType.LAZY)
    AmsMes amsMes;
    
    @ManyToOne(fetch = FetchType.LAZY)
    AmsMesFileType amsMesFileType;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public AmsMes getAmsMes() {
        return amsMes;
    }

    public void setAmsMes(AmsMes amsMes) {
        this.amsMes = amsMes;
    }

    public AmsMesFileType getAmsMesFileType() {
        return amsMesFileType;
    }

    public void setAmsMesFileType(AmsMesFileType amsMesFileType) {
        this.amsMesFileType = amsMesFileType;
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

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AmsMesFile)) {
            return false;
        }
        AmsMesFile other = (AmsMesFile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ams.AmsMesFile[ id=" + id + " ]";
    }
    
}
