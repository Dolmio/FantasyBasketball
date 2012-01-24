package fantasy.web.ui.admin;

import fantasy.web.ui.admin.AbstractEntityView;
import fantasy.web.ui.admin.EntityEditor;
import com.vaadin.spring.roo.addon.annotations.RooVaadinEntityView;
import com.vaadin.ui.Table;

@RooVaadinEntityView(formBackingObject = fantasy.domain.AdminSwitch.class)
public class AdminSwitchView extends AbstractEntityView<fantasy.domain.AdminSwitch> {

    @Override
    protected EntityEditor createForm() {
        return new AdminSwitchForm();
    }

    @Override
    protected void configureTable(Table table) {
        table.setContainerDataSource(getTableContainer());
        table.setVisibleColumns(getTableColumns());

        setupGeneratedColumns(table);
    }

}
