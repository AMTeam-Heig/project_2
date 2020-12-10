Feature("Login");

Scenario("test login scenario success", (I) => {
    I.amOnPage("/login");
    I.see("Sign in");
    I.fillField("Username", "cosmicdarine");
    I.fillField("Password", "lolilol");
    I.click("Login");
    I.amOnPage("/home");
    I.see("logout");
    I.click("logout");
    I.saveScreenshot("logout_success_screenshot.png");
});
