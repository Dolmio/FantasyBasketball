package fantasy.web.ui;

import fantasy.web.AutomaticEntityForm;

import com.vaadin.spring.roo.addon.annotations.RooVaadinAutomaticEntityForm;

@RooVaadinAutomaticEntityForm(formBackingObject = fantasy.domain.Player.class)
public class PlayerForm extends AutomaticEntityForm<fantasy.domain.Player> {

    public PlayerForm() {
        super(fantasy.domain.Player.class);

        getForm().setFormFieldFactory(getFormFieldFactory());
    }
}
