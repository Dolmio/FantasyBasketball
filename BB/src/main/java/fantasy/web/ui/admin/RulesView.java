package fantasy.web.ui.admin;

import com.vaadin.spring.roo.addon.annotations.RooVaadinEntityView;
import com.vaadin.ui.Table;

@RooVaadinEntityView(formBackingObject = fantasy.domain.Rules.class)
public class RulesView extends AbstractEntityView<fantasy.domain.Rules> {

    @Override
    protected EntityEditor createForm() {
        return new RulesForm();
    }

    @Override
    protected void configureTable(Table table) {
        table.setContainerDataSource(getTableContainer());
        table.setVisibleColumns(getTableColumns());

        setupGeneratedColumns(table);
    }

}
