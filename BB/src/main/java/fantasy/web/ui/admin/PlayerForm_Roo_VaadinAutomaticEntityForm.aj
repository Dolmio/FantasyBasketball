// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fantasy.web.ui.admin;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;
import fantasy.domain.GameStat;
import fantasy.domain.Player;
import fantasy.domain.Team;
import fantasy.domain.positions.TeamPosition;
import java.lang.Class;
import java.lang.Long;
import java.lang.Object;
import java.lang.String;
import java.util.Arrays;
import java.util.Collection;
import org.springframework.context.i18n.LocaleContextHolder;
import org.vaadin.addon.customfield.beanfield.BeanFieldWrapper;
import org.vaadin.addon.customfield.beanfield.BeanSetFieldWrapper;

privileged aspect PlayerForm_Roo_VaadinAutomaticEntityForm {
    
    private BeanItemContainer<TeamPosition> PlayerForm.containerForTeamPositions;
    
    public TwinColSelect PlayerForm.buildPossiblePositionsImpMultiSelect() {
        TwinColSelect select = new TwinColSelect(null, getContainerForPlayerPositions());
        Object captionPropertyId = getPlayerPositionCaptionPropertyId();
        if (captionPropertyId != null) {
            select.setItemCaptionPropertyId(captionPropertyId);
        }
        return select;
    }
    
    public TwinColSelect PlayerForm.buildStatsMultiSelect() {
        TwinColSelect select = new TwinColSelect(null, getContainerForGameStats());
        Object captionPropertyId = getGameStatCaptionPropertyId();
        if (captionPropertyId != null) {
            select.setItemCaptionPropertyId(captionPropertyId);
        }
        return select;
    }
    
    public ComboBox PlayerForm.buildTeamCombo() {
        ComboBox combo = new ComboBox(null, getContainerForTeams());
        Object captionPropertyId = getTeamCaptionPropertyId();
        if (captionPropertyId != null) {
            combo.setItemCaptionPropertyId(captionPropertyId);
        }
        return combo;
    }
    
    public ComboBox PlayerForm.buildCurrentPositionCombo() {
        ComboBox combo = new ComboBox(null, getContainerForTeamPositions());
        Object captionPropertyId = getTeamPositionCaptionPropertyId();
        if (captionPropertyId != null) {
            combo.setItemCaptionPropertyId(captionPropertyId);
        }
        return combo;
    }
    
    public FormFieldFactory PlayerForm.getFormFieldFactory() {
        return new DefaultFieldFactory() {
            @Override
            public Field createField(Item item, Object propertyId, Component uiContext) {
                Field field = null;
                if (getIdProperty().equals(propertyId) || getVersionProperty().equals(propertyId)) {
                    return null;
                } else if ("possiblePositionsImp".equals(propertyId)) {
                    field = buildPossiblePositionsImpMultiSelect();
                    field.setCaption(createCaptionByPropertyId(propertyId));
                } else if ("stats".equals(propertyId)) {
                    TwinColSelect select = buildStatsMultiSelect();
                    field = new BeanSetFieldWrapper<GameStat>(select, GameStat.class, getContainerForGameStats(), "id");
                    field.setCaption(createCaptionByPropertyId(propertyId));
                } else if ("possiblePositions".equals(propertyId)) {
                    field = buildPossiblePositionsMultiSelect();
                    field.setCaption(createCaptionByPropertyId(propertyId));
                } else if ("team".equals(propertyId)) {
                    ComboBox combo = buildTeamCombo();
                    field = new BeanFieldWrapper<Team>(combo, Team.class, getContainerForTeams(), "id");
                    field.setCaption(createCaptionByPropertyId(propertyId));
                } else if ("currentPosition".equals(propertyId)) {
                    field = buildCurrentPositionCombo();
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
    
    public BeanItemContainer<TeamPosition> PlayerForm.getContainerForTeamPositions() {
        if (containerForTeamPositions == null) {
            Collection<TeamPosition> items = Arrays.asList(TeamPosition.class.getEnumConstants());
            BeanItemContainer<TeamPosition> container = new BeanItemContainer<TeamPosition>(items);
            containerForTeamPositions = container;
        }
        return containerForTeamPositions;
    }
    
    public BeanContainer<Long, Team> PlayerForm.getContainerForTeams() {
        BeanContainer<Long, Team> container = new BeanContainer<Long, Team>(Team.class);
        container.setBeanIdProperty("id");
        for (Team entity : Team.findAllTeams()) {
            container.addBean(entity);
        }
        return container;
    }
    
    public BeanContainer<Long, GameStat> PlayerForm.getContainerForGameStats() {
        BeanContainer<Long, GameStat> container = new BeanContainer<Long, GameStat>(GameStat.class);
        container.setBeanIdProperty("id");
        for (GameStat entity : GameStat.findAllGameStats()) {
            container.addBean(entity);
        }
        return container;
    }
    
    public Class<Player> PlayerForm.getEntityClass() {
        return Player.class;
    }
    
    public Object PlayerForm.getPlayerPositionCaptionPropertyId() {
        return null;
    }
    
    public Object PlayerForm.getTeamPositionCaptionPropertyId() {
        return null;
    }
    
    public Object PlayerForm.getTeamCaptionPropertyId() {
        return "name";
    }
    
    public Object PlayerForm.getGameStatCaptionPropertyId() {
        return null;
    }
    
    public String PlayerForm.getIdProperty() {
        return "id";
    }
    
    public String PlayerForm.getVersionProperty() {
        return "version";
    }
    
}
