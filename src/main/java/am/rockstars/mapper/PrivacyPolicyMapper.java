package am.rockstars.mapper;

import am.rockstars.dto.privacypolicy.PrivacyPolicyRequest;
import am.rockstars.dto.privacypolicy.PrivacyPolicyResponse;
import am.rockstars.entity.PrivacyPolicy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface PrivacyPolicyMapper {
    List<PrivacyPolicyResponse> map(List<PrivacyPolicy> privacyPolicies);

    PrivacyPolicy map(PrivacyPolicyRequest request);
}
