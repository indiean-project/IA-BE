package com.ia.indieAn.common.pageDto;

import lombok.Data;

@Data
public class PageInfo {


    private int startPage;

    private int endPage;

    private int currentPage;

    private int totalCount;

    private int totalPage;

    public PageInfo(int totalPage, int currentPage, int totalCount, int boardLimit) {
        System.out.println(currentPage);
        this.currentPage = currentPage;
        this.startPage = (currentPage - 1) / boardLimit * boardLimit + 1;
        this.endPage = startPage + boardLimit - 1;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        if (totalPage < this.endPage) {
            this.endPage = totalPage;
        }
    }

}
