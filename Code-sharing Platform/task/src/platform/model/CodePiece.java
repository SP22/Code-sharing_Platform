package platform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CodePiece {
    private String code;
    private String date;
    @JsonIgnore
    private String title;

    public CodePiece(String code, String title, String date) {
        this.code = code;
        this.title = title;
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
