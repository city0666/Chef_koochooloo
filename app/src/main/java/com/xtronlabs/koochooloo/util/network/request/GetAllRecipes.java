package com.xtronlabs.koochooloo.util.network.request;

import android.content.Context;

import com.xtronlabs.koochooloo.fragment.BaseFragment;
import com.xtronlabs.koochooloo.util.network.AbstractRequest;
import com.xtronlabs.koochooloo.util.network.response_models.ProcessResponseInterface;
import com.xtronlabs.koochooloo.util.network.response_models.Recipe;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Xtron005 on 30-07-2016.
 */
public class GetAllRecipes extends AbstractRequest implements Callback<Recipe[]> {

    private ProcessResponseInterface<Recipe[]> mResponseHandler;

    public GetAllRecipes(Context mContext, ProcessResponseInterface<Recipe[]> responseHandler) {
        super(mContext);
        mResponseHandler = responseHandler;
        Call<Recipe[]> getAllRecipeCall = mNetworkInterface.getRecipies();
        getAllRecipeCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<Recipe[]> call, Response<Recipe[]> response) {
        mResponseHandler.processResponse(response.body());
    }

    @Override
    public void onFailure(Call<Recipe[]> call, Throwable t) {
        mResponseHandler.processResponse(null);
    }
}
