package fantasy.web.ui;

import fantasy.web.AutomaticEntityForm;

import com.vaadin.spring.roo.addon.annotations.RooVaadinAutomaticEntityForm;

@RooVaadinAutomaticEntityForm(formBackingObject = fantasy.domain.PlayerPos.class)
public class PlayerPosForm extends AutomaticEntityForm<fantasy.domain.PlayerPos> {

    public PlayerPosForm() {
        super(fantasy.domain.PlayerPos.class);

        getForm().setFormFieldFactory(getFormFieldFactory());
    }
}
