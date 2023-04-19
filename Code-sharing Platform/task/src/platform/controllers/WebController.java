package platform.controllers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import platform.model.CodePiece;
import platform.utils.HtmlFormatter;

@RestController
public class WebController {
    @Autowired
    private HtmlFormatter htmlFormatter;

    private CodePiece code;

    @PostConstruct
    public void init() {
         this.code = new CodePiece(
                "{\n\"code\": \"public static void main(String[] args) {\\n    SpringApplication.run(CodeSharingPlatform.class, args);\\n}\"\n}",
                "Code",
                htmlFormatter.getCurrentDate()
        );
    }
    @GetMapping(value = "/code", produces = "text/html")
    public String getCode() {
        return htmlFormatter.getHtmlCode(code);
    }

    @GetMapping(value = "/api/code", produces = "application/json;charset=UTF-8")
    public CodePiece getApiCode() {
        return code;
    }

    @GetMapping(value = "/code/new", produces = "text/html")
    public ResponseEntity<String> getCodeNew() {
        return ResponseEntity.ok(htmlFormatter.getHtmlCodeNew());
    }

    @PostMapping(value = "/api/code/new", produces = "application/json;charset=UTF-8")
    public String setCode(@RequestBody CodePiece newCode) {
        code.setCode(newCode.getCode());
        code.setDate(htmlFormatter.getCurrentDate());
        return "{}";
    }
}
