package com.dunnoWhatToEat.snapshot.repository;

import com.dunnoWhatToEat.snapshot.Entity.Ingrediente;
import com.dunnoWhatToEat.snapshot.Entity.Ricetta;
import com.dunnoWhatToEat.snapshot.Entity.RicettaResponse;
import com.dunnoWhatToEat.snapshot.utility.SesionCreator;
import org.hibernate.Criteria;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RicettaRepositoryImpl implements RicettaRepositoryCustom{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List getRandomRecipes(int numberOfRecipes) {
        List<RicettaResponse> ricette = new ArrayList<>();
        while(ricette.size()<=numberOfRecipes){
            getRecipesfromIngredients();
            double randomNum =(Math.random() /Math.random())*  (Math.floor((Math.random()+10)))*(Math.random() /Math.random())*(Math.random() /Math.random())*(Math.floor((Math.random()+10)))*(Math.random() /Math.random());
            if(randomNum>=1 && randomNum<=28198) {
                Long randomId = Math.round(randomNum);
                Ricetta ricetta = em.find(Ricetta.class, randomId);

                if ( ricetta!=null && ricetta.getIngrediente_princ() != null) {
                    RicettaResponse response = new RicettaResponse();
                    response.setRicetta(ricetta);
                List<Ingrediente> arr = ricetta.getIngredienti().stream().filter(
                        res -> res.getId().equals(ricetta.getIngrediente_princ())).collect(Collectors.toList());
                for (Ingrediente ingPrinc : arr
                ) {
                    response.setIngrediente(ingPrinc);
                }
                    ricette.add(response);

                }
            }
        }
        return ricette;
    }
    public void getRecipesfromIngredients(){
//        NativeQuery quer = SesionCreator.getSessionFactory().openSession().createNativeQuery("select ingrediente_id from ingredienti where nome_ingrediente = ' pesto'");
//        List ingredienti = quer.list();
//        StringBuilder sb = new StringBuilder();
//        for (Object ingredient: ingredienti) {
//            sb.append(ingredient);
//            sb.append(",");
//        }
//            sb.deleteCharAt(sb.length()-1);
//            SesionCreator.getSessionFactory().getCurrentSession().beginTransaction();


    }
}
