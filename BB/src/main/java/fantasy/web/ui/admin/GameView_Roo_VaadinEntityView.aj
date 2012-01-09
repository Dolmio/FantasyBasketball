// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fantasy.web.ui.admin;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Table;
import fantasy.domain.Game;
import fantasy.web.ui.admin.EntityTableColumnGenerator;
import java.lang.Class;
import java.lang.Long;
import java.lang.Object;
import java.lang.String;
import java.util.List;

privileged aspect GameView_Roo_VaadinEntityView {
    
    public String GameView.getEntityName() {
        return "Game";
    }
    
    public Class<Game> GameView.getEntityClass() {
        return Game.class;
    }
    
    public boolean GameView.isCreateAllowed() {
        return true;
    }
    
    public boolean GameView.isUpdateAllowed() {
        return true;
    }
    
    public boolean GameView.isDeleteAllowed() {
        return true;
    }
    
    public List<Game> GameView.listEntities() {
        List<Game> list = Game.findAllGames();
        return list;
    }
    
    public Game GameView.saveEntity(Game entity) {
        if (entity == null) {
            return null;
        }
        return (Game) entity.merge();
    }
    
    public void GameView.deleteEntity(Game entity) {
        if (entity != null) {
            entity.remove();
        }
    }
    
    public boolean GameView.isNewEntity(Game entity) {
        return (entity != null && getIdForEntity(entity) == null);
    }
    
    public String GameView.getIdProperty() {
        return "id";
    }
    
    public String GameView.getVersionProperty() {
        return "version";
    }
    
    public Game GameView.createEntityInstance() {
        return new Game();
    }
    
    public BeanContainer<Long, Game> GameView.getTableContainer() {
        BeanContainer<Long, Game> container = new BeanContainer<Long, Game>(Game.class);
        container.setBeanIdProperty("id");
        for (Game entity : Game.findAllGames()) {
            container.addBean(entity);
        }
        return container;
    }
    
    public Item GameView.getItemForEntity(Game entity) {
        Item item = getTable().getItem(entity.getId());
        if (item == null) {
            item = new BeanItem<Game>(entity);
        }
        return item;
    }
    
    public Game GameView.getEntityForItem(Item item) {
        if (item != null) {
            return ((BeanItem<Game>) item).getBean();
        } else {
            return null;
        }
    }
    
    public Object GameView.getIdForEntity(Game entity) {
        return entity != null ? entity.getId() : null;
    }
    
    public void GameView.setupGeneratedColumns(Table table) {
        table.removeGeneratedColumn("homeTeam");
        table.addGeneratedColumn("homeTeam", new EntityTableColumnGenerator((String) getTeamCaptionPropertyId()));
        table.removeGeneratedColumn("awayTeam");
        table.addGeneratedColumn("awayTeam", new EntityTableColumnGenerator((String) getTeamCaptionPropertyId()));
        table.removeGeneratedColumn("winnerTeam");
        table.addGeneratedColumn("winnerTeam", new EntityTableColumnGenerator((String) getTeamCaptionPropertyId()));
        table.removeGeneratedColumn("round");
        table.addGeneratedColumn("round", new EntityTableColumnGenerator((String) getRoundCaptionPropertyId()));
    }
    
    public Object GameView.getTeamCaptionPropertyId() {
        return "name";
    }
    
    public Object GameView.getRoundCaptionPropertyId() {
        return "name";
    }
    
}