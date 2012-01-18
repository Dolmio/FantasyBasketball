package fantasy.web.ui.admin;

import javax.persistence.EntityManager;

import org.springframework.transaction.annotation.Transactional;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.spring.roo.addon.annotations.RooVaadinEntityView;
import com.vaadin.ui.Table;

import fantasy.domain.Game;
import fantasy.domain.Player;

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
       table.setVisibleColumns(new Object[]{"lastName", "firstName", "team","value",  "possiblePositions", "currentPosition", "statCount"});
       table.setColumnHeaders(new String[] {"Lastname", "Firstname", "Team", "Value", "Possible positions", "Current position", "Stats"});
    }
    
    @Override
	@Transactional
	public boolean doCommit() {
		try {
			getForm().commit();
			//we have to make merging in two parts because when we first merge player savedPlayer 
			//doesn't have information about updates made to reference relationships because they
			//aren't yet part of the Persistence context. We can't also merge references first because
			//player doesn't have id yet.
			
			Player player = (Player) getEntityForItem(getForm().getItemDataSource());
			System.out.println(player.getTeam().getPlayerCount());
			EntityManager em = player.giveEntityManager();
			Player savedPlayer = em.merge(player);
			em.flush();
			player.setId(savedPlayer.getId());
			player.setVersion(savedPlayer.getVersion());
			System.out.println(player.getTeam().getPlayerCount());
			if(player.getTeam() != null){
				em.merge(player.getTeam());
			}
			em.flush();
			
			

			return true;
		} catch (InvalidValueException e) {
			// show validation error also on the save button
			getForm().setCommitErrorMessage(e.getMessage());
			return false;
		}
	}
    
    @Override
    public void doDelete(){
    	Player player = (Player) getEntityForItem(getForm().getItemDataSource());
    	if(player.getTeam() != null){
    		player.getTeam().removePlayer(player);
    		player.getTeam().merge();
    	}
    	deleteEntity(player);
    }

}
