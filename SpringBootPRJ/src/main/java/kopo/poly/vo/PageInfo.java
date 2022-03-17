package kopo.poly.vo;

public class PageInfo {

    private int currentPage; // 현재 페이지
    private int listCount;   // 총 게시글의 수 [ SELECT COUNT(*) FROM BOARD ]
    private int pageLimit;   // 한 페이지에 보여질 페이징 수 ex) 총 페이지가 13개면, 1 ~ 10 / 11 ~ 13
    private int maxPage;     // 총 페이지    (1 ~ 50)
    private int startPage;   // 시작 페이지  (1    11    21    31    41)
    private int endPage;     // 끝 페이지    (10    20    30    40    50)

    private int boardLimit;

    public PageInfo() {
        super();
    }

    public PageInfo(int currentPage, int listCount, int pageLimit, int maxPage, int startPage, int endPage, int boardLimit) {
        super();
        this.currentPage = currentPage;
        this.listCount = listCount;
        this.pageLimit = pageLimit;
        this.maxPage = maxPage;
        this.startPage = startPage;
        this.endPage = endPage;
        this.boardLimit = boardLimit;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getListCount() {
        return listCount;
    }

    public void setListCount(int listCount) {
        this.listCount = listCount;
    }

    public int getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(int pageLimit) {
        this.pageLimit = pageLimit;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getBoardLimit() {
        return boardLimit;
    }

    public void setBoardLimit(int boardLimit) {
        this.boardLimit = boardLimit;
    }

    @Override
    public String toString() {
        return "PageInfo [currentPage=" + currentPage + ", listCount=" + listCount + ", pageLimit=" + pageLimit
                + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage=" + endPage + ", boardLimit="
                + boardLimit + "]";
    }
}