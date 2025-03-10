package com.shami.travel_advisor;

import com.shami.travel_advisor.city.City;
import com.shami.travel_advisor.hotel.Hotel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@RestController
public class TravelController {

    @GetMapping("/travel")
    public String getTravels() {
        return "Hello traveller Welcome!!!!";
    }

    /**
     * method to generate csrf token to prevent hacking -internally connected to spring Security JWT
     * It will generate each CSRF(Cross Site Request Forgery) token for all objects when client request to servlet for login
     * This token can use one person in one browsing/site (can't use this token from browser)
     * @param httpSecurity handle server request to access spring container
     * @return csrf token {you can also get it through browser in viewPage source -code}
     */
    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest httpSecurity) {
        return (CsrfToken) httpSecurity.getAttribute("_csrf");
    }

}
