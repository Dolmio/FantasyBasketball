package fantasy.domain;

import java.io.Serializable;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import fantasy.domain.positions.PlayerPosition;
import javax.persistence.Enumerated;

@RooJavaBean
@RooToString
@RooEntity
public class PlayerPos implements Serializable {

    @Enumerated
    private PlayerPosition playerPosition;
}
