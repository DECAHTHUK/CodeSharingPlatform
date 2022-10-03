package platform.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.business.Code;
import platform.business.CodeService;

@RestController
public class codeController {
    @Autowired
    CodeService codeService;

    /*@GetMapping(value = "/code/{id}", produces = "text/html")
    public ModelAndView getPieceOfCode(Model model, @PathVariable int id) {
        model.addAttribute("code", codeService.findById(id));
        return new ModelAndView("codeAsHtml");
    }*/

    @GetMapping(value = "/code/new")
    public ModelAndView postCode() {
        return new ModelAndView("create");
    }

    @GetMapping(value = "/code/latest", produces = "text/html")
    public ModelAndView getLatest(Model model) {
        model.addAttribute("codes", codeService.getLatest());
        return new ModelAndView("latestCodes");
    }

    @GetMapping(value = "/code/{uuid}")
    public ModelAndView getUuidCode(Model model, @PathVariable String uuid) {
        Code code = codeService.findByUuid(uuid);
        model.addAttribute("code", code);
        System.out.println(code);
        return new ModelAndView("codeAsHtml");
    }
}
