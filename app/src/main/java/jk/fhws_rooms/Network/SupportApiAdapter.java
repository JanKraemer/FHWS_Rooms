package jk.fhws_rooms.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jan on 11.04.2017.
 */

public class SupportApiAdapter {

    private final String baseURL = "http://backend2.applab.fhws.de:8080/fhwsapi/v1/";
    private static IFhwsApi fhwsAdapter;

    private SupportApiAdapter(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        fhwsAdapter = retrofit.create(IFhwsApi.class);
    }

    public static IFhwsApi getSupportApiAdapter(){
        if(fhwsAdapter == null)
            new SupportApiAdapter();
        return fhwsAdapter;
    }



}
