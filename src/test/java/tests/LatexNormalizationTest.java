package tests;

import base.BaseTest;
import org.lms.listeners.ExtentReportListener;
import org.lms.pages.LatexNormalizationPage;
import org.testng.Assert;
import org.testng.annotations.*;
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
        //latexNormalizationPage.navigatetobaseicon();

    }
    @AfterMethod
    public void  AfterAllTest()
    {
        latexNormalizationPage.ReloadDashboard();
    }

    @DataProvider(name = "addarticledata")
    public Object[][] getJourArticAddData() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\LatexNormalization.xlsx",0);
    }

    @DataProvider(name = "addarticlesearchdata")
    public Object[][] getsearchdata() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\LatexNormalization.xlsx",1);
    }

/*
    @Test(priority =1,description="JMS-229 : Verify the Article's General details - Version 1",dataProvider ="addarticledata" ,enabled = false)
    public void verifyarticledetails(String journalacro, String articleid, String artname, String doinum, String workflow,String uname,String upass,String luname,String lpass) throws InterruptedException {
        latexNormalizationPage.navigatetobaseicon();
        latexNormalizationPage.verifyArticleGeneralDetails(journalacro,articleid,artname,doinum,workflow,uname,upass,luname,lpass);

    }

    @Test(priority = 2,description = "JMS-318 : LTX Norm DB: Apply filter on Pub, journal separately and ensure the list is updated by filter - Version 1")
    public void verifyfilterupdate() throws InterruptedException {
       List<Boolean> filterupdates = latexNormalizationPage.EnsureFilterUpdated("7000","7000","1948","1948");
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertTrue(filterupdates.get(0),"Updated publisher is not showing");
        softAssert.assertTrue(filterupdates.get(1),"journals tables are not showing eventhough update the filter");
        softAssert.assertFalse(filterupdates.get(2),"After clicking the clear all button ,Still tables are showing");

    }



   */
