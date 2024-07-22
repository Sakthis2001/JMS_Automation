
package tests;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.lms.listeners.ExtentReportListener;
import org.lms.pages.AddArticlePage;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utils.ExcelReader;
import utils.ExcelUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static utils.ExcelReader.ReadExcelData;


@Listeners(ExtentReportListener.class)
public class AddArticleTest extends BaseTest {

    String cateogry = "addarticle";


    @BeforeMethod
    public void beforeallTest() {
        addarticlepage = homepage.navigatetoaddarticlepage();
        //addarticlepage.addarticlepage();


    }


    @Test(priority = 1, description = "Ensure navigation of Form navigates to form filling page of article")
    public void navigatetoaddarticle() {
        addarticlepage.addarticlepage();
    }

   /* @Test(priority =1,dataProvider ="duplicatedata", description = "Upon providing valid inputs, Article can be added - need to be listed")
    public void addarticle(String journalacro,String articleid,String artname,String doinum,String workflow) throws InterruptedException {

        addarticlepage.AddArticleByMandatoryFields(journalacro,articleid,artname,doinum,workflow);
    }*/

    @Test(priority = 2, description = "Ensure three options to add article - Form, Upload, SFTP all are available ")
    public void ensureThreeOptIsVisible() throws InterruptedException {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO, "Click on the Add Article");
        ExtentReportListener.getTest().log(Status.INFO, "Verifing Whether  the upload icon ,form icon,Clientftp icon is visible ");

