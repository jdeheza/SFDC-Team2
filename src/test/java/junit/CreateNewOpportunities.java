package junit;

import framework.DriverManager;
import framework.Environment;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.*;

/**
 * Created by Fernando Nakamura on 6/26/2015.
 */
public class CreateNewOpportunities {

    private LoginPage loginPage;
    private MainApp mainApp;

    private AppBody appOpportunities;

    @Before
    public void setUp() {
        loginPage = new LoginPage();
        String email = Environment.getInstance().getPrimaryUser();
        String password = Environment.getInstance().getPrimaryPassword();
        String userName = Environment.getInstance().getDisplayName();

        mainApp = loginPage.loginAs(email,password,userName);
    }

    @Test
    public void testUntitled() {

        String [] availableFields = {"Private","Closed"};
        String [][] filterAdditionalFields = {{"Opportunity Name","equals","Test"},{"Account Name","equals","Test"}};

        AppHeader appHeader = mainApp.goToAppHeader();
        appOpportunities = appHeader.clickOpportunities();

        appOpportunities.clickNewView();
        ViewPage viewPage = new ViewPageBuilder("NewViewFNC","NewViewFNCUnique")
                .setFilterByOwnerMyViewRadioBtn(true)
                .setFilterByOwnerAllViewsRadioBtn(true)
                .setfilterByAdditionalField(filterAdditionalFields)
                .setAvailableFields(availableFields)
                .setVisibleAllUsersRadioBtn(true)
                .build();
        viewPage.createView();

        Assert.assertTrue("Test Passed", appOpportunities.getSelectedValue("NewViewFNC"));

    }

    @After
    public void tearDown() {
        appOpportunities.clickDelete();

    }
}
