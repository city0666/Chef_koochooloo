package com.xtronlabs.koochooloo.util.network.request;

import android.content.Context;

import com.xtronlabs.koochooloo.fragment.BaseFragment;
import com.xtronlabs.koochooloo.util.network.AbstractRequest;
import com.xtronlabs.koochooloo.util.network.response_models.Fact;
import com.xtronlabs.koochooloo.util.network.response_models.Facts;
import com.xtronlabs.koochooloo.util.network.response_models.ProcessResponseInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetFactsForCountryRequest extends AbstractRequest implements Callback<Facts> {

    ProcessResponseInterface<Facts> mResponseHandler;

    @SuppressWarnings("unchecked")
    public GetFactsForCountryRequest(Context mContext, ProcessResponseInterface<Facts> responseHandler, int countryId) {
        super(mContext);
        mResponseHandler = responseHandler;
        Call<Facts> getFactsCall = mNetworkInterface.getFactForCountry(countryId);
        getFactsCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<Facts> call, Response<Facts> response) {
        mResponseHandler.processResponse(response.body());
    }

    @Override
    public void onFailure(Call<Facts> call, Throwable t) {
        mResponseHandler.processResponse(null);
    }
}
