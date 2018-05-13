/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mrnkv.baseappmav.entities.umg;

import org.mrnkv.baseappmav.entities.ams.*;
import org.mrnkv.baseappmav.entities.user.AppUser;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Proxy;

/**
 *
 * @author mrnkv
 */
@Entity
@Proxy(lazy=false)
public class Umg implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String shortName;
    private String longName;
    private String address;
    @OneToMany(mappedBy = "umg")
    List<Ams> amses;
    @OneToMany(mappedBy = "umg")
    List<AppUser> users;


    public List<Ams> getAmses() {
        return amses;
    }

    public void setAmses(List<Ams> amses) {
        this.amses = amses;
    }
    

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        if (!(object instanceof Umg)) {
            return false;
        }
        Umg other = (Umg) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ("UMG shortName:"+shortName+" longName:"+longName);
    }
    
}
