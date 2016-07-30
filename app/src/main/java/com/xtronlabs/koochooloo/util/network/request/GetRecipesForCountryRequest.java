package com.xtronlabs.koochooloo.util.network.request;

import android.content.Context;

import com.xtronlabs.koochooloo.fragment.BaseFragment;
import com.xtronlabs.koochooloo.util.network.AbstractRequest;
import com.xtronlabs.koochooloo.util.network.response_models.ProcessResponseInterface;
import com.xtronlabs.koochooloo.util.network.response_models.Recipe;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetRecipesForCountryRequest extends AbstractRequest implements Callback<Recipe[]> {

    private ProcessResponseInterface<Recipe[]> mResponseHandler;

    @SuppressWarnings("unchecked")
    public GetRecipesForCountryRequest(Context mContext,
                                       ProcessResponseInterface<Recipe[]> responseHandler,
                                       int countryId) {
        super(mContext);
        mResponseHandler =  responseHandler;
        Call<Recipe[]> getRecipesForCountryCall = mNetworkInterface.getRecipesForCountry(countryId);
        getRecipesForCountryCall.enqueue(this);
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
