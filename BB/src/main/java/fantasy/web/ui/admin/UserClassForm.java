package fantasy.web.ui.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.vaadin.data.Item;
import com.vaadin.spring.roo.addon.annotations.RooVaadinAutomaticEntityForm;

import fantasy.web.FantasyApplication;
@RooVaadinAutomaticEntityForm(formBackingObject = fantasy.domain.UserClass.class)
public class UserClassForm extends AutomaticEntityForm<fantasy.domain.UserClass> {

   
	private static final long serialVersionUID = 1L;

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



