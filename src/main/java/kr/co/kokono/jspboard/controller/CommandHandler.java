package kr.co.kokono.jspboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface CommandHandler {

    String process(HttpServletRequest req, HttpServletResponse res) throws Exception;
}
