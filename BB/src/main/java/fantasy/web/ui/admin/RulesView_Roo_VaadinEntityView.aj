// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fantasy.web.ui.admin;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Table;
import fantasy.domain.Rules;
import fantasy.web.ui.admin.EntityTableColumnGenerator;
import java.lang.Class;
import java.lang.Long;
import java.lang.String;
import java.util.List;

privileged aspect RulesView_Roo_VaadinEntityView {
    
    public String RulesView.getEntityName() {
        return "Rules";
    }
    
    public Class<Rules> RulesView.getEntityClass() {
        return Rules.class;
    }
    
    public boolean RulesView.isCreateAllowed() {
        return true;
    }
    
    public boolean RulesView.isUpdateAllowed() {
        return true;
    }
    
    public boolean RulesView.isDeleteAllowed() {
        return true;
    }
    
    public List<Rules> RulesView.listEntities() {
        List<Rules> list = Rules.findAllRuleses();
        return list;
    }
    
    public Rules RulesView.saveEntity(Rules entity) {
        if (entity == null) {
            return null;
        }
        return (Rules) entity.merge();
    }
    
    public void RulesView.deleteEntity(Rules entity) {
        if (entity != null) {
            entity.remove();
        }
    }
    
    public boolean RulesView.isNewEntity(Rules entity) {
        return (entity != null && getIdForEntity(entity) == null);
    }
    
    public String RulesView.getIdProperty() {
        return "id";
    }
    
    public String RulesView.getVersionProperty() {
        return "version";
    }
    
    public Rules RulesView.createEntityInstance() {
        return new Rules();
    }
    
    public BeanContainer<Long, Rules> RulesView.getTableContainer() {
        BeanContainer<Long, Rules> container = new BeanContainer<Long, Rules>(Rules.class);
        container.setBeanIdProperty("id");
        for (Rules entity : Rules.findAllRuleses()) {
            container.addBean(entity);
        }
        return container;
    }
    
    public Item RulesView.getItemForEntity(Rules entity) {
        Item item = getTable().getItem(entity.getId());
        if (item == null) {
            item = new BeanItem<Rules>(entity);
        }
        return item;
    }
    
    public Rules RulesView.getEntityForItem(Item item) {
        if (item != null) {
            return ((BeanItem<Rules>) item).getBean();
        } else {
            return null;
        }
    }
    
    public Object RulesView.getIdForEntity(Rules entity) {
        return entity != null ? entity.getId() : null;
    }
    
    public void RulesView.setupGeneratedColumns(Table table) {
        // Add generated columns here
    }
    
}