/* @Test(priority = 3,enabled = false)
    public void EnsureFilterNavigation() throws InterruptedException
    {
       Boolean filtertoogle = latexNormalizationPage.EnsuretoogleInFilter("1915","1915");
        Assert.assertTrue(filtertoogle,"Toogle is not working properly in Filter");

    }*//*


    @Test(priority =3,description = " JMS-322 : LTX Norm DB: Search with any text and verify the list is updated accordingly - Version 1", dataProvider = "addarticlesearchdata")
    public void SearchFunctionality(String journalacro, String articleid, String artname, String doinum, String workflow,String uname,String upass,String luname,String lpass) throws InterruptedException {
        Boolean articlefilterIsSuccess=latexNormalizationPage.VerifySearchFunctionality(journalacro,articleid,artname,doinum,workflow,uname,upass,luname,lpass);
        Assert.assertTrue(articlefilterIsSuccess,"Article is not filtering in search bar");
    }


    @Test(priority =4,description = "JMS-323 : LTX Norm DB: Toggle between column selections - Version 1")
    public void SearchArticleFunctionality() throws InterruptedException
    {
        Boolean filtertoogle = latexNormalizationPage.EnsuretoogleInFilter("1915","1915");
        Assert.assertTrue(filtertoogle,"Toogle is not working properly in Filter");
    }
*/





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


    @DataProvider(name = "assignicon")
    public Object[][] verifyAssignFunctionality() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\LatexNormalization.xlsx",4);
    }
    @Test(priority = 8,description = " JMS-235 : ASSIGN option for TL and user - Version 1",dataProvider = "assignicon")
    public void ISAssignIConDisplayed(String tluname,String tlupass,String luname,String lupass,String journalacro, String articleid, String artname, String doinum, String workflow) throws InterruptedException {
        Boolean AssignIsVisible=latexNormalizationPage.VerifyTlAssignFunctionality(tluname,tlupass,luname,lupass,journalacro,articleid,doinum,artname,workflow);
        Assert.assertTrue(AssignIsVisible,"Assing icon is not visible for the TL login");

    }

    @DataProvider(name = "otheruser")
    public Object[][] verifyotherdeptuser() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\LatexNormalization.xlsx",5);
    }

    @Test(priority = 9,description = "JMS-236 : Other DEPT Users availability - Version 1",dataProvider = "otheruser")
    public void IsOtherUserAvailable(String latextluname,String latextlpass)
    {
        Boolean isotheruseravailable=latexNormalizationPage.otherDeptUserAvailablity(latextluname,latextlpass);
        Assert.assertTrue(isotheruseravailable,"Other department users are available ");
    }

    @DataProvider(name = "existstart")
    public Object[][] alreadystart() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\LatexNormalization.xlsx",6);
    }


    @Test(priority = 10,dataProvider = "existstart",description = "JMS-237 : Already started Task - Version 1")
    public void VerifyTlLoginAlreadyStart(String pmunmae,String pmupass,String journalacro, String articleid, String artname, String doinum, String workflow,String luname,String lupass,String ltlunmae,String ltupass) throws InterruptedException
    {

        Boolean isAssignshown=latexNormalizationPage.verifyAlreadyStarted(pmunmae,pmupass,journalacro,articleid,artname,doinum,workflow,luname,lupass,ltlunmae,ltupass);
        Assert.assertFalse(isAssignshown,"can abl to assign even in WIP article");

    }

    @DataProvider(name = "ytshold")
    public Object[][] availableytshold() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\LatexNormalization.xlsx",7);
    }

    @DataProvider(name = "dualtl")
    public Object[][] DualTeamleader() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\LatexNormalization.xlsx",9);
    }

    @Test(priority = 11,description = "JMS-238 : availability of HOLD/YTS tasks - Version 1",dataProvider = "ytshold")
    public void VerifyYtsHoldReassignarticle(String pmunmae,String pmupass,String journalacro, String articleid, String artname, String doinum, String workflow,String luname,String lupass,String ltlunmae,String ltupass,String luname1,String luname2) throws InterruptedException
    {
       Boolean isarticlevisibletonewuser= latexNormalizationPage.verifyAvailableOfYtsHold(pmunmae,pmupass,journalacro,articleid,artname,doinum,workflow,luname,lupass,ltlunmae,ltupass,luname1,luname2);
       Assert.assertTrue(isarticlevisibletonewuser,"Article not failed");


    }


    @Test(priority = 12,description = "JMS-284 : Verify Some other User takes assigned Task - Version 1",dataProvider = "ytshold")
    public void  VerifyISArticleAvailableForUnassign(String pmunmae,String pmupass,String journalacro, String articleid, String artname, String doinum, String workflow,String luname,String lupass,String ltlunmae,String ltupass,String luname1,String luname2) throws InterruptedException {
        String css="cursor";
        Boolean notvisible=latexNormalizationPage.VerifyUnAssignedUserCanStart(pmunmae,pmupass,journalacro,articleid,artname,doinum,workflow,luname,lupass,ltlunmae,ltupass,luname1,luname2,css);
        Assert.assertTrue(notvisible,"Can able to start the article by unassigned user");

    }

    @DataProvider(name = "parallelwork")
    public Object[][] verifyassignstartparallel() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\LatexNormalization.xlsx",8);
    }

    @Test(priority = 13,description = "JMS-285 : TL can work on Article and do Assigning works Parallel - Version 1",dataProvider = "parallelwork")
    public void verifytlparralel(String pmunmae,String pmupass,String journalacro, String articleid, String artname, String doinum, String workflow,String luname,String lupass,String ltlunmae,String ltupass) throws InterruptedException {
        Boolean isassigned=latexNormalizationPage.VerifyTlParallelWork(pmunmae,pmupass,journalacro,articleid,artname,doinum,workflow,luname,lupass,ltlunmae,ltupass);
        Assert.assertTrue(isassigned,"Can not able to assign an article to user when tl in WIP");
    }


    @Test(priority = 14,description = "JMS-286 : TL holds the Incomplete article to some other - Version 1",dataProvider = "ytshold")
    public void VerifyTlAssignHoldArticle(String pmunmae,String pmupass,String journalacro, String articleid, String artname, String doinum, String workflow,String luname,String lupass,String ltlunmae,String ltupass,String luname1,String lupass1) throws InterruptedException {
        Boolean isassigned=latexNormalizationPage.VerifytlAssignIncompleteArticle(pmunmae,pmupass,journalacro,articleid,artname,doinum,workflow,luname,lupass,ltlunmae,ltupass,luname1,lupass1);
        Assert.assertTrue(isassigned,"Can not able to assign an article to user when article in incomplete");
    }

    //Dashboard Actions

    @Test(priority = 15,description = "JMS-242 : Starting an article - Version 1",dataProvider = "ytshold")
    public void VerifyEditorPageText(String pmunmae,String pmupass,String journalacro, String articleid, String artname, String doinum, String workflow,String luname,String lupass,String ltlunmae,String ltupass,String luname1,String lupass1) throws InterruptedException
    {

        String editormsg=latexNormalizationPage.verifyLatexInitiaLDone(pmunmae,pmupass,journalacro,articleid,artname,doinum,workflow,luname,lupass,ltlunmae,ltupass,luname1,lupass1);
        System.out.println(editormsg);
        Assert.assertEquals(editormsg,"LaTeX Initial Done!","Can not able to assign an article to user when article in incomplete");


    }



    @Test(priority = 16,description = "JMS-243 : Editor page - homepage navigation - Version 1 ",dataProvider = "ytshold")
    public void VerifyNaviFromHomeToEditor(String pmunmae,String pmupass,String journalacro, String articleid, String artname, String doinum, String workflow,String luname,String lupass,String ltlunmae,String ltupass,String luname1,String lupass1) throws InterruptedException {
        List<Object> editorpage=latexNormalizationPage.VerifyHomePageNavigation(pmunmae,pmupass,journalacro,articleid,artname,doinum,workflow,luname,lupass,ltlunmae,ltupass,luname1,lupass1);
        System.out.println(editorpage);
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertTrue((Boolean) editorpage.get(0),"Navigate to honmepage is failed");
        softAssert.assertEquals(editorpage.get(1),"LaTeX Initial Done!","Navigate to editoorpage is failed");
        softAssert.assertTrue((Boolean) editorpage.get(2),"Navigate to honmepage is failed after from editorpage");
        softAssert.assertAll();
    }

    @Test(priority =17,description ="JMS-247 : User restricted to take other task - Version 1",dataProvider = "ytshold")
    public void VerifyArticleStartRestriction(String pmunmae,String pmupass,String journalacro, String articleid, String artname, String doinum, String workflow,String luname,String lupass,String ltlunmae,String ltupass,String luname1,String lupass1) throws InterruptedException {
        String css="cursor";
        List <Boolean> articlerestriction=latexNormalizationPage.verifyOtherArticleCanTakeInPause(pmunmae,pmupass,journalacro,articleid,artname,doinum,workflow,luname,lupass,ltlunmae,ltupass,luname1,lupass1,css);
        System.out.println(articlerestriction);
        Assert.assertTrue(articlerestriction.get(0),"Pause is not visible");
        Assert.assertTrue(articlerestriction.get(1),"can able to start when article is pause");
        Assert.assertTrue(articlerestriction.get(2),"can able to start when article is pause");

    }



    @Test(priority =18,description ="JMS-254 : user hold and take same Task - Version 1",dataProvider = "ytshold")
    public void VerifyHoldArticleStartAfterTlAssign(String pmunmae,String pmupass,String journalacro, String articleid, String artname, String doinum, String workflow,String luname,String lupass,String ltlunmae,String ltupass,String luname1,String lupass1) throws InterruptedException {
        String css="cursor";
      Boolean articleacess=latexNormalizationPage.verifytUserTakeHoldTaskAfterTlAssign(pmunmae,pmupass,journalacro,articleid,artname,doinum,workflow,luname,lupass,ltlunmae,ltupass,luname1,lupass1);
       Assert.assertTrue(articleacess,"Cant able to start the article");
    }


    @Test(priority =19,description ="JMS-255 : User holds and other user been assigned the task - Version 1",dataProvider = "ytshold")
    public void VerifyOtherUserHoldArticleStartAfterTlAssign(String pmunmae,String pmupass,String journalacro, String articleid, String artname, String doinum, String workflow,String luname,String lupass,String ltlunmae,String ltupass,String luname1,String lupass1) throws InterruptedException {

        String css="cursor";
        List<Boolean> articleacess=latexNormalizationPage.verifytOtherUserTakeHoldTaskAfterTlAssign(pmunmae,pmupass,journalacro,articleid,artname,doinum,workflow,luname,lupass,ltlunmae,ltupass,luname1,lupass1,css);
        System.out.println(articleacess);

        SoftAssert softAssert=new SoftAssert();
        softAssert.assertTrue(articleacess.get(0),"Cant able to start the article");
        softAssert.assertFalse(articleacess.get(1),"Cant able to start the article");

    }



    @Test(priority =20,description ="JMS-256 : user hold and take some other TASK - Version 1",dataProvider = "ytshold")
    public void UserTakeOtherWhileHOld(String pmunmae,String pmupass,String journalacro, String articleid, String artname, String doinum, String workflow,String luname,String lupass,String ltlunmae,String ltupass,String luname1,String lupass1) throws InterruptedException {

        String css="cursor";
        List<Boolean> articleacess=latexNormalizationPage.verifyUserTakeOtherAticleWhenHold(pmunmae,pmupass,journalacro,articleid,artname,doinum,workflow,luname,lupass,ltlunmae,ltupass,luname1,lupass1,css);
        System.out.println(articleacess);

        SoftAssert softAssert=new SoftAssert();
        softAssert.assertFalse(articleacess.get(0),"Cant able to start the article");
        softAssert.assertFalse(articleacess.get(1),"Cant able to start the article");

    }


    @Test(priority =21,description ="JMS-259 : Confirmation Popup for Saving the Day's work - Version 1",dataProvider = "ytshold")
    public void VerifyPopUpMsg(String pmunmae,String pmupass,String journalacro, String articleid, String artname, String doinum, String workflow,String luname,String lupass,String ltlunmae,String ltupass,String luname1,String lupass1) throws InterruptedException {


        Boolean articleacess=latexNormalizationPage.verifyAlertMsgForPause(pmunmae,pmupass,journalacro,articleid,artname,doinum,workflow,luname,lupass,ltlunmae,ltupass,luname1,lupass1);
        System.out.println(articleacess);

         Assert.assertTrue(articleacess,"Pause popup is not displayed");
    }

    @Test(priority =23,description =" JMS-291 : Skipping of any mandatory process - should be prevented - Version 1",dataProvider = "ytshold")
    public void VerifeComplete(String pmunmae,String pmupass,String journalacro, String articleid, String artname, String doinum, String workflow,String luname,String lupass,String ltlunmae,String ltupass,String luname1,String lupass1) throws InterruptedException {


        Boolean iscompleted=latexNormalizationPage.CompleteArticleDirectVerify(pmunmae,pmupass,journalacro,articleid,artname,doinum,workflow,luname,lupass,ltlunmae,ltupass,luname1,lupass1);
        Assert.assertFalse(iscompleted,"Article allowing to complete the article directly");


    }

    @Test(priority =23,description =" JMS-291 : Skipping of any mandatory process - should be prevented - Version 1",dataProvider = "ytshold")
    public void VerifeCompleteAfterRestart(String pmunmae,String pmupass,String journalacro, String articleid, String artname, String doinum, String workflow,String luname,String lupass,String ltlunmae,String ltupass,String luname1,String lupass1) throws InterruptedException {


        Boolean iscompleted=latexNormalizationPage.RestartAndCompleteArticleDirectVerify(pmunmae,pmupass,journalacro,articleid,artname,doinum,workflow,luname,lupass,ltlunmae,ltupass,luname1,lupass1);
        Assert.assertFalse(iscompleted,"Article allowing to complete the article directly");


    }


    @Test(priority =23,description =" JMS-291 : Skipping of any mandatory process - should be prevented - Version 1",dataProvider = "ytshold")
    public void VerifeSkipcompile(String pmunmae,String pmupass,String journalacro, String articleid, String artname, String doinum, String workflow,String luname,String lupass,String ltlunmae,String ltupass,String luname1,String lupass1) throws InterruptedException {


        Boolean iscompleted=latexNormalizationPage.VerifyCompleteWithoutCompile(pmunmae,pmupass,journalacro,articleid,artname,doinum,workflow,luname,lupass,ltlunmae,ltupass,luname1,lupass1);
        Assert.assertFalse(iscompleted,"Article allowing to complete the article directly");

    }



    @Test(priority =23,description =" JMS-292 : Generation of new Tex file after Normalise - Version 1",dataProvider = "ytshold")
    public void VerifyLatexNormalizationDone(String pmunmae,String pmupass,String journalacro, String articleid, String artname, String doinum, String workflow,String luname,String lupass,String ltlunmae,String ltupass,String luname1,String lupass1) throws InterruptedException {


        Boolean isnormalizedone=latexNormalizationPage.VerifyNormalizeMsg(pmunmae,pmupass,journalacro,articleid,artname,doinum,workflow,luname,lupass,ltlunmae,ltupass,luname1,lupass1);
        Assert.assertTrue(isnormalizedone,"Normalize done message is not displayed");

    }


    @Test(priority =24,description =" JMS-232 : Entry of completed article - Version 1 ",dataProvider = "ytshold")
    public void VerifyLatexCompletedArticle(String pmunmae,String pmupass,String journalacro, String articleid, String artname, String doinum, String workflow,String luname,String lupass,String ltlunmae,String ltupass,String luname1,String lupass1) throws InterruptedException {


        Boolean iscompleted=latexNormalizationPage.VerifyCompletedArticleInLatex(pmunmae,pmupass,journalacro,articleid,artname,doinum,workflow,luname,lupass,ltlunmae,ltupass,luname1,lupass1);
        Assert.assertFalse(iscompleted,"Article allowing to complete the article directly");

    }

    @Test(priority =25,description =" JMS-230 : Non started Article Entry - Version 1 ",dataProvider = "ytshold")
    public void VerifyNonStart(String pmunmae,String pmupass,String journalacro, String articleid, String artname, String doinum, String workflow,String luname,String lupass,String ltlunmae,String ltupass,String luname1,String lupass1) throws InterruptedException {

        String cssvalue="cursor";
        List<Boolean> starticon=latexNormalizationPage.NonStartedArticle(pmunmae,pmupass,journalacro,articleid,artname,doinum,workflow,luname,lupass,ltlunmae,ltupass,luname1,lupass1,cssvalue);
        System.out.println(starticon);
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertFalse(starticon.get(0),"Start icon is   disabled");
        softAssert.assertFalse(starticon.get(1),"Start icon is   disabled");
        softAssert.assertAll();

    }


    @Test(priority =26,description =" JMS-231 : Entry of other user's Task - Version 1 ",dataProvider = "ytshold")
    public void VerifyOtherUserTask(String pmunmae,String pmupass,String journalacro, String articleid, String artname, String doinum, String workflow,String luname,String lupass,String ltlunmae,String ltupass,String luname1,String lupass1) throws InterruptedException {

        String cssvalue="cursor";
        Boolean starticon=latexNormalizationPage.VerifyOtherUserEntry(pmunmae,pmupass,journalacro,articleid,artname,doinum,workflow,luname,lupass,ltlunmae,ltupass,luname1,lupass1,cssvalue);
        Assert.assertFalse(starticon,"Article is editable in other user login,After one user started already");


    }

    @Test(priority = 27,description = "JMS-241 : Verify details on Multiple TLs dashboard - Version 1",dataProvider = "dualtl")
    public void verifyMultipleDashboard(String pmunmae,String pmupass,String journalacro, String articleid, String artname, String doinum, String workflow,String luname,String lupass,String ltlunmae,String ltupass,String luname1,String lupass1,String ltluname1,String ltlupass1) throws InterruptedException {
       List<Boolean> verifyarticle=latexNormalizationPage.VerifMultipleTLEntry(pmunmae,pmupass,journalacro,articleid,artname,doinum,workflow,luname,lupass,ltlunmae,ltupass,luname1,lupass1,ltluname1,ltlupass1);
       SoftAssert softAssert=new SoftAssert();
       softAssert.assertTrue(verifyarticle.get(0),"Article is not visible");
       softAssert.assertTrue(verifyarticle.get(1),"Article is not visible");


    }


    @Test(priority = 28,description = "JMS-244 : Raise query from Dashboard - Version 1",dataProvider = "ytshold")
    public void VerifyQueryAddedFromDashboard(String pmunmae,String pmupass,String journalacro, String articleid, String artname, String doinum, String workflow,String luname,String lupass,String ltlunmae,String ltupass,String luname1,String luname2) throws InterruptedException {
      Boolean verifyarticle=latexNormalizationPage.RaiseQueryFromDashboard(pmunmae,pmupass,journalacro,articleid,artname,doinum,workflow,luname,lupass,ltlunmae,ltupass,luname1,luname2);
        Assert.assertTrue(verifyarticle,"Added Query is not showing");


    }


    @Test(priority = 29,description = "JMS-245 : Raise query from Editor view - Version 1",dataProvider = "ytshold")
    public void VerifyQueryAddedFromEditor(String pmunmae,String pmupass,String journalacro, String articleid, String artname, String doinum, String workflow,String luname,String lupass,String ltlunmae,String ltupass,String luname1,String luname2) throws InterruptedException
    {
        Boolean verifyarticle=latexNormalizationPage.RaiseQueryFromEditorView(pmunmae,pmupass,journalacro,articleid,artname,doinum,workflow,luname,lupass,ltlunmae,ltupass,luname1,luname2);
        Assert.assertTrue(verifyarticle,"Added Query is not showing");

    }



    @Test(priority = 30,description = "JMS-223 : Add article and verify the Cards - Version 1",dataProvider = "ytshold")
    public void VerifyAddArticleCount(String pmunmae,String pmupass,String journalacro, String articleid, String artname, String doinum, String workflow,String luname,String lupass,String ltlunmae,String ltupass,String luname1,String luname2) throws InterruptedException
    {
        Boolean verifyarticle=latexNormalizationPage.VerifyArticleCount(pmunmae,pmupass,journalacro,articleid,artname,doinum,workflow,luname,lupass,ltlunmae,ltupass,luname1,luname2);
        Assert.assertTrue(verifyarticle,"Count is not increased after added a article");

    }

    @Test(priority = 31,description = " JMS-224 : Complete article and Check count - Version 1",dataProvider = "ytshold")
    public void VerifyArticleCountAfterCompleted(String pmunmae,String pmupass,String journalacro, String articleid, String artname, String doinum, String workflow,String luname,String lupass,String ltlunmae,String ltupass,String luname1,String luname2) throws InterruptedException
    {
        Boolean verifyarticlecount=latexNormalizationPage.CompletedArticleCount(pmunmae,pmupass,journalacro,articleid,artname,doinum,workflow,luname,lupass,ltlunmae,ltupass,luname1,luname2);
        Assert.assertTrue(verifyarticlecount,"Count is not reduced after Completed ");

    }

    @Test(priority = 32,description = " JMS-224 : Complete article and Check count - Version 1",dataProvider = "ytshold")
    public void VerifyUnassignPub(String pmunmae,String pmupass,String journalacro, String articleid, String artname, String doinum, String workflow,String luname,String lupass,String ltlunmae,String ltupass,String luname1,String luname2) throws InterruptedException {
        latexNormalizationPage.IsArticleUpdatedAfterAssigned(pmunmae,pmupass,journalacro,articleid,artname,doinum,workflow,luname,lupass,ltlunmae,ltupass,luname1,luname2);



    }
















































}
