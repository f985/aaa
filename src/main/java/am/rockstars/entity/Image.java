package am.rockstars.entity;

import am.rockstars.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * A Image.
 */
@Getter
@Setter
@Entity
@Table(name = "image")
public class Image extends BaseEntity {

    @NotNull
    @Column(name = "url", nullable = false)
    private String url;

    @ManyToOne
    private Product productId;
}
