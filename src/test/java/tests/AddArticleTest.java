
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

    @Test(priority =22,dataProvider = "addarticledata",description ="JMS-197 : Save the checklist and reopen and verify it can be edited further and saved" )
    public void verifyIsCheckedInEditArticle(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {
       Boolean IsChecked=addarticlepage.IsCheckboxIsCheckedInEditArticle(journalacro, articleid, artname, doinum, workflow,pub,jour);
       Assert.assertTrue(IsChecked,"Can able to click  the checkbox in EditArticle");
        //Assert.assertTrue(ischecked,"On open access is not checked");
    }

    @Test(priority =23,dataProvider = "addarticledata",description =" JMS-199 : Change the higher level information like Journal title or Article title, verify the checklist reset" )
    public void verifyChecklistAfterChangeHighLvlInfo(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {
       Boolean ischecked=addarticlepage.VerifyChecklistAfterChangeHigherLevelInfo(journalacro, articleid, artname, doinum, workflow,pub,jour);
       Assert.assertFalse(ischecked,"Checklist is not resetted");
    }


    @Test(priority =24,dataProvider = "addarticledata",description ="JMS-201 : Clicking on ‘X’ - doesn’t saves the content and returns to Add article page -\n" )
    public void verifyChecklistclosefunctionality(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {
        Boolean ischecked=addarticlepage.ChecklistCloseButton(journalacro, articleid, artname, doinum, workflow,pub,jour);
        Assert.assertFalse(ischecked,"Checklist is not resetted");
    }



    @Test(priority =26,dataProvider = "addarticledata",description ="JMS-202 : Verify the checklist is raised as Login query correctly - verify the contents " )
    public void verifyQueryIsAdded(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {
        Boolean isadded=addarticlepage.IsQueryShowing(journalacro, articleid, artname, doinum, workflow,pub,jour);
        Assert.assertTrue(isadded,"Query is showing");
    }

    //TAt

    @DataProvider(name = "Tatdata")
    public Object[][] VerifyTat() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\AddArticle.xlsx",12);
    }

    @Test(priority =27,dataProvider ="Tatdata",description ="JMS-32 : Verify the TATs imported here is imported from Journal not from Publisher")
    public void verifyTatImportFromJournal(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour,String f_latex,String f_gra,String f_pre,String f_copy,String f_pag,String f_qc,String au_pag,String au_qc,String pe_pag,String pe_qc,String onlinepag,String onlineqc,String onlinexml,String issuepag,String issueqc,String printpag,String printqc,String print_xml ) throws InterruptedException {
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


    }

    @DataProvider(name = "ModifiedTatdata")
    public Object[][] VerifymodifiedTat() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\AddArticle.xlsx",13);
    }

    @Test(priority =28,dataProvider ="ModifiedTatdata",description ="JMS-36 : Modification of TAT after imported")
    public void verifyModifiedTat(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour,String f_latex,String f_gra,String f_pre,String f_copy,String f_pag,String f_qc,String au_pag,String au_qc,String pe_pag,String pe_qc,String onlinepag,String onlineqc,String onlinexml,String issuepag,String issueqc,String printpag,String printqc,String print_xml ) throws InterruptedException {
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

    }

    @Test(priority =29,dataProvider = "addarticledata",description ="JMS-202 : Verify the checklist is raised as Login query correctly - verify the contents " )
    public void verifyDueDateCalculations(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {
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
    }

    @Test(priority =29,dataProvider = "addarticledata",description ="JMS-202 : Verify the checklist is raised as Login query correctly - verify the contents " )
    public void VerifyStartDate(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {

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

    }



    @Test(priority =29,dataProvider = "addarticledata",description ="JMS-202 : Verify the checklist is raised as Login query correctly - verify the contents " )
    public void VerifyToogleBetweenAcknowandKnowledge(String journalacro, String articleid, String artname, String doinum, String workflow,String pub,String jour) throws InterruptedException
    {

        Boolean istoogled=addarticlepage.ToggleBetweenNotificationAndAcknowledgementMail(journalacro, articleid, artname, doinum, workflow,pub,jour);
        Assert.assertTrue(istoogled,"Toogle between ackno and knowledge is not toggled");

    }



































    //file testcases















    @AfterMethod
    public void Afteralltest()
    {
        addarticlepage.reloadpage();
    }









}

