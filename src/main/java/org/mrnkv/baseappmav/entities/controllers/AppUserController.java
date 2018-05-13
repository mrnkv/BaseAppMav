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
import javax.faces.model.SelectItem;

import org.mrnkv.baseappmav.entities.umg.*;
import org.mrnkv.baseappmav.entities.facades.*;
import org.mrnkv.baseappmav.entities.util.PaginationHelper;

import org.jboss.logging.Logger;
import org.mrnkv.baseappmav.entities.user.AppUser;
import org.mrnkv.baseappmav.entities.user.UserRole;
import org.mrnkv.baseappmav.entities.util.JsfUtil;

import org.mrnkv.baseappmav.security.UserManager;

@Named("appUserController")
@SessionScoped
public class AppUserController implements Serializable {

    private AppUser current;
    private DataModel items = null;
    private List<UserRole> roles;

    @EJB
    private UserFacade ejbFacade;
    
    @EJB
    private RoleFacade ejbRoleFacade;


    private int selectedItemIndex;
    private PaginationHelper pagination;
    
    private static final Logger LOGGER = Logger.getLogger(AppUserController.class);

    public AppUserController() {
    }

    private RoleFacade getEjbRoleFacade(){
        return ejbRoleFacade;
    }

    public List<UserRole> getRoles(){
        roles = getEjbRoleFacade().findAll();
        return roles;
    }

    public AppUser getSelected() {
        if (current == null) {
            current = new AppUser();
            selectedItemIndex = -1;
        }
        return current;
    }

    private UserFacade getFacade() {
        return ejbFacade;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (AppUser) getItems().getRowData();
//        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new AppUser();
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
        current = (AppUser) getItems().getRowData();
        //    selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            FacesContext.getCurrentInstance().addMessage("MsgId", new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Record succeffuly updated added."));
            return "View";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("MsgId", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Record not updated"));
            return null;
        }
    }

    public String destroy() {
        current = (AppUser) getItems().getRowData();
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
     
    public AppUser getAppUser(java.lang.Long id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = AppUser.class)
    public static class AppUserControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AppUserController controller = (AppUserController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "appUserController");
            return controller.getAppUser(getKey(value));
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
                AppUser o = (AppUser) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Umg.class.getName());
            }
        }

    }

}
