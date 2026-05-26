package com.example.recipehome;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class HomeFragment extends Fragment {
    private static final int BLUE = Color.rgb(53, 84, 129);
    private static final int SECONDARY = Color.rgb(159, 165, 192);
    private static final int FORM = Color.rgb(244, 245, 247);
    private static final int GREEN = Color.rgb(31, 204, 121);

    private final Recipe[] recipes = {
            new Recipe("Calum Lewis", "Pancake", "Food", "> 60 mins",
                    R.drawable.pancake_one, Color.rgb(53, 84, 129)),
            new Recipe("Eilif Sonas", "Salad", "Food", "> 60 mins",
                    R.drawable.salad_one, Color.rgb(46, 113, 89)),
            new Recipe("Elena Shelby", "Pancake", "Food", "> 60 mins",
                    R.drawable.pancake_two, Color.rgb(171, 108, 42)),
            new Recipe("John Priyadi", "Salad", "Food", "> 60 mins",
                    R.drawable.salad_two, Color.rgb(112, 131, 154))
    };

    @Override
    public View onCreateView(android.view.LayoutInflater inflater, ViewGroup container, Bundle state) {
        ScrollView scrollView = new ScrollView(getActivity());
        scrollView.setFillViewport(true);
        scrollView.setBackgroundColor(Color.WHITE);

        FrameLayout screen = new FrameLayout(getActivity());
        screen.setBackgroundColor(Color.WHITE);
        scrollView.addView(screen, new ScrollView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dp(854)));

        screen.addView(searchBox(), frame(327, 56, 24, 60));
        screen.addView(label("Category", 18, Typeface.BOLD, BLUE, Gravity.LEFT), frame(327, 28, 24, 139));

        screen.addView(categoryChip("All", true, 15, 24, 15, 24), frame(68, 48, 24, 183));
        screen.addView(categoryChip("Food", false, 11, 24, 11, 24), frame(86, 47, 108, 183));
        screen.addView(categoryChip("Drink", false, 11, 24, 11, 24), frame(88, 47, 210, 183));

        screen.addView(recipeCard(recipes[0]), frame(151, 264, 24, 278));
        screen.addView(recipeCard(recipes[1]), frame(151, 264, 200, 278));
        screen.addView(recipeCard(recipes[2]), frame(151, 264, 24, 566));
        screen.addView(recipeCard(recipes[3]), frame(151, 264, 200, 566));

        return scrollView;
    }


    private View searchBox() {
        LinearLayout box = new LinearLayout(getActivity());
        box.setGravity(Gravity.CENTER_VERTICAL);
        box.setPadding(dp(20), 0, dp(20), 0);
        box.setBackground(rounded(FORM, 0, 0, dp(2)));

        TextView icon = label("Q", 22, Typeface.NORMAL, BLUE, Gravity.CENTER);
        icon.setRotation(-18);
        box.addView(icon, new LinearLayout.LayoutParams(dp(28), ViewGroup.LayoutParams.MATCH_PARENT));

        TextView hint = label("Search", 14, Typeface.NORMAL, SECONDARY, Gravity.CENTER_VERTICAL);
        LinearLayout.LayoutParams hintParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        hintParams.setMargins(dp(8), 0, 0, 0);
        box.addView(hint, hintParams);

        return box;
    }

    private TextView categoryChip(String text, boolean selected, int top, int right, int bottom, int left) {
        TextView chip = label(text, 14, Typeface.BOLD, selected ? Color.WHITE : SECONDARY, Gravity.CENTER);
        chip.setPadding(dp(left), dp(top), dp(right), dp(bottom));
        chip.setBackground(rounded(selected ? GREEN : FORM, 0, 0, dp(32)));
        return chip;
    }

    private View recipeCard(Recipe recipe) {
        FrameLayout card = new FrameLayout(getActivity());

        LinearLayout author = new LinearLayout(getActivity());
        author.setGravity(Gravity.CENTER_VERTICAL);
        author.addView(avatar(recipe.author, recipe.avatarColor));

        TextView name = label(recipe.author, 11, Typeface.NORMAL, BLUE, Gravity.CENTER_VERTICAL);
        LinearLayout.LayoutParams nameParams = new LinearLayout.LayoutParams(
                0, ViewGroup.LayoutParams.MATCH_PARENT, 1f);
        nameParams.setMargins(dp(8), 0, 0, 0);
        author.addView(name, nameParams);
        card.addView(author, frame(151, 32, 0, 0));

        card.addView(recipeImage(recipe), frame(151, 126, 0, 40));
        card.addView(label(recipe.title, 18, Typeface.BOLD, BLUE, Gravity.LEFT), frame(151, 30, 0, 178));
        card.addView(label(recipe.category + "  >  " + recipe.time, 11, Typeface.NORMAL, SECONDARY, Gravity.LEFT), frame(151, 24, 0, 211));

        return card;
    }

    private View avatar(String author, int color) {
        TextView avatar = label(author.substring(0, 1), 11, Typeface.BOLD, Color.WHITE, Gravity.CENTER);
        avatar.setBackground(rounded(color, 0, 0, dp(12)));
        avatar.setLayoutParams(new LinearLayout.LayoutParams(dp(24), dp(24)));
        return avatar;
    }

    private View recipeImage(Recipe recipe) {
        FrameLayout frame = new FrameLayout(getActivity());
        frame.setBackground(rounded(FORM, 0, 0, dp(12)));

        ImageView image = new ImageView(getActivity());
        image.setImageResource(recipe.imageRes);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        frame.addView(image, frame(-1, -1, 0, 0));

        TextView heart = label("\u2661", 26, Typeface.BOLD, Color.WHITE, Gravity.CENTER);
        heart.setBackground(rounded(Color.argb(120, 255, 255, 255), 0, 0, dp(18)));
        frame.addView(heart, frame(36, 36, 105, 10));

        return frame;
    }

    private TextView label(String value, int sp, int style, int color, int gravity) {
        TextView view = new TextView(getActivity());
        view.setText(value);
        view.setTextColor(color);
        view.setTextSize(sp);
        view.setTypeface(Typeface.DEFAULT, style);
        view.setGravity(gravity);
        view.setIncludeFontPadding(true);
        return view;
    }

    private FrameLayout.LayoutParams frame(int width, int height, int left, int top) {
        int w = width < 0 ? ViewGroup.LayoutParams.MATCH_PARENT : dp(width);
        int h = height < 0 ? ViewGroup.LayoutParams.MATCH_PARENT : dp(height);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(w, h);
        params.leftMargin = dp(left);
        params.topMargin = dp(top);
        return params;
    }

    private GradientDrawable rounded(int color, int strokeWidth, int strokeColor, int radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(radius);
        if (strokeWidth > 0) {
            drawable.setStroke(strokeWidth, strokeColor);
        }
        return drawable;
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }

    private static class Recipe {
        final String author;
        final String title;
        final String category;
        final String time;
        final int imageRes;
        final int avatarColor;

        Recipe(String author, String title, String category, String time, int imageRes, int avatarColor) {
            this.author = author;
            this.title = title;
            this.category = category;
            this.time = time;
            this.imageRes = imageRes;
            this.avatarColor = avatarColor;
        }
    }
}
