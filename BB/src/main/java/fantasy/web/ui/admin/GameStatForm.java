package fantasy.web.ui.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.spring.roo.addon.annotations.RooVaadinAutomaticEntityForm;
import com.vaadin.ui.ComboBox;

import fantasy.domain.Player;

@RooVaadinAutomaticEntityForm(formBackingObject = fantasy.domain.GameStat.class)
public class GameStatForm extends AutomaticEntityForm<fantasy.domain.GameStat> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameStatForm() {
        super(fantasy.domain.GameStat.class);

        getForm().setFormFieldFactory(getFormFieldFactory());
    }
    
    /**
     * returns list of fields to show in form
     */
    @Override
	protected Collection<?> getItemPropertyIds(Item item){
    	List<String> fields = 
    			new ArrayList<String>(Arrays.asList(
    					new String[]{							
    															"player",
    															"dateWhen"
    			                                               ,"points", 
    			                                               "rebounds", 
    			                                               "assists", 
    			                                               "blocks", 
    			                                               "steals", 
    			                                               "turnovers", 
    			                                               "fgMade", 
    			                                               "fgAttempts", 
    			                                               "ftMade", 
    			                                               "threePointsMade"}));
    
    
   
    return fields;	
    }
    
    //show player's name in comboBox instead of id
    public ComboBox buildPlayerCombo() {
        BeanContainer<Long, Player> playerContainer = getContainerForPlayers();
        
    	ComboBox combo = new ComboBox(null, playerContainer);
    	for(Object id : combo.getItemIds()){
    		Player p = playerContainer.getItem(id).getBean();
    		combo.setItemCaption(id, p.getLastName() + " " + p.getFirstName());
    	}
        Object captionPropertyId = getPlayerCaptionPropertyId();
        if (captionPropertyId != null) {
            combo.setItemCaptionPropertyId(captionPropertyId);
        }
        return combo;
    }
}
