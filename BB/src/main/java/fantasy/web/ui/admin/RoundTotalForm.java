package fantasy.web.ui.admin;


import com.vaadin.spring.roo.addon.annotations.RooVaadinAutomaticEntityForm;

@RooVaadinAutomaticEntityForm(formBackingObject = fantasy.domain.RoundTotal.class)
public class RoundTotalForm extends AutomaticEntityForm<fantasy.domain.RoundTotal> {

    
	private static final long serialVersionUID = 1L;

	public RoundTotalForm() {
        super(fantasy.domain.RoundTotal.class);

        getForm().setFormFieldFactory(getFormFieldFactory());
    }
    
  
}
