// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fantasy.web.ui.admin;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Table;
import fantasy.domain.News;
import fantasy.web.ui.admin.EntityTableColumnGenerator;
import java.lang.Class;
import java.lang.Long;
import java.lang.String;
import java.util.List;

privileged aspect NewsView_Roo_VaadinEntityView {
    
    public String NewsView.getEntityName() {
        return "News";
    }
    
    public Class<News> NewsView.getEntityClass() {
        return News.class;
    }
    
    public boolean NewsView.isCreateAllowed() {
        return true;
    }
    
    public boolean NewsView.isUpdateAllowed() {
        return true;
    }
    
    public boolean NewsView.isDeleteAllowed() {
        return true;
    }
    
    public List<News> NewsView.listEntities() {
        List<News> list = News.findAllNews();
        return list;
    }
    
    public News NewsView.saveEntity(News entity) {
        if (entity == null) {
            return null;
        }
        return (News) entity.merge();
    }
    
    public void NewsView.deleteEntity(News entity) {
        if (entity != null) {
            entity.remove();
        }
    }
    
    public boolean NewsView.isNewEntity(News entity) {
        return (entity != null && getIdForEntity(entity) == null);
    }
    
    public String NewsView.getIdProperty() {
        return "id";
    }
    
    public String NewsView.getVersionProperty() {
        return "version";
    }
    
    public News NewsView.createEntityInstance() {
        return new News();
    }
    
    public BeanContainer<Long, News> NewsView.getTableContainer() {
        BeanContainer<Long, News> container = new BeanContainer<Long, News>(News.class);
        container.setBeanIdProperty("id");
        for (News entity : News.findAllNews()) {
            container.addBean(entity);
        }
        return container;
    }
    
    public Item NewsView.getItemForEntity(News entity) {
        Item item = getTable().getItem(entity.getId());
        if (item == null) {
            item = new BeanItem<News>(entity);
        }
        return item;
    }
    
    public News NewsView.getEntityForItem(Item item) {
        if (item != null) {
            return ((BeanItem<News>) item).getBean();
        } else {
            return null;
        }
    }
    
    public Object NewsView.getIdForEntity(News entity) {
        return entity != null ? entity.getId() : null;
    }
    
    public void NewsView.setupGeneratedColumns(Table table) {
        // Add generated columns here
    }
    
}
