package platform.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Table(name = "code_piece")
public class CodePiece {
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;

    private final UUID uuid;

    @Column(name = "code", columnDefinition = "VARCHAR(2000)")
    private String code;
    private LocalDateTime createdAt;
    private LocalDateTime availableUntil;
    private int views;
    private boolean timeRestricted;
    private boolean viewRestricted;
    private boolean restricted;

    public CodePiece() {
        this.uuid = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
    }

    public CodePiece(String code, LocalDateTime createdAt, LocalDateTime availableUntil, int views) {
        this();
        this.code = code;
        this.createdAt = createdAt;
        this.availableUntil = availableUntil;
        this.views = views;
        this.timeRestricted = availableUntil.isAfter(createdAt);
        this.viewRestricted = views > 0;
        if (this.timeRestricted || this.viewRestricted) {
            this.restricted = true;
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public long getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public LocalDateTime getAvailableUntil() {
        return availableUntil;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public boolean isTimeRestricted() {
        return timeRestricted;
    }

    public boolean isViewRestricted() {
        return viewRestricted;
    }

    public boolean isRestricted() {
        return restricted;
    }
}
