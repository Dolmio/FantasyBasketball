package fantasy.web.ui.admin;

import java.util.Arrays;

import fantasy.web.ui.admin.AbstractEntityView;
import fantasy.web.ui.admin.EntityEditor;
import com.vaadin.spring.roo.addon.annotations.RooVaadinEntityView;
import com.vaadin.ui.Table;

@RooVaadinEntityView(formBackingObject = fantasy.domain.Round.class)
public class RoundView extends AbstractEntityView<fantasy.domain.Round> {

    @Override
    protected EntityEditor createForm() {
        return new RoundForm();
    }

    @Override
    protected void configureTable(Table table) {
        table.setContainerDataSource(getTableContainer());
        setupGeneratedColumns(table);
        table.setVisibleColumns(new Object[] {"id", "name", "startDate", "endDate", "gameCount" });
        table.setColumnHeaders(new String[] {"Id", "Name", "Start", "End", "Games"});
    }

}
