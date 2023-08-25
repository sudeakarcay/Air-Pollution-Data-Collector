package co.mobileaction.example.web.controller;

import co.mobileaction.example.common.dto.CoordinateDto;
import co.mobileaction.example.web.service.ICoordinateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("geocoding")
@RequiredArgsConstructor
public class CoordinateController
{
    private final ICoordinateService coordinateService;

    @GetMapping ("/coordinates/{cityName}")
    public ResponseEntity<CoordinateDto>getCoordinatesByCityName(@PathVariable String cityName){

        return ResponseEntity.ok(coordinateService.getCoordinatesByCityName(cityName));
    }

}

