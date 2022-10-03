package platform.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import platform.persistence.CodeRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CodeService {
    private final CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public Code save(Code toSave) {
        return codeRepository.save(toSave);
    }

    public List<Code> getAll() {
        return codeRepository.findAll();
    }
    public ArrayList<Code> getLatest() {
        ArrayList<Code> list = new ArrayList<>(codeRepository.findAll());
        ArrayList<Code> out = new ArrayList<>();
        int cnt = 0;
        for (int i = list.size() - 1;  i >= 0 && cnt < 10; i--) {
            if (!isRestricted(list.get(i))) {
                out.add(list.get(i));
                cnt++;
            }
        }
        return out;
    }

    public Code findById(int id) {
        return codeRepository.findCodeById((long)id);
    }

    public Code findByUuid(String uuid) {
        List<Code> list = codeRepository.findAll().stream()
                .filter(t -> t.getUuid().equals(uuid))
                    .collect(Collectors.toList());
        if (list.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Code code = list.get(0);
        if (isRestricted(code)) {
            if (code.getViews() != 0) {
                code.setViews(code.getViews() - 1);
            } else if (code.getViews() == 0 && code.isViewsInit()) {
                codeRepository.delete(code);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            if (code.getStartingTime() != 0) {
                Instant now = Instant.now();
                Instant est = LocalDateTime.parse(code.getDate()).atZone(ZoneId.of("GMT+3")).toInstant().plusSeconds(code.getStartingTime());
                Long time1 = now.getLong(ChronoField.INSTANT_SECONDS);
                Long time2 = est.getLong(ChronoField.INSTANT_SECONDS);
                Long difference = time2 - time1;
                if (difference <= 0) {
                    codeRepository.delete(code);
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                }
                code.setTime(difference);
            }
            codeRepository.save(code);
        }
        return code;
    }

    public boolean isRestricted(Code code) {
        return code.getStartingTime() != 0 || code.isViewsInit();
    }
}
