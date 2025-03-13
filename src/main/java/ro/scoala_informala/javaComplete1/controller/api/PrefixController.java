package ro.scoala_informala.javaComplete1.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.scoala_informala.javaComplete1.model.Prefix;
import ro.scoala_informala.javaComplete1.service.PrefixService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/prefixes")
public class PrefixController {

    private final PrefixService prefixService;

    @GetMapping()
    public List<Prefix> getAllPrefixes(@RequestParam(required = false) String country) {
        if (country != null && !country.isEmpty()) {
            return prefixService.getAllPrefixes(country);
        }
        return prefixService.getAllPrefixes();
    }



}
