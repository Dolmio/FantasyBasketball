// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fantasy.web.ui.admin;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Table;
import fantasy.domain.Player;
import fantasy.web.ui.admin.EntityTableColumnGenerator;
import java.lang.Class;
import java.lang.Long;
import java.lang.Object;
import java.lang.String;
import java.util.List;

privileged aspect PlayerView_Roo_VaadinEntityView {
    
    public String PlayerView.getEntityName() {
        return "Player";
    }
    
    public Class<Player> PlayerView.getEntityClass() {
        return Player.class;
    }
    
    public boolean PlayerView.isCreateAllowed() {
        return true;
    }
    
    public boolean PlayerView.isUpdateAllowed() {
        return true;
    }
    
    public boolean PlayerView.isDeleteAllowed() {
        return true;
    }
    
    public List<Player> PlayerView.listEntities() {
        List<Player> list = Player.findAllPlayers();
        return list;
    }
    
    public Player PlayerView.saveEntity(Player entity) {
        if (entity == null) {
            return null;
        }
        return (Player) entity.merge();
    }
    
    public void PlayerView.deleteEntity(Player entity) {
        if (entity != null) {
            entity.remove();
        }
    }
    
    public boolean PlayerView.isNewEntity(Player entity) {
        return (entity != null && getIdForEntity(entity) == null);
    }
    
    public String PlayerView.getIdProperty() {
        return "id";
    }
    
    public String PlayerView.getVersionProperty() {
        return "version";
    }
    
    public Player PlayerView.createEntityInstance() {
        return new Player();
    }
    
    public BeanContainer<Long, Player> PlayerView.getTableContainer() {
        BeanContainer<Long, Player> container = new BeanContainer<Long, Player>(Player.class);
        container.setBeanIdProperty("id");
        for (Player entity : Player.findAllPlayers()) {
            container.addBean(entity);
        }
        return container;
    }
    
    public Item PlayerView.getItemForEntity(Player entity) {
        Item item = getTable().getItem(entity.getId());
        if (item == null) {
            item = new BeanItem<Player>(entity);
        }
        return item;
    }
    
    public Player PlayerView.getEntityForItem(Item item) {
        if (item != null) {
            return ((BeanItem<Player>) item).getBean();
        } else {
            return null;
        }
    }
    
    public Object PlayerView.getIdForEntity(Player entity) {
        return entity != null ? entity.getId() : null;
    }
    
    public void PlayerView.setupGeneratedColumns(Table table) {
        table.removeGeneratedColumn("team");
        table.addGeneratedColumn("team", new EntityTableColumnGenerator((String) getTeamCaptionPropertyId()));
        table.removeGeneratedColumn("stats");
        table.addGeneratedColumn("stats", new EntityTableColumnGenerator((String) getGameStatCaptionPropertyId()));
    }
    
    public Object PlayerView.getPlayerPositionCaptionPropertyId() {
        return null;
    }
    
    public Object PlayerView.getTeamPositionCaptionPropertyId() {
        return null;
    }
    
    public Object PlayerView.getTeamCaptionPropertyId() {
        return "name";
    }
    
    public Object PlayerView.getGameStatCaptionPropertyId() {
        return null;
    }
    
}
