package fantasy.web.ui.admin;

import fantasy.web.ui.admin.AutomaticEntityForm;

import com.vaadin.spring.roo.addon.annotations.RooVaadinAutomaticEntityForm;

@RooVaadinAutomaticEntityForm(formBackingObject = fantasy.domain.RoundTotal.class)
public class RoundTotalForm extends AutomaticEntityForm<fantasy.domain.RoundTotal> {

    public RoundTotalForm() {
        super(fantasy.domain.RoundTotal.class);

        getForm().setFormFieldFactory(getFormFieldFactory());
    }
}
