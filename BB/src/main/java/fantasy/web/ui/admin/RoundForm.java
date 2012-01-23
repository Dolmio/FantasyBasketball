package fantasy.web.ui.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import com.vaadin.data.Item;
import com.vaadin.spring.roo.addon.annotations.RooVaadinAutomaticEntityForm;
import com.vaadin.ui.FormFieldFactory;
@RooVaadinAutomaticEntityForm(formBackingObject = fantasy.domain.Round.class)
public class RoundForm extends AutomaticEntityForm<fantasy.domain.Round> {

   
	private static final long serialVersionUID = 1L;


	public RoundForm() {
        super(fantasy.domain.Round.class);

        getForm().setFormFieldFactory(getFormFieldFactory());
    }
    
    /**
     * returns form fields to show and their order
     */
    @Override
	protected Collection<?> getItemPropertyIds(Item item) {
		if (null == item) {
			return Collections.emptyList();
		}
	
		return  new ArrayList<Object>(Arrays.asList(new Object[] {"name", "startDate", "endDate"}));
		
	}
    
    public FormFieldFactory getFormFieldFactory(){
    	return null;
    }
    
    
   
}
