package com.xtronlabs.koochooloo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xtronlabs.koochooloo.R;
import com.xtronlabs.koochooloo.util.network.response_models.ResponseModelsNew.RecipeIngredientNew;
import com.xtronlabs.koochooloo.view.KoochoolooLabel;

import java.util.ArrayList;
import java.util.List;


public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.IngredientItemViewHolder> {

    private List<RecipeIngredientNew> mIngredients;
    private Context mContext;

    public IngredientListAdapter(List<RecipeIngredientNew> mIngredients, Context mContext) {
        this.mIngredients = mIngredients;
        this.mContext = mContext;
    }

    @Override
    public IngredientItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new IngredientItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.extra_ingredient_item, parent, false));
    }

    @Override
    public void onBindViewHolder(IngredientItemViewHolder holder, int position) {
        holder.mLabel.setText(mIngredients.get(position).name);
    }

    @Override
    public int getItemCount() {
        return mIngredients.size();
    }

    class IngredientItemViewHolder extends RecyclerView.ViewHolder {

        private KoochoolooLabel mLabel;

        public IngredientItemViewHolder(View itemView) {
            super(itemView);
            mLabel = (KoochoolooLabel) itemView.findViewById(R.id.lblIngredientText);
        }
    }

}
