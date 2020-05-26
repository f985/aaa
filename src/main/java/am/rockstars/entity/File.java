package am.rockstars.entity;

import am.rockstars.entity.base.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * A File.
 */
@Getter
@Setter
@Entity
@Table(name = "file")
public class File extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "extension")
    private String extension;

    @Column(name = "path")
    private String path;
}
