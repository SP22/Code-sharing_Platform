package platform.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.errors.CodeNotFoundException;
import platform.model.CodePiece;
import platform.model.dto.CodeDto;
import platform.model.dto.CodeMapper;
import platform.repository.CodeRepository;

@Service
public class CodeService {
    @Autowired
    private CodeRepository codeRepository;
    @Autowired
    private CodeMapper codeMapper;

    public CodePiece getById(long id) {
        Optional<CodePiece> code = codeRepository.findById(id);
        if (code.isEmpty()) {
            throw new CodeNotFoundException();
        } else {
            return code.get();
        }
    }

    public CodeDto getByUuid(String uuid) {
        Optional<CodePiece> code = codeRepository.findByUuid(UUID.fromString(uuid));
        if (code.isEmpty()) {
            throw new CodeNotFoundException();
        } else {
            CodePiece codePiece = code.get();
            if (codePiece.isRestricted()) {
                if (codePiece.isTimeRestricted()
                        && LocalDateTime.now().isAfter(codePiece.getAvailableUntil())) {
                    codeRepository.delete(codePiece);
                    throw new CodeNotFoundException();
                }
                if (codePiece.getViews() > 0) {
                    codePiece.setViews(codePiece.getViews() - 1);
                    if (codePiece.getViews() == 0) {
                        codeRepository.delete(codePiece);
                    } else {
                        codeRepository.save(codePiece);
                    }
                }
            }
            return codeMapper.convertToDto(code.get());
        }
    }

    public List<CodeDto> getLatest() {
        List<CodePiece> latest = new ArrayList<>();
        Iterable<CodePiece> all = codeRepository.findTop10ByRestrictedFalseOrderByIdDesc();
        for (CodePiece item : all) {
            latest.add(item);
            System.out.println(item.getId() + " " + item.getCode());
        }
        return latest.stream()
                .map(codeMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public synchronized UUID save(CodeDto code) {
        return codeRepository.save(codeMapper.convertToEntity(code)).getUuid();
    }

}
