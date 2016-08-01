package com.xtronlabs.koochooloo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.xtronlabs.koochooloo.R;
import com.xtronlabs.koochooloo.util.network.response_models.Country;
import com.xtronlabs.koochooloo.view.KoochoolooLabel;

import java.util.ArrayList;

/**
 * Created by Xtron005 on 01-08-2016.
 */
public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryItemViewHolder> {

    private Country[] mCountries;
    private Context mContext;

    public CountryListAdapter(Country[] mCountries, Context mContext) {
        this.mCountries = mCountries;
        this.mContext = mContext;
    }

    @Override
    public CountryItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CountryItemViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.country_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final CountryItemViewHolder holder, int position) {
        Country country = mCountries[position];
        Glide.with(mContext)
                .load(country.countryFlag.toString())
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        holder.imgFlag.setImageDrawable(resource);
                    }
                });

        holder.lblCountryName.setText(country.name);
    }

    @Override
    public int getItemCount() {
        return mCountries.length;
    }

    class CountryItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imgFlag;
        private KoochoolooLabel lblCountryName;

        public CountryItemViewHolder(View itemView) {
            super(itemView);
            imgFlag = (ImageView) itemView.findViewById(R.id.imgFlag);
            lblCountryName = (KoochoolooLabel) itemView.findViewById(R.id.lblCountryName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
