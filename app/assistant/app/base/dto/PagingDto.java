package assistant.app.base.dto;

import java.io.Serializable;

import play.mvc.Http.Request;

/**
 * 分页
 * 
 * 默认 curPage=1 pageSize=10
 * 
 */
public class PagingDto implements Serializable {
    private Integer curPage;
    private Integer pageSize;
    private Long count;

    public PagingDto() {
        try {
            this.curPage = Integer.parseInt(Request.current().params.get("curPage"));
        } catch (NumberFormatException e) {

        }
        try {
            this.pageSize = Integer.parseInt(Request.current().params.get("pageSize"));
        } catch (NumberFormatException e) {
        }
        init();
    }

    public PagingDto(Integer curPage, Integer pageSize) {
        this.curPage = curPage;
        this.pageSize = pageSize;

        init();
    }

    private void init() {
        if ((this.curPage == null) || (this.curPage <= 0)) {
            this.curPage = 1;
        }

        if ((this.pageSize == null) || (this.pageSize <= 0)) {
            this.pageSize = 10;
        }
    }

    public Integer getCurPage() {
        return curPage;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Long getBegin() {
        return 1L * (this.curPage - 1) * this.pageSize;
    }

    public Integer getPageCount() {
        int mod = ((this.count % this.pageSize) == 0) ? 0 : 1;
        int divide = (int) (this.count / this.pageSize);

        return divide + mod;
    }

    public Long getCount() {
        return count;
    }

    public boolean hasRecords() {
        return (curPage * pageSize) < count;
    }

}
