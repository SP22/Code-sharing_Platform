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
import platform.repository.CodeRepository;
import platform.utils.DateUtils;

@RestController
@RequestMapping(path = "/api/code",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiController {

    @Autowired
    CodeRepository codeRepository;
    @Autowired
    DateUtils dateUtils;

    @GetMapping(value = "/{id}")
    public CodePiece getCode(
            @PathVariable("id") int id
    ) {
        return codeRepository.getByIndex(id);
    }

    @GetMapping(value = "/latest")
    public Object[] getLatest() {
        return codeRepository.getLatest();
    }

    @PostMapping(value = "/new")
    public String setCode(@RequestBody CodePiece newCode) {
        CodePiece code = new CodePiece(newCode.getCode(), "Code", dateUtils.getCurrentDate());
        int id = codeRepository.add(code);
        return "{ \"id\" : \"" + id + "\" }";
    }
}
