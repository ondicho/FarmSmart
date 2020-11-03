package com.networking;
////
////
import com.Constants;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.models.Datum;
import com.models.Links;
import com.moringaschool.farmsmart.FarmProcedureActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
////
////public class TrefleClient {
////    public static Retrofit retrofit=null;
////
////    public static TrefleApi apiInstances(){
////        OkHttpClient.Builder okHttpClient=new OkHttpClient.Builder();
////
////        if (retrofit == null)   {
////
////            HttpLoggingInterceptor httpLoggingInterceptor= new HttpLoggingInterceptor();
////            httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
////
////            okHttpClient.addInterceptor(httpLoggingInterceptor).build();
////        }
////        retrofit=new Retrofit
////                .Builder()
////                .baseUrl(Constants.TREFLE_BASE_URL)
////                .client(okHttpClient.build())
////                .addConverterFactory(GsonConverterFactory.create())
////                .build();
////
////        return retrofit.create(TrefleApi.class);
////    }
////}
//
//
//package com.networking;
//
//import com.Constants;
//
//import java.io.IOException;
//
//import okhttp3.Interceptor;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class TrefleClient{
//
//    private static Retrofit retrofit = null;
//
//    public static TrefleApi getPlants() {
//        if (retrofit == null) {
//            OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                    .addInterceptor(new Interceptor() {
//                        @Override
//                        public Response intercept(Chain chain) throws IOException {
//                            Request newRequest = chain.request().newBuilder()
//                                    .addHeader("Authorization", Constants.TREFLE_API_KEY)
//                                    .build();
//                            return chain.proceed(newRequest);
//                        }
//                    })
//                    .build();
//
////            HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.TREFLE_LEFT_URL + Constants.TREFLE_API_KEY + Constants.TREFLE_RIGHT_URL + userInput).newBuilder();
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(Constants.TREFLE_LEFT_URL+ Constants.TREFLE_API_KEY + Constants.TREFLE_RIGHT_URL)
//                    .client(okHttpClient)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//            return retrofit.create(TrefleApi.class);
//    }
//}
public class TrefleClient {
    public static void findCrops(String userInput, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.TREFLE_LEFT_URL + Constants.TREFLE_API_KEY + Constants.TREFLE_LEFT_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.TREFLE_PLANT_QUERY_PARAMETER, userInput);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Constants.TREFLE_API_KEY)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Datum> processResults(Response response) {
        ArrayList<Datum> crops = new ArrayList<>();
        try {
            Gson g=new Gson();
            String jsonData = response.body().string();
            JSONObject trefleJSON = new JSONObject(jsonData);
            JSONArray plantJSON=trefleJSON.getJSONArray("data");
//            Datum data = g.fromJson(jsonData, Datum.class);

            if (response.isSuccessful()) {
                for (int i = 0; i < plantJSON.length(); i++) {
                    JSONObject datumJSON = plantJSON.getJSONObject(i);
                    Datum datum = new Datum();
                    Links links = new Links();
                    datum.setId(datumJSON.getInt("id"));
                    datum.setCommonName(datumJSON.getString("common_name"));
                    datum.setScientificName(datumJSON.getString("scientific_name"));
                    datum.setSlug(datumJSON.getString("slug"));
                    datum.setYear(datumJSON.getInt("year"));
                    datum.setAuthor(datumJSON.getString("author"));
                    datum.setBibliography(datumJSON.getString("bibliography"));
                    datum.setStatus(datumJSON.getString("status"));
                    datum.setRank(datumJSON.getString("rank"));
                    datum.setImageUrl(datumJSON.getString("image_url"));
                    datum.setFamilyCommonName(datumJSON.getString("family_common_name"));
                    datum.setGenusId(datumJSON.getInt("genus_id"));
                    datum.setFamily(datumJSON.getString("family"));
                    ArrayList<String> synonyms = new ArrayList<>();
                    JSONArray synonymsJSON = datumJSON.getJSONObject("synonyms").getJSONArray("display_synonym");
                    for (int y = 0; y < synonymsJSON.length(); y++) {
                        synonyms.add(synonyms.get(y).toString());
                    }


                    ////                    ArrayList<Links> Links= new ArrayList<>();
                    //                      JSONArray LinksJSON = datumJSON.getJSONObject("Links").getJSONArray("Links");
                    //                        String genus = datumJSON.getString("genus");
                    links.setPlant(datumJSON.getString("plant"));
                    links.setGenus(datumJSON.getString("genus"));
                    links.setSelf(datumJSON.getString("self"));
                    //                   for (int y = 0; y < LinksJSON.length(); y++){
                    //                       Links.add(Links.get(y));
                    //                     }


                    //                    Datum datum = new Datum(id,commonName, slug,scientificName, year, bibliography,author,status,rank,familyCommonName,genusId,imageUrl,synonyms,genus,family,new Links(self,plant,genus));
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




























































