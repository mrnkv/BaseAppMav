/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mrnkv.baseappmav.entities.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.mrnkv.baseappmav.entities.ams.Ams;

/**
 *
 * @author mrnkv
 */
@Stateless
public class AmsFacade extends AbstractFacade<Ams> {

    @PersistenceContext(unitName = "org.mrnkv_BaseAppMav_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AmsFacade() {
        super(Ams.class);
    }
    
}
