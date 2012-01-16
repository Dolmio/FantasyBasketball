package fantasy.web.ui.admin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import fantasy.web.ui.admin.AutomaticEntityForm;

import com.vaadin.data.Item;
import com.vaadin.spring.roo.addon.annotations.RooVaadinAutomaticEntityForm;

@RooVaadinAutomaticEntityForm(formBackingObject = fantasy.domain.Game.class)
public class GameForm extends AutomaticEntityForm<fantasy.domain.Game> {

    public GameForm() {
        super(fantasy.domain.Game.class);

        getForm().setFormFieldFactory(getFormFieldFactory());
    }
    
    @Override
	protected Collection<?> getItemPropertyIds(Item item) {
		if (null == item) {
			return Collections.emptyList();
		}
	
		ArrayList<Object> properties = new ArrayList<Object>(item.getItemPropertyIds());
		properties.remove("round");
		
		return properties;
	}
}
