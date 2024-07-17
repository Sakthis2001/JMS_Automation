
package tests;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.lms.listeners.ExtentReportListener;
import org.lms.pages.AddArticlePage;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.ExcelReader;
import utils.ExcelUtils;

import java.io.IOException;
import java.util.List;

import static utils.ExcelReader.ReadExcelData;


@Listeners(ExtentReportListener.class)
public class AddArticleTest extends BaseTest {

    String cateogry="addarticle";


    @DataProvider(name = "getdata")
    public Object[][] getpublisherdata() throws IOException
    {
        return ReadExcelData("D:\\uploadtest\\addpublisher.xlsx",4);

    }




    @BeforeMethod
    public void beforeallTest()
    {
        addarticlepage=homepage.navigatetoaddarticlepage();
        addarticlepage.addarticlepage();


    }


    @Test(priority =0,description = "Ensure navigation of Form navigates to form filling page of article")
    public void navigatetoaddarticle()
    {
        addarticlepage.addarticlepage();
    }

   /* @Test(priority =1,dataProvider ="duplicatedata", description = "Upon providing valid inputs, Article can be added - need to be listed")
    public void addarticle(String journalacro,String articleid,String artname,String doinum,String workflow) throws InterruptedException {

        addarticlepage.AddArticleByMandatoryFields(journalacro,articleid,artname,doinum,workflow);
    }*/

    @Test(priority =1,description = "Ensure three options to add article - Form, Upload, SFTP all are available ")
    public void ensureThreeOptIsVisible() throws InterruptedException {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Click on the Add Article");
        ExtentReportListener.getTest().log(Status.INFO,"Verifing Whether  the upload icon ,form icon,Clientftp icon is visible ");

       Boolean val= addarticlepage.ensureThreeOption();
        System.out.println(val);
        Assert.assertTrue(val,"Upload icon,form icon,clientftp icon is not showing");
        ExtentReportListener.getTest().log(Status.INFO,"Upload icon ,form icon,Clientftp icon is visible");

    }

    @DataProvider(name = "duplicatedata")
    public Object[][] addduplicateArticle() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\AddArticle.xlsx",2);
    }
   @Test(priority =2,dataProvider = "duplicatedata")
    public void AddArticle(String journalacro,String articleid,String artname,String doinum,String workflow)
   {
       System.out.println(journalacro);
       System.out.println(articleid);
       System.out.println(artname);
       System.out.println(doinum);

        ExtentReportListener.getTest().assignCategory(cateogry);

        String errormessage=addarticlepage.ensureduplicationarticle(journalacro,articleid,artname,doinum,workflow);
       System.out.println(errormessage);
        Assert.assertEquals(errormessage,"Article ID("+articleid+") already exists!","Article duplicate alert is not showing");

   }

    @DataProvider(name = "articleaddacess")
    public Object[][] adddArticledataFromAllUser() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\AddArticle.xlsx",3);
    }

    @Test(priority =3,dataProvider = "articleaddacess")
    public void VerifyAddArticleAcess(String journalacro,String articleid,String artname,String doinum,String workflow,String username,String password,String jacrm,String pubname)
   {
       Boolean addarticleSuccess=addarticlepage.addarticleacess(journalacro,articleid,artname,doinum,workflow,username,password,jacrm,pubname);
       Assert.assertTrue(addarticleSuccess,"Article add functionality failed");
   }

    @DataProvider(name = "addarticleUnauthorizedacess")
    public Object[][] adddArticledataFromotherThanPMUser() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\AddArticle.xlsx",3);
    }
    @Test(priority =4,dataProvider = "addarticleUnauthorizedacess",description = "")
   public void verifyOtherUserShoulNotCreateArticle()
   {

   }








    @AfterMethod
    public void Afteralltest()
    {
        addarticlepage.reloadpage();
    }









}

