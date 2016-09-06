package com.xtronlabs.koochooloo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.xtronlabs.koochooloo.R;
import com.xtronlabs.koochooloo.util.network.response_models.Recipe;
import com.xtronlabs.koochooloo.view.KoochoolooLabel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeListViewHolder> {


    private List<Recipe> mRecipesList;
    private Context mContext;
    private AlertDialog mAlertDialog;

    public RecipeListAdapter(List<Recipe> mRecipesList, Context mContext) {
        this.mRecipesList = mRecipesList;
        this.mContext = mContext;
    }

    @Override
    public RecipeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecipeListViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.favourites_and_recipe_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecipeListViewHolder holder, int position) {
        Recipe recipe = mRecipesList.get(position);
        if (recipe == null) return;
        holder.mRecipeItemTitle.setText(recipe.name);
        if (recipe.thumbnail != null)
            Glide.with(mContext)
                    .load(recipe.thumbnail)
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            holder.mImgRecipeItemImage.setImageBitmap(resource);
                        }
                    });
    }

    @Override
    public int getItemCount() {
        return mRecipesList.size();
    }

    class RecipeListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgRecipeItemImage)
        ImageView mImgRecipeItemImage;
        @BindView(R.id.recipeItemTitle)
        KoochoolooLabel mRecipeItemTitle;
        @BindView(R.id.imgRecipeItemRemove)
        ImageButton mImgRecipeItemRemove;

        public RecipeListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.imgRecipeItemRemove)
        public void onClick() {
            View v = LayoutInflater.from(mContext).inflate(R.layout.screen_four_dialog,null, false);
            if (v == null) return;

            KoochoolooLabel lblQuestion = (KoochoolooLabel) v.findViewById(R.id.lblDialogRemove);
            ImageButton btnRemove = (ImageButton) v.findViewById(R.id.btnDialogOk);
            ImageButton btnCancel = (ImageButton) v.findViewById(R.id.btnDialogCancel);

            lblQuestion.setText("Are you sure, you want to remove the selected item?");
            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRecipesList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mAlertDialog != null){
                        mAlertDialog.dismiss();
                    }
                }
            });

            mAlertDialog = new AlertDialog.Builder(mContext)
                    .setView(v)
                    .setCancelable(false)
                    .show();

        }
    }
}
