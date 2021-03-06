// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fantasy.web.ui.admin;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Table;
import fantasy.domain.RoundTotal;
import fantasy.web.ui.admin.EntityTableColumnGenerator;
import java.lang.Class;
import java.lang.Long;
import java.lang.Object;
import java.lang.String;
import java.util.List;

privileged aspect RoundTotalView_Roo_VaadinEntityView {
    
    public String RoundTotalView.getEntityName() {
        return "Round Total";
    }
    
    public Class<RoundTotal> RoundTotalView.getEntityClass() {
        return RoundTotal.class;
    }
    
    public boolean RoundTotalView.isCreateAllowed() {
        return true;
    }
    
    public boolean RoundTotalView.isUpdateAllowed() {
        return true;
    }
    
    public boolean RoundTotalView.isDeleteAllowed() {
        return true;
    }
    
    public List<RoundTotal> RoundTotalView.listEntities() {
        List<RoundTotal> list = RoundTotal.findAllRoundTotals();
        return list;
    }
    
    public RoundTotal RoundTotalView.saveEntity(RoundTotal entity) {
        if (entity == null) {
            return null;
        }
        return (RoundTotal) entity.merge();
    }
    
    public void RoundTotalView.deleteEntity(RoundTotal entity) {
        if (entity != null) {
            entity.remove();
        }
    }
    
    public boolean RoundTotalView.isNewEntity(RoundTotal entity) {
        return (entity != null && getIdForEntity(entity) == null);
    }
    
    public String RoundTotalView.getIdProperty() {
        return "id";
    }
    
    public String RoundTotalView.getVersionProperty() {
        return "version";
    }
    
    public RoundTotal RoundTotalView.createEntityInstance() {
        return new RoundTotal();
    }
    
    public BeanContainer<Long, RoundTotal> RoundTotalView.getTableContainer() {
        BeanContainer<Long, RoundTotal> container = new BeanContainer<Long, RoundTotal>(RoundTotal.class);
        container.setBeanIdProperty("id");
        for (RoundTotal entity : RoundTotal.findAllRoundTotals()) {
            container.addBean(entity);
        }
        return container;
    }
    
    public Item RoundTotalView.getItemForEntity(RoundTotal entity) {
        Item item = getTable().getItem(entity.getId());
        if (item == null) {
            item = new BeanItem<RoundTotal>(entity);
        }
        return item;
    }
    
    public RoundTotal RoundTotalView.getEntityForItem(Item item) {
        if (item != null) {
            return ((BeanItem<RoundTotal>) item).getBean();
        } else {
            return null;
        }
    }
    
    public Object RoundTotalView.getIdForEntity(RoundTotal entity) {
        return entity != null ? entity.getId() : null;
    }
    
    public void RoundTotalView.setupGeneratedColumns(Table table) {
        table.removeGeneratedColumn("round");
        table.addGeneratedColumn("round", new EntityTableColumnGenerator((String) getRoundCaptionPropertyId()));
        table.removeGeneratedColumn("team");
        table.addGeneratedColumn("team", new EntityTableColumnGenerator((String) getTeamCaptionPropertyId()));
    }
    
    public Object RoundTotalView.getRoundCaptionPropertyId() {
        return "name";
    }
    
    public Object RoundTotalView.getTeamCaptionPropertyId() {
        return "name";
    }
    
}
