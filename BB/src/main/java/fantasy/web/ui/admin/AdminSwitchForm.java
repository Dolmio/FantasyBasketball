package fantasy.web.ui.admin;

import fantasy.web.ui.admin.AutomaticEntityForm;

import com.vaadin.spring.roo.addon.annotations.RooVaadinAutomaticEntityForm;

@RooVaadinAutomaticEntityForm(formBackingObject = fantasy.domain.AdminSwitch.class)
public class AdminSwitchForm extends AutomaticEntityForm<fantasy.domain.AdminSwitch> {

    public AdminSwitchForm() {
        super(fantasy.domain.AdminSwitch.class);

        getForm().setFormFieldFactory(getFormFieldFactory());
    }
}
