package com.xtronlabs.koochooloo.util.network.request;

import android.content.Context;

import com.xtronlabs.koochooloo.util.network.AbstractRequest;
import com.xtronlabs.koochooloo.util.network.response_models.Ingredient;
import com.xtronlabs.koochooloo.util.network.response_models.ProcessResponseInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetIngredientAndTipsRequest extends AbstractRequest implements Callback<Ingredient[]> {

    private ProcessResponseInterface<Ingredient[]> mResponseHandler;

    public GetIngredientAndTipsRequest(Context mContext,
                                       ProcessResponseInterface<Ingredient[]> resposneHandler,
                                       int ingredientId) {
        super(mContext);
        mResponseHandler = resposneHandler;
        Call<Ingredient[]> getIngredientAndTipsCall = mNetworkInterface.getIngredientAndTips(ingredientId);
        getIngredientAndTipsCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<Ingredient[]> call, Response<Ingredient[]> response) {
        mResponseHandler.processResponse(response.body());
    }

    @Override
    public void onFailure(Call<Ingredient[]> call, Throwable t) {
        mResponseHandler.processResponse(null);
    }
}
