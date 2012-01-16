package fantasy.web.ui.admin;

import com.vaadin.spring.roo.addon.annotations.RooVaadinEntityView;
import com.vaadin.ui.Table;

@RooVaadinEntityView(formBackingObject = fantasy.domain.Player.class)
public class PlayerView extends AbstractEntityView<fantasy.domain.Player> {

    @Override
    protected EntityEditor createForm() {
        return new PlayerForm();
    }

    @Override
    protected void configureTable(Table table) {
       table.setContainerDataSource(getTableContainer());
       setupGeneratedColumns(table);
       table.setVisibleColumns(new Object[]{"lastName", "firstName", "team",  "possiblePositions", "currentPosition", "statCount"});
       table.setColumnHeaders(new String[] {"Lastname", "Firstname", "Team", "Possible positions", "Current position", "Stats"});
    }

}
