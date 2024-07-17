package tests;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.lms.listeners.ExtentReportListener;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

@Listeners(ExtentReportListener.class)
public class QuickLinksTest extends BaseTest {

    String cateogry="Quick Links";
    public void cateogry()
    {
        ExtentReportListener.getTest().assignCategory(cateogry);
    }



    @Test(priority = 0, description = "Verify the quicklinks displays all three icons (Add Publisher, Journal, Article) and user can navigate to respective pages correctly - verify this on PM login")
    public void NavigatetoBaseIcon() throws InterruptedException {
        cateogry();
        ExtentReportListener.getTest().log(Status.INFO,"Login with valid credential for manager");
        ExtentReportListener.getTest().log(Status.INFO,"click quick link option");


        quickLinksPage= homepage.navigatetoquickPage();
        Boolean Isvisible=quickLinksPage.ensureThreeIcon();
        System.out.println(Isvisible);
        Assert.assertTrue(Isvisible,"Icon not displayed");
        ExtentReportListener.getTest().log(Status.INFO,"add publisher,add journal,add article displayed sucessfully");
    }

    @Test(priority = 1, description = "Verify the quicklinks displays only Add article for Login user, Add Pub,Journal is not available for login user")
    public void EnsureLoginUserHasOnlyAddArticleIcon()
    {

         cateogry();
        ExtentReportListener.getTest().log(Status.INFO,"login with valid credential for login\n" +
                "\n");
        ExtentReportListener.getTest().log(Status.INFO,"verify login user quick link option is visible and clickable\n" +
                "\n");
        ExtentReportListener.getTest().log(Status.INFO,"verify quick link displayed only add article icon\n" +
                "\n");

        quickLinksPage= homepage.navigatetoquickPage();
        String uname=prop.getProperty("loginusername");
        String upass=prop.getProperty("loginpassword");
       List<Boolean>visiblity=quickLinksPage.EnsureLoginUserHasArticleIcon(uname,upass);
        System.out.println(visiblity);
        Boolean addarticleiconvisiblity=visiblity.get(2);
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertTrue(addarticleiconvisiblity,"Add article icon is not displayed ");
        softAssert.assertFalse(visiblity.get(0),"Icon is Dispplayed In Login User");
        softAssert.assertFalse(visiblity.get(1),"Icon Is Displayed In Login User");
        softAssert.assertAll();
        ExtentReportListener.getTest().log(Status.INFO,"Only add article icon is displayed");

    }

    @Test(priority = 2, description = "Verify apart from PM, login users, quicklinks is not available for other users")
    public void  EnsureManagerNotHaveQuickLinks()
    {
        cateogry();
        ExtentReportListener.getTest().log(Status.INFO,"Login with valid credential for manager");
        ExtentReportListener.getTest().log(Status.INFO,"Verify Quik links icon is showing for manager");


        quickLinksPage= homepage.navigatetoquickPage();
        String uname=prop.getProperty("managerusername");
        String upass=prop.getProperty("managerpassword");
        Boolean quicklinksvisible=quickLinksPage.EnsureQuickLinksnotAvailableForManagerLogin(uname,upass);
        System.out.println(quicklinksvisible);
        Assert.assertFalse(quicklinksvisible,"Icon is Displayed");
        ExtentReportListener.getTest().log(Status.INFO,"Expected like quicklink icon is not showing for manager login");

    }


    @Test(priority = 3, description = "verify Quicklinks is available from that page and working fine from every page")
    public void VerifyQuickLinkIsWorkingINFlow()
    {
        cateogry();
        ExtentReportListener.getTest().log(Status.INFO,"Login valid credential for Projectmanager");
        ExtentReportListener.getTest().log(Status.INFO,"add publisher");
        ExtentReportListener.getTest().log(Status.INFO,"Click the quicklink icon");
        ExtentReportListener.getTest().log(Status.INFO,"Click the journal icon");


        ExtentReportListener.getTest().log(Status.INFO,"add journal");
        ExtentReportListener.getTest().log(Status.INFO,"Click the quicklink icon");
        ExtentReportListener.getTest().log(Status.INFO,"Click the article icon");
        ExtentReportListener.getTest().log(Status.INFO,"add article");






        quickLinksPage= homepage.navigatetoquickPage();
        String uname=prop.getProperty("username");
        String upass=prop.getProperty("password");
        Boolean issworking=quickLinksPage.QuicklinksIsWorkingInFlowWise(uname,upass);
        Assert.assertTrue(issworking,"Quicklinks Working Fine");
        ExtentReportListener.getTest().log(Status.INFO,"quicklink is Working fine ");




    }












}
