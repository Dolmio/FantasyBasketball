package fantasy.web.ui.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.springframework.context.i18n.LocaleContextHolder;
import org.vaadin.addon.customfield.beanfield.BeanSetFieldWrapper;


import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.spring.roo.addon.annotations.RooVaadinAutomaticEntityForm;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;

import fantasy.domain.Game;
import fantasy.domain.Player;
import fantasy.domain.Round;
import fantasy.domain.RoundTotal;
import fantasy.domain.Team;

@RooVaadinAutomaticEntityForm(formBackingObject = fantasy.domain.Team.class)
public class TeamForm extends AutomaticEntityForm<fantasy.domain.Team> {

	public TeamForm() {
		super(fantasy.domain.Team.class);


		
		getForm().setFormFieldFactory(getFormFieldFactory());
	}


	@Override
	protected Collection<?> getItemPropertyIds(Item item) {
		if (null == item) {
			return Collections.emptyList();
		}
	
		ArrayList<Object> properties = new ArrayList<Object>(Arrays.asList(new Object[]{"name"}));
		
		
		return properties;
	}
	
	
	 public FormFieldFactory getFormFieldFactory() {
	        return new DefaultFieldFactory() {
	            @Override
	            public Field createField(Item item, Object propertyId, Component uiContext) {
	                Field field = null;
	                if (getIdProperty().equals(propertyId) || getVersionProperty().equals(propertyId)) {
	                    return null;
	                } else if ("players".equals(propertyId)) {
	                    TwinColSelect select = buildPlayersMultiSelect();
	                    field = new BeanSetFieldWrapper<Player>(select, Player.class, getContainerForPlayers(), "lastName");
	                    field.setCaption(createCaptionByPropertyId(propertyId));
	                    
	                }else if("roundTotals".equals(propertyId)){
	                	TwinColSelect select = buildRoundTotalsMultiSelect();
	                	field = new BeanSetFieldWrapper<RoundTotal>(select, RoundTotal.class, getContainerForRoundTotals(), "id");
	                	field.setCaption(createCaptionByPropertyId(propertyId));
	                	
	                }else if("games".equals(propertyId)){
	                	TwinColSelect select = buildGamesMultiSelect();
	                	field = new BeanSetFieldWrapper<Game>(select, Game.class, getContainerForGames(), "id");
	                	field.setCaption(createCaptionByPropertyId(propertyId));
	                
	                	
	                }else if("wins".equals(propertyId)){
	                	TwinColSelect select = buildGamesMultiSelect();
	                	field = new BeanSetFieldWrapper<Game>(select, Game.class, getContainerForGames(), "id");
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
	
	
	 public BeanContainer<String, Player> getContainerForPlayers() {
	        BeanContainer<String, Player> container = new BeanContainer<String, Player>(Player.class);
	        container.setBeanIdProperty("lastName");
	        for (Player entity : Player.findAllPlayers()) {
	            container.addBean(entity);
	        }
	        return container;
	    }
	 
	 
	 
	 public TwinColSelect buildPlayersMultiSelect() {
		 	BeanContainer<String, Player> players = getContainerForPlayers();
		 
		 	TwinColSelect select = new TwinColSelect(null, players);
		 	
	        //change item caption values to "lastname + firstname"
		 	for(String id : players.getItemIds()){
	        	select.setItemCaption(id,  players.getItem(id).getItemProperty("lastName").getValue() + " " +
	        			 players.getItem(id).getItemProperty("firstName").getValue());
	        }
	        Object captionPropertyId = getPlayerCaptionPropertyId();
	        if (captionPropertyId != null) {
	            select.setItemCaptionPropertyId(captionPropertyId);
	        }
	        return select;
	    }
	 
	 public TwinColSelect buildRoundTotalsMultiSelect(){
		 BeanContainer<Long, RoundTotal> totals = getContainerForRoundTotals();
		 TwinColSelect select = new TwinColSelect(null, totals);
		 	
	        //change item caption values to "lastname + firstname"
		 	for(Long id : totals.getItemIds()){
	        	Round round = (Round) totals.getItem(id).getItemProperty("round").getValue();
		 		Team team = (Team) totals.getItem(id).getItemProperty("team").getValue();
		 		
	        	select.setItemCaption(id, round.getName() + " " + team.getName());
	        }
	        Object captionPropertyId = getPlayerCaptionPropertyId();
	        if (captionPropertyId != null) {
	            select.setItemCaptionPropertyId(captionPropertyId);
	        }
	        return select;
	 }
	 
	
	 public TwinColSelect buildGamesMultiSelect(){
		 BeanContainer<Long, Game> games = getContainerForGames();
		 
		 TwinColSelect select = new TwinColSelect(null, games);
		 	
	        //change item caption values to "lastname + firstname"
		 	for(Long id : games.getItemIds()){
	        	Team homeTeam = (Team) games.getItem(id).getItemProperty("homeTeam").getValue();
		 		Team awayTeam = (Team) games.getItem(id).getItemProperty("awayTeam").getValue();
		 		Round round = (Round) games.getItem(id).getItemProperty("round").getValue();
	        	select.setItemCaption(id, homeTeam.getName() + " VS " + awayTeam.getName() + " " + round.getName());
	        }
		 	select.setWidth("500px");
	        return select;
	 }
	 
}
