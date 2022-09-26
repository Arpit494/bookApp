package com.favourite.services;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.favourite.model.Favourite;
import com.favourite.repository.FavouriteRepository;



@Service
public class FavouriteService {

    @Autowired
    private FavouriteRepository fr;

    // add fav book url
    public Favourite addFavourite(Favourite fav) {
        String un=fav.getUserEmail();
        if(fr.existsById(un)) {
            Favourite f=fr.findById(un).get();
            List<String> sk=f.getWorkUrl();
            //
            List<String> li=fav.getWorkUrl();
            for(String s:li) {
                sk.add(s);
            }

            //remove duplicity
            Set<String> set= new HashSet<>(sk);
            //set toList
            List<String> list=set.stream().collect(Collectors.toList());
            fav.setWorkUrl(list);
            return fr.save(fav);
        }
        else {
            return fr.save(fav);
        }

    }

    // get all fav
    public List<String> getAllFav(String userEmail){
        if(fr.existsById(userEmail)) {
            Favourite f=fr.findById(userEmail).get();
            List<String> sk=f.getWorkUrl();
            return sk;
        }else {
            return null;
        }
    }

    // delete fav
    public List<String> deleteFav(String userName, String url){
        //String un=fav.getUserName();
        //String str=fav.getBookUrl().get(0);
        System.out.println(userName + " " + url);
        if(fr.existsById(userName)) {
            Favourite f=fr.findById(userName).get();
            List<String> list=f.getWorkUrl();
            System.out.println("Yes");
            if(list.contains(url)) {
                System.out.println("No");
                list.remove(url);
                f.setWorkUrl(list);
                fr.save(f);
            }
            return f.getWorkUrl();

        }else {
            return null;
        }

    }
}
