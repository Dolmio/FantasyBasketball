package fantasy.web.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import fantasy.web.AutomaticEntityForm;

import com.vaadin.data.Item;
import com.vaadin.spring.roo.addon.annotations.RooVaadinAutomaticEntityForm;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;

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
	
		ArrayList<Object> properties = new ArrayList<Object>(item.getItemPropertyIds());
		//properties.remove("players");
		
		return properties;
	}

}
