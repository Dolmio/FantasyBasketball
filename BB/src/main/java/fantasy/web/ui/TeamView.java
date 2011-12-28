package fantasy.web.ui;

import fantasy.web.AbstractEntityView;
import fantasy.web.EntityEditor;
import com.vaadin.spring.roo.addon.annotations.RooVaadinEntityView;
import com.vaadin.ui.Table;

@RooVaadinEntityView(formBackingObject = fantasy.domain.Team.class)
public class TeamView extends AbstractEntityView<fantasy.domain.Team> {

    @Override
    protected EntityEditor createForm() {
        return new TeamForm();
    }

    @Override
    protected void configureTable(Table table) {
        table.setContainerDataSource(getTableContainer());
        table.setVisibleColumns(getTableColumns());

        setupGeneratedColumns(table);
    }

}
