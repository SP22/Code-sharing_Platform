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
import platform.service.CodeService;
import platform.utils.DateUtils;

@RestController
@RequestMapping(path = "/api/code",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiController {

    @Autowired
    CodeService codeService;
    @Autowired
    DateUtils dateUtils;

    @GetMapping(value = "/{id}")
    public CodePiece getCode(
            @PathVariable("id") int id
    ) {
        return codeService.getById(id);
    }

    @GetMapping(value = "/latest")
    public Object[] getLatest() {
        return codeService.getLatest();
    }

    @PostMapping(value = "/new")
    public String setCode(@RequestBody CodePiece newCode) {
        CodePiece code = new CodePiece(newCode.getCode(), "Code", dateUtils.getCurrentDate());
        long id = codeService.save(code);
        return "{ \"id\" : \"" + id + "\" }";
    }
}
