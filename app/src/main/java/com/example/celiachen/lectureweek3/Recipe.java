package com.example.celiachen.lectureweek3;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by celiachen on 2/5/18.
 */

public class Recipe {


    // instance variables or fields
    public String title;
    public String imageUrl;
    public String instructionUrl;
    public String description;
    public String label;
    public int servings;
    public int prepTime;
    // constructor
    // default

    // method
    // static methods that read the json file in and load into Recipe

    // static method that loads our recipes.json using the helper method
    // this method will return an array list of recipes constructed from the JSON
    // file
    public static ArrayList<com.example.celiachen.lectureweek3.Recipe> getRecipesFromFile(String filename,Context context){
        ArrayList<com.example.celiachen.lectureweek3.Recipe> recipeList = new ArrayList<com.example.celiachen.lectureweek3.Recipe>();


        // try to read from JSON file
        // get information by using the tags
        // construct a Recipe Object for each recipe in JSON
        // add the object to arraylist
        // return arraylist
        try{
            String jsonString = loadJsonFromAsset("recipes.json", context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray recipes = json.getJSONArray("recipes");

            // for loop to go through each recipe in your recipes array

            for (int i = 0; i < recipes.length(); i++){
                com.example.celiachen.lectureweek3.Recipe recipe = new com.example.celiachen.lectureweek3.Recipe();
                recipe.title = recipes.getJSONObject(i).getString("title");
                recipe.description = recipes.getJSONObject(i).getString("description");
                recipe.imageUrl = recipes.getJSONObject(i).getString("image");
                recipe.instructionUrl = recipes.getJSONObject(i).getString("url");
                recipe.label = recipes.getJSONObject(i).getString("dietLabel");
                recipe.servings = recipes.getJSONObject(i).getInt("servings");
                recipe.prepTime = recipes.getJSONObject(i).getInt("prepTime");


                // add to arraylist
                recipeList.add(recipe);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipeList;
    }


    // helper method that loads from any Json file
    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

}