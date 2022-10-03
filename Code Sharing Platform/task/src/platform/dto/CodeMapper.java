package platform.dto;

import org.springframework.stereotype.Component;
import platform.business.Code;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CodeMapper {
    public Code toCode(CodeDto codeDto) {
        Code code = new Code();
        code.setCode(codeDto.getCode());
        code.setDate(LocalDateTime.now().toString());
        code.setTime(codeDto.getTime());
        code.setStartingTime(codeDto.getTime());
        if (codeDto.getViews() == null || codeDto.getViews() == 0) {
            code.setViews(0L);
            code.setViewsInit(false);
        } else {
            code.setViews(codeDto.getViews());
            code.setViewsInit(true);
        }
        UUID uuid = UUID.randomUUID();
        code.setUuid(uuid.toString());
        return code;
    }
}
