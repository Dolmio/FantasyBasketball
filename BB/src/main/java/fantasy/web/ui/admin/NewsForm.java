package fantasy.web.ui.admin;


import com.vaadin.data.Item;
import com.vaadin.spring.roo.addon.annotations.RooVaadinAutomaticEntityForm;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.RichTextArea;

@RooVaadinAutomaticEntityForm(formBackingObject = fantasy.domain.News.class)
public class NewsForm extends AutomaticEntityForm<fantasy.domain.News> {

    public NewsForm() {
        super(fantasy.domain.News.class);
        FormFieldFactory fff =   new DefaultFieldFactory() {
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
