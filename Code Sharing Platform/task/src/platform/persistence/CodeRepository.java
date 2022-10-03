package platform.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.business.Code;

import java.util.List;

public interface CodeRepository extends JpaRepository<Code, Long> {
    @Override
    Code save(Code toSave);

    List<Code> findAll();

    Code findCodeById(Long id);

}
