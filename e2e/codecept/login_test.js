Feature('Login');

Scenario('test login scenario success', (I) => {
    I.amOnPage('/login');
    I.see('Sign in');
    I.fillField('Username', 'cosmicdarine');
    I.fillField('Password', 'lolilol');
    I.click('Login');
    I.amOnPage('/home');
    I.see('Welcome');
    I.saveScreenshot('login_success_screenshot.png');
});
