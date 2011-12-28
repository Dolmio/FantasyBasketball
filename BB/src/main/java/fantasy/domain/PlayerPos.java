package fantasy.domain;

import java.io.Serializable;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import fantasy.domain.positions.PlayerPosition;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@RooJavaBean
@RooToString
@RooEntity
@Table(name = "playerpos", uniqueConstraints = @UniqueConstraint(columnNames = { "playerPosition" }))
public class PlayerPos implements Serializable {
	
	@NotNull
    @Enumerated(EnumType.STRING)
    private PlayerPosition playerPosition;
}
