package fantasy.web.ui.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.context.i18n.LocaleContextHolder;
import org.vaadin.addon.customfield.beanfield.BeanFieldWrapper;

import fantasy.domain.Team;
import fantasy.web.FantasyApplication;
import fantasy.web.ui.admin.AutomaticEntityForm;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.spring.roo.addon.annotations.RooVaadinAutomaticEntityForm;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;
import java.util.List;
@RooVaadinAutomaticEntityForm(formBackingObject = fantasy.domain.UserClass.class)
public class UserClassForm extends AutomaticEntityForm<fantasy.domain.UserClass> {

    public UserClassForm() {
        super(fantasy.domain.UserClass.class);

        getForm().setFormFieldFactory(getFormFieldFactory());
        
    }
    
    /**
     * We override superclass method, so we can hash the password.
     */
    @Override
    public void commit(){
    	super.commit();
    	getForm().getItemProperty("password").setValue(FantasyApplication.getHash((String)getForm().getItemProperty("password").getValue()));
    	super.commit();
    }

   /**
    * Fields to show in form
    */
    protected Collection<?> getItemPropertyIds(Item item) {
    	return new ArrayList<String> (Arrays.asList(new String[] {"username", "password", "userRole", "team"}));
    	
    }

}



