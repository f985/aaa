package am.rockstars.controller;

import am.rockstars.entity.Header;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/header")
public class HeaderController {

    public Header get() {
        return null;
    }
}
