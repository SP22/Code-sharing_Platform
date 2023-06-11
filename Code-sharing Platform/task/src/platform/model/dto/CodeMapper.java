package platform.model.dto;

import org.springframework.stereotype.Service;
import platform.model.CodePiece;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Service
public class CodeMapper {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            .withZone(ZoneId.systemDefault());;

    public CodeDto convertToDto(CodePiece codePiece) {
        long time = codePiece.isTimeRestricted() ? codePiece.getAvailableUntil().toEpochSecond(ZoneOffset.UTC)
                - LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) : 0;
        String date = codePiece.getCreatedAt().format(formatter);

        return new CodeDto(codePiece.getCode(), time, codePiece.getViews(), date, codePiece.isViewRestricted());
    }

    public CodePiece convertToEntity(CodeDto codeDto) {
        String code = codeDto.getCode();
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime availableUntil = createdAt.plusSeconds(codeDto.getTime());
        int views = codeDto.getViews();

        return new CodePiece(code, createdAt, availableUntil, views);
    }
}
