package fantasy.web.ui;

import java.util.Arrays;
import java.util.List;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Item;
import fantasy.domain.Team;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;

import fantasy.domain.Player;
import fantasy.web.FantasyApplication;

public class PlayerEditor extends EntityEditor {

	public PlayerEditor() {
		super(Player.class, 
				Arrays.asList(new String[] {"firstName", "lastName", "team"}));
		
		
	}
	
	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {
		if(propertyId.equals("team")){
			JPAContainer<Team> teams = JPAContainerFactory.make(Team.class, FantasyApplication.PERSISTENCE_UNIT);
			ComboBox cb = new ComboBox("Team");
			cb.setContainerDataSource(teams);
			cb.setNullSelectionAllowed(false);
			return cb;
		}
		
		return super.createField(item, propertyId, uiContext);
		
	
	}
}
