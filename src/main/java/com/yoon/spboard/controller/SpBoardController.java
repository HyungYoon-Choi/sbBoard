package com.yoon.spboard.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yoon.spboard.dto.BbsDto;
import com.yoon.spboard.service.BbsService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bbs")
public class SpBoardController {

    private final BbsService bbsService;

    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BbsDto bbsDto) {
        System.out.println("bbsDto = " + bbsDto);
        bbsService.save(bbsDto);
        return "index";
    }

    @GetMapping("/")
    public String findAll(Model model) {
        // 게시물 목록 보기
        List<BbsDto> bbsDtoList = bbsService.findAll();
        model.addAttribute("list", bbsDtoList);
        return "list";
    }

    @GetMapping("/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<BbsDto> bbsList = bbsService.paging(pageable);
        int pageLimit = 5; // 1, 6, 11, 16 ... 20
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / pageLimit))) - 1) * pageLimit + 1;
        int endPage = ((startPage + pageLimit - 1) < bbsList.getTotalPages()) ? startPage + pageLimit - 1
                : bbsList.getTotalPages();

        model.addAttribute("bbsList", bbsList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "paging";
    }

    @GetMapping("/{num}")
    public String findById(@PathVariable Long num, Model model) {
        bbsService.updateHits(num);
        BbsDto bbsDto = bbsService.findById(num);
        model.addAttribute("bbs", bbsDto);
        return "detail";
    }

    @GetMapping("/update/{num}")
    public String updateForm(@PathVariable Long num, Model model) {
        BbsDto bbsDto = bbsService.findById(num);
        model.addAttribute("bbs", bbsDto);
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BbsDto bbsDto, Model model) {
        BbsDto bbs = bbsService.update(bbsDto);
        model.addAttribute("bbs", bbs);
        return "detail";
        // return "redirect:/bbs/" + bbsDto.getNum();
    }

    @GetMapping("/delete/{num}")
    public String delete(@PathVariable Long num) {
        bbsService.delete(num);
        return "redirect:/bbs/";
    }

    // @GetMapping("/addPage")
    // public String addpage() {
    // bbsService.addPage();
    // return "redirect:/bbs/";
    // }
}
