package fantasy.web.ui.admin;

import com.vaadin.spring.roo.addon.annotations.RooVaadinEntityView;


@RooVaadinEntityView(formBackingObject = fantasy.domain.UserClass.class)
public class UserView extends AbstractEntityView<fantasy.domain.UserClass> {

}
