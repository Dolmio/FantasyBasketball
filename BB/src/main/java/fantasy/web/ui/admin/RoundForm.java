package fantasy.web.ui.admin;

import fantasy.web.ui.admin.AutomaticEntityForm;

import com.vaadin.spring.roo.addon.annotations.RooVaadinAutomaticEntityForm;

@RooVaadinAutomaticEntityForm(formBackingObject = fantasy.domain.Round.class)
public class RoundForm extends AutomaticEntityForm<fantasy.domain.Round> {

    public RoundForm() {
        super(fantasy.domain.Round.class);

        getForm().setFormFieldFactory(getFormFieldFactory());
    }
}
