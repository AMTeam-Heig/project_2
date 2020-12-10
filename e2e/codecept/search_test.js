Feature('search');

Scenario('test search bar ', (I) => {
    //the search bar always on the top of every page 
    //no need to check the url 
    I.click('search');
    I.fillField("search","AMT");
    I.see("results");
});
