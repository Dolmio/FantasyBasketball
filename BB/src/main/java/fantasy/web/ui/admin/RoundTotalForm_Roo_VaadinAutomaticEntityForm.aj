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
import fantasy.domain.Round;
import fantasy.domain.RoundTotal;
import java.lang.Class;
import java.lang.Long;
import java.lang.Object;
import java.lang.String;
import org.springframework.context.i18n.LocaleContextHolder;
import org.vaadin.addon.customfield.beanfield.BeanFieldWrapper;

privileged aspect RoundTotalForm_Roo_VaadinAutomaticEntityForm {
    
    public ComboBox RoundTotalForm.buildRoundCombo() {
        ComboBox combo = new ComboBox(null, getContainerForRounds());
        Object captionPropertyId = getRoundCaptionPropertyId();
        if (captionPropertyId != null) {
            combo.setItemCaptionPropertyId(captionPropertyId);
        }
        return combo;
    }
    
    public FormFieldFactory RoundTotalForm.getFormFieldFactory() {
        return new DefaultFieldFactory() {
            @Override
            public Field createField(Item item, Object propertyId, Component uiContext) {
                Field field = null;
                if (getIdProperty().equals(propertyId) || getVersionProperty().equals(propertyId)) {
                    return null;
                } else if ("round".equals(propertyId)) {
                    ComboBox combo = buildRoundCombo();
                    field = new BeanFieldWrapper<Round>(combo, Round.class, getContainerForRounds(), "id");
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
    
    public BeanContainer<Long, Round> RoundTotalForm.getContainerForRounds() {
        BeanContainer<Long, Round> container = new BeanContainer<Long, Round>(Round.class);
        container.setBeanIdProperty("id");
        for (Round entity : Round.findAllRounds()) {
            container.addBean(entity);
        }
        return container;
    }
    
    public Class<RoundTotal> RoundTotalForm.getEntityClass() {
        return RoundTotal.class;
    }
    
    public Object RoundTotalForm.getRoundCaptionPropertyId() {
        return "name";
    }
    
    public String RoundTotalForm.getIdProperty() {
        return "id";
    }
    
    public String RoundTotalForm.getVersionProperty() {
        return "version";
    }
    
}
