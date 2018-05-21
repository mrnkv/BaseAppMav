package org.mrnkv.baseappmav.entities.controllers;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.jboss.logging.Logger;
import org.mrnkv.baseappmav.entities.ams.Ams;

import org.mrnkv.baseappmav.entities.umg.*;
import org.mrnkv.baseappmav.entities.facades.*;
import org.mrnkv.baseappmav.entities.util.PaginationHelper;
import org.mrnkv.baseappmav.security.UserManager;

@Named("amsController")
@SessionScoped
public class AmsController implements Serializable {

    private Ams current;
    private DataModel items = null;
    private List<Ams> amsList = null;
    
    
    @EJB
    private AmsFacade ejbFacade;
    

    private int selectedItemIndex;
    private PaginationHelper pagination;
    
    private static final Logger LOGGER = Logger.getLogger(AmsController.class);

    public AmsController() {
    }

    public List<Ams> getAmsList() {
        if (amsList == null) {
            amsList = getFacade().findAll();
        }
        return amsList;
    }

    public void setAmsList(List<Ams> amsList) {
        this.amsList = amsList;
    }

    

    public Ams getSelected() {
        if (current == null) {
            current = new Ams();
            selectedItemIndex = -1;
        }
        return current;
    }

    private AmsFacade getFacade() {
        return ejbFacade;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Ams) getItems().getRowData();
        return "View";
    }

    public String prepareCreate() {
        current = new Ams();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {

        try {
            getFacade().create(current);
            FacesContext.getCurrentInstance().addMessage("MsgId", new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Record successfuly added."));
            LOGGER.info("Record:" +current +" was successfully added by user:" + UserManager.getUserName());
            return prepareCreate();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("MsgId", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Record not added."));
            LOGGER.error("Record:" +current +" not added by user:" + UserManager.getUserName());
            return null;
        }
    }

    public String prepareEdit() {
        current = (Ams) getItems().getRowData();
        //    selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }
    
    public String update() {
        try {
            getFacade().edit(current);
            FacesContext.getCurrentInstance().addMessage("MsgId", new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Record succeffuly updated."));
            return "View";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("MsgId", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Record not updated"));
            return null;
        }
    }


    public String destroy() {
        current = (Ams) getItems().getRowData();
        //selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        //recreatePagination();
        recreateModel();
        return "List.xhtml";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            LOGGER.info("Record:" + current +" was successfully deleted by user:" + UserManager.getUserName());

            //  JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UmgDeleted"));
        } catch (Exception e) {
            LOGGER.error("Record:" + current +" was not deleted by user:" + UserManager.getUserName());
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            //if (pagination.getPageFirstItem() >= count) {
            //    pagination.previousPage();
            //}
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
        amsList = null;
    }

    public String next() {
//        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        //      getPagination().previousPage();
        recreateModel();
        return "List";
    }

    /*
    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }
*/
     
    public Ams getAms(java.lang.Long id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Ams.class)
    public static class AmsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AmsController controller = (AmsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "amsController");
            return controller.getAms(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Umg) {
                Ams o = (Ams) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Umg.class.getName());
            }
        }

    }

}
