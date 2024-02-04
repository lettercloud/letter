package org.letter.console.tasker.domain.source;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;

import java.io.Serializable;

/**
 * Datasource
 *
 * @author letter
 */
@Getter
@Setter
@Entity
@Table(name = "datasource")
public class Datasource  extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 191)
    private String name;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "category", nullable = false, length = 255)
    private String category;

    @Column(name = "plugin_id", nullable = false)
    private Integer pluginId;

    @Column(name = "plugin_type", nullable = false, length = 255)
    private String pluginType;

    @Column(name = "plugin_type_name", nullable = false, length = 255)
    private String pluginTypeName;

    @Column(name = "cluster_name", nullable = false, length = 255)
    private String clusterName;

    @Lob
    @Column(name = "settings", nullable = false, columnDefinition = "TEXT")
    private String settings;

    @Column(name = "status", nullable = false, length = 255)
    private String status;

    @Column(name = "http", nullable = false, length = 4096)
    private String http;

    @Column(name = "auth", nullable = false, length = 8192)
    private String auth;
	
}
