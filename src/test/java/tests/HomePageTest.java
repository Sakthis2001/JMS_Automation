package tests;

import base.BaseTest;
import org.lms.listeners.ExtentReportListener;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
@Listeners(ExtentReportListener.class)
public class HomePageTest extends BaseTest {

    private String authorname="Sakthi";

   @Test(priority = 0)
    public void verifyhometitle()
    {

        ExtentReportListener.getTest().assignCategory("HomePageTests");
        ExtentReportListener.getTest().assignAuthor(authorname);
        ExtentReportListener.getTest().info("Login successfully");
        ExtentReportListener.getTest().info("verifing the title");
        ExtentReportListener.getTest().info("Title expected as LMS");
        String titlename= homepage.verifytitle();

        Assert.assertEquals(titlename,"JMS","title wrong");
        ExtentReportListener.getTest().info(titlename+" "+" right");



    }


    @Test(priority = 1)
    public void verifyurl()
    {

        ExtentReportListener.getTest().assignCategory("HomePageTests");
        ExtentReportListener.getTest().assignAuthor(authorname);
        ExtentReportListener.getTest().info("Login successfully");
        ExtentReportListener.getTest().info("verifing the url");
        ExtentReportListener.getTest().info("url expected as https://pdmrindia.co.in/tester/LMS/auth/signin");
        String urlname= homepage.verifyurl();

        Assert.assertEquals(urlname,"http://192.168.1.39:3002/jms/stock","Wrong url");
        ExtentReportListener.getTest().info(urlname+ " "+"  is right");

    }

    @Test(priority = 2)
    public void verifyIconIsDisplayed()
    {
        ExtentReportListener.getTest().assignCategory("HomePageTests");
        ExtentReportListener.getTest().assignAuthor(authorname);

        ExtentReportListener.getTest().info("verify icon is display or not");

        boolean visible=homepage.isbaseicondisplayed();
        System.out.println(visible);

        Assert.assertTrue(visible,"icon not displayed");
        ExtentReportListener.getTest().info("verify icon is displayed");


    }





}
