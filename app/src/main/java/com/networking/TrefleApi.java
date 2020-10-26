package com.networking;

import com.models.Datum;
import com.models.TreflePlantSearchResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TrefleApi {
        @GET("plants/search")
        Call <List<Datum>> getPlants(
                @Query("common_name") String commonName
        );
}
