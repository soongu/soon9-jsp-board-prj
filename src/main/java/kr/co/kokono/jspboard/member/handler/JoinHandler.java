package kr.co.kokono.jspboard.member.handler;

import kr.co.kokono.jspboard.controller.CommandHandler;
import kr.co.kokono.jspboard.member.exception.DuplicateAccountException;
import kr.co.kokono.jspboard.member.service.JoinRequestDto;
import kr.co.kokono.jspboard.member.service.JoinService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JoinHandler implements CommandHandler {

    public static final String FORM_VIEW = "/WEB-INF/view/joinForm.jsp";
    private final JoinService joinService = new JoinService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        if (req.getMethod().equalsIgnoreCase("GET")) {
            return processForm(req, res);
        } else if (req.getMethod().equalsIgnoreCase("POST")) {
            return processSubmit(req, res);
        } else {
            res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return null;
        }
    }

    private String processForm(HttpServletRequest req, HttpServletResponse res) {
        return FORM_VIEW;
    }
    private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
        JoinRequestDto dto = new JoinRequestDto();
        dto.setAccount(req.getParameter("account"));
        dto.setName(req.getParameter("name"));
        dto.setPassword(req.getParameter("password"));
        dto.setConfirmPassword(req.getParameter("confirmPassword"));

        Map<String, Boolean> errors = new HashMap<>();
        req.setAttribute("errors", errors);

        dto.validate(errors);

        if (!errors.isEmpty()) {
            return FORM_VIEW;
        }

        try {
            joinService.join(dto);
            return "/WEB-INF/view/joinSuccess.jsp";
        } catch (DuplicateAccountException e) {
            errors.put("duplicateAccount", Boolean.TRUE);
            return FORM_VIEW;
        }
    }

}
