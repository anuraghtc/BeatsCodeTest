package sample.com.beatscodetest.model;

/**
 *POJO to hold extra info returned in search result
 */
public class SearchResultInfo {

    private int offset = 0;
    private int total = 0;

    public SearchResultInfo(int offset, int total) {
        this.offset = offset;
        this.total = total;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public void reset(){
        this.offset = 0;
        this.total = 0;
    }


}
