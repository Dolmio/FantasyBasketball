// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fantasy.web.ui.admin;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;
import fantasy.domain.GameStat;
import fantasy.domain.Player;
import java.lang.Class;
import java.lang.Long;
import java.lang.Object;
import java.lang.String;
import org.springframework.context.i18n.LocaleContextHolder;
import org.vaadin.addon.customfield.beanfield.BeanFieldWrapper;

privileged aspect GameStatForm_Roo_VaadinAutomaticEntityForm {
    
    public FormFieldFactory GameStatForm.getFormFieldFactory() {
        return new DefaultFieldFactory() {
            @Override
            public Field createField(Item item, Object propertyId, Component uiContext) {
                Field field = null;
                if (getIdProperty().equals(propertyId) || getVersionProperty().equals(propertyId)) {
                    return null;
                } else if ("player".equals(propertyId)) {
                    ComboBox combo = buildPlayerCombo();
                    field = new BeanFieldWrapper<Player>(combo, Player.class, getContainerForPlayers(), "id");
                    field.setCaption(createCaptionByPropertyId(propertyId));
                } else {
                    field = super.createField(item, propertyId, uiContext);
                    if (field instanceof TextField) {
                        ((TextField) field).setNullRepresentation("");
                    }
                    if (field instanceof DateField) {
                        ((DateField) field).setLocale(LocaleContextHolder.getLocale());
                        field.setInvalidAllowed(true);
                    }
                }
                return field;
            }
        };
    }
    
    public BeanContainer<Long, Player> GameStatForm.getContainerForPlayers() {
        BeanContainer<Long, Player> container = new BeanContainer<Long, Player>(Player.class);
        container.setBeanIdProperty("id");
        for (Player entity : Player.findAllPlayers()) {
            container.addBean(entity);
        }
        return container;
    }
    
    public Class<GameStat> GameStatForm.getEntityClass() {
        return GameStat.class;
    }
    
    public Object GameStatForm.getPlayerCaptionPropertyId() {
        return null;
    }
    
    public String GameStatForm.getIdProperty() {
        return "id";
    }
    
    public String GameStatForm.getVersionProperty() {
        return "version";
    }
    
}
