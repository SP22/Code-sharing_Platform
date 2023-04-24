package platform.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;
import platform.model.CodePiece;

@Component
public class CodeRepository {
    List<CodePiece> storage = new ArrayList<>();

    public CodePiece getByIndex(int i) {
        return storage.get(i - 1);
    }

    public int size() {
        return storage.size();
    }

    public int add(CodePiece codePiece) {
        storage.add(codePiece);
        return storage.size();
    }

    public Object[] getLatest() {
        ArrayList<CodePiece> latest = new ArrayList<>(storage.subList(Math.max(size() - 10, 0), size()));
        Collections.reverse(latest);
        return latest.toArray();
    }

}
