Feature('navigation');


Scenario('home -> profile', (I) => {
    I.amOnPage('/home');
    I.click('Profile'); 
    I.amOnPage('/profile');
    //saving the result
    I.saveScreenshot("homeToprofile.jpg");
});
Scenario('home ->stats', (I) => {
    I.amOnPage('/home');
    I.click('stats'); 
    I.amOnPage('/statistics');
    //saving the result
    I.saveScreenshot("homeTostats.jpg");
});

Scenario('home ->question', (I) => {
    I.amOnPage('/home');
    I.fillField('text','where is frodo ?');
    I.click('submit');
    I.amOnPage('/home');
    I.click('where is frodo ?');
    I.amOnPage('/question');
    //saving the result
    I.saveScreenshot("homeToquestion.jpg");
 
});
Scenario('home ->logout', (I) => {
    I.amOnPage('/home');
    I.click('logout');
    I.amOnPage('/home');
    I.dontSee('ask something');
    I.saveScreenshot("homeTologout.jpg");
});

