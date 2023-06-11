package platform.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.model.CodePiece;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CodeRepository extends CrudRepository<CodePiece, Long> {
    Optional<CodePiece> findByUuid(UUID UUID);

    public Iterable<CodePiece> findTop10ByRestrictedFalseOrderByIdDesc();
}
