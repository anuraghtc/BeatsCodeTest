package sample.com.beatscodetest.model;

import android.os.Parcelable;

/**
 * POJO to hold search result item
 */
public class AlbumSearchResultItem implements Cloneable{

    private String id = "";
    private String display = "";
    private String detail = "";

    public AlbumSearchResultItem() {
    }

    public AlbumSearchResultItem(String id) {
        this.id = id;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public AlbumSearchResultItem(String id, String display, String detail) {

        this.id = id;
        this.display = display;
        this.detail = detail;
    }


    @Override
    public Object clone()  {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            // does not happen (since we implement Cloneable)
            return new AlbumSearchResultItem();
        }
    }
}
