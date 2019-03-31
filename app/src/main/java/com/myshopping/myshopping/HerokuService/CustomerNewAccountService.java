package com.myshopping.myshopping.HerokuService;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CustomerNewAccountService {

    @POST("customer/new")
    Call<CustomerNewAccount>create(@Body CustomerNewAccount newAccount);

}
