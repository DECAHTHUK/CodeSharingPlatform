package platform.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.Responce.Idshnik;
import platform.Responce.Restrictions;
import platform.business.Code;
import platform.business.CodeService;
import platform.dto.CodeDto;
import platform.dto.CodeMapper;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
//@RequiredArgsConstructor
public class ApiController {
    @Autowired
    CodeService codeService;

    @Autowired
    CodeMapper codeMapper;

    @PostMapping(value = "/code/new", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Idshnik postCode(@RequestBody CodeDto codeDto) {
        Idshnik idshnik = new Idshnik(codeService.save(codeMapper.toCode(codeDto)).getUuid());
        return idshnik;
    }

    /*@GetMapping("/code/{id}")
    public Code getCode(@PathVariable int id) {
        return codeService.findById(id);
    }*/

    @GetMapping(value = "/code/latest", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getApiLatest() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String postAsString = objectMapper.writeValueAsString(codeService.getLatest());
        return postAsString;
    }

    @GetMapping(value = "/code/{uuid}")
    public String getUuidCode(@PathVariable String uuid) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String postAsString = objectMapper.writeValueAsString(codeService.findByUuid(uuid));
        return postAsString;

    }
}
