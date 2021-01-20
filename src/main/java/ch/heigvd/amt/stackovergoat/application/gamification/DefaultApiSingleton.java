package ch.heigvd.amt.stackovergoat.application.gamification;

import ch.heig.amt.gamification.api.DefaultApi;

/**
 * Classe Singleton pour ne créer qu'une instance DefaultApi qui
 * nous permet de communiquer avec le serveur de Gamification.
 * Cela nous évite de créer plusieurs instances qui créeront des connections superflues.
 */
public class DefaultApiSingleton {

    public static String NAME;

    private DefaultApiSingleton(){}

    private static DefaultApi INSTANCE = new DefaultApi();

    public static DefaultApi getInstance()
    {   return INSTANCE;
    }

}
