package platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import platform.model.CodePiece;
import platform.model.dto.CodeDto;
import platform.service.CodeService;
import platform.utils.DateUtils;

import java.util.UUID;

@Controller
@RequestMapping(path = "/code",
        produces = MediaType.TEXT_HTML_VALUE)
public class WebController {
    @Autowired
    private DateUtils dateUtils;
    @Autowired
    private CodeService codeService;

    @GetMapping(value = "/{uuid}")
    public String getCode(
            @PathVariable String uuid,
            Model model
    ) {
        CodeDto codePiece = codeService.getByUuid(uuid);
        model.addAttribute("code", codePiece);
        return "codepiece";
    }

    @GetMapping(value = "/new")
    public String getCodeNew(Model model) {
        return "newcodepiece";
    }

    @GetMapping(value = "/latest")
    public String getLatest(Model model) {
        model.addAttribute("latest", codeService.getLatest());
        return "latestcodepieces";
    }
}
