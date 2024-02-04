package org.letter.console.scheduler.domain.source;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;

import java.io.Serializable;

/**
 * BuiltinCate
 *
 * @author letter
 */
@Getter
@Setter
@Entity
@Table(name = "builtin_cate")
public class BuiltinCate  extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 191)
    private String name;

    @Column(name = "user_id", nullable = false)
    private Long userId;
	
}
