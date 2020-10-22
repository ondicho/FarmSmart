package com.moringaschool.farmsmart;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TrefleApi {
        @GET("plants/search")
        Call<TreflePlantSearchResponse> getPlants(
                @Query("common_name") String commonName,
                @Query("scientific_name") String scientificName
        );
}
