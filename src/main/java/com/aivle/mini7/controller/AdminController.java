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

//     pageable default value
    @GetMapping("")
    public ModelAndView index(Pageable pageable) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/index");

        @GetMapping("/input")
        public List<LogDto.ResponseInputList> getInputLogs() {
            return logService.getInputLogs();
        }

        @GetMapping("/output")
        public List<LogDto.ResponseOutputList> getOutputLogs() {
            return logService.getOutputLogs();
        }        
        
        mv.addObject("Inputloglist", Inputlog);
        mv.addObject("Outputloglist", Outputlog);
        return mv;
    }
}
