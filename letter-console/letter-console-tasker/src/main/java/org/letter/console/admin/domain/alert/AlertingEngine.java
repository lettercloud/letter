package org.letter.console.admin.domain.alert;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;

import java.io.Serializable;

/**
 * AlertingEngine
 *
 * @author letter
 */
@Getter
@Setter
@Entity
@Table(name = "alerting_engines")
public class AlertingEngine  extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "instance", nullable = false, length = 128)
    private String instance;

    @Column(name = "datasource_id", nullable = false)
    private Long datasourceId;

    @Column(name = "engine_cluster", nullable = false, length = 128)
    private String engineCluster;

    @Column(name = "clock", nullable = false)
    private Long clock;
	
}
