package fantasy.web.ui.admin;

import org.springframework.transaction.annotation.Transactional;

import fantasy.domain.RoundTotal;
import fantasy.domain.Team;
import fantasy.web.ui.admin.AbstractEntityView;
import fantasy.web.ui.admin.EntityEditor;

import com.vaadin.data.Validator.InvalidValueException;
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


		setupGeneratedColumns(table);

		Object[] visibleColumns = new Object[] {"id", "team", "round", "points", "lpPoints","rebounds", "lpRebounds", "assists", "lpAssists", 
				"blocks", "lpBlocks", "steals", "lpSteals", "turnovers", "lpTurnovers", "ftMade", "lpFtMade", "threePointsMade",
				"lpThreePointsMade", "fgMade", "fgAttempts","fieldGoalPercentage", "lpFieldGoalPercentage", "totalPoints"};

		String[] columnHeaders = new String[]{"Id", "Team", "Round", "Pts", "LP-Pts", "Reb", "LP-Reb", "Ass", "LP-Ass", "Blk", "LP-Blk", "Stl", "LP-Stl",
				"To", "LP-To", "Ftm", "LP-Ftm", "3Fgm", "LP-3Fgm", "Fgm", "Fga", "Fg%", "LP-Fg%", "Total points"};
		table.setVisibleColumns(visibleColumns);
		table.setColumnHeaders(columnHeaders);

	}

	/**
	 * Method saves entity and manages bidirectional relationships so they are also updated.
	 * @return
	 */
	@Override
	@Transactional
	public boolean doCommit() {
		try{
			getForm().commit();
			RoundTotal total = (RoundTotal) getEntityForItem(getForm().getItemDataSource());
			RoundTotal savedTotal = total.merge();
			total.setId(savedTotal.getId());
			total.setVersion(savedTotal.getVersion());
			if(total.getTeam() != null){
				total.getTeam().merge();
			}
			return true;
		}
		catch (InvalidValueException e) {
			// show validation error also on the save button
			getForm().setCommitErrorMessage(e.getMessage());
			return false;
		}
	}



	@Override
	public void doDelete(){
		RoundTotal total = getEntityForItem(getForm().getItemDataSource()); 
		if(total.getTeam() != null){
			total.getTeam().removeRoundTotal(total);
			total.getTeam().merge();
		}
		else{
			deleteEntity(total);
		}
	}

}
