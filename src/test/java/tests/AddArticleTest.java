
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
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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


    @Test(priority = 0, description = "Ensure navigation of Form navigates to form filling page of article")
    public void navigatetoaddarticle() {
        addarticlepage.addarticlepage();
    }

   @Test(priority =1,dataProvider ="duplicatedata", description = "Upon providing valid inputs, Article can be added - need to be listed")
    public void addarticle(String journalacro,String articleid,String artname,String doinum,String workflow) throws InterruptedException {

       ExtentReportListener.getTest().assignCategory(cateogry);
       ExtentReportListener.getTest().log(Status.INFO, "Click on the Add Article");
       ExtentReportListener.getTest().log(Status.INFO, "Enter all the mandatory data ");
       ExtentReportListener.getTest().log(Status.INFO, "Click the AddButton ");
       ExtentReportListener.getTest().log(Status.INFO, "verifing article is added in article view ");


       Boolean isadded= addarticlepage.AddArticleByMandatoryFields(journalacro,articleid,artname,doinum,workflow);
       Assert.assertTrue(isadded,"Article is not added with only the mandatory data");
       ExtentReportListener.getTest().log(Status.INFO, " article is added in article view ");


    }

    @Test(priority = 2, description = "JMS:13-Ensure three options to add article - Form, Upload, SFTP all are available ")
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

    @Test(priority = 3, dataProvider = "duplicatedata",description = "JMS-15 : verify article duplication is prevented across PM and Login ")
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


    @Test(priority = 4, dataProvider = "articleaddacess",description = "JMS-14 : Article can be added by both PM and LOGIN user. Other users should not be able to add article")
    public void VerifyAddArticleAcess(String journalacro, String articleid, String artname, String doinum, String workflow, String username, String password, String jacrm, String pubname) {
        ExtentReportListener.getTest().assignCategory(cateogry);
        Boolean addarticleSuccess = addarticlepage.addarticleacess(journalacro, articleid, artname, doinum, workflow, username, password, jacrm, pubname);
        Assert.assertTrue(addarticleSuccess, "Article add functionality failed");
    }



    @DataProvider(name = "addarticleUnauthorizedacess")
    public Object[][] adddArticledataFromotherThanPMUser() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\AddArticle.xlsx", 4);
    }

    @Test(priority = 5, dataProvider = "addarticleUnauthorizedacess", description = "Other than PM and Login should create the Add article ")
    public void verifyOtherUserShoulNotCreateArticle(String uname, String upass)
    {
        ExtentReportListener.getTest().assignCategory(cateogry);
        Boolean visible = addarticlepage.verifyUnauthorizedUserAddArticle(uname, upass);
        Assert.assertFalse(visible, "Add articl icon is showing");

    }

    @Test(priority = 6,description = "JMS-39 : On/OFF any option is applicable for all entries ")
    public void veriychecklistradiobuttonfunctionality() {
        ExtentReportListener.getTest().assignCategory(cateogry);
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

    @Test(priority = 7, dataProvider = "articletolatex", description = "JMS:40-Article with no images should not move to Graphics Department")
    public void verifyarticlemovingToLatex(String journalacro, String articleid, String artname, String doinum, String workflow, String uname, String upass, String displayfigcount, String Inlinefigcount) {
        ExtentReportListener.getTest().assignCategory(cateogry);
        boolean IsarticleShowingInGraphics = addarticlepage.verifyArticleMovedToLatex(journalacro, articleid, artname, doinum, workflow, uname, upass, displayfigcount, Inlinefigcount);
        Assert.assertTrue(IsarticleShowingInGraphics, "Article is not showing in the graphics");

    }


    @Test(priority = 8, dataProvider = "articletographics", description = "JMS-41:Article with  images should  move to Graphics Department")
    public void verifyarticlemoveToGraphics(String journalacro, String articleid, String artname, String doinum, String workflow, String uname, String upass, String displayfigcount, String Inlinefigcount) {
        ExtentReportListener.getTest().assignCategory(cateogry);
        boolean IsarticleShowingInGraphics = addarticlepage.verifyArticleShouldMoveToGraphics(journalacro, articleid, artname, doinum, workflow, uname, upass, displayfigcount, Inlinefigcount);
        Assert.assertTrue(IsarticleShowingInGraphics, "Article is not showing in the graphics");

    }

    @Test(priority = 9, dataProvider = "articletographicsandlatex", description = "JMS-42 : Article with Graphics - verify the initial flow")
    public void verifyarticlemoveToGraphicsAndLatex(String journalacro, String articleid, String artname, String doinum, String workflow, String uname, String upass, String displayfigcount, String Inlinefigcount, String luname, String lupass) {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO, "Add a Article");
        ExtentReportListener.getTest().log(Status.INFO, "verify article is Showing in Latex and Graphics ");
        List<Boolean> articlevisible = addarticlepage.verifyArticleShouldMoveToGraphicsAndLatex(journalacro, articleid, artname, doinum, workflow, uname, upass, displayfigcount, Inlinefigcount, luname, lupass);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(articlevisible.get(0), "Article is not showing in the graphics");
        softAssert.assertTrue(articlevisible.get(1), "Article is not showing in the graphics");
        softAssert.assertAll();
        ExtentReportListener.getTest().log(Status.INFO, " article is Showing in Latex and Graphics ");

    }

    @DataProvider(name = "addarticlewithoutfile")
    public Object[][] addarticlewithoutZipfile() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\AddArticle.xlsx", 8);
    }

    @Test(priority = 10, dataProvider = "addarticlewithoutfile", description = " JMS-112 : One ZIP package is mandatory, without this Article addition should not be done")
    public void verifyFileIsMandatory(String journalacro, String articleid, String artname, String doinum, String workflow) throws InterruptedException {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO, "Adding Article without the file ");
        ExtentReportListener.getTest().log(Status.INFO, "Expected file upload mandatory alert is showing ");


        String filealerttext = addarticlepage.addarticlewithoutZipFile(journalacro, articleid, artname, doinum, workflow);
        System.out.println(filealerttext);
        Assert.assertEquals(filealerttext, "Kindly upload a .zip file", "File Upload mandatorry alert is not showing");

    }

    @Test(priority = 11, dataProvider = "addarticlewithoutfile", description = "JMS-113 : More than one ZIP package should not be added for article - should be prevented ")
    public void verifyMultipleFileUploadAlert(String journalacro, String articleid, String artname, String doinum, String workflow) throws InterruptedException {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO, "Adding Article without multiple file ");
        ExtentReportListener.getTest().log(Status.INFO, "Expected multiple file  alert is showing ");

        String filealerttext = addarticlepage.addarticlewithMultipleZipFile(journalacro, articleid, artname, doinum, workflow);
        System.out.println(filealerttext);
        Assert.assertEquals(filealerttext, "Only one zip file should be uploaded", "File Upload alert is not showing");
        ExtentReportListener.getTest().log(Status.INFO, " multiple file upload alert is showing ");



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
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO, "AddAticle with Addtional files ");
        ExtentReportListener.getTest().log(Status.INFO, "verify additonal file is added");
        Boolean filesvisible = addarticlepage.addarticlewithMultipleAdditionalFile(journalacro, articleid, artname, doinum, workflow);
        Assert.assertTrue(filesvisible, "files is not showing");
        ExtentReportListener.getTest().log(Status.INFO, "Additonal file is added");

    }


    @Test(priority = 13, dataProvider = "addarticlewithoutfile", description = "JMS-115 : All the files including ZIP, will have Download, Remove options")
    public void verifyDownloadandRemoveOption(String journalacro, String articleid, String artname, String doinum, String workflow) throws InterruptedException {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO, "Add Article with Files");
        ExtentReportListener.getTest().log(Status.INFO, "verify Download and remove option is showing for the added files");
        List<Boolean> downloadremove = addarticlepage.ensuredownloadandRemoveoption(journalacro, articleid, artname, doinum, workflow);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(downloadremove.get(0), "Download symbol is not showing");
        softAssert.assertTrue(downloadremove.get(1), "remove symbol is not showing");
       /* softAssert.assertTrue(downloadremove.get(2),"remove symbol is not showing");
        softAssert.assertTrue(downloadremove.get(3),"remove symbol is not showing");*/

        softAssert.assertAll();
        ExtentReportListener.getTest().log(Status.INFO, "  Download and remove option is showing for the added files");

    }

    @Test(priority = 14, dataProvider = "addarticlewithoutfile", description = "JMS-116 : Downloading the files should be possible from download icon - verify")
    public void verifyDownloadFunctionality(String journalacro, String articleid, String artname, String doinum, String workflow) throws InterruptedException {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO, "Add Article with Files");
        ExtentReportListener.getTest().log(Status.INFO, "click the download icon to download thee files ");
        String filepath = addarticlepage.EnsureDownloadFunctionality(journalacro, articleid, artname, doinum, workflow);
        System.out.println(filepath);
        File file = new File(filepath);
        Assert.assertTrue(file.exists(), "Downloaded file does not exist: " + filepath);
        Assert.assertTrue(file.length() > 0, "Downloaded file is empty: " + filepath);
        ExtentReportListener.getTest().log(Status.INFO, "File is downloaded");

    }

    @Test(priority = 15, dataProvider = "addarticlewithoutfile", description = " JMS-117 : File removal can be possible from Remove icon - Version 1")
    public void verifyRemovalFunctionality(String journalacro, String articleid, String artname, String doinum, String workflow) throws InterruptedException {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO, "Add Article with Files");
        ExtentReportListener.getTest().log(Status.INFO, "Click the remove icon");
        boolean IsVisible = addarticlepage.EnsureRemovalFunctionality(journalacro, articleid, artname, doinum, workflow);
        Assert.assertFalse(IsVisible, "File IS not removed");
        ExtentReportListener.getTest().log(Status.INFO, "File is removed");

    }

    //Notes

    @DataProvider(name = "addarticledata")
    public Object[][] VerifyNotes() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\AddArticle.xlsx",9);
    }

    @Test(priority = 16,dataProvider = "addarticledata",description ="JMS-186 : Notes section - Verify the Publisher, Journal title, Article title are correct" )
        public void verifyPubJourArticleINNotes(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException

        {
            ExtentReportListener.getTest().assignCategory(cateogry);
            ExtentReportListener.getTest().log(Status.INFO,"Cick  on Add Article option");
            ExtentReportListener.getTest().log(Status.INFO,"Ensure that Mandatory Text Fields Are Filled");
            ExtentReportListener.getTest().log(Status.INFO,"Click Add Notes");
            ExtentReportListener.getTest().log(Status.INFO,"verify that Publisher ,Journal Title,Article Title");


            List<String> verifypubartjournal=addarticlepage.VerifyPubJournalArticleInNotes(journalacro, articleid, artname, doinum, workflow,pub,jour);
            SoftAssert softAssert=new SoftAssert();
            System.out.println(verifypubartjournal);
            softAssert.assertEquals(verifypubartjournal.get(0),pub,"pubisher name is wrongly displayed in Notes");
            softAssert.assertEquals(verifypubartjournal.get(1),jour,"Journal name is wrongly displayed in Notes");
            softAssert.assertEquals(verifypubartjournal.get(3), verifypubartjournal.get(2),"Article name is wrongly displayed in Notes");
            softAssert.assertAll();
            ExtentReportListener.getTest().log(Status.INFO,"publisher ,journal name and Article are correct ");
        }
    @DataProvider(name = "notescont")
    public Object[][] VerifyNotescont() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\AddArticle.xlsx",10);
    }


    @Test(priority = 17,dataProvider = "notescont",description =" JMS-188 : Save the note and reopen the note and verify the information are correct " )
    public void VerifyContentOfNotes(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour,String cont) throws InterruptedException {

        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Cick  on Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Ensure that Mandatory Text Fields Are Filled");
        ExtentReportListener.getTest().log(Status.INFO,"Click Add Notes");
        ExtentReportListener.getTest().log(Status.INFO,"verify that write content in view notes and save");
        ExtentReportListener.getTest().log(Status.INFO,"verify that reopen the Note and check the information are correct");



        String notecont=addarticlepage.VerifyNotesContent(journalacro, articleid, artname, doinum, workflow,pub,jour,cont);
            Assert.assertEquals(notecont,cont,"Content are looking different");
        ExtentReportListener.getTest().log(Status.INFO,"information are correct inside the view notes successfully");

    }

    @Test(priority = 18,dataProvider = "notescont",description ="MS-189 : Change the higher level information like Journal title or Article title, verify the notes is reset" )
    public void VerifyContentOfNotesAfterChangeJournal(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour,String cont) throws InterruptedException
    {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Cick  on Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Ensure that Mandatory Text Fields Are Filled");
        ExtentReportListener.getTest().log(Status.INFO,"Click Add Notes");
        ExtentReportListener.getTest().log(Status.INFO,"verify that change higher level information like Journal Title or Article title");
        ExtentReportListener.getTest().log(Status.INFO,"verify the notes is Resetted with All type information was deleted and titles are change Accordingly");



        List<String> Notes=addarticlepage.VerifyNotesContentAfterChangeJournal(journalacro, articleid, artname, doinum, workflow,pub,jour,cont);
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(Notes.get(0),cont,"Notes are not  resetted");
        softAssert.assertEquals(Notes,"","Notes are not  resetted");
        softAssert.assertAll();
        ExtentReportListener.getTest().log(Status.INFO,"All view notes  information are  resetted sucessfully");


    }



    @DataProvider(name = "notesnotsave")
    public Object[][] NotesnotSave() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\AddArticle.xlsx",11);
    }


    @Test(priority =19,dataProvider ="notesnotsave",description = "JMS-192 :Clicking on ‘X’ - doesn’t saves the content and returns to Add article page")
    public void notecancel(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour,String cont) throws InterruptedException
    {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Cick  on Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Ensure that Mandatory Text Fields Are Filled");
        ExtentReportListener.getTest().log(Status.INFO,"Clicking on X doesn't save the content and Return to Add Article PAge");
        ExtentReportListener.getTest().log(Status.INFO,"verify and reopen and check again");


        String notes=addarticlepage.NoteCloseButton(journalacro, articleid, artname, doinum, workflow,pub,jour,cont);
        Assert.assertEquals(notes,"","Notes have been saved without clicking the save button");
        ExtentReportListener.getTest().log(Status.INFO,"It should be empty field showing successfully");


    }

    //checklistfinal

    @Test(priority = 20,dataProvider = "addarticledata",description ="JMS-193 : Verify the Publisher, Journal title, Article title are correct" )
    public void verifyPubJourArticleINChecklistSelection(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException

    {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Cick  on Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Ensure that Mandatory Text Fields Are Filled");
        ExtentReportListener.getTest().log(Status.INFO,"click Check List Box");
        ExtentReportListener.getTest().log(Status.INFO,"verify the Publisher ,journal Title,Article Title are correct");

        List<String> verifypubartjournal=addarticlepage.VerifyPubJournalArticleInChecklist(journalacro, articleid, artname, doinum, workflow,pub,jour);
        SoftAssert softAssert=new SoftAssert();
        System.out.println(verifypubartjournal);
        softAssert.assertEquals(verifypubartjournal.get(0),pub,"pubisher name is wrongly displayed in Notes");
        softAssert.assertEquals(verifypubartjournal.get(1),jour,"Journal name is wrongly displayed in Notes");
        softAssert.assertEquals(verifypubartjournal.get(3), verifypubartjournal.get(2),"Article name is wrongly displayed in Notes");
        softAssert.assertAll();
        ExtentReportListener.getTest().log(Status.INFO,"Publisher,Journal Title,Article Title are correctly displaying successfully");

    }


    @Test(priority =21,dataProvider = "addarticledata",description ="JMS-194 : Verify, on successful saving, Checkbox is selected in Article page" )
    public void verifyCheckboxChecklistSelection(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Cick  on Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Ensure that Mandatory Text Fields Are Filled");
        ExtentReportListener.getTest().log(Status.INFO,"click Check List Box");
        ExtentReportListener.getTest().log(Status.INFO,"Click any figures And click submit button");
        ExtentReportListener.getTest().log(Status.INFO,"verify that Check box is Selected");

        Boolean ischecked= addarticlepage.IsCheckboxIsChecked(journalacro, articleid, artname, doinum, workflow,pub,jour);
           Assert.assertTrue(ischecked,"On open access is not checked");
        ExtentReportListener.getTest().log(Status.INFO,"Add Article page checklist box selected Successfully");

    }

    @Test(priority =22,dataProvider = "addarticledata",description ="JMS-197 : Save the checklist and reopen and verify it can be edited further and saved" )
    public void verifyIsCheckedInEditArticle(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Cick  on Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Ensure that Mandatory Text Fields Are Filled");
        ExtentReportListener.getTest().log(Status.INFO,"click Check List Box");
        ExtentReportListener.getTest().log(Status.INFO,"verify that select any one checklist and click the Save button");
        ExtentReportListener.getTest().log(Status.INFO,"verify and Reopen and it can be Edited Further and saved");

        Boolean IsChecked=addarticlepage.IsCheckboxIsCheckedInEditArticle(journalacro, articleid, artname, doinum, workflow,pub,jour);
       Assert.assertTrue(IsChecked,"Can able to click  the checkbox in EditArticle");
        //Assert.assertTrue(ischecked,"On open access is not checked");
        ExtentReportListener.getTest().log(Status.INFO,"Edited And Saved Sucessfully");

    }

    @Test(priority =23,dataProvider = "addarticledata",description =" JMS-199 : Change the higher level information like Journal title or Article title, verify the checklist reset" )
    public void verifyChecklistAfterChangeHighLvlInfo(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Cick  on Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Ensure that Mandatory Text Fields Are Filled");
        ExtentReportListener.getTest().log(Status.INFO,"verify that change higher level information like Journal Title or Article title");


        Boolean ischecked=addarticlepage.VerifyChecklistAfterChangeHigherLevelInfo(journalacro, articleid, artname, doinum, workflow,pub,jour);
       Assert.assertFalse(ischecked,"Checklist is not resetted");
        ExtentReportListener.getTest().log(Status.INFO,"All the details needed to be resetted");


    }


    @Test(priority =24,dataProvider = "addarticledata",description ="JMS-201 : Clicking on ‘X’ - doesn’t saves the content and returns to Add article page -\n" )
    public void verifyChecklistclosefunctionality(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Cick  on Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Ensure that Mandatory Text Fields Are Filled");
        ExtentReportListener.getTest().log(Status.INFO,"Clicking on\"X\" doesn't save the content and Return to Add Article PAge");

        ExtentReportListener.getTest().log(Status.INFO,"verify and reopen and check again");

        Boolean ischecked=addarticlepage.ChecklistCloseButton(journalacro, articleid, artname, doinum, workflow,pub,jour);
        Assert.assertFalse(ischecked,"Checklist is not resetted");
        ExtentReportListener.getTest().log(Status.INFO,"It should be empty field showing successfully");

    }



    @Test(priority =26,dataProvider = "addarticledata",description ="JMS-202 : Verify the checklist is raised as Login query correctly - verify the contents " )
    public void verifyQueryIsAdded(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Cick  on Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Ensure that Mandatory Text Fields Are Filled");
        ExtentReportListener.getTest().log(Status.INFO,"Click on Checklist raised Query");

        ExtentReportListener.getTest().log(Status.INFO,"click on Add Article button ");
        ExtentReportListener.getTest().log(Status.INFO,"verify the Raised Querry in Login correctly");


        Boolean isadded=addarticlepage.IsQueryShowing(journalacro, articleid, artname, doinum, workflow,pub,jour);
        Assert.assertTrue(isadded,"Query is showing");
        ExtentReportListener.getTest().log(Status.INFO,"Query is in Login Successfully");

    }

    //TAt

    @DataProvider(name = "Tatdata")
    public Object[][] VerifyTat() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\AddArticle.xlsx",12);
    }

    @Test(priority =27,dataProvider ="Tatdata",description ="JMS-32 : Verify the TATs imported here is imported from Journal not from Publisher")
    public void verifyTatImportFromJournal(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour,String f_latex,String f_gra,String f_pre,String f_copy,String f_pag,String f_qc,String au_pag,String au_qc,String pe_pag,String pe_qc,String onlinepag,String onlineqc,String onlinexml,String issuepag,String issueqc,String printpag,String printqc,String print_xml ) throws InterruptedException {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Cick  on Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Click TAT Drop Down");
        ExtentReportListener.getTest().log(Status.INFO,"Click general and import");

        ExtentReportListener.getTest().log(Status.INFO,"Click Fastrack and import");


        List<String> journaltatimport= addarticlepage.verifyTatFromJournal(journalacro, articleid, artname, doinum, workflow,pub,jour,f_latex,f_gra,f_pre,f_copy,au_pag,au_qc,pe_pag,pe_qc,onlinepag,onlineqc,onlinexml,issuepag,issueqc,printpag,printqc,print_xml);
        System.out.println(journaltatimport);
        SoftAssert softAssert=new SoftAssert();

        softAssert.assertEquals(journaltatimport.get(0),f_latex,"Imported days are wrong");
        softAssert.assertEquals(journaltatimport.get(1),f_gra,"Imported days are wrong");
        softAssert.assertEquals(journaltatimport.get(2),f_pre,"Imported days are wrong");
        softAssert.assertEquals(journaltatimport.get(3),f_copy,"Imported days are wrong");
        softAssert.assertEquals(journaltatimport.get(4),f_pag,"Imported days are wrong");
        softAssert.assertEquals(journaltatimport.get(5),f_qc,"Imported days are wrong");
        softAssert.assertEquals(journaltatimport.get(6),au_pag,"Imported days are wrong");
        softAssert.assertEquals(journaltatimport.get(7),au_qc,"Imported days are wrong");
        softAssert.assertEquals(journaltatimport.get(8),pe_pag,"Imported days are wrong");
        softAssert.assertEquals(journaltatimport.get(9),pe_qc,"Imported days are wrong");
        softAssert.assertEquals(journaltatimport.get(10),onlinepag,"Imported days are wrong");
        softAssert.assertEquals(journaltatimport.get(11),onlineqc,"Imported days are wrong");
        softAssert.assertEquals(journaltatimport.get(12),onlinexml,"Imported days are wrong");
        softAssert.assertEquals(journaltatimport.get(13),issuepag,"Imported days are wrong");
        softAssert.assertEquals(journaltatimport.get(14),issueqc,"Imported days are wrong");
        softAssert.assertEquals(journaltatimport.get(15),printpag,"Imported days are wrong");
        softAssert.assertEquals(journaltatimport.get(16),printqc,"Imported days are wrong");
        softAssert.assertEquals(journaltatimport.get(17),print_xml,"Imported days are wrong");

        softAssert.assertNotEquals(journaltatimport.get(0),"2","Imported days are wrong");
        softAssert.assertNotEquals(journaltatimport.get(1),"2","Imported days are wrong");
        softAssert.assertNotEquals(journaltatimport.get(2),"2","Imported days are wrong");
        softAssert.assertNotEquals(journaltatimport.get(3),"2","Imported days are wrong");
        softAssert.assertNotEquals(journaltatimport.get(4),"2","Imported days are wrong");
        softAssert.assertNotEquals(journaltatimport.get(5),"2","Imported days are wrong");
        softAssert.assertNotEquals(journaltatimport.get(6),"2","Imported days are wrong");
        softAssert.assertNotEquals(journaltatimport.get(7),"2","Imported days are wrong");
        softAssert.assertNotEquals(journaltatimport.get(8),"2","Imported days are wrong");
        softAssert.assertNotEquals(journaltatimport.get(9),"2","Imported days are wrong");
        softAssert.assertNotEquals(journaltatimport.get(10),"2","Imported days are wrong");
        softAssert.assertNotEquals(journaltatimport.get(11),"2","Imported days are wrong");
        softAssert.assertNotEquals(journaltatimport.get(12),"2","Imported days are wrong");
        softAssert.assertNotEquals(journaltatimport.get(13),"2","Imported days are wrong");
        softAssert.assertNotEquals(journaltatimport.get(14),"2","Imported days are wrong");
        softAssert.assertNotEquals(journaltatimport.get(15),"2","Imported days are wrong");
        softAssert.assertNotEquals(journaltatimport.get(16),"2","Imported days are wrong");
        softAssert.assertNotEquals(journaltatimport.get(17),"2","Imported days are wrong");

        softAssert.assertAll();

        ExtentReportListener.getTest().log(Status.INFO,"The Data of the fastrack TAT days count from journal.");
    }

    @DataProvider(name = "ModifiedTatdata")
    public Object[][] VerifymodifiedTat() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\AddArticle.xlsx",13);
    }

    @Test(priority =28,dataProvider ="ModifiedTatdata",description ="JMS-36 : Modification of TAT after imported")
    public void verifyModifiedTat(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour,String f_latex,String f_gra,String f_pre,String f_copy,String f_pag,String f_qc,String au_pag,String au_qc,String pe_pag,String pe_qc,String onlinepag,String onlineqc,String onlinexml,String issuepag,String issueqc,String printpag,String printqc,String print_xml ) throws InterruptedException {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Cick  on Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Check the TAT are Imported and Ensure that TAT can be modify the Duration of the Days");
        ExtentReportListener.getTest().log(Status.INFO,"Check the DUE ON dates gets modified Accordingly");

        List<String> articletatimport= addarticlepage.ModifyTatAfterImport(journalacro, articleid, artname, doinum, workflow,pub,jour,f_latex,f_gra,f_pre,f_copy,au_pag,au_qc,pe_pag,pe_qc,onlinepag,onlineqc,onlinexml,issuepag,issueqc,printpag,printqc,print_xml);
        System.out.println(articletatimport);
        SoftAssert softAssert=new SoftAssert();

        softAssert.assertEquals(articletatimport.get(0),f_latex,"Tat are not modified");
        softAssert.assertEquals(articletatimport.get(1),f_gra,"Tat are not modified");
        softAssert.assertEquals(articletatimport.get(2),f_pre,"Tat are not modified");
        softAssert.assertEquals(articletatimport.get(3),f_copy,"Tat are not modified");
        softAssert.assertEquals(articletatimport.get(4),f_pag,"Tat are not modified");
        softAssert.assertEquals(articletatimport.get(5),f_qc,"Tat are not modified");
        softAssert.assertEquals(articletatimport.get(6),au_pag,"Tat are not modified");
        softAssert.assertEquals(articletatimport.get(7),au_qc,"Tat are not modified");
        softAssert.assertEquals(articletatimport.get(8),pe_pag,"Tat are not modified");
        softAssert.assertEquals(articletatimport.get(9),pe_qc,"Tat are not modified");
        softAssert.assertEquals(articletatimport.get(10),onlinepag,"Tat are not modified");
        softAssert.assertEquals(articletatimport.get(11),onlineqc,"Tat are not modified");
        softAssert.assertEquals(articletatimport.get(12),onlinexml,"Tat are not modified");
        softAssert.assertEquals(articletatimport.get(13),issuepag,"Tat are not modified");
        softAssert.assertEquals(articletatimport.get(14),issueqc,"Tat are not modified");
        softAssert.assertEquals(articletatimport.get(15),printpag,"Tat are not modified");
        softAssert.assertEquals(articletatimport.get(16),printqc,"Tat are not modified");
        softAssert.assertEquals(articletatimport.get(17),print_xml,"Tat are not modified");
        softAssert.assertAll();
        ExtentReportListener.getTest().log(Status.INFO,"DUE ON dates are Modified and the Dates are updating successfully");

    }

    @Test(priority =29,dataProvider = "addarticledata",description =" JMS-34 : Verify the 'Due On' date is calculated" )
    public void verifyDueDateCalculations(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {
        ExtentReportListener.getTest().assignCategory(cateogry);

        ExtentReportListener.getTest().log(Status.INFO,"Click TAT drop Down Icon\n" +
                "\n");
        ExtentReportListener.getTest().log(Status.INFO,"check the TAT Both the Due on Date  is calculated   the No.of completion days  (saturday and sunday is not counted)\n" +
                "\n");
        ExtentReportListener.getTest().log(Status.INFO,"Check the No.of Completion Days Select the  Different Dates and calculate the Days");

        int totaldays=6;

        LocalDate resultDate =LocalDate.now();
        System.out.println(resultDate);
        int addedDays = 1;

        while (addedDays < totaldays)
        {
            resultDate = resultDate.plusDays(1);

            // Skip weekends
            if (!(resultDate.getDayOfWeek() == DayOfWeek.SATURDAY || resultDate.getDayOfWeek() == DayOfWeek.SUNDAY))
            {
                addedDays++;
            }
        }

       String firstproofduedate=addarticlepage.VerifyDueDateAutoCalculate(journalacro, articleid, artname, doinum, workflow,pub,jour);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adjust the pattern according to your date format
        LocalDate actualDate = LocalDate.parse(firstproofduedate, formatter);
        System.out.println(firstproofduedate);
        System.out.println(resultDate);
        Assert.assertEquals(actualDate,resultDate,"Saturday and sunday calculatiion is not getting skipped");
        ExtentReportListener.getTest().log(Status.INFO,"Start date and the No.of Completion days are correctly counted and updating\n" +
                "\n");

    }

    @Test(priority =30,dataProvider = "addarticledata",description ="JMS-35 : Verify leave days (sat,Sun) can’t be selected as StartDate" )
    public void VerifyStartDate(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Click Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Click the TAT Drop Down icon");
        ExtentReportListener.getTest().log(Status.INFO,"check the Both TAT and Count the leave days of (saturday and Sunday) ,User should NOT be allowed the leave days (sat,Sun) as StartDate while adding the Article TAT");

        int totaldays=30;
        LocalDate resultDate =LocalDate.now();
        System.out.println(resultDate);
        int addedDays = 1;

        while (addedDays < totaldays) {
            resultDate = resultDate.plusDays(1);

            if (resultDate.getDayOfWeek() == DayOfWeek.SATURDAY || resultDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                System.out.println("saturday date"+resultDate);
                break;
            }
            else
            {
                addedDays++;

            }
        }


        System.out.println(resultDate);

        Boolean startdate=addarticlepage.VerifyStartWorkingDAte(journalacro, articleid, artname, doinum, workflow,pub,jour,resultDate);
        Assert.assertFalse(startdate,"Saturday and sunday date is accepting in start date");
        ExtentReportListener.getTest().log(Status.INFO,"Saturday and sunday are skipped");

    }
//MailPreview


    @Test(priority =31,dataProvider = "addarticledata",description ="JMS-96 : Ensure there are two mail types ‘Acknowledgements’ , ‘Notifications’ and both can be toggled" )
    public void VerifyToogleBetweenAcknowandKnowledge(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO, "Click Add Article icon in the quick link");
        ExtentReportListener.getTest().log(Status.INFO,"Ensure that Mandatory Text Field should be Filled");
        ExtentReportListener.getTest().log(Status.INFO,"Click Mail Preview Check Box");
        ExtentReportListener.getTest().log(Status.INFO,"click Acknowledements Mail Ensure that without Adding To Address and save mail");
        ExtentReportListener.getTest().log(Status.INFO,"click Acknowledgements mail and Notification Mail can be toggled");






        Boolean istoogled=addarticlepage.ToggleBetweenNotificationAndAcknowledgementMail(journalacro, articleid, artname, doinum, workflow,pub,jour);
        Assert.assertTrue(istoogled,"Toogle between ackno and knowledge is not toggled");
        ExtentReportListener.getTest().log(Status.INFO,"Acknowledgement mail and Nofication mail Toggled correctly");

    }


    @Test(priority =32,dataProvider = "addarticledata",description ="JMS-99 : Ensure we can’t save the mail without selecting ‘To’ addresses - check on both " )
    public void VerifyToMailMandatoryy(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {

        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Click Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Ensure that Mandatory Text Fields Are Filled");
        ExtentReportListener.getTest().log(Status.INFO,"Click Mail Preview Check Box");
        ExtentReportListener.getTest().log(Status.INFO,"click Acknowledements Mail Ensure that without Adding To Address and save mail");
        ExtentReportListener.getTest().log(Status.INFO,"Click Notification mail Ensure that Without Adding TO Address and Save Mail");


        String cssvalue="cursor";

        String savemailcursor=addarticlepage.ToMailMandatoryAlert(journalacro, articleid, artname, doinum, workflow,pub,jour,cssvalue);
      Assert.assertEquals(savemailcursor,"not-allowed","Can able to click the savemail without selecting the to mail");
        ExtentReportListener.getTest().log(Status.INFO,"Notification Mail Cant Able to Save the Mail Without TO Address");

    }

    @Test(priority =32,dataProvider = "addarticledata",description ="JMS-100 : Ensure the selection of ‘Cc’ is optional - not mandatory - check on both " )
    public void VerifyCcMailNotMandatory(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {
        ExtentReportListener.getTest().assignCategory(cateogry);

        ExtentReportListener.getTest().log(Status.INFO,"Click Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Ensure that Mandatory Text Fields Are Filled");
        ExtentReportListener.getTest().log(Status.INFO,"Click Mail Preview Check Box");
        ExtentReportListener.getTest().log(Status.INFO,"click Acknowledgements mail and select CC Address");
        ExtentReportListener.getTest().log(Status.INFO,"click  Notification Mail And Selecct CC Address");


        Boolean MailsentWithoutCcMail= addarticlepage.CcMailNotMandatory(journalacro, articleid, artname, doinum, workflow,pub,jour);
        Assert.assertTrue(MailsentWithoutCcMail,"Without  Cc mail is not allowing to add article");
        ExtentReportListener.getTest().log(Status.INFO,"Notification mail CC is not Mandatory its optional");

    }

    @Test(priority =33,dataProvider = "addarticledata",description ="JMS-101 : Save any one mail, don’t save another - user can still navigate back to Article page" )
    public void AddAcknowledgementMailandNavigateBack(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {

        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Click Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Ensure that Mandatory Text Fields Are Filled");
        ExtentReportListener.getTest().log(Status.INFO,"Click Mail Preview Check Box");
        ExtentReportListener.getTest().log(Status.INFO,"Click Acknowledegement Mail  and  Select \"TO\" and \"CC\" Address and save the Mail");
        ExtentReportListener.getTest().log(Status.INFO,"Check user can Navigate back to Article Page");
        Boolean saveonemail= addarticlepage.SaveAcknoMailAndNavigateBack(journalacro, articleid, artname, doinum, workflow,pub,jour);
        Assert.assertTrue(saveonemail,"After save only Acknowmail cant able to navigate to Add article tab");
        ExtentReportListener.getTest().log(Status.INFO,"Navigate to Article Page is Sucessfully");

    }

    @Test(priority =34,dataProvider = "addarticledata",description =" JMS-101 : Save any one mail, don’t save another - user can still navigate back to Article page" )
    public void AddOnlyNotificationtMailandNavigateBack(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {

        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Click Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Ensure that Mandatory Text Fields Are Filled");
        ExtentReportListener.getTest().log(Status.INFO,"Click Mail Preview Check Box");
        ExtentReportListener.getTest().log(Status.INFO,"Click Acknowledement Mail select \"TO\" And \"CC\" and save the Mail");
        ExtentReportListener.getTest().log(Status.INFO,"Check user can Navigate back to Article Page");

        Boolean saveonemail= addarticlepage.SaveAcknoMailAndNavigateBack(journalacro, articleid, artname, doinum, workflow,pub,jour);
        Assert.assertTrue(saveonemail,"After save only Acknowmail cant able to navigate to Add article tab");
        ExtentReportListener.getTest().log(Status.INFO,"Navigate to Article Page is Sucessfully");

    }

    @Test(priority =35,dataProvider = "addarticledata",description =" JMS-102 : Save any one mail, don’t save another - mail checkbox still not selected" )
    public void SaveOneMailAndVerifyPreviewCheckbox(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Click Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Ensure that Mandatory Text Fields Are Filled");
        ExtentReportListener.getTest().log(Status.INFO,"Click Mail Preview Check Box");
        ExtentReportListener.getTest().log(Status.INFO,"Click Acknowledement Mail select \"TO\" And \"CC\" and save the Mail");
        ExtentReportListener.getTest().log(Status.INFO,"Check that Mail Preveiw Checkbox");


        Boolean previewcheckbox= addarticlepage.SaveAcknoMailAndVerifyPreview(journalacro, articleid, artname, doinum, workflow,pub,jour);
        Assert.assertTrue(previewcheckbox,"After save only Acknowmail cant able to navigate to Add article tab");
        ExtentReportListener.getTest().log(Status.INFO,"Mail preview check Box is not Selected");

    }

    @Test(priority =36,dataProvider = "addarticledata",description =" JMS-103 : Verify, on successful saving both mail, mail checkbox is selected in Article page - verify by reopen" )
    public void SaveBothMailAndVerifyPreviewCheckbox(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Click Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Ensure that Mandatory Text Fields Are Filled");
        ExtentReportListener.getTest().log(Status.INFO,"Click Mail Preview Check Box");
        ExtentReportListener.getTest().log(Status.INFO,"click  Acknowledegement Mail Select \"TO\" And \"CC\" Address and click save button.");
        ExtentReportListener.getTest().log(Status.INFO,"click Notification Mail Select \"TO\" And \"CC\" Address and click Save Button");


        Boolean previewcheckbox= addarticlepage.SaveBothMailAndVerifyPreview(journalacro, articleid, artname, doinum, workflow,pub,jour);
        Assert.assertTrue(previewcheckbox,"After save Both mail ,Mail preview checkbox is not checked");
        ExtentReportListener.getTest().log(Status.INFO,"verify by Reopen And Check Again");

    }


    @Test(priority =37,dataProvider = "addarticledata",description ="  JMS-105 : Save the mail and reopen and verify it can be edited further and saved" )
    public void SaveBothMailAndVerifyMailUpdate(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Click Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Ensure that Mandatory Text Fields Are Filled");
        ExtentReportListener.getTest().log(Status.INFO,"Click Mail Preview Check Box");
        ExtentReportListener.getTest().log(Status.INFO,"click  Acknowledegement Mail Select \"TO\" And \"CC\" Address and click save button.");
        ExtentReportListener.getTest().log(Status.INFO,"click Notification Mail Select \"TO\" And \"CC\" Address and click Save Button");
        ExtentReportListener.getTest().log(Status.INFO,"verify Reopen and Edit Further  and saved");


        Boolean subjectmail= addarticlepage.VerifyUpdationOnMailAfterSave(journalacro, articleid, artname, doinum, workflow,pub,jour);
        Assert.assertTrue(subjectmail,"After save Both mail ,Cant able to update Subject of the mail");

        ExtentReportListener.getTest().log(Status.INFO,"Both Acknowledegement and Notification Mail are edited and Update Successfully");

    }

    @Test(priority =38,dataProvider = "addarticledata",description ="JMS-107 : Change the higher level information like Journal title or Article title" )
    public void verifyMailAfterChangeHighLevel(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Click Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Ensure that Mandatory Text Fields Are Filled");
        ExtentReportListener.getTest().log(Status.INFO,"Click Mail Preview Check Box");
        ExtentReportListener.getTest().log(Status.INFO,"Click  Acknowledegement Mail Adding \"TO\" and \"CC\" Address and Click Save Button");
        ExtentReportListener.getTest().log(Status.INFO,"Click Notification Mail Adding \"TO\" and \"CC\" Address and Click Save Button");
        ExtentReportListener.getTest().log(Status.INFO,"change higher level (Journal name or  Article Title)");
        ExtentReportListener.getTest().log(Status.INFO,"verify the Mail Preview is Resetted (checkbox should be Deselected)");



        Boolean subjectmail= addarticlepage.VerifyMailAfterChangeHighLevels(journalacro, articleid, artname, doinum, workflow,pub,jour);
        Assert.assertTrue(subjectmail,"After save Both mail ,Cant able to update Subject of the mail");
        ExtentReportListener.getTest().log(Status.INFO,"Mail are Resetted and the check Box is Deselected Successfully");

    }


    @Test(priority =39,dataProvider = "addarticledata",description ="JMS-109 : Verify Acknowledgement mail is triggered and mail received after article added(check To and Cc both)" )
    public void VerifyAcknowledgementToast(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {

        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Click Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Ensure that Mandatory Text Fields Are Filled");
        ExtentReportListener.getTest().log(Status.INFO,"Click Mail Preview Check Box");
        ExtentReportListener.getTest().log(Status.INFO,"Click  Acknowledegement Mail Adding \"TO\" and \"CC\" Address and Click Save Button");
        ExtentReportListener.getTest().log(Status.INFO,"verify the Acknownledegement Mail and Received After Artile Added (check TO and CC)");


        Boolean acknowledgementtoast= addarticlepage.VerifyAcknowledgementAddedMailToast(journalacro, articleid, artname, doinum, workflow,pub,jour);
        Assert.assertTrue(acknowledgementtoast,"Acknowledgement mail successfull Toast  is not showing ");
        ExtentReportListener.getTest().log(Status.INFO,"Article Added Trigger Pop up Message displayed and Mail Received Successfully");

    }

    @Test(priority =40,dataProvider = "addarticledata",description ="JMS-110 : Verify Notification mail is triggered and mail received after article added(check To" )
    public void VerifyNotificationToast(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Click Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Ensure that Mandatory Text Fields Are Filled");
        ExtentReportListener.getTest().log(Status.INFO,"Click Mail Preview Check Box");
        ExtentReportListener.getTest().log(Status.INFO,"Click  Notification Mail Adding \"TO\" and \"CC\" Address and Click Save Button ");
        ExtentReportListener.getTest().log(Status.INFO,"verify the Notification Mail and Received After Artile Added (check TO and CC)");


        Boolean Notificationmailtoast= addarticlepage.VerifyNotificationAddedMailToast(journalacro, articleid, artname, doinum, workflow,pub,jour);
        Assert.assertTrue(Notificationmailtoast,"Notification Mail  successfull Toast  is not showing ");
        ExtentReportListener.getTest().log(Status.INFO,"Article Added Trigger Pop up Message displayed and Mail Received Successfully");
    }


    @Test(priority =41,dataProvider = "addarticledata",description ="JMS-111 : Clicking on ‘X’ - doesn’t saves the content and returns to Add article page" )
    public void MailCloseFunctionality(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Click Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Ensure that Mandatory Text Fields Are Filled");
        ExtentReportListener.getTest().log(Status.INFO,"Click Mail Preview Check Box");
        ExtentReportListener.getTest().log(Status.INFO,"clicking on\"X\" Doesnt save the coontent and Return to Add Article Page");
        ExtentReportListener.getTest().log(Status.INFO,"verify by  Reopen And Check Again");

         Boolean isarticlepageshowing=addarticlepage.VerifyCloseMail(journalacro, articleid, artname, doinum, workflow,pub,jour);
         Assert.assertTrue(isarticlepageshowing,"Article Contet is resett");
        ExtentReportListener.getTest().log(Status.INFO,"No chances done In Add Article");


    }


    @Test(priority =42,dataProvider = "addarticledata",description =" JMS-203 : Editing article - Major corrections like below should not be allowed ")
    public void VerifyHighLevelDataChangeUpdate(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {

        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Click Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Ensure that Mandatory Text Fields Are Filled");
        ExtentReportListener.getTest().log(Status.INFO,"Click Mail Preview Check Box");
        ExtentReportListener.getTest().log(Status.INFO,"verify Publisher Name,Journal Title,Article,Title ,MainZip Package  Replace/Delete CE bypass,Workflow,Checklist,Notes,mail Preview\n" +
                "\n");


        List<Boolean> iseditable=addarticlepage.MajorEditShouldnottAllowed(journalacro, articleid, artname, doinum, workflow,pub,jour);
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertFalse(iseditable.get(0),"ArticleId data is edited");
        softAssert.assertFalse(iseditable.get(1),"ArticleName data is edited");
        softAssert.assertFalse(iseditable.get(2),"CeByPass data is edited");
        softAssert.assertFalse(iseditable.get(3),"workflow data is edited");
        softAssert.assertFalse(iseditable.get(4),"journalacro data is edited");
        softAssert.assertAll();

        ExtentReportListener.getTest().log(Status.INFO,"Major Text Fields are not Allowed to Edit the Article");



    }






    @Test(priority =43,dataProvider = "addarticledata",description ="JMS-204 : Editing article - Minor corrections like below should  be allowed" )
    public void VerifyMinorLevelUpdate(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Click Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Ensure that Mandatory Text Fields Are Filled");
        ExtentReportListener.getTest().log(Status.INFO,"Click Mail Preview Check Box");
        ExtentReportListener.getTest().log(Status.INFO,"verify the Editing Article minor changes");
        ExtentReportListener.getTest().log(Status.INFO,"verify the updation Reflected Correctly\n" +
                "\n");


        Boolean isenable=addarticlepage.MinorEditShouldnottAllowed(journalacro, articleid, artname, doinum, workflow,pub,jour);
        Assert.assertTrue(isenable,"low level data is not edited");
        ExtentReportListener.getTest().log(Status.INFO,
                "Updation are succesfull");

    }

    //MetaData Test

    @DataProvider(name = "uniquearticleid")
    public Object[][] uniquearticleid() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\AddArticle.xlsx",14);

    }

    @Test(priority =44,dataProvider ="uniquearticleid",description = " JMS-84 : Verify article ID is unique for every article")
    public void addArticleUniqueID(String journalacro, String articleid, String artname, String doinum, String workflow)
    {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Click Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Click on the form button");
        ExtentReportListener.getTest().log(Status.INFO,"Enter the new Article ID");
        ExtentReportListener.getTest().log(Status.INFO,"verify the Editing Article minor changes");
        ExtentReportListener.getTest().log(Status.INFO,"Enter the existing the Article ID");
        addarticlepage.addarticleforuniqueID(journalacro,articleid,artname,doinum,workflow);
        ExtentReportListener.getTest().log(Status.INFO,"Pop Message should be displayed as Article ID already exist");

    }

    @Test(priority =45,dataProvider ="uniquearticleid",description = " JMS-84 : Verify article ID is unique for every article")
    public void VerifyUniqueArticleAlert(String journalacro, String articleid, String artname, String doinum, String workflow) throws InterruptedException {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Click Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Click on the form button");
        ExtentReportListener.getTest().log(Status.INFO,"Enter the new Article ID");
        ExtentReportListener.getTest().log(Status.INFO,"verify the Editing Article minor changes");
        ExtentReportListener.getTest().log(Status.INFO,"Enter the existing the Article ID");
        Boolean isalertshowing=addarticlepage.UniqueArticleIDVerification(journalacro,articleid,artname,doinum,workflow);
        Assert.assertTrue(isalertshowing,"Article duplicate alert is not showing");
        ExtentReportListener.getTest().log(Status.INFO,"Pop Message should be displayed as Article ID already exist");
    }

    @DataProvider(name = "mailcount")
    public Object[][] MismatchMailCount() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\AddArticle.xlsx",15);

    }
    @Test(priority =46,dataProvider ="mailcount",description = " JMS-85 : Verify number of authors and mailID’s must match")
    public void VerifyMismatchMailcount(String journalacro, String articleid, String artname, String doinum, String workflow,String authorname,String authormail) throws InterruptedException {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Click Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Click on the form button");
        ExtentReportListener.getTest().log(Status.INFO,"Enter the 3 author name and 4 mail ID ");
        ExtentReportListener.getTest().log(Status.INFO,"Enter the 4 author name and 3 mail ID");
        boolean IsArticleAdded=addarticlepage.MismatchCountOfAuthorAndMail(journalacro,articleid,artname,doinum,workflow,authorname,authormail);
        System.out.println(IsArticleAdded);
      Assert.assertFalse(IsArticleAdded,"Mismatch mail count is not showing");
        ExtentReportListener.getTest().log(Status.INFO,"Article should not be added, stating that authors and mailID’s must match");

    }

    @Test(priority =47,dataProvider ="mailcount",description = " JMS-86 : Verify atleast one author is mandatory for an article")
    public void VerifyAuthorFieldMandatory(String journalacro, String articleid, String artname, String doinum, String workflow,String authorname,String authormail) throws InterruptedException {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Click Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Click on the form button");
        ExtentReportListener.getTest().log(Status.INFO,"Skip by not give the Author name to the input field.");

        boolean IsArticleAdded=addarticlepage.AuthorFieldMandatory(journalacro,articleid,artname,doinum,workflow,authorname,authormail);
        System.out.println(IsArticleAdded);
        Assert.assertFalse(IsArticleAdded,"Article added without the author name");
        ExtentReportListener.getTest().log(Status.INFO,"Article should not be created, atleast one author name needed to be mandatory.");

    }





    @Test(priority =48,dataProvider ="mailcount",description = "JMS-88 : Verify ‘,’ comma - separates the author name")
    public void VerifyCommaValidationInAuthorName(String journalacro, String articleid, String artname, String doinum, String workflow,String authorname,String authormail) throws InterruptedException {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Click Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Click on the form button");
        ExtentReportListener.getTest().log(Status.INFO,"Enter the Author name with , comma in between ");

        boolean IsArticleAdded=addarticlepage.CommaAcceptsValidation(journalacro,articleid,artname,doinum,workflow,authorname,authormail);
        System.out.println(IsArticleAdded);
        Assert.assertTrue(IsArticleAdded,"Comma is not accepted in Author name Field");
        ExtentReportListener.getTest().log(Status.INFO,"The Author name should accept");


    }

    @Test(priority =49,dataProvider ="mailcount",description = "JMS-89 : Verify ‘;’ semicolon - separates the author mailID")
    public void VerifyCommaValidationInAuthorMail(String journalacro, String articleid, String artname, String doinum, String workflow,String authorname,String authormail) throws InterruptedException {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Click Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Click on the form button");
        ExtentReportListener.getTest().log(Status.INFO,"Enter the email ID using the semi colon ");

        boolean IsArticleAdded=addarticlepage.CommaAcceptsValidation(journalacro,articleid,artname,doinum,workflow,authorname,authormail);
        System.out.println(IsArticleAdded);
        Assert.assertTrue(IsArticleAdded,"Comma is not accepted in Author name Field");
        ExtentReportListener.getTest().log(Status.INFO,"The semi colon should accept inbetween the email ID");


    }

    @DataProvider(name = "mailformat")
    public Object[][] MailFormat() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\AddArticle.xlsx",16);

    }

    @Test(priority =50,dataProvider ="mailformat",description = " JMS-90 : validation check in mail id ")
    public void VerifyMailFormat(String journalacro, String articleid, String artname, String doinum, String workflow,String authorname,String authormail) throws InterruptedException {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Click Add Article option");
        ExtentReportListener.getTest().log(Status.INFO,"Click on the form button");
        ExtentReportListener.getTest().log(Status.INFO,"Enter the mail using the correct format ABC@kkk.com ");
        ExtentReportListener.getTest().log(Status.INFO,"Enter the mail ID using the incorrect format like  ABC@gmail.comcom, AC.com , A/io.com ");

        boolean IsArticleAdded=addarticlepage.MailFormatValidation(journalacro,articleid,artname,doinum,workflow,authorname,authormail);
        System.out.println(IsArticleAdded);
        Assert.assertFalse(IsArticleAdded,"Invalid format is accepting in mail id");

        ExtentReportListener.getTest().log(Status.INFO,"The Mail Id should be accepted");
    }


    @DataProvider(name = "Articlenameformat")
    public Object[][] ArticleFormat() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\AddArticle.xlsx",17);

    }

    @Test(priority =51,dataProvider ="Articlenameformat",description = "JMS-91 : Check the article name, author name, mailID’s with invalid inputs and invalid formats")
    public void VerifyAuthorNameFormat(String journalacro, String articleid, String artname, String doinum, String workflow,String authorname,String authormail) throws InterruptedException
    {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Enter the Article name valid input like Alphabets");
        ExtentReportListener.getTest().log(Status.INFO,"Enter the Article name with invalid input lik special characters ");
        ExtentReportListener.getTest().log(Status.INFO,"Enter the Author name valid input like Alphabets ");
        ExtentReportListener.getTest().log(Status.INFO,"Enter the Author name with invalid input lik special characters  ");

        String Authoralert=addarticlepage.AuthorNameFormatValidation(journalacro,articleid,artname,doinum,workflow,authorname,authormail);
        System.out.println();
        System.out.println(Authoralert);
        Assert.assertEquals(Authoralert,"Alphabets, Period(.), Hyphen(-), Apostrophe('), Accute, Space, Comma(for separate) only allowed","Article name alert is not showing");

        ExtentReportListener.getTest().log(Status.INFO,"The Author name should not be accepted");


    }


    @Test(priority =52,dataProvider ="Articlenameformat",description = "JMS-91 : Check the article name, author name, mailID’s with invalid inputs and invalid formats")
    public void VerifyArticleNameFormat(String journalacro, String articleid, String artname, String doinum, String workflow,String authorname,String authormail) throws InterruptedException
    {
        ExtentReportListener.getTest().assignCategory(cateogry);
        String Articlealert=addarticlepage.ArticleNameFormatValidation(journalacro,articleid,artname,doinum,workflow,authorname,authormail);
        System.out.println();
        System.out.println(Articlealert);
        Assert.assertEquals(Articlealert,"Alphabets, Period(.), Hyphen(-), Apostrophe('), Accute, Space, Comma(for separate) only allowed","Article name alert is not showing");


    }


    @Test(priority =53,dataProvider ="duplicatedata",description = "JMS-92 : DOI number and ensure it is indicated accordingly and further article can be added  ")
    public void VerifyDoiAlert(String journalacro, String articleid, String artname, String doinum, String workflow) throws InterruptedException
    {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,
                "Proceed to add article page through FORM option");
        ExtentReportListener.getTest().log(Status.INFO,"Try Adding a new article with existing DOI number and verify the indication");
        ExtentReportListener.getTest().log(Status.INFO,"Proceed with adding the article with Duplicate DOI");

        Boolean isvisible=addarticlepage.AddArticleWithDuplicateDoi(journalacro,articleid,artname,doinum,workflow);

        Assert.assertTrue(isvisible,"Duplicate Doi allowed to create article");
        ExtentReportListener.getTest().log(Status.INFO,"Article can be still added with duplicated DOI number");



    }

    @DataProvider(name = "DecimalPagetest")
    public Object[][] pages() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\AddArticle.xlsx",18);

    }


    @Test(priority =54,dataProvider ="DecimalPagetest",description = "JMS-94 : No. of pages - Alphabets & decimal inputs should be prevented ")
    public void VerifyDecimalPagesAlert(String journalacro, String articleid, String artname, String doinum, String workflow,String pages) throws InterruptedException
    {
        ExtentReportListener.getTest().assignCategory(cateogry);
        ExtentReportListener.getTest().log(Status.INFO,"Click on the Add article button");
        ExtentReportListener.getTest().log(Status.INFO,"Click on the form buttton");
        ExtentReportListener.getTest().log(Status.INFO,"Enter the alphabets , negative, and special character   at the numberial field");
        ExtentReportListener.getTest().log(Status.INFO,"Enter the Author name with invalid input lik special characters  ");

        Boolean isvisible=addarticlepage.DecimalPreventionOnPage(journalacro,articleid,artname,doinum,workflow,pages);

        Assert.assertFalse(isvisible,"Duplicate Doi allowed to create article");
        ExtentReportListener.getTest().log(Status.INFO,"No. of pages - Alphabets & decimal inputs should be prevented");



    }

    @Test(priority = 55,dataProvider = "addarticlewithoutfile",description = " JMS-196 : Toggle any checkboxes above for Tables,Figures - Version 1")
    public void VerifyChecklistToogle(String journalacro, String articleid, String artname, String doinum, String workflow) throws InterruptedException {
        ExtentReportListener.getTest().assignCategory(cateogry);

        Boolean ischecked = addarticlepage.VerifyToogleChecklist(journalacro, articleid, artname, doinum, workflow);
        System.out.println("ischecked"+ischecked);
        Assert.assertFalse(ischecked,"figure is still checked");



    }



    @DataProvider(name = "notes")
    public Object[][] availablenotes() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\AddArticle.xlsx", 19);
    }

    @Test(priority = 56,dataProvider = "notes",description = " JMS-196 : Toggle any checkboxes above for Tables,Figures - Version 1")
    public void VerifyNotesAvailableAtLaterStage(String journalacro, String articleid, String artname, String doinum, String workflow,String pmuname,String pmupass,String luname,String lupass,String notes) throws InterruptedException {
        ExtentReportListener.getTest().assignCategory(cateogry);

        Boolean isnotesavailable = addarticlepage.VerifyNotesAvailable(journalacro, articleid, artname, doinum, workflow,pmuname,pmupass,luname,lupass,notes);
        System.out.println("ischecked"+isnotesavailable);
       Assert.assertTrue(isnotesavailable,"Notes not available");


    }




    @Test(priority = 57,dataProvider = "notes",description = "JMS-185 : When any Additional files are removed, still Article addition can be possible")
    public void VerifyFileRemovalAddArticle(String journalacro, String articleid, String artname, String doinum, String workflow,String pmuname,String pmupass,String luname,String lupass,String notes) throws InterruptedException {
        ExtentReportListener.getTest().assignCategory(cateogry);
        Boolean isadded=addarticlepage.VerifyAddArticleAvailableForRemovalFile(journalacro, articleid, artname, doinum, workflow,pmuname,pmupass,luname,lupass,notes);
        Assert.assertTrue(isadded,"Without the AdditionalFiles article not added");

    }






















































    //file testcases















    @AfterMethod
    public void Afteralltest()
    {
        addarticlepage.reloadpage();
    }









}

