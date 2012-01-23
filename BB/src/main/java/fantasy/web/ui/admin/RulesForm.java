package fantasy.web.ui.admin;


import com.vaadin.data.Item;
import com.vaadin.spring.roo.addon.annotations.RooVaadinAutomaticEntityForm;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.RichTextArea;

@RooVaadinAutomaticEntityForm(formBackingObject = fantasy.domain.Rules.class)
public class RulesForm extends AutomaticEntityForm<fantasy.domain.Rules> {

   private static final long serialVersionUID = 1L;

	public RulesForm() {
        super(fantasy.domain.Rules.class);
        FormFieldFactory fff =   new DefaultFieldFactory() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Field createField(Item item, Object propertyId, Component uiContext) {
				if(propertyId.equals("text")){
					RichTextArea rta = new RichTextArea();
					rta.setHeight("1000px");
					rta.setWidth("900px");
					return rta;
				}
				return null;
			}
        };

        getForm().setFormFieldFactory(fff);
    }
}
