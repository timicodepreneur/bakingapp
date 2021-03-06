package com.tarrotsystem.codepreneur.bakingrite;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.core.deps.guava.annotations.VisibleForTesting;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.tarrotsystem.codepreneur.bakingrite.adapter.RecipeAdapter;
import com.tarrotsystem.codepreneur.bakingrite.model.Recipe;
import com.tarrotsystem.codepreneur.bakingrite.utils.SimpleIdlingResource;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tarrotsystem.codepreneur.bakingrite.DetailActivity.SELECTED_RECIPES;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.RecipeClickListener{

    @BindView(R.id.my_toolbar)
    Toolbar toolbar;

    @Nullable
    private SimpleIdlingResource mIdlingResource;

    /**
     * Only called from test, creates and returns a new {@link SimpleIdlingResource}.
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onClick(Recipe clickedItemIndex) {
        ArrayList<Recipe> selectedRecipe = new ArrayList<>();
        selectedRecipe.add(clickedItemIndex);

        Bundle selectedRecipeBundle = new Bundle();
        selectedRecipeBundle.putParcelableArrayList(SELECTED_RECIPES,selectedRecipe);

        final Intent intent = new Intent(this, DetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtras(selectedRecipeBundle);
        startActivity(intent);
    }
}
