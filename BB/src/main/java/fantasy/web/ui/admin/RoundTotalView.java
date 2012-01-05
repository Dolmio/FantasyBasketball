package fantasy.web.ui.admin;

import fantasy.web.ui.admin.AbstractEntityView;
import fantasy.web.ui.admin.EntityEditor;
import com.vaadin.spring.roo.addon.annotations.RooVaadinEntityView;
import com.vaadin.ui.Table;

@RooVaadinEntityView(formBackingObject = fantasy.domain.RoundTotal.class)
public class RoundTotalView extends AbstractEntityView<fantasy.domain.RoundTotal> {

    @Override
    protected EntityEditor createForm() {
        return new RoundTotalForm();
    }

    @Override
    protected void configureTable(Table table) {
        table.setContainerDataSource(getTableContainer());
        table.setVisibleColumns(getTableColumns());

        setupGeneratedColumns(table);
    }

}
