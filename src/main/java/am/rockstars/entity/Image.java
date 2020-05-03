package am.rockstars.entity;

import am.rockstars.entity.base.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A Image.
 */
@Getter
@Setter
@Entity
@Table(name = "image")
public class Image extends AbstractEntity {

    @NotNull
    @Column(name = "url", nullable = false)
    private String url;

    @ManyToOne
    private Product productId;
}
