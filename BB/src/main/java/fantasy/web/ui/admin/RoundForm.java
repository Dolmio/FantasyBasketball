package fantasy.web.ui.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import fantasy.domain.Player;
import fantasy.web.ui.admin.AutomaticEntityForm;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.spring.roo.addon.annotations.RooVaadinAutomaticEntityForm;
import com.vaadin.ui.TwinColSelect;
import fantasy.domain.Game;
import fantasy.domain.Round;
import fantasy.domain.Team;
@RooVaadinAutomaticEntityForm(formBackingObject = fantasy.domain.Round.class)
public class RoundForm extends AutomaticEntityForm<fantasy.domain.Round> {

    public RoundForm() {
        super(fantasy.domain.Round.class);

        getForm().setFormFieldFactory(getFormFieldFactory());
    }
    
    /**
     * returns form fields to show and their order
     */
    @Override
	protected Collection<?> getItemPropertyIds(Item item) {
		if (null == item) {
			return Collections.emptyList();
		}
	
		return  new ArrayList<Object>(Arrays.asList(new Object[] {"name", "startDate", "endDate"}));
		
	}
    
    
    public TwinColSelect buildGamesMultiSelect() {
	 	
    	BeanContainer<Long, Game> games = getContainerForGames();
	 
	 	TwinColSelect select = new TwinColSelect(null, games);
	 	
        //change item caption values to "awayTeam + homeTeam + roundName"
	 	for(Long id : games.getItemIds()){
        	String awayTeam = ((Team) games.getItem(id).getItemProperty("awayTeam").getValue()).getName();
        	String homeTeam = ((Team) games.getItem(id).getItemProperty("homeTeam").getValue()).getName();
        	String roundName = "";
        	Round round = (Round) games.getItem(id).getItemProperty("round").getValue();
        	if(round != null){
        		roundName = round.getName();
        	}
	 		select.setItemCaption(id,  awayTeam + " VS " + homeTeam + " " + roundName );
        }
        Object captionPropertyId = getGameCaptionPropertyId();
        if (captionPropertyId != null) {
            select.setItemCaptionPropertyId(captionPropertyId);
        }
        select.setWidth("500px");
        return select;
    }
}
