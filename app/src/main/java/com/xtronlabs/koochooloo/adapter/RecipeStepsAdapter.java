package com.xtronlabs.koochooloo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xtronlabs.koochooloo.R;
import com.xtronlabs.koochooloo.util.network.response_models.ResponseModelsNew.RecipeStepNew;
import com.xtronlabs.koochooloo.view.KoochoolooLabel;

import java.util.ArrayList;


public class RecipeStepsAdapter  extends  RecyclerView.Adapter<RecipeStepsAdapter.RecipeStepsViewHolder>{


    private ArrayList<RecipeStepNew> mList;
    private Context mContext;

    public RecipeStepsAdapter(ArrayList<RecipeStepNew> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public RecipeStepsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecipeStepsViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.extra_recipe_step_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecipeStepsViewHolder holder, int position) {
        holder.mLabel.setText(mList.get(position).text);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class RecipeStepsViewHolder extends RecyclerView.ViewHolder {

        private KoochoolooLabel mLabel;

        public RecipeStepsViewHolder(View itemView) {
            super(itemView);
            mLabel = (KoochoolooLabel) itemView.findViewById(R.id.lblStepItem);
        }
    }

}
