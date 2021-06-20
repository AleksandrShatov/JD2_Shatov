package com.noirix.controller.rest;

import com.noirix.beans.SecurityConfig;
import com.noirix.controller.requests.LocationCreateRequest;
import com.noirix.domain.Location;
import com.noirix.repository.LocationRepository;
import com.noirix.util.LocationGenerator;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/rest/locations")
@RequiredArgsConstructor
public class LocationRestController {

    private final LocationRepository locationRepository;
    private final LocationGenerator locationGenerator;
    private final SecurityConfig config;

    @GetMapping
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @ApiOperation(value = "Creating one location")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "country", dataType = "string", paramType = "query", value = "Country for this Location"),
            @ApiImplicitParam(name = "Secret-Key", dataType = "string", paramType = "header",
                    value = "Secret header for secret functionality!! Hoho")
    })
    @PostMapping("/create")
    public Location createLocation(@RequestBody LocationCreateRequest createRequest, @RequestParam String country, HttpServletRequest request) {
        String secretKey = request.getHeader("Secret-Key");

        if (StringUtils.isNotBlank(secretKey) && secretKey.equals(config.getSecretKey())) {
            Location generatedLocation = locationGenerator.generate();
            generatedLocation.setCountry(country);
            generatedLocation.setCity(createRequest.getCity());
            generatedLocation.setLatitude(createRequest.getLatitude());
            generatedLocation.setLongitude(createRequest.getLongitude());

            return locationRepository.save(generatedLocation);
        } else {
            throw new RuntimeException("No secret code in header!");
        }
    }

}
