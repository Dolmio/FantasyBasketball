// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fantasy.web.ui;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Table;
import fantasy.domain.Team;
import fantasy.web.EntityTableColumnGenerator;
import java.lang.Class;
import java.lang.Long;
import java.lang.Object;
import java.lang.String;
import java.util.List;

privileged aspect TeamView_Roo_VaadinEntityView {
    
    public String TeamView.getEntityName() {
        return "Team";
    }
    
    public Class<Team> TeamView.getEntityClass() {
        return Team.class;
    }
    
    public boolean TeamView.isCreateAllowed() {
        return true;
    }
    
    public boolean TeamView.isUpdateAllowed() {
        return true;
    }
    
    public boolean TeamView.isDeleteAllowed() {
        return true;
    }
    
    public List<Team> TeamView.listEntities() {
        List<Team> list = Team.findAllTeams();
        return list;
    }
    
    public Team TeamView.saveEntity(Team entity) {
        if (entity == null) {
            return null;
        }
        return (Team) entity.merge();
    }
    
    public void TeamView.deleteEntity(Team entity) {
        if (entity != null) {
            entity.remove();
        }
    }
    
    public boolean TeamView.isNewEntity(Team entity) {
        return (entity != null && getIdForEntity(entity) == null);
    }
    
    public String TeamView.getIdProperty() {
        return "id";
    }
    
    public String TeamView.getVersionProperty() {
        return "version";
    }
    
    public Team TeamView.createEntityInstance() {
        return new Team();
    }
    
    public BeanContainer<Long, Team> TeamView.getTableContainer() {
        BeanContainer<Long, Team> container = new BeanContainer<Long, Team>(Team.class);
        container.setBeanIdProperty("id");
        for (Team entity : Team.findAllTeams()) {
            container.addBean(entity);
        }
        return container;
    }
    
    public Item TeamView.getItemForEntity(Team entity) {
        Item item = getTable().getItem(entity.getId());
        if (item == null) {
            item = new BeanItem<Team>(entity);
        }
        return item;
    }
    
    public Team TeamView.getEntityForItem(Item item) {
        if (item != null) {
            return ((BeanItem<Team>) item).getBean();
        } else {
            return null;
        }
    }
    
    public Object TeamView.getIdForEntity(Team entity) {
        return entity != null ? entity.getId() : null;
    }
    
    public void TeamView.setupGeneratedColumns(Table table) {
        table.removeGeneratedColumn("players");
        table.addGeneratedColumn("players", new EntityTableColumnGenerator((String) getPlayerCaptionPropertyId()));
    }
    
    public Object TeamView.getPlayerCaptionPropertyId() {
        return null;
    }
    
}
