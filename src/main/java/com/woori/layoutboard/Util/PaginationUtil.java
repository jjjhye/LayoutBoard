package com.woori.layoutboard.Util;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PaginationUtil {
    //페이지처리 메소드
    public static Map<String,Integer> Pagination(Page<?>page){
        Map<String,Integer> pageInfo = new HashMap<>();
        //페이지 기본 정보, 변수명 사용자 마음대로, put고정
        int currentPage = page.getNumber()+1;
        int totalPages = page.getTotalPages();
        int blockLimit = 5;

        //필요한 페이지 정보를 만들기
        int startPage = Math.max(1,currentPage-blockLimit/2);
        int endPage = Math.min(startPage+blockLimit-1,totalPages);
        int prevPage = Math.max(1,currentPage-1);//currentPage-10
        int nextPage = Math.min(totalPages,currentPage+1);//2->3, currentpage+10 2->12
        int lastPage = totalPages;

        pageInfo.put("startPage", startPage);
        pageInfo.put("endPage", endPage);
        pageInfo.put("prevPage", prevPage);
        pageInfo.put("nextPage", nextPage);
        pageInfo.put("lastPage", lastPage);
        pageInfo.put("currentPage", currentPage);
        return pageInfo;
    }
}
