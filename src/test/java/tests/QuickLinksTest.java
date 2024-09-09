package tests;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.lms.listeners.ExtentReportListener;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utils.ExcelReader;

import java.io.IOException;
import java.util.List;

@Listeners(ExtentReportListener.class)
public class QuickLinksTest extends BaseTest {

    String cateogry="Quick Links";
    public void cateogry()
    {
        ExtentReportListener.getTest().assignCategory(cateogry);
    }

    @BeforeMethod
    public void BeforeAllTest()
    {
        quickLinksPage= homepage.navigatetoquickPage();

    }

    @AfterMethod
    public void AfterAllTest()
    {
        quickLinksPage.reload();

    }




    @Test(priority = 0, description = "JMS-65 :Verify the quicklinks displays all three icons (Add Publisher, Journal, Article) and user can navigate to respective pages correctly - verify this on PM login-version 3")
    public void NavigatetoBaseIcon() throws InterruptedException {
        cateogry();
        ExtentReportListener.getTest().log(Status.INFO,"Login with valid credential for manager");
        ExtentReportListener.getTest().log(Status.INFO,"click quick link option");


        Boolean Isvisible=quickLinksPage.ensureThreeIcon();
        System.out.println(Isvisible);
        Assert.assertTrue(Isvisible,"Icon not displayed");
        ExtentReportListener.getTest().log(Status.INFO,"add publisher,add journal,add article displayed sucessfully");
    }


    @Test(priority = 1, description = "JMS-66:Verify the quicklinks displays only Add article for Login user, Add Pub,Journal is not available for login user- version 3")
    public void EnsureLoginUserHasOnlyAddArticleIcon()
    {
       // quickLinksPage= homepage.navigatetoquickPage();
        cateogry();
        ExtentReportListener.getTest().log(Status.INFO,"login with valid credential for login\n" +
                "\n");
        ExtentReportListener.getTest().log(Status.INFO,"Check Quicklinks option not showing 'Add Publisher', 'Add Journal' option");
        ExtentReportListener.getTest().log(Status.INFO,"Check Quicklinks option showing 'Add Article' option");
        ExtentReportListener.getTest().log(Status.INFO,"from Manage->Publisher, check on 'Three dots' menu option on each publisher");
        ExtentReportListener.getTest().log(Status.INFO,"from Manage->journals, check on 'Three dots' menu option on each publisher");
        ExtentReportListener.getTest().log(Status.INFO,"from Manage->Article, check on Edit option");

        String uname=prop.getProperty("loginusername");
        String upass=prop.getProperty("loginpassword");
        List<Boolean>visiblity=quickLinksPage.EnsureLoginUserHasArticleIcon(uname,upass);
        System.out.println(visiblity);
        Boolean addarticleiconvisiblity=visiblity.get(2);
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertTrue(visiblity.get(0),"Add article icon is not displayed ");
        softAssert.assertFalse(visiblity.get(1),"pubIcon is Dispplayed In Login User");
        softAssert.assertFalse(visiblity.get(2),"journalIcon Is Displayed In Login User");
        softAssert.assertFalse(visiblity.get(3),"PubEditIcon Is Displayed In Login User");
        softAssert.assertFalse(visiblity.get(4),"JounalEditIcon Is Displayed In Login User");
        softAssert.assertTrue(visiblity.get(5),"ArticleEditIcon Is not Displayed In Login User");
        softAssert.assertAll();
        ExtentReportListener.getTest().log(Status.INFO,"Only add article icon is displayed");

    }

    @DataProvider(name ="usercredentials")
    public Object[][] getcredtentials() throws IOException {
        return ExcelReader.ReadExcelData(".//src//test//resources//files//Quicklinks.xlsx",0);

    }

    @Test(priority = 2,dataProvider ="usercredentials", description = "JMS-67,Verify apart from PM, login users, quicklinks is not available for other users-version 3")
    public void  EnsureManagerNotHaveQuickLinks(String uname,String upass) throws InterruptedException {
        cateogry();
        ExtentReportListener.getTest().log(Status.INFO,"Check From Production Manager login:\n" +
                "\n" +
                "Whether Quicklinks is available");
        ExtentReportListener.getTest().log(Status.INFO,"Check From Production Manager login:\n" +
                "\n" +
                "Add / Edit options for Publisher, Journal, Article is available from MANAGE view");

        ExtentReportListener.getTest().log(Status.INFO,"Repeat steps 1,2 from AM/TL login");
        ExtentReportListener.getTest().log(Status.INFO,"Repeat steps 1,2 from User login");

        //quickLinksPage= homepage.navigatetoquickPage();

        Boolean quicklinksvisible=quickLinksPage.EnsureQuickLinksnotAvailableForManagerLogin(uname,upass);
        System.out.println(quicklinksvisible);
        Assert.assertFalse(quicklinksvisible,"Icon is Displayed"+uname+":"+upass);
        ExtentReportListener.getTest().log(Status.INFO,"Expected like quicklink icon is not showing for manager login");

    }


    @Test(priority = 3, description = "JMS-68 verify Quicklinks is available from that page and working fine from every page-version 3")
    public void VerifyQuickLinkIsWorkingINFlow()
    {
        cateogry();
        ExtentReportListener.getTest().log(Status.INFO,"Login as Proj. Manager");
        ExtentReportListener.getTest().log(Status.INFO,"Check from all the pages(Add article, query, profile), Quicklinks option should be available from that page for user");
        ExtentReportListener.getTest().log(Status.INFO,"Login as LOGIN user");
        ExtentReportListener.getTest().log(Status.INFO,"Repeat step 2");

       // quickLinksPage= homepage.navigatetoquickPage();
        String pmuname=prop.getProperty("Projectmanageruname");
        String pmupass=prop.getProperty("Projectmanagerupass");
        String loginuname=prop.getProperty("loginusername");
        String loginupass=prop.getProperty("loginpassword");
        Boolean issworking=quickLinksPage.QuicklinksIsWorkingInFlowWise(pmuname,pmupass,loginuname,loginupass);
        Assert.assertTrue(issworking,"Quicklinks Working Fine");
        ExtentReportListener.getTest().log(Status.INFO,"Option is available from all pages");




    }











}
