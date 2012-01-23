package fantasy.web.ui.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.context.i18n.LocaleContextHolder;
import org.vaadin.addon.customfield.beanfield.BeanFieldWrapper;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.spring.roo.addon.annotations.RooVaadinAutomaticEntityForm;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;

import fantasy.domain.Team;
import fantasy.domain.positions.PlayerPosition;

@RooVaadinAutomaticEntityForm(formBackingObject = fantasy.domain.Player.class)
public class PlayerForm extends AutomaticEntityForm<fantasy.domain.Player> {
	
	private static final long serialVersionUID = 1L;
	private BeanItemContainer<PlayerPosition> containerForPlayerPositions;
	
	public PlayerForm() {
		super(fantasy.domain.Player.class);
		
		
		FormFieldFactory fff =   new DefaultFieldFactory() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Field createField(Item item, Object propertyId, Component uiContext) {
				Field field = null;
				if (getIdProperty().equals(propertyId) || getVersionProperty().equals(propertyId)) {
					return null;
				} else if ("possiblePositionsImp".equals(propertyId)) {
					field = buildPossiblePositionsMultiSelect();
					field.setCaption("Possible Positions");
					
				} 
				else if ("team".equals(propertyId)) {
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
		getForm().setFormFieldFactory(fff);
	}
	
	
	/**
	 * returns fields to show in the form
	 */
	@Override
	protected Collection<?> getItemPropertyIds(Item item) {
		
		ArrayList<Object> properties = new ArrayList<Object>(Arrays.asList(new Object[] {"firstName", "lastName", "team","value", "possiblePositionsImp", 
				"currentPosition"}));
		
		
		return properties;
	}
	
	 public TwinColSelect buildPossiblePositionsMultiSelect() {
	        TwinColSelect select = new TwinColSelect(null, getContainerForPlayerPositions());
	        Object captionPropertyId = getPlayerPositionCaptionPropertyId();
	        if (captionPropertyId != null) {
	            select.setItemCaptionPropertyId(captionPropertyId);
	        }
	        return select;
	    }
	 public BeanItemContainer<PlayerPosition> getContainerForPlayerPositions() {
	        if (containerForPlayerPositions == null) {
	            Collection<PlayerPosition> items = Arrays.asList(PlayerPosition.class.getEnumConstants());
	            @SuppressWarnings("deprecation")
				BeanItemContainer<PlayerPosition> container = new BeanItemContainer<PlayerPosition>(items);
	            containerForPlayerPositions = container;
	        }
	        return containerForPlayerPositions;
	    }
	 
	 
}
