package fantasy.web.ui.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import com.vaadin.data.Item;
import com.vaadin.spring.roo.addon.annotations.RooVaadinAutomaticEntityForm;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;

@RooVaadinAutomaticEntityForm(formBackingObject = fantasy.domain.Team.class)
public class TeamForm extends AutomaticEntityForm<fantasy.domain.Team> {


	private static final long serialVersionUID = 1L;


	public TeamForm() {
		super(fantasy.domain.Team.class);
		getForm().setFormFieldFactory(getFormFieldFactory());
	}

	/**
	 * returns fields to show in the form
	 */
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

			private static final long serialVersionUID = 1L;

			@Override
			public Field createField(Item item, Object propertyId, Component uiContext) {
				Field field = super.createField(item, propertyId, uiContext);
				if (field instanceof TextField) {
					((TextField) field).setNullRepresentation("");
				}
				return field;

			}
		};
	}
}
