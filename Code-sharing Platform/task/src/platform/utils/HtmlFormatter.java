package platform.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;
import platform.model.CodePiece;

@Component
public class HtmlFormatter {
    public String getHtmlCode(CodePiece code) {
        String template = "<title>%s</title>"
                + "<body>"
                + "<span id=\"load_date\">%s</span>"
                + "<pre id=\"code_snippet\">%s</pre>"
                + "</body>";
        return String.format(template, code.getTitle(), code.getDate(), code.getCode());
    }

    public String getHtmlCodeNew() {
        return "<title>Create</title>"
                + "<body>"
                + "<textarea id=\"code_snippet\">...</textarea>"
                + "<button id=\"send_snippet\" type=\"submit\" onclick=\"send()\">Submit</button>"
                + "<script type=\"text/javascript\">\n" +
                "function send() {\n" +
                "    let object = {\n" +
                "        \"code\": document.getElementById(\"code_snippet\").value\n" +
                "    };\n" +
                "    let json = JSON.stringify(object);\n" +
                "    let xhr = new XMLHttpRequest();\n" +
                "    xhr.open(\"POST\", '/api/code/new', false)\n" +
                "    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');\n" +
                "    xhr.send(json);\n" +
                "    if (xhr.status == 200) {\n" +
                "      alert(\"Success!\");\n" +
                "    }\n" +
                "}</script>"
                + "</body>";
    }

    public String getCurrentDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