        Boolean val = addarticlepage.ensureThreeOption();
        System.out.println(val);
        Assert.assertTrue(val, "Upload icon,form icon,clientftp icon is not showing");
        ExtentReportListener.getTest().log(Status.INFO, "Upload icon ,form icon,Clientftp icon is visible");

    }

    @DataProvider(name = "duplicatedata")
    public Object[][] addduplicateArticle() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\AddArticle.xlsx", 2);
    }

    @Test(priority = 3, dataProvider = "duplicatedata")
    public void AddArticle(String journalacro, String articleid, String artname, String doinum, String workflow) {
        System.out.println(journalacro);
        System.out.println(articleid);
        System.out.println(artname);
        System.out.println(doinum);

        ExtentReportListener.getTest().assignCategory(cateogry);

        String errormessage = addarticlepage.ensureduplicationarticle(journalacro, articleid, artname, doinum, workflow);
        System.out.println(errormessage);
        Assert.assertEquals(errormessage, "Article ID(" + articleid + ") already exists!", "Article duplicate alert is not showing");

    }

    @DataProvider(name = "articleaddacess")
    public Object[][] adddArticledataFromAllUser() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\AddArticle.xlsx", 3);
    }

    @Test(priority = 4, dataProvider = "articleaddacess")
    public void VerifyAddArticleAcess(String journalacro, String articleid, String artname, String doinum, String workflow, String username, String password, String jacrm, String pubname) {
        Boolean addarticleSuccess = addarticlepage.addarticleacess(journalacro, articleid, artname, doinum, workflow, username, password, jacrm, pubname);
        Assert.assertTrue(addarticleSuccess, "Article add functionality failed");
    }

    @DataProvider(name = "addarticleUnauthorizedacess")
    public Object[][] adddArticledataFromotherThanPMUser() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\AddArticle.xlsx", 4);
    }

    @Test(priority = 5, dataProvider = "addarticleUnauthorizedacess", description = "Other than PM and Login should create the Add article ")
    public void verifyOtherUserShoulNotCreateArticle(String uname, String upass) {
        Boolean visible = addarticlepage.verifyUnauthorizedUserAddArticle(uname, upass);
        Assert.assertFalse(visible, "Add articl icon is showing");

    }

    @Test(priority = 6)
    public void veriychecklistradiobuttonfunctionality() {

        String uname = prop.getProperty("username");
        String upass = prop.getProperty("username");


        List<String> cssValue = addarticlepage.Verifychecklistfunctionality(uname, upass, "background-color");
        System.out.println(cssValue);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cssValue.get(0).trim(), "rgb(60, 179, 113)", "The background color is not as expected.");
        softAssert.assertEquals(cssValue.get(1).trim(), "rgb(60, 179, 113)", "The background color is not as expected.");
        softAssert.assertEquals(cssValue.get(2).trim(), "rgb(60, 179, 113)", "The background color is not as expected.");
        softAssert.assertEquals(cssValue.get(3).trim(), "rgb(60, 179, 113)", "The background color is not as expected.");
        softAssert.assertEquals(cssValue.get(4).trim(), "rgb(60, 179, 113)", "The background color is not as expected.");
        softAssert.assertEquals(cssValue.get(5).trim(), "rgb(60, 179, 113)", "The background color is not as expected.");
        softAssert.assertEquals(cssValue.get(6).trim(), "rgb(60, 179, 113)", "The background color is not as expected.");
        softAssert.assertAll();
    }

    @DataProvider(name = "articletolatex")
    public Object[][] getarticleToLatex() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\AddArticle.xlsx", 5);
    }

    @DataProvider(name = "articletographics")
    public Object[][] getarticleToGraphics() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\AddArticle.xlsx", 6);
    }

    @DataProvider(name = "articletographicsandlatex")
    public Object[][] getarticleToGraphicsAndLatex() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\AddArticle.xlsx", 7);
    }

    @Test(priority = 7, dataProvider = "articletolatex", description = "Article with no images should not move to Graphics Department")
    public void verifyarticlemovingToLatex(String journalacro, String articleid, String artname, String doinum, String workflow, String uname, String upass, String displayfigcount, String Inlinefigcount) {
        boolean IsarticleShowingInGraphics = addarticlepage.verifyArticleMovedToLatex(journalacro, articleid, artname, doinum, workflow, uname, upass, displayfigcount, Inlinefigcount);
        Assert.assertTrue(IsarticleShowingInGraphics, "Article is not showing in the graphics");

    }


    @Test(priority = 8, dataProvider = "articletographics", description = "Article with  images should  move to Graphics Department")
    public void verifyarticlemoveToGraphics(String journalacro, String articleid, String artname, String doinum, String workflow, String uname, String upass, String displayfigcount, String Inlinefigcount) {
        boolean IsarticleShowingInGraphics = addarticlepage.verifyArticleShouldMoveToGraphics(journalacro, articleid, artname, doinum, workflow, uname, upass, displayfigcount, Inlinefigcount);
        Assert.assertTrue(IsarticleShowingInGraphics, "Article is not showing in the graphics");

    }

    @Test(priority = 9, dataProvider = "articletographicsandlatex", description = "JMS-42 : Article with Graphics - verify the initial flow")
    public void verifyarticlemoveToGraphicsAndLatex(String journalacro, String articleid, String artname, String doinum, String workflow, String uname, String upass, String displayfigcount, String Inlinefigcount, String luname, String lupass) {
        List<Boolean> articlevisible = addarticlepage.verifyArticleShouldMoveToGraphicsAndLatex(journalacro, articleid, artname, doinum, workflow, uname, upass, displayfigcount, Inlinefigcount, luname, lupass);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(articlevisible.get(0), "Article is not showing in the graphics");
        softAssert.assertTrue(articlevisible.get(1), "Article is not showing in the graphics");
        softAssert.assertAll();
    }

    @DataProvider(name = "addarticlewithoutfile")
    public Object[][] addarticlewithoutZipfile() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\AddArticle.xlsx", 8);
    }

    @Test(priority = 10, dataProvider = "addarticlewithoutfile", description = "JMS-42 : Article with Graphics - verify the initial flow")
    public void verifyFileIsMandatory(String journalacro, String articleid, String artname, String doinum, String workflow) throws InterruptedException {
        String filealerttext = addarticlepage.addarticlewithoutZipFile(journalacro, articleid, artname, doinum, workflow);
        System.out.println(filealerttext);
        Assert.assertEquals(filealerttext, "Kindly upload a .zip file", "File Upload alert is not showing");

    }

    @Test(priority = 11, dataProvider = "addarticlewithoutfile", description = "JMS-113 : More than one ZIP package should not be added for article - should be prevented ")
    public void verifyMultipleFileUploadAlert(String journalacro, String articleid, String artname, String doinum, String workflow) throws InterruptedException {
        String filealerttext = addarticlepage.addarticlewithMultipleZipFile(journalacro, articleid, artname, doinum, workflow);
        System.out.println(filealerttext);
        Assert.assertEquals(filealerttext, "Only one zip file should be uploaded", "File Upload alert is not showing");

    }


    @Test(priority = 12, dataProvider = "addarticlewithoutfile", description = "JMS-114 : Other files like PDF or JPG can be added - no limitations in number of files ")
    public void verifyMultipleAdditionalFileUpload(String journalacro, String articleid, String artname, String doinum, String workflow) throws InterruptedException {
//      List<Boolean>files= addarticlepage.addarticlewithMultipleAdditionalFile(journalacro,articleid,artname,doinum,workflow);
//        System.out.println(files);
//      SoftAssert softAssert=new SoftAssert();
//      softAssert.assertTrue(files.get(0),"Files is not visible");
//        softAssert.assertTrue(files.get(1),"Files is not visible");
//        softAssert.assertTrue(files.get(2),"Files is not visible");
//        softAssert.assertTrue(files.get(3),"Files is not visible");
//        softAssert.assertTrue(files.get(4),"Files is not visible");
//        softAssert.assertAll();
        Boolean filesvisible = addarticlepage.addarticlewithMultipleAdditionalFile(journalacro, articleid, artname, doinum, workflow);
        Assert.assertTrue(filesvisible, "files is not showing");

    }


    @Test(priority = 13, dataProvider = "addarticlewithoutfile", description = "JMS-115 : All the files including ZIP, will have Download, Remove options")
    public void verifyDownloadandRemoveOption(String journalacro, String articleid, String artname, String doinum, String workflow) throws InterruptedException {

        List<Boolean> downloadremove = addarticlepage.ensuredownloadandRemoveoption(journalacro, articleid, artname, doinum, workflow);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(downloadremove.get(0), "Download symbol is not showing");
        softAssert.assertTrue(downloadremove.get(1), "remove symbol is not showing");
       /* softAssert.assertTrue(downloadremove.get(2),"remove symbol is not showing");
        softAssert.assertTrue(downloadremove.get(3),"remove symbol is not showing");*/

        softAssert.assertAll();

    }

    @Test(priority = 14, dataProvider = "addarticlewithoutfile", description = "JMS-116 : Downloading the files should be possible from download icon - verify")
    public void verifyDownloadFunctionality(String journalacro, String articleid, String artname, String doinum, String workflow) throws InterruptedException {

        String filepath = addarticlepage.EnsureDownloadFunctionality(journalacro, articleid, artname, doinum, workflow);
        System.out.println(filepath);
        File file = new File(filepath);
        Assert.assertTrue(file.exists(), "Downloaded file does not exist: " + filepath);
        Assert.assertTrue(file.length() > 0, "Downloaded file is empty: " + filepath);

    }

    @Test(priority = 15, dataProvider = "addarticlewithoutfile", description = "JMS-116 : Downloading the files should be possible from download icon - verify")
    public void verifyRemovalFunctionality(String journalacro, String articleid, String artname, String doinum, String workflow) throws InterruptedException {

        boolean IsVisible = addarticlepage.EnsureRemovalFunctionality(journalacro, articleid, artname, doinum, workflow);
        Assert.assertFalse(IsVisible, "File IS not removed");

    }

    //Notes

    @DataProvider(name = "addarticledata")
    public Object[][] VerifyNotes() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\AddArticle.xlsx",9);
    }

    @Test(priority = 16,dataProvider = "addarticledata",description ="JMS-186 : Notes section - Verify the Publisher, Journal title, Article title are correct" )
        public void verifyPubJourArticleINNotes(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException

        {
            List<String> verifypubartjournal=addarticlepage.VerifyPubJournalArticleInNotes(journalacro, articleid, artname, doinum, workflow,pub,jour);
            SoftAssert softAssert=new SoftAssert();
            System.out.println(verifypubartjournal);
            softAssert.assertEquals(verifypubartjournal.get(0),pub,"pubisher name is wrongly displayed in Notes");
            softAssert.assertEquals(verifypubartjournal.get(1),jour,"Journal name is wrongly displayed in Notes");
            softAssert.assertEquals(verifypubartjournal.get(3), verifypubartjournal.get(2),"Article name is wrongly displayed in Notes");
            softAssert.assertAll();
        }
    @DataProvider(name = "notescont")
    public Object[][] VerifyNotescont() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\AddArticle.xlsx",10);
    }


    @Test(priority = 17,dataProvider = "notescont",description ="JMS-186 : Notes section - Verify the Publisher, Journal title, Article title are correct" )
    public void VerifyContentOfNotes(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour,String cont) throws InterruptedException {
            String notecont=addarticlepage.VerifyNotesContent(journalacro, articleid, artname, doinum, workflow,pub,jour,cont);
            Assert.assertEquals(notecont,cont,"Content are looking different");
        }

    @Test(priority = 18,dataProvider = "notescont",description ="JMS-186 : Notes section - Verify the Publisher, Journal title, Article title are correct" )
    public void VerifyContentOfNotesAfterChangeJournal(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour,String cont) throws InterruptedException
    {
        List<String> Notes=addarticlepage.VerifyNotesContentAfterChangeJournal(journalacro, articleid, artname, doinum, workflow,pub,jour,cont);
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(Notes.get(0),cont,"Notes are not  resetted");
        softAssert.assertEquals(Notes,"","Notes are not  resetted");
        softAssert.assertAll();

    }



    @DataProvider(name = "notesnotsave")
    public Object[][] NotesnotSave() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\AddArticle.xlsx",11);
    }


    @Test(priority =19,dataProvider ="notesnotsave",description = "JMS-192 :Clicking on ‘X’ - doesn’t saves the content and returns to Add article page")
    public void notecancel(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour,String cont) throws InterruptedException
    {
        String notes=addarticlepage.NoteCloseButton(journalacro, articleid, artname, doinum, workflow,pub,jour,cont);
        Assert.assertEquals(notes,"","Notes have been saved without clicking the save button");

    }

    //checklistfinal

    @Test(priority = 20,dataProvider = "addarticledata",description ="JMS-193 : Verify the Publisher, Journal title, Article title are correct" )
    public void verifyPubJourArticleINChecklistSelection(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException

    {
        List<String> verifypubartjournal=addarticlepage.VerifyPubJournalArticleInChecklist(journalacro, articleid, artname, doinum, workflow,pub,jour);
        SoftAssert softAssert=new SoftAssert();
        System.out.println(verifypubartjournal);
        softAssert.assertEquals(verifypubartjournal.get(0),pub,"pubisher name is wrongly displayed in Notes");
        softAssert.assertEquals(verifypubartjournal.get(1),jour,"Journal name is wrongly displayed in Notes");
        softAssert.assertEquals(verifypubartjournal.get(3), verifypubartjournal.get(2),"Article name is wrongly displayed in Notes");
        softAssert.assertAll();
    }


    @Test(priority =21,dataProvider = "addarticledata",description ="JMS-194 : Verify, on successful saving, Checkbox is selected in Article page" )
    public void verifyCheckboxChecklistSelection(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {
           Boolean ischecked= addarticlepage.IsCheckboxIsChecked(journalacro, articleid, artname, doinum, workflow,pub,jour);
           Assert.assertTrue(ischecked,"On open access is not checked");
    }

    @Test(priority =22,dataProvider = "addarticledata",description =" Toggle any checkboxes above for Tables,Figures" )
    public void verifyIsCheckedInEditArticle(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {
       Boolean IsChecked=addarticlepage.IsCheckboxIsCheckedInEditArticle(journalacro, articleid, artname, doinum, workflow,pub,jour);
       Assert.assertTrue(IsChecked,"Can able to click  the checkbox in EditArticle");
        //Assert.assertTrue(ischecked,"On open access is not checked");
    }
































    //file testcases















    @AfterMethod
    public void Afteralltest()
    {
        addarticlepage.reloadpage();
    }









}

