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
@Entity
@Table(name = "image")
@Getter
@Setter
public class Image extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "url", nullable = false)
    private String url;

    @ManyToOne
    @JsonIgnoreProperties("images")
    private Product productId;
}
