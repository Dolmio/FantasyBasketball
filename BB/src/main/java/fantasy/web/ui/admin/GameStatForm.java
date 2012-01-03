package fantasy.web.ui.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import fantasy.web.ui.admin.AutomaticEntityForm;

import com.vaadin.data.Item;
import com.vaadin.spring.roo.addon.annotations.RooVaadinAutomaticEntityForm;

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
    					new String[]{							"dateWhen"
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
}
