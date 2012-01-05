package fantasy.domain;

import org.eclipse.persistence.annotations.CascadeOnDelete;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import java.io.Serializable;
import java.util.Set;
import fantasy.domain.Player;
import java.util.HashSet;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import fantasy.domain.RoundTotal;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;

@RooJavaBean
@RooToString
@RooEntity
@Table(name = "team", uniqueConstraints = @UniqueConstraint(columnNames = { "name" }))
public class Team implements Serializable {

    @OneToMany(mappedBy = "team", orphanRemoval = true, cascade = { CascadeType.ALL })
    @CascadeOnDelete
    private Set<Player> players = new HashSet<Player>();

    @NotNull
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<RoundTotal> roundTotals = new HashSet<RoundTotal>();

    public void setPlayers(Set<Player> players) {
        removeOldPlayers();
        this.players = players;
        for (Player p : players) {
            p.setTeam(this);
        }
    }

    private void removeOldPlayers() {
        for (Player p : players) {
            p.setTeam(null);
            p.flush();
        }
    }
}
