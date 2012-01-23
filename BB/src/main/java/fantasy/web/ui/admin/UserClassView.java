package fantasy.web.ui.admin;

import com.vaadin.spring.roo.addon.annotations.RooVaadinEntityView;
import com.vaadin.ui.Table;

@RooVaadinEntityView(formBackingObject = fantasy.domain.UserClass.class)
public class UserClassView extends AbstractEntityView<fantasy.domain.UserClass> {

    private static final long serialVersionUID = 1L;

	@Override
    protected EntityEditor createForm() {
        return new UserClassForm();
    }

    @Override
    protected void configureTable(Table table) {
        table.setContainerDataSource(getTableContainer());
        table.setVisibleColumns(getTableColumns());

        setupGeneratedColumns(table);
    }

}
