package fantasy.web.ui;

import fantasy.web.AbstractEntityView;
import fantasy.web.EntityEditor;
import com.vaadin.spring.roo.addon.annotations.RooVaadinEntityView;
import com.vaadin.ui.Table;

@RooVaadinEntityView(formBackingObject = fantasy.domain.PlayerPos.class)
public class PlayerPosView extends AbstractEntityView<fantasy.domain.PlayerPos> {

    @Override
    protected EntityEditor createForm() {
        return new PlayerPosForm();
    }

    @Override
    protected void configureTable(Table table) {
        table.setContainerDataSource(getTableContainer());
        table.setVisibleColumns(getTableColumns());

        setupGeneratedColumns(table);
    }

}
