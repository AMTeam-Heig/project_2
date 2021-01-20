package ch.heigvd.amt.stackovergoat.application.gamification;

import ch.heig.amt.gamification.api.DefaultApi;
import ch.heig.amt.gamification.api.dto.Application;
import ch.heig.amt.gamification.api.dto.NewApplication;


public class GamificationFacade {

    private DefaultApi defaultApi;

    public GamificationFacade(String name){

        DefaultApiSingleton.NAME = name;

        defaultApi = DefaultApiSingleton.getInstance();

        //Application application;
        NewApplication newApplication;

        //newApplication.setName(name);

        String url = System.getenv("GAMIFICATION_SERVER_URL");

        if(url.isEmpty()){
            url = "http://192.168.99.100:9081";
        }

        defaultApi.getApiClient().setBasePath(url);

        newApplication = new NewApplication();
        newApplication.setName(name);
        try {
            defaultApi.createApplication(newApplication);
            defaultApi.getApiClient().setApiKey(defaultApi.getApplication(name).getApiKey());
        }catch (Exception e){
            e.printStackTrace();
        }


        /*try {
            application = defaultApi.getApplication(name);
            //on regarde si l'application existe déjà, si oui on la stock dans defaultApi
            //si non on en crée une nouvelle
            if(application != null){
                defaultApi.getApiClient().setApiKey(application.getApiKey());
            }else{
                newApplication = new NewApplication();
                newApplication.setName(name);
                defaultApi.createApplication(newApplication);
            }
        }catch (Exception e){
            e.printStackTrace();
        }*/

    }
}
