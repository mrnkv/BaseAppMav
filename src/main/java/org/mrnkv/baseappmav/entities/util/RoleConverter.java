/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mrnkv.baseappmav.entities.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import org.jboss.logging.Logger;
import org.mrnkv.baseappmav.entities.umg.Umg;
import org.mrnkv.baseappmav.entities.user.RoleName;
import org.mrnkv.baseappmav.entities.user.UserRole;

/**
 *
 * @author mrnkv
 */
@Named("roleConverter")
public class RoleConverter implements Converter{
    
    private static final Logger LOGGER = Logger.getLogger(RoleConverter.class);
        
    @PersistenceContext(unitName = "org.mrnkv_BaseAppMav_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        LOGGER.info("To Object begin string = " + value );
        UserRole role = new UserRole();
        LOGGER.info("To Object create role");
        role.setId((Long)111L);
        LOGGER.info("To Object set Id");

        role.setRoleName(RoleName.KLS_ADMIN);
        LOGGER.info("To Object set Rolename");
        
        
        /*
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root<UserRole> root = cq.from(UserRole.class);
        cq.select(root).where(cb.equal(root.get("roleName"), RoleName.valueOf(value)));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return (UserRole)q.getSingleResult();
        */
        return role;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        LOGGER.info("To String create role");
        String retval = null;
        try{
            UserRole role = (UserRole)value;
            retval = role.getRoleName().toString();
        }catch(Throwable ex){
            
        }
        LOGGER.info("To String =" + retval);
        
        return "String2";
    }
    
    
}
