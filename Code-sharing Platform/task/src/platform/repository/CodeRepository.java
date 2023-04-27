package platform.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.model.CodePiece;

@Repository
public interface CodeRepository extends CrudRepository<CodePiece, Long> {

    public Iterable<CodePiece> findTop10ByOrderByIdDesc();
}
