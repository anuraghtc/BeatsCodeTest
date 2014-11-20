package sample.com.beatscodetest;

/**
 * General configuration for the app
 */
public class Config {

    public static final String CLIENT_ID = "qtv3jd27hk45ymsmhhfsbc9q";
    public static final String BASE_URI = "https://partner.api.beatsmusic.com/v1/api";
    public static final String ALBUM_SEARCH_URI = BASE_URI + "/search?q=%s&type=album&offset=%s&client_id="+CLIENT_ID;
    public static final String IMAGE_LOOKUP_URI = BASE_URI + "/albums/%s/images/default?size=%s&client_id="+CLIENT_ID;

    public static enum ALBUM_IMAGE_SIZE {
         thumb
    };
}
