package com.example.tiago.establishmentexample.network;

import com.example.tiago.establishmentexample.domain.Contato;
import com.example.tiago.establishmentexample.domain.Order;
import com.example.tiago.establishmentexample.domain.OrderRetrofit;
import com.example.tiago.establishmentexample.product.Product;
import com.example.tiago.establishmentexample.promotionalListRecycler.Promotion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static android.R.attr.order;

/**
 * Created by tiago on 09/10/2017.
 */

public interface EstablishmentService {

    @GET("/product/establishment/restaurant")
    Call<List<Product>> getProducts();

    @GET("/product/establishment/shop")
    Call<List<Product>> getShop();

    @GET("/product/establishment/bar")
    Call<List<Product>> getBar();

    @GET("/product/establishment/picturesBar")
    Call<List<Product>> getPicturesBar();


    @GET("/promotion/establishment/restaurant")
    Call<List<Promotion>> getPromotions();

    @GET("/promotion/establishment/shop")
    Call<List<Promotion>> getPromotionsShop();

    @POST("/contact")
    Call<Contato> postContact(@Body Contato contato);


    @Headers("Content-Type: application/json")
    @POST("/cart")
    Call<Order> postOrder( @Body Order order);





}
