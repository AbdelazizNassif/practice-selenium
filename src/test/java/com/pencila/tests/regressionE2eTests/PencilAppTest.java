package com.pencila.tests.regressionE2eTests;

import com.pencil.pages.*;
import io.qameta.allure.Description;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import utils.WaitUtility;

import java.util.Arrays;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class PencilAppTest extends BaseTest {
    long timeAfterLogin;
    HomePage home;
    Login login;
    SpacesListPage spaces;

    @BeforeAll
    public void precondition_loginWithValidCredentials() {
        home = new HomePage(driver);
        home.navigate();
        login = home.clickContinueWithEmail();
        spaces = login.loginWithEmail();
        timeAfterLogin = System.currentTimeMillis();
    }

    @Test
    @DisplayName("1-Validate time from login to spaces list is less than one second")
    @Description("1-Validate time from login to spaces list is less than one second")
    public void a_testTimeToLoadSpacesLessThanOneSecond() {

        long timeTillSpacesListLoaded = spaces.getTimeFromFinishingLoginToSpacesListing(timeAfterLogin);
        MatcherAssert.assertThat("Time to load spaces afer login should be less than 1000ms",
                (timeTillSpacesListLoaded - timeAfterLogin),
                Matchers.lessThanOrEqualTo(1000L));
    }

    @Test
    @DisplayName("2-Validate content of spaces list page")
    @Description("2-Validate content of spaces list page")
    public void b_validateContentOfSpacesListPage() {
        Assertions.assertEquals(1, spaces.getNumberOfSpaces(),
                "number of spaces should be 1");
        Assertions.assertEquals("My First Space", spaces.getFirstSpaceTitle(),
                "first space title is not as expected");
        Assertions.assertEquals(true, spaces.isEnterSpaceButtonEnabled(),
                "enter space button should be enabled");
        var spacesSideMenu = new SpacesSideMenu(driver);
        Assertions.assertEquals(Arrays.asList("Home", "Schedule"), spacesSideMenu.getSideMenuEntries(),
                "side menu items are not as expected");
        var spacesActionsBar = new SpacesActionsBar(driver);
        Assertions.assertEquals(true, spacesActionsBar.isProfilePictureDisplayed(),
                "profile pic should be displayed");
    }

    @Test
    @DisplayName("3-test drawing tools in the space")
    @Description("3-test drawing tools in the space")
    public void c_testDrawingInSpace() {
        var space = spaces.clickEnterSpace();
        new WaitUtility().waitForInterval(5000);
        space.clickShapes();
        // Draw a vertical line in the space of height 50px.
        space.drawLine();
        // todo Select it and move it right by 10px.
        // space.clickSelect();
        // todo space.moveLine();
        // Insert a text box, by selecting the text tool from the bottom toolbar.
        // Enter “This is a test” in the text box and click outside to set it.
        space.selectTextTool();
        space.useTextTool("This is test");
    }

    @Test
    @DisplayName("4-test leaving space")
    @Description("4-test leaving space")
    public void d_testLeavingSpace() {
        // Click on the top left button and then click to leave the space.
        var spacesListPage = new SpaceBar(driver).leaveSpace();
        Assertions.assertEquals(true, spacesListPage.isEnterSpaceButtonEnabled(),
                "check user is redirected to spaces list page");
    }

    @Test
    @DisplayName("5-test signing out of pencila app")
    @Description("5-test signing out of pencila app")
    public void e_testSigningOutOfApp() {
        // Click on the user avatar at the top right and select “Sign out”,
        var spacesActionsBar = new SpacesActionsBar(driver);
        spacesActionsBar.signOut();
        // verify that you are redirected to the login page.
        Assertions.assertEquals(true, home.isContinueWithEmailDisplayed(),
                "continue with email button should be displayed");
        Assertions.assertTrue(home.getCurrentUrl().contains("login"),
                "current url should contain login");
    }

    @Test
    @DisplayName("6-test navigating to my pencila page")
    @Description("6-test navigating to my pencila page")
    public void f_testNavigatingToMyPencilaPage() {
        // Change the URL to my.pencilapp.com and verify that you get redirected to the login page
        var myPencilaPage = new MyPencilaPage(driver).navigate();
        Assertions.assertEquals(true, myPencilaPage.isContinueWithEmailDisplayed(),
                "continue with email button should be displayed");
        Assertions.assertTrue(myPencilaPage.getCurrentUrl().contains("login"),
                "current url should contain login");
    }

}
