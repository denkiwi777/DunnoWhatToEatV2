package com.dunnoWhatToEat.snapshot.repository;


import java.util.List;

interface RicettaRepositoryCustom {

     List getRandomRecipes(int numberOfRecipes);
     void getRecipesfromIngredients();
}
