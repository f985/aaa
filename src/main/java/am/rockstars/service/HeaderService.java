package am.rockstars.service;

import am.rockstars.dto.CreateHeaderRequest;
import am.rockstars.entity.Header;
import am.rockstars.repository.HeaderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeaderService {

    private final HeaderRepository headerRepository;

    public HeaderService(HeaderRepository headerRepository) {
        this.headerRepository = headerRepository;
    }

    public List<Header> get() {
        return headerRepository.findAll();
    }

    public void addHeader(final CreateHeaderRequest headerRequest) {
        final Header header = new Header();
        BeanUtils.copyProperties(headerRequest, header);
        headerRepository.save(header);
    }
}
