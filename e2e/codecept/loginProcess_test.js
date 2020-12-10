Feature('login');

Scenario('testing login process', (I) => {
    //check the url
    I .amOnPage('/home');
    I.click('Login');
    I .amOnPage('/login');
    //fill the user name and password field
    I.fillField("Identifiant","admin");
    I.fillField("Mot de passe","admin");
    I.click('Se connecter');
    
    I.see("En phase d'implémentation admin admin");
    I.saveScreenshot("loginPage.jpg")

});
