package jk.fhws_rooms.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jan on 11.04.2017.
 */

public class SimpleLecturer {

    @SerializedName("label")
    private String fullName;

    private String url;

    public SimpleLecturer( ){ }

    public void setUrl(String url) { this.url = url; }

    public String getUrl( ) { return url; }

    public String getFullName( ) { return fullName; }

    public void setFullName(String fullName) { this.fullName = fullName; }
}
