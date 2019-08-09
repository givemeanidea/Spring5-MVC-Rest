package com.springframework.controllers.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/vendors")
public class VendorController {

    public static final String BASE_URL = "/api/v1/vendors";
}
