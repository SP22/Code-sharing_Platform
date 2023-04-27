package platform.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.errors.CodeNotFoundException;
import platform.model.CodePiece;
import platform.repository.CodeRepository;

@Service
public class CodeService {
    @Autowired
    private CodeRepository codeRepository;

    public CodePiece getById(long id) {
        Optional<CodePiece> code = codeRepository.findById(id);
        if (code.isEmpty()) {
            throw new CodeNotFoundException();
        } else {
            return code.get();
        }
    }

    public Object[] getLatest() {
        ArrayList<CodePiece> latest = new ArrayList<>();
        Iterable<CodePiece> all = codeRepository.findTop10ByOrderByIdDesc();
        for (CodePiece item : all) {
            latest.add(item);
            System.out.println(item.getId() + " " + item.getCode());
        }
        return latest.toArray();
    }

    public synchronized long save(CodePiece code) {
        codeRepository.save(code);
        return code.getId();
    }

}
