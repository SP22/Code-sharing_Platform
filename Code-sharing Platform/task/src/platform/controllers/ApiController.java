package platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.model.CodePiece;
import platform.model.dto.CodeDto;
import platform.service.CodeService;
import platform.utils.DateUtils;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/code",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiController {

    @Autowired
    CodeService codeService;

    @GetMapping(value = "/{uuid}")
    public CodeDto getCode(
            @PathVariable String uuid
    ) {
        return codeService.getByUuid(uuid);
    }

    @GetMapping(value = "/latest")
    public List<CodeDto> getLatest() {
        return codeService.getLatest();
    }

    @PostMapping(value = "/new")
    public String setCode(@RequestBody CodeDto newCode) {
        UUID uuid = codeService.save(newCode);
        return "{ \"id\" : \"" + uuid + "\" }";
    }
}
