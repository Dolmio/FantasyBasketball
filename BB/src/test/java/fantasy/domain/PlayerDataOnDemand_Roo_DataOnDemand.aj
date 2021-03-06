// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fantasy.domain;

import fantasy.domain.Player;
import fantasy.domain.Team;
import fantasy.domain.TeamDataOnDemand;
import fantasy.domain.positions.TeamPosition;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect PlayerDataOnDemand_Roo_DataOnDemand {
    
    declare @type: PlayerDataOnDemand: @Component;
    
    private Random PlayerDataOnDemand.rnd = new SecureRandom();
    
    private List<Player> PlayerDataOnDemand.data;
    
    @Autowired
    private TeamDataOnDemand PlayerDataOnDemand.teamDataOnDemand;
    
    public Player PlayerDataOnDemand.getNewTransientPlayer(int index) {
        Player obj = new Player();
        setCurrentPosition(obj, index);
        setFirstName(obj, index);
        setInjured(obj, index);
        setLastName(obj, index);
        setTeam(obj, index);
        setValue(obj, index);
        return obj;
    }
    
    public void PlayerDataOnDemand.setCurrentPosition(Player obj, int index) {
        TeamPosition currentPosition = TeamPosition.class.getEnumConstants()[0];
        obj.setCurrentPosition(currentPosition);
    }
    
    public void PlayerDataOnDemand.setFirstName(Player obj, int index) {
        String firstName = "firstName_" + index;
        obj.setFirstName(firstName);
    }
    
    public void PlayerDataOnDemand.setInjured(Player obj, int index) {
        Boolean injured = false;
        obj.setInjured(injured);
    }
    
    public void PlayerDataOnDemand.setLastName(Player obj, int index) {
        String lastName = "lastName_" + index;
        obj.setLastName(lastName);
    }
    
    public void PlayerDataOnDemand.setTeam(Player obj, int index) {
        Team team = teamDataOnDemand.getRandomTeam();
        obj.setTeam(team);
    }
    
    public void PlayerDataOnDemand.setValue(Player obj, int index) {
        Integer value = new Integer(index);
        if (value < 0) {
            value = 0;
        }
        obj.setValue(value);
    }
    
    public Player PlayerDataOnDemand.getSpecificPlayer(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        Player obj = data.get(index);
        return Player.findPlayer(obj.getId());
    }
    
    public Player PlayerDataOnDemand.getRandomPlayer() {
        init();
        Player obj = data.get(rnd.nextInt(data.size()));
        return Player.findPlayer(obj.getId());
    }
    
    public boolean PlayerDataOnDemand.modifyPlayer(Player obj) {
        return false;
    }
    
    public void PlayerDataOnDemand.init() {
        data = Player.findPlayerEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Player' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<fantasy.domain.Player>();
        for (int i = 0; i < 10; i++) {
            Player obj = getNewTransientPlayer(i);
            try {
                obj.persist();
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> it = e.getConstraintViolations().iterator(); it.hasNext();) {
                    ConstraintViolation<?> cv = it.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
    
}
