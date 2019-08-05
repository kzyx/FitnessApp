package com.example.fitnessapp.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

// Named "Utility" as advised by https://stackoverflow.com/a/1484371
public class Utility {

    /*
    For a string of words separated by spaces, returns a string with each word capitalized
     */
    public static String capitalize(String string) {
        if (string.length() == 0) return string;
        if (string.length() == 1) return string.toUpperCase();

        // Split string on spaces
        String tempArray[] = string.split("\\s+");

        // Iterate, capitalizing each word
        StringBuilder returnString = new StringBuilder();
        String temp;
        for (String s : tempArray) {
            temp = s.toLowerCase();
            returnString.append(temp.substring(0, 1).toUpperCase())
                    .append(temp.substring(1)).append(" ");
        }

        // Remove last space
        returnString.setLength(returnString.length() - 1);

        return returnString.toString();
    }


    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
