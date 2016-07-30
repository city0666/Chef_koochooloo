package com.xtronlabs.koochooloo.util.network.request;

import android.content.Context;

import com.xtronlabs.koochooloo.fragment.BaseFragment;
import com.xtronlabs.koochooloo.util.network.AbstractRequest;
import com.xtronlabs.koochooloo.util.network.response_models.Fact;
import com.xtronlabs.koochooloo.util.network.response_models.ProcessResponseInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetFactsForCountryRequest extends AbstractRequest implements Callback<Fact[]> {

    ProcessResponseInterface<Fact[]> mResponseHandler;

    @SuppressWarnings("unchecked")
    public GetFactsForCountryRequest(Context mContext, ProcessResponseInterface<Fact[]> responseHandler, int countryId) {
        super(mContext);
        mResponseHandler = responseHandler;
        Call<Fact[]> getFactsCall = mNetworkInterface.getFactForCountry(countryId);
        getFactsCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<Fact[]> call, Response<Fact[]> response) {
        mResponseHandler.processResponse(response.body());
    }

    @Override
    public void onFailure(Call<Fact[]> call, Throwable t) {
        mResponseHandler.processResponse(null);
    }
}
