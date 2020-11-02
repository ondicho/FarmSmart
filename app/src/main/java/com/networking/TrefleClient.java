//package com.networking;
//
//
//import com.Constants;
//
//import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class TrefleClient {
//    public static Retrofit retrofit=null;
//
//    public static TrefleApi apiInstances(){
//        OkHttpClient.Builder okHttpClient=new OkHttpClient.Builder();
//
//        if (retrofit == null)   {
//
//            HttpLoggingInterceptor httpLoggingInterceptor= new HttpLoggingInterceptor();
//            httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
//
//            okHttpClient.addInterceptor(httpLoggingInterceptor).build();
//        }
//        retrofit=new Retrofit
//                .Builder()
//                .baseUrl(Constants.TREFLE_BASE_URL)
//                .client(okHttpClient.build())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        return retrofit.create(TrefleApi.class);
//    }
//}


package com.networking;

import com.Constants;

import com.models.Datum;
import com.models.Links;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TrefleClient{

    public static void getPlants(String userInput, Callback callback){
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.TREFLE_BASE_URL).newBuilder();
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Constants.TREFLE_API_KEY)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Datum> processResults(Response response){
        ArrayList<Datum> crops = new ArrayList<>();
        try{
            String jsonData = response.body().string();
            JSONObject trefleJSON = new JSONObject(jsonData);
            JSONArray plantJSON = trefleJSON.getJSONArray("plants");
            if (response.isSuccessful()){
                for (int i = 0; i < plantJSON.length(); i++){
                    JSONObject datumJSON = trefleJSON.getJSONObject(String.valueOf(i));
                    Integer id=datumJSON.getInt("id");
                    String commonName = datumJSON.getString("common_name");
                    String scientificName = datumJSON.getString("scientific_name");
                    String slug = datumJSON.getString("slug");
                    Integer year = datumJSON.getInt("year");
                    String author= datumJSON.getString("author");
                    String bibliography= datumJSON.getString("bibliography");
                    String status= datumJSON.getString("status");
                    String rank= datumJSON.getString("rank");
                    String imageUrl= datumJSON.getString("imageUrl");
                    String familyCommonName= datumJSON.getString("familyCommonName");
                    Integer genusId=datumJSON.getInt("genusId");

                    ArrayList<String> synonyms= new ArrayList<>();
                    JSONArray synonymsJSON = datumJSON.getJSONObject("synonym").getJSONArray("display_synonym");
                    for (int y = 0; y < synonymsJSON.length(); y++){
                        synonyms.add(synonyms.get(y).toString());
                    }
                    String genus= datumJSON.getString("genus");
                    String family= datumJSON.getString("family");

////                    ArrayList<Links> Links= new ArrayList<>();
//                      JSONArray LinksJSON = datumJSON.getJSONObject("Links").getJSONArray("Links");
//                        String genus = datumJSON.getString("genus");
                        String plant = datumJSON.getString("plant");
                        String self = datumJSON.getString("self");
//                   for (int y = 0; y < LinksJSON.length(); y++){
//                       Links.add(Links.get(y));
//                     }


                    Datum datum = new Datum(id,commonName, slug,scientificName, year, bibliography,author,status,rank,familyCommonName,genusId,imageUrl,synonyms,genus,family,new Links(self,plant,genus));
                    crops.add(datum);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return crops;
    }
}

