package tests;

import base.BaseTest;
import org.lms.listeners.ExtentReportListener;
import org.lms.pages.LatexNormalizationPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.ExcelReader;

import java.io.IOException;
import java.util.List;

@Listeners(ExtentReportListener.class)
public class LatexNormalizationTest extends BaseTest {

    @BeforeMethod
    public void beforelatex()
    {
        latexNormalizationPage=homepage.navigatetoLatex();
        latexNormalizationPage.navigatetobaseicon();

    }

    @DataProvider(name = "addarticledata")
    public Object[][] getJourArticAddData() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\LatexNormalization.xlsx",0);
    }

    @DataProvider(name = "addarticlesearchdata")
    public Object[][] getsearchdata() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\LatexNormalization.xlsx",1);
    }

    @Test(priority =1,dataProvider ="addarticledata" )
    public void verifyarticledetails(String journalacro, String articleid, String artname, String doinum, String workflow,String uname,String upass,String luname,String lpass) throws InterruptedException {
        latexNormalizationPage.navigatetobaseicon();
        latexNormalizationPage.verifyArticleGeneralDetails(journalacro,articleid,artname,doinum,workflow,uname,upass,luname,lpass);

    }

    @Test(priority = 2)
    public void verifyfilterupdate() throws InterruptedException {
       List<Boolean> filterupdates = latexNormalizationPage.EnsureFilterUpdated("7000","7000","1948","1948");
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertTrue(filterupdates.get(0),"Updated publisher is not showing");
        softAssert.assertTrue(filterupdates.get(1),"journals tables are not showing eventhough update the filter");
        softAssert.assertFalse(filterupdates.get(2),"After clicking the clear all button ,Still tables are showing");

    }



    @Test(priority = 3)
    public void EnsureFilterNavigation() throws InterruptedException
    {
       Boolean filtertoogle = latexNormalizationPage.EnsuretoogleInFilter("1915","1915");
        Assert.assertTrue(filtertoogle,"Toogle is not working properly in Filter");

    }


    @Test(priority =4)
    public void SearchArticleFunctionality() throws InterruptedException
    {
        Boolean filtertoogle = latexNormalizationPage.EnsuretoogleInFilter("1915","1915");
        Assert.assertTrue(filtertoogle,"Toogle is not working properly in Filter");
    }



    @Test(priority =5,dataProvider = "addarticlesearchdata")
    public void SearchFunctionality(String journalacro, String articleid, String artname, String doinum, String workflow,String uname,String upass,String luname,String lpass)
    {
       Boolean articlefilterIsSuccess=latexNormalizationPage.VerifySearchFunctionality(journalacro,articleid,artname,doinum,workflow,uname,upass,luname,lpass);
        Assert.assertTrue(articlefilterIsSuccess,"Article is not filtering in search bar");
    }

    //TL and User

    @DataProvider(name = "latexuserarticledata")
    public Object[][] getarticledata() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\LatexNormalization.xlsx",2);
    }


    @Test(priority =6,description ="JMS-233 : TL Not assigns the task",dataProvider = "latexuserarticledata")
    public void verifySelfAssign(String journalacro, String articleid, String artname, String doinum, String workflow,String pmuname,String pmupass,String latexuname,String latexupass) throws InterruptedException
    {


        Boolean isenable=latexNormalizationPage.UserFreeToTakeArticle(journalacro,articleid,artname,doinum,workflow,pmuname,pmupass,latexuname,latexupass);
        Assert.assertTrue(isenable,"Cant able to take article to start");

    }

    @DataProvider(name = "otherarticleeplayicon")
    public Object[][] verifyotherarticleplayicon() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\LatexNormalization.xlsx",3);
    }

    @Test(priority =7,description =" JMS-234 : TL assign One task to user - Version 1",dataProvider = "otherarticleeplayicon")
    public void verifyplay(String journalacro, String articleid, String artname, String doinum, String workflow,String pmuname,String pmupass,String latextluname,String latextlpass,String latexuname,String latexupass) throws InterruptedException
    {
        String cssvalue="cursor";
        List<Boolean> article=latexNormalizationPage.UserArticlePlayIcon(journalacro,articleid,artname,doinum,workflow,pmuname,pmupass,latextluname,latextlpass,latexuname,latexupass,cssvalue);
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertTrue(article.get(0),"Other articles are enable When one article in WIP");
        softAssert.assertTrue(article.get(1),"Cant able to start other article one after complete the other article");

    }















}
