package am.rockstars.dto.header.edit;

import am.rockstars.dto.header.BaseHeader;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
abstract class BaseHeaderEdit extends BaseHeader {
    private Long id;

    private Integer orderNumber;

    private boolean disabled;
}
