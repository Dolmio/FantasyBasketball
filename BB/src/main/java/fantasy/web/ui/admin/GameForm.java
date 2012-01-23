package fantasy.web.ui.admin;



import fantasy.web.ui.admin.AutomaticEntityForm;
import com.vaadin.spring.roo.addon.annotations.RooVaadinAutomaticEntityForm;

@RooVaadinAutomaticEntityForm(formBackingObject = fantasy.domain.Game.class)
public class GameForm extends AutomaticEntityForm<fantasy.domain.Game> {

   

	private static final long serialVersionUID = 1L;

	public GameForm() {
        super(fantasy.domain.Game.class);

        getForm().setFormFieldFactory(getFormFieldFactory());
    }
    
}
