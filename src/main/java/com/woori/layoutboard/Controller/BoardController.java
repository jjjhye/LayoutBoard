package com.woori.layoutboard.Controller;

import com.woori.layoutboard.DTO.BoardDTO;
import com.woori.layoutboard.Service.BoardService;
import com.woori.layoutboard.Util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    //삽입
    //브라우저나 메뉴에서 /insert로 요청이 들어오면
    //insert.html을 전달
    @GetMapping("/insert")
    public String insert() {
        return "board/insert";
    }
    //insert.html에서 DTO를 이용해서 값을 전달하면
    //BoardService에 insert를 통해서 데이터 베이스에 값을 저장 gn
    // list를 전달
    @PostMapping("/insert")
    public String insertProc(BoardDTO boardDTO){
        boardService.insert(boardDTO);
        return "redirect:/list";//목록없고, 페이지정보없이 이동
    }
    //수정
    //목록폼에서 제목을 클릭시 해당 id번호를 받아서
    //BoardService에 read를 통해 DTO값을 전달받아서, update.html에 전달
    @GetMapping("/update")
    public String update(Integer id, Model model) {
       BoardDTO boardDTO = boardService.read(id);
       model.addAttribute("data", boardDTO);
       return "board/update";
    }
    //수정폼에서 수정한 내용을 DTO로 받아서
    //BoardService에 update를 통해 DTO를 전달해서 데이터베이스에 저장
    //list.html에 전달
    @PostMapping("/update")
    public String updateProc(BoardDTO boardDTO){
        boardService.update(boardDTO);
        return "redirect:/list";//목록없고, 페이지정보없이 이동
    }
    //삭제
    //목록에서 일련번호를 클릭했을 때
    //해당id번호를 받아서 BoardService에 delete를 통해 id에 해당하는 데이터를 삭제
    //list.html를 전달
    @GetMapping("/delete")
    public String delete(Integer id) {
        boardService.delete(id);
        return "redirect:/list";//목록없고, 페이지정보없이 이동
    }
    //목록
    //페이지번호를 입력받아서(없으면 기본페이지 1)
    //BoardService dp list에 페이지번호를 전달하고 결과를 DTO로 받아
    //페이지번호를 HTML에 필요한 값으로 재가공한 후 list.html에 전달
    @GetMapping("/list")
    public String list(@PageableDefault(page=1)Pageable page, Model model) {
        Page<BoardDTO> boardDTOS = boardService.list(page);

        Map<String,Integer> pageInfo = PaginationUtil.Pagination(boardDTOS);
        model.addAttribute("list", boardDTOS);
        model.addAllAttributes(pageInfo);
        return "board/list";//목록있고, 페이지정보도 존재
    }

}
//return에 파일명이 동일한 부분을 찾아서 서로 다른 내용으로 구성되어 있으면
//