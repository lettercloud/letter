package org.letter.console.admin.domain.source;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;

import java.io.Serializable;

/**
 * EsIndexPattern
 *
 * @author letter
 */
@Getter
@Setter
@Entity
@Table(name = "es_index_pattern")
public class EsIndexPattern  extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "datasource_id", nullable = false)
    private Long datasourceId;

    @Column(name = "name", nullable = false, length = 191)
    private String name;

    @Column(name = "time_field", nullable = false, length = 128, columnDefinition = "varchar(128) default '@timestamp'")
    private String timeField;

    @Column(name = "allow_hide_system_indices", nullable = false)
    private boolean allowHideSystemIndices;

    @Column(name = "fields_format", nullable = false, columnDefinition = "varchar(4096) default ''")
    private String fieldsFormat;
}
