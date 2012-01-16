package fantasy.web.ui.admin;

import fantasy.web.ui.admin.AbstractEntityView;
import fantasy.web.ui.admin.EntityEditor;
import com.vaadin.spring.roo.addon.annotations.RooVaadinEntityView;
import com.vaadin.ui.Table;

@RooVaadinEntityView(formBackingObject = fantasy.domain.Game.class)
public class GameView extends AbstractEntityView<fantasy.domain.Game> {

    @Override
    protected EntityEditor createForm() {
        return new GameForm();
    }

    @Override
    protected void configureTable(Table table) {
        table.setContainerDataSource(getTableContainer());
        table.setVisibleColumns(new Object[] {"id", "awayTeam", "homeTeam", "winnerTeam"});

        setupGeneratedColumns(table);
    }

}
