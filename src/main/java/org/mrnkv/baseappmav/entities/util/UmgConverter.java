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
import org.mrnkv.baseappmav.entities.facades.UmgFacade;
import org.mrnkv.baseappmav.entities.umg.Umg;

/**
 *
 * @author mrnkvimplements Serializable
 */
@Named("umgConverter")
public class UmgConverter implements Converter{
    private static final Logger LOGGER = Logger.getLogger(UmgFacade.class);

        
    @PersistenceContext(unitName = "org.mrnkv_BaseAppMav_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        LOGGER.info("Get as object begin");
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root<Umg> root = cq.from(Umg.class);
        cq.select(root).where(cb.equal(root.get("shortName"), value));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return (Umg)q.getSingleResult();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        LOGGER.info("Get as string begin");
        String retval = null;
        try{
            Umg umg = (Umg)value;
            retval = umg.getShortName();
        }catch(Throwable ex){
            
        }
        return retval;
    }
    
}
