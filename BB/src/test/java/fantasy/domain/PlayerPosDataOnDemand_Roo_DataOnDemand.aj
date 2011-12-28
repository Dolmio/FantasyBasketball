// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fantasy.domain;

import fantasy.domain.PlayerPos;
import fantasy.domain.positions.PlayerPosition;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;

privileged aspect PlayerPosDataOnDemand_Roo_DataOnDemand {
    
    declare @type: PlayerPosDataOnDemand: @Component;
    
    private Random PlayerPosDataOnDemand.rnd = new SecureRandom();
    
    private List<PlayerPos> PlayerPosDataOnDemand.data;
    
    public PlayerPos PlayerPosDataOnDemand.getNewTransientPlayerPos(int index) {
        PlayerPos obj = new PlayerPos();
        setPlayerPosition(obj, index);
        return obj;
    }
    
    public void PlayerPosDataOnDemand.setPlayerPosition(PlayerPos obj, int index) {
        PlayerPosition playerPosition = PlayerPosition.class.getEnumConstants()[0];
        obj.setPlayerPosition(playerPosition);
    }
    
    public PlayerPos PlayerPosDataOnDemand.getSpecificPlayerPos(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        PlayerPos obj = data.get(index);
        return PlayerPos.findPlayerPos(obj.getId());
    }
    
    public PlayerPos PlayerPosDataOnDemand.getRandomPlayerPos() {
        init();
        PlayerPos obj = data.get(rnd.nextInt(data.size()));
        return PlayerPos.findPlayerPos(obj.getId());
    }
    
    public boolean PlayerPosDataOnDemand.modifyPlayerPos(PlayerPos obj) {
        return false;
    }
    
    public void PlayerPosDataOnDemand.init() {
        data = PlayerPos.findPlayerPosEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'PlayerPos' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<fantasy.domain.PlayerPos>();
        for (int i = 0; i < 10; i++) {
            PlayerPos obj = getNewTransientPlayerPos(i);
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
