package com.aivle.mini7.controller;

import com.aivle.mini7.dto.LogDto;
import com.aivle.mini7.service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    private final LogService logService;

    // Admin 메인 페이지
    @GetMapping("")
    public ModelAndView index(Pageable pageable) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/index");

        Page<LogDto.ResponseInputList> inputLogs = logService.getInputLogList(pageable);
        Page<LogDto.ResponseOutputList> outputLogs = logService.getOutputLogList(pageable);

        mv.addObject("InputLogList", inputLogs);
        mv.addObject("OutputLogList", outputLogs);

        return mv;
    }
}
