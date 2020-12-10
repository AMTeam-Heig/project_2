Feature("register");

Scenario("test register process", (I) => {
    I .amOnPage("/login");
    I.fillField("Prénom", "Olivier");
    I.fillField("Nom", "Liechti");
    I.fillField("E-mail", "o.liechti@example.com");
    I.fillField("Identifiant", "wasadigi");
    I.fillField("Mot de passe", "lolilol");
    I.click("Validate");
    I.saveScreenshot("register_test.png");
});
