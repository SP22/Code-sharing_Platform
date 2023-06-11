package platform.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CodeDto {
    private String code;
    private long time;
    private int views;
    private String date;

    @JsonIgnore
    private boolean viewRestricted;

    public CodeDto() {
    }

    public CodeDto(String code, long time, int views, String date, boolean viewRestricted) {
        this.code = code;
        this.time = time;
        this.views = views;
        this.date = date;
        this.viewRestricted = viewRestricted;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isViewRestricted() {
        return viewRestricted;
    }
}
