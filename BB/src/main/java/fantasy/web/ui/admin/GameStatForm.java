package fantasy.web.ui.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import fantasy.web.ui.admin.AutomaticEntityForm;
import fantasy.domain.Player;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.Item;
import com.vaadin.spring.roo.addon.annotations.RooVaadinAutomaticEntityForm;
import com.vaadin.ui.ComboBox;

@RooVaadinAutomaticEntityForm(formBackingObject = fantasy.domain.GameStat.class)
public class GameStatForm extends AutomaticEntityForm<fantasy.domain.GameStat> {

    public GameStatForm() {
        super(fantasy.domain.GameStat.class);

        getForm().setFormFieldFactory(getFormFieldFactory());
    }
    
    
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
