// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fantasy.domain;

import fantasy.domain.positions.PlayerPosition;

privileged aspect PlayerPos_Roo_JavaBean {
    
    public PlayerPosition PlayerPos.getPlayerPosition() {
        return this.playerPosition;
    }
    
    public void PlayerPos.setPlayerPosition(PlayerPosition playerPosition) {
        this.playerPosition = playerPosition;
    }
    
}