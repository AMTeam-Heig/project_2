Feature("Profile");

Scenario("test login scenario success", (I) => {
    I.amOnPage("/login");
    I.see("Sign in");
    I.fillField("Username", "cosmicdarine");
    I.fillField("Password", "lolilol");
    I.click("Login");
    I.amOnPage("/home");
    I.click("Profile");
    I.see("Firstname");
    I.saveScreenshot("access_profile_success_screenshot.png");
});