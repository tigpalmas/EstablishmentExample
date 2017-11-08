package com.example.tiago.establishmentexample.network;

import com.example.tiago.establishmentexample.domain.Contato;
import com.example.tiago.establishmentexample.domain.MessagePush;
import com.example.tiago.establishmentexample.domain.Push;
import com.example.tiago.establishmentexample.domain.Order;
import com.example.tiago.establishmentexample.product.Product;
import com.example.tiago.establishmentexample.promotionalListRecycler.Promotion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

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

    @POST("/push/register")
    Call<Push> regsterPush(@Body Push oneSignal);

    @POST("/push/send-message")
    Call<MessagePush> sendPushMessage(@Body MessagePush push);


    @Headers("Content-Type: application/json")
    @POST("/cart")
    Call<Order> postOrder( @Body Order order);





}
