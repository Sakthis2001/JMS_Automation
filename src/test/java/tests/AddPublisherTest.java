package tests;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Page;
import org.lms.listeners.ExtentReportListener;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utils.ExcelReader;
import utils.ExcelUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static utils.ExcelReader.ReadExcelData;

@Listeners(ExtentReportListener.class)
public class AddPublisherTest extends BaseTest {

    private  String authorname="Sakthi";

    @BeforeMethod
    public void beforeallTest()
    {
        addpublisherpage=homepage.navigatetoaddpublisherpage();
        addpublisherpage.verifyAddPublisherIconIsClickable();

    }


    @AfterMethod
    public void adterall()
    {
        addpublisherpage.reloadpage();
    }

    @Test(priority = 0,description = "Verifying Navigating to the Add Publisher Page successfully")
    public void NavigateToAddPubIcon()
    {
        ExtentReportListener.getTest().assignCategory("Add Publisher");
        ExtentReportListener.getTest().assignAuthor(authorname);

        ExtentReportListener.getTest().log(Status.INFO,"Clicking the Base icon");
        ExtentReportListener.getTest().log(Status.INFO,"Clicking the add publisher icon");



        addpublisherpage.verifyAddPublisherIconIsClickable();
        ExtentReportListener.getTest().log(Status.INFO,"Navigated successfully to the addpublisher page");

    }

        @DataProvider(name = "getdata")
        public Object[][] getpublisherdata() throws IOException {
            return ReadExcelData(".//src//test//resources//files//addpublisher.xlsx",4);

        }

        @Test(priority = 1, dataProvider = "getdata",description = " JMS-118 : Add publisher with valid mandatory details - Version 2")
        public void AddPublisherWithCopyTatData(String acro,String pub) throws InterruptedException {
            ExtentReportListener.getTest().assignCategory("Add Publisher");
            ExtentReportListener.getTest().assignAuthor(authorname);
            ExtentReportListener.getTest().log(Status.INFO,"Enter all the input which are mandatory");
            ExtentReportListener.getTest().log(Status.INFO,"Click on 'ADD publisher'");
            ExtentReportListener.getTest().log(Status.INFO,"verifying publiher is added");
            ExtentReportListener.getTest().log(Status.INFO,"Verify each publisher's details from Edit-Publisher page\n" +
                    "\n" +
                    "Publisher Acronym \n" +
                    "Publisher Name \n" +
                    "Email \n" +
                    "Description\n" +
                    "On Board \n" +
                    "Publisher Location\n" +
                    "TAT\n" +
                    "Logo\n" +
                    "Guidelines \n" +
                    "Style template ");

            List<Object[]> excelData = ExcelUtils.getExcelData("D:\\uploadtest\\addpublisher.xlsx",0);


            //Iterate through the data and call the addpublisher method


            for (Object[] row : excelData) {
                if (row.length == 27) { // Ensure the row has the expected number of columns
                    String a = row[0].toString();
                    String b = row[1].toString();
                    String c = row[2].toString();
                    String d = row[3].toString();
                    String e = row[4].toString();
                    String f = row[5].toString();
                    String g = row[6].toString();
                    String h = row[7].toString();
                    String i = row[8].toString();
                    String j = row[9].toString();
                    String k = row[10].toString();
                    String l = row[11].toString();
                    String m = row[12].toString();
                    String n = row[13].toString();
                    String o = row[14].toString();
                    String p = row[15].toString();
                    String q = row[16].toString();
                    String r = row[17].toString();
                    String s = row[18].toString();
                    String t = row[19].toString();
                    String u = row[20].toString();
                    String v = row[21].toString();
                    String w = row[22].toString();
                    String x = row[23].toString();
                    String y = row[24].toString();
                    String z = row[25].toString();
                    String aa = row[26].toString();

                    List<String> pubmetadata = addpublisherpage.addpublisher(acro, pub, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, aa);
                    System.out.println("Publisher added: " + pubmetadata);
                    SoftAssert softAssert=new SoftAssert();
                    System.out.println(pubmetadata);
                    softAssert.assertEquals(pubmetadata.get(0),pubmetadata.get(0),"acro name seems wrong");
                    softAssert.assertEquals(pubmetadata.get(1),pubmetadata.get(1),"pub name seems wrong");
                    softAssert.assertEquals(pubmetadata.get(2),"sak@gmail.com","pub mail seems wrong");
                    softAssert.assertEquals(pubmetadata.get(3),"1","description name seems wrong");
                    softAssert.assertEquals(pubmetadata.get(4),"1", "location  seems wrong");
                    softAssert.assertAll();

                }
                else
                {
                    System.out.println("Row does not have the expected number of columns: " + row.length);
                }
            }
            ExtentReportListener.getTest().log(Status.INFO,"Publisher added successfully");
            ExtentReportListener.getTest().log(Status.INFO,"Details are added successfully");
        }



    @DataProvider(name = "getalldata")
    public Object[][] getallpublisherdata() throws IOException {
        return ReadExcelData(".//src//test//resources//files//addpublisher.xlsx",1);

    }



    @Test(priority = 2, dataProvider = "getalldata",description = "validate the AddPublisher Functionality by add all the TAT Data without copy")
    public void AddPublisherWithAllInputData(String a,String b,String c,String d,String e,String f,String g,String h,String i,String j,String k,String l,String m,String n,String o,String p,String q,String r,String s,String t,String u,String v,String w,String x,String y,String z,String aa,String bb,String cc,String dd,String ee,String ff,String gg,String hh,String ii,String jj,String kk,String ll,String mm,String nn,String oo,String pp,String qq,String rr,String ss) {
        ExtentReportListener.getTest().assignCategory("Add Publisher");
        ExtentReportListener.getTest().assignAuthor(authorname);
        ExtentReportListener.getTest().log(Status.INFO,"Enter all the input which are mandatory");
        ExtentReportListener.getTest().log(Status.INFO,"verifying publiher is added");



      String pubname=addpublisherpage.AddPublisherWithAllData(a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,aa,bb,cc,dd,ee,ff,gg,hh,ii,jj,kk,ll,mm,nn,oo,pp,qq,rr,ss);
     Assert.assertEquals(pubname,a,"publisher not added successfully");
        ExtentReportListener.getTest().log(Status.INFO,"Publisher added successfully");
    }


    @DataProvider(name = "addpub")
    public Object[][] addpub() throws IOException {
        return  ExcelReader.ReadExcelData (".//src//test//resources//files//addpublisher.xlsx",0);
    }





    @Test(priority =3,dataProvider = "addpub",description = "JMS-127:validate the all the FastTrack and General TAT values are equal  which is imported from general - version 3")
    public void VerifyAcronymCopyTatValue(String a,String b,String c,String d,String e,String f,String g,String h,String i,String j,String k,String l,String m,String n,String o,String p,String q,String r,String s,String t,String u,String v,String w,String x,String y,String z,String aa)
    {
        ExtentReportListener.getTest().assignCategory("Add Publisher");
        ExtentReportListener.getTest().assignAuthor(authorname);
        ExtentReportListener.getTest().log(Status.INFO,"Enter all the input which are mandatory");
        ExtentReportListener.getTest().log(Status.INFO,"click the copy tat icon");
        ExtentReportListener.getTest().log(Status.INFO,"verifing the Latexduedate for fasttrack which is imported from general");

         addpublisherpage.verify_Tat_values(a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,aa);

         SoftAssert sassert=new SoftAssert();

        sassert.assertEquals(addpublisherpage.f_latex_val,i,"value not equal");


        sassert.assertEquals(addpublisherpage.f_graph_val,j,"value not equal");
        sassert.assertEquals(addpublisherpage.f_pre_val,k,"value not equal");

        sassert.assertEquals(addpublisherpage.f_copy_edit_val,l,"value not equal");

        sassert.assertEquals(addpublisherpage.f_ts_val,m,"vale not equal");

        sassert.assertEquals(addpublisherpage.f_qc_val,n,"val not equal");


        try {
            sassert.assertAll();
            ExtentReportListener.getTest().log(Status.INFO,"Value  fetched successfully to Fast Track and also verification is wright!");

        } catch (AssertionError err) {
            // Log the assertion errors to the Extent Report
            ExtentReportListener.getTest().log(Status.FAIL, "Assertions failed: " +err.getMessage());
            ExtentReportListener.getTest().log(Status.INFO,"Value not fetched successfully to Fast Track and also verification is wrong!");

            // Optionally, you can rethrow the exception if you want the test to fail
            throw err;
        }
        ExtentReportListener.getTest().log(Status.INFO,"Values are fetched successfully to Fast Track and also values are equal");

    }





 @DataProvider(name = "getdecdata")
public Object[][] getdecdata() throws IOException {
    return ExcelReader.ReadExcelData(".//src//test//resources//files//addpublisher.xlsx",0);
}







    @DataProvider(name = "geterrordata")
    public Object[][] getallpublisher() throws IOException {
        return ReadExcelData(".//src//test//resources//files//addpublisher.xlsx",2);

    }


    @Test(priority =4,dataProvider = "geterrordata",description = "MS-119 : Verify Neither by Acronym nor by Pub-name, Publisher can be duplicated - Version 2")
    public void VerifyAlertIsShowingWhenAddDuplicateInPubAndAcro(String a,String b)
    {
        ExtentReportListener.getTest().assignCategory("Add Publisher");
        ExtentReportListener.getTest().assignAuthor(authorname);
        ExtentReportListener.getTest().log(Status.INFO,"verify duplicate alert is showing When add a duplicate pub");
        ExtentReportListener.getTest().log(Status.INFO,"Add a new publisher (Pub- B) with unique details (including a pub-acronym and pub-name)");
        ExtentReportListener.getTest().log(Status.INFO,"\t\n" +
                "Add another publisher (Pub - C) with the same acronym as the previously added publisher but a different pub-name");

        String error=addpublisherpage.VerifyPublisherNotDuplicate(a,b);
        System.out.println(error);
        Assert.assertEquals(error,"Publisher already exists","alert is not showing");
        ExtentReportListener.getTest().log(Status.INFO,"The system should display an error message indicating that the publisher name already exists and cannot be duplicated.");


    }





    @DataProvider(name = "getfilename")
    public Object[][] getallpublisherlog() throws IOException {
        return ReadExcelData(".//src//test//resources//files//addpublisher.xlsx",3);

    }

    @Test(priority =5,dataProvider = "getfilename",description = "JMS:222-validate  image is added after created a Publisher-version 2")
    public void verifyAddedPublisherLogo(String a,String b,String c)
    {
        ExtentReportListener.getTest().assignCategory("Add Publisher");
        ExtentReportListener.getTest().assignAuthor(authorname);
        ExtentReportListener.getTest().log(Status.INFO,"Adding publisher");
        ExtentReportListener.getTest().log(Status.INFO,"Verifing the uploaded logo is correctly visible in EditPub Page");


        String logoname=addpublisherpage.addpublisherwithlogo(a,b,c);
        System.out.println("File name is"+logoname);
        Assert.assertEquals(logoname,c,"Logofileincorrectorempty in the edit pub");
        ExtentReportListener.getTest().log(Status.INFO," logo is correctly Displayed in EditPub Page");


    }





   @Test(priority =6,description = "verify the update functionality")
    public void verifyupdatedpublisher()
    {
        ExtentReportListener.getTest().assignCategory("Add Publisher");
        ExtentReportListener.getTest().assignAuthor(authorname);
        ExtentReportListener.getTest().log(Status.INFO,"Publisher added successfully");
        ExtentReportListener.getTest().log(Status.INFO,"verifing the updated field in Edit publisher");
        LocalDate today = LocalDate.now();
        LocalDate tomarrow=today.plusDays(1);

        String formattedDate = today.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String tomorrow = (today.plusDays(1)).format(DateTimeFormatter.ISO_DATE);

        String changedDate=addpublisherpage.addpublisher("EP");

        Assert.assertEquals(changedDate,tomorrow,"Date not updated in Edit publisher");
        ExtentReportListener.getTest().log(Status.INFO,"Publisher details updated successfully");

    }

    @DataProvider
    public Object[][] verifylogo() throws IOException {
        return ExcelReader.ReadExcelData(".//src//test//resources//files//addpublisher.xlsx",5);
    }


    @Test(priority =7,description = "JMS-122:Change the logo and verify the change in Pub. view version-2")
    public void verifyupdatedlogo()
    {
        ExtentReportListener.getTest().assignCategory("Add Publisher");
        ExtentReportListener.getTest().assignAuthor(authorname);
        ExtentReportListener.getTest().log(Status.INFO,"Adding Publisher");
        ExtentReportListener.getTest().log(Status.INFO,"Updating the Logo");



        addpublisherpage.addpub("LT");
        String imagename= addpublisherpage.verifylog("LT","Ai.jpg");
       ExtentReportListener.getTest().assignCategory("Add Publisher");
       Assert.assertEquals(imagename,"Ai.jpg","reupload is not success");
       ExtentReportListener.getTest().log(Status.INFO,"Image reuploaded successfully and correctly viewed in the publisher view");

    }



    @Test(priority = 8,dependsOnMethods = "AddPublisherWithCopyTatData",description = "JMS-123:Verify the created publishers are available while creating the Journals - version 2")
    public void VerifyPubAvailInAddJournal()
    {
        ExtentReportListener.getTest().assignCategory("Add Publisher");
        ExtentReportListener.getTest().assignAuthor(authorname);

        ExtentReportListener.getTest().log(Status.INFO,"Pre Request -Add The Publisher");

        ExtentReportListener.getTest().log(Status.INFO,"Navigate to the JMS dashboard and click on the 'Add Journal' icon.");
        ExtentReportListener.getTest().log(Status.INFO,"Click on the publisher selection dropdown");
        ExtentReportListener.getTest().log(Status.INFO,"Check for the publisher from Pre-Condition");
        ExtentReportListener.getTest().log(Status.INFO,"Select the said publisher and proceed with creating journal");
        ExtentReportListener.getTest().log(Status.INFO,"All publisher are available fr journal creation");




        addpublisherpage.addpub("AT");
        List<String>publist =addpublisherpage.pubavailinjournal("AllTat");
        for(String pubname:publist)
        {
            if(pubname.equals("AllTat"))
            {
                System.out.println(pubname);
                Assert.assertEquals(pubname,"AllTat","pub not displayed");
                break;
            }
        }
        ExtentReportListener.getTest().log(Status.INFO,"Added pub is available While adding a journal");

    }
    @Test(priority =9,dataProvider = "addpub",description = "Need to ensure the inputs days in TAT in decimal should be allowed (0.8,0.9,1.2)")
    public void verifyTatacceptsDecimal(String a,String b,String c,String d,String e,String f,String g,String h,String i,String j,String k,String l,String m,String n,String o,String p,String q,String r,String s,String t,String u,String v,String w,String x,String y,String z,String aa)
    {
        ExtentReportListener.getTest().assignCategory("Add Publisher");
        ExtentReportListener.getTest().assignAuthor(authorname);
        String f_vl=addpublisherpage.verifyaddpub(a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,aa);
        ExtentReportListener.getTest().log(Status.INFO,"Verifing Tat Accepts decimal");

        Assert.assertEquals(f_vl,i,"Decimal value is not accepted ");
        ExtentReportListener.getTest().log(Status.INFO,"Decimal val accepted"+i);

    }

    @Test(priority =10,description = "JMS-131:Files added now should be in Latest files, unless moved to archive - version 3")
    public void VerifyFileInRecentUntilMoveTOArchieve()
    {
        ExtentReportListener.getTest().assignCategory("Add Publisher");
        ExtentReportListener.getTest().assignAuthor(authorname);
        String text=  addpublisherpage.checkrecentfiles();
        ExtentReportListener.getTest().log(Status.INFO,"verifing the uploaded files is showing in Recent files");
        Assert.assertEquals(text,"Latest Files","showing unexpected name:"+text);
        ExtentReportListener.getTest().log(Status.INFO,"uploaded files are showing in the Recent files");


    }


    @DataProvider(name = "getstyfilename")
    public Object[][] getstylefilename() throws IOException {
        return ExcelReader.ReadExcelData(".//src//test//resources//files//addpublisher.xlsx",6);
    }

    @Test(priority = 11,dataProvider = "getstyfilename",description = "JMS:132-Verify other than ‘.sty’ files can’t be uploaded in template -version2")
    public void VerifyStypeUploadRestriction(String a)
    {
        ExtentReportListener.getTest().assignCategory("Add Publisher");
        ExtentReportListener.getTest().assignAuthor(authorname);
        ExtentReportListener.getTest().log(Status.INFO,"verifing add pub with other than .sty file in sty template field");


        String styerrortext=addpublisherpage.verifystyformat(a);
        System.out.println(styerrortext);
        Assert.assertEquals(styerrortext,"Only files with .sty extension are allowed",styerrortext+"This format should not have to accept");
        ExtentReportListener.getTest().log(Status.INFO,"Other than .sty Extension is not accepted");


    }


    @DataProvider(name = "getguidelinefilename")
    public Object[][] getguidelinefilename() throws IOException {
        return ExcelReader.ReadExcelData(".//src//test//resources//files//addpublisher.xlsx",7);
    }



    @Test(priority = 12,dataProvider = "getguidelinefilename",description = "JMS:133-Verify only ‘doc,docx,pdf’ can be uploaded in guidelines document, other formats must not be allowed-version 2")
    public  void verifyguidelinesuploadverification(String a)
    {
        ExtentReportListener.getTest().assignCategory("Add Publisher");
        ExtentReportListener.getTest().assignAuthor(authorname);
        ExtentReportListener.getTest().log(Status.INFO,"Navigate to \"Add publisher\" page");
        ExtentReportListener.getTest().log(Status.INFO,"Navigate to the file upload section where drag and drop functionality is available.");
        ExtentReportListener.getTest().log(Status.INFO,"Try uploading a file format other than \"doc', 'docx', or 'pdf\" in the guidelines document area.");
        ExtentReportListener.getTest().log(Status.INFO,"Upload and verify that only \"doc,docx,pdf\" can upload in the gudieliness document");

        String actualMessage= addpublisherpage.verifyfuidelinesdocuploadverification( a);
        Assert.assertEquals(actualMessage,"Only files with  following extensions allowed .doc, .docx, .pdf", "File upload validation failed for: " + a);
        ExtentReportListener.getTest().log(Status.INFO,"The guidelines document section should only accept 'doc', 'docx', or 'pdf' files .,Alert is displayed");

    }


    @Test(priority = 13,description = "JMS-134 : Ensure there is no limits for no. of files uploaded for both template and Gd.Lines document -version 1")
    public  void verifyisdocumentUploadCanUploadMore() throws InterruptedException {
        ExtentReportListener.getTest().assignCategory("Add Publisher");
        ExtentReportListener.getTest().assignAuthor(authorname);
        ExtentReportListener.getTest().log(Status.INFO,"Adding publisher with more no of doc");

        int text=addpublisherpage.VerifyNoOfFileInTeamplateAndGuidelines();
        String count=Integer.toString(text);
        System.out.println(count);
        Assert.assertEquals(count,"8","count not equal some file is missing");
        ExtentReportListener.getTest().log(Status.INFO,"Adding publisher with more no of doc successfully");

    }

    @Test(priority = 14,description = "JMS-135:Ensure STY files can be viewed and modified in publisher view")
    public void verifyISStyDocIsUpdated()
    {
        ExtentReportListener.getTest().assignCategory("Add Publisher");
        ExtentReportListener.getTest().assignAuthor(authorname);
        ExtentReportListener.getTest().log(Status.INFO,"Add the publisher");
        ExtentReportListener.getTest().log(Status.INFO,"Updating the  styfile");
        ExtentReportListener.getTest().log(Status.INFO,"verifing sty file is updated");




        String text=addpublisherpage.editstyfiles();
        System.out.println(text);
        Assert.assertEquals(text,"Publisher Updated Successfully","not updated ");

    }

    @Test(priority =15,description = "Ensure any STY file should be in latest is mandatory. (Need confirmation as it is a template)")
  public void IsStyMandatoryAlertIsShowing()
  {
      ExtentReportListener.getTest().assignCategory("Add Publisher");
      ExtentReportListener.getTest().assignAuthor(authorname);
      ExtentReportListener.getTest().log(Status.INFO,"Adding publisher without the .sty files");
      ExtentReportListener.getTest().log(Status.INFO,"verifing styfile upload alert is showing");


      addpublisherpage.addpubconstant();
    String styerrortext=addpublisherpage.EnsureStyMandatory();
    System.out.println(styerrortext);
    Assert.assertEquals(styerrortext,"Before submit, please upload Latest Style Template.","StyUpload mandatory is not showing");


  }

  @DataProvider(name = "getfilesname")
  public  Object[][] getfiles() throws IOException {
      return ExcelReader.ReadExcelData(".//src//test//resources//files//addpublisher.xlsx",8);
  }

  @Test(priority = 16,dataProvider = "getfilesname",description =
  "JMS:138-Ensure Files can be moved back and forth from Latest to Archive. Also ensure while creating journal, current list must be fetched-version 2")
  public void ISArchiveListIsShowingCorrectFiles(String a,String b,String c,String d) throws InterruptedException {
      ExtentReportListener.getTest().assignCategory("Add Publisher");
      ExtentReportListener.getTest().assignAuthor(authorname);
      ExtentReportListener.getTest().log(Status.INFO,"Adding Publisher with archived files");
      ExtentReportListener.getTest().log(Status.INFO,"Verifing archived files are only showing in Edit Pub");


      System.out.println(a);
      System.out.println(b);
      System.out.println(c);
      System.out.println(d);
      addpublisherpage.EnsureArchiveAndListFiles(a,b,c,d);
      System.out.println(addpublisherpage.bool1);
     System.out.println(addpublisherpage.bool2);
      Assert.assertEquals(addpublisherpage.bool1,"Resume.pdf","Archived files is not showing"+a);
     Assert.assertEquals(addpublisherpage.bool2,"guidelines.docx","Archived files are not showing"+d);

      ExtentReportListener.getTest().log(Status.INFO,"Archived files showing correctly");
  }

    @DataProvider(name = "fname")
    public  Object[][] getfname() throws IOException {
        return ExcelReader.ReadExcelData(".//src//test//resources//files//addpublisher.xlsx",9);
    }

  @Test(priority = 17,dataProvider ="fname",description = "JMS-139:Add and move all files to archive, Adding / Updating publisher must NOT be allowed. Atleast any one file should be available at point of time to Add/Edit publisher - version 3")
  public void AddPubWithAtleastOneRecentFiles(String a,String b)
  {
      ExtentReportListener.getTest().assignCategory("Add Publisher");
      ExtentReportListener.getTest().assignAuthor(authorname);
      ExtentReportListener.getTest().log(Status.INFO,"Adding pub without the recent files");
      ExtentReportListener.getTest().log(Status.INFO,"verifing recentfile erro message is displayed");



      List<String> pubfilealert= addpublisherpage.AddPubWithAtleastOneRecentFiles(a,b);
      System.out.println(pubfilealert);
      SoftAssert softAssert=new SoftAssert();
      softAssert.assertEquals(pubfilealert.get(0),"Before submit, please upload Guidelines Style Template.","alert is not received" );
      softAssert.assertEquals(pubfilealert.get(1),"Before submit, please upload Latest Style Template.","alert is not received" );
      softAssert.assertEquals(pubfilealert.get(2),"Before submit, please upload Guidelines Style Template.","alert is not received" );
      softAssert.assertEquals(pubfilealert.get(3),"Before submit, please upload Guidelines Style Template.","alert is not received" );
      softAssert.assertAll();
     ExtentReportListener.getTest().log(Status.INFO,"Showing recent files alert");

  }

  @Test(priority =18,description = "JMS-124 : Ensure SFTP related parameters are not mandatory for publisher - Version 3")
  public void AddPubWithoutFTPDetails()
  {

      ExtentReportListener.getTest().assignCategory("Add Publisher");
      ExtentReportListener.getTest().assignAuthor(authorname);
      ExtentReportListener.getTest().log(Status.INFO,"Adding pub without the ftp related data");
      ExtentReportListener.getTest().log(Status.INFO,"Verifing publisher is added");



      String acro=addpublisherpage.AddPubWithoutFtp("f", "Ftp");
      Assert.assertEquals(acro,"f","Pub not added without the Ftp details");
      ExtentReportListener.getTest().log(Status.INFO,"Publisher Added Successfully Without the FTP Details");

  }

  @DataProvider(name="getftpartialltestdata")
  public Object[][] getftptestdata() throws IOException {
      return ReadExcelData(".//src//test//resources//files//addpublisher.xlsx",10);
  }

  @Test(priority = 19,dataProvider ="getftpartialltestdata" ,description = "JMS:125-Ensure among 4 SFTP parameters, ensure user should either fill all 4 details or leave out all 4 details. Partially filling should not be allowed to create publisher")
  public void AddPubWithPartiallData(String c,String d,String e,String f)
  {
      ExtentReportListener.getTest().assignCategory("Add Publisher");
      ExtentReportListener.getTest().assignAuthor(authorname);

      List<Object[]> excelData = ExcelUtils.getExcelData("D:\\uploadtest\\addpublisher.xlsx",11);



      //Iterate through the data and call the addpublisher method
      SoftAssert softAssert=new SoftAssert();



      for (Object[] row : excelData) {
          if (row.length ==2) { // Ensure the row has the expected number of columns
              String a = row[0].toString();
              String b = row[1].toString();

              List<String>text=addpublisherpage.AddPubWithPartiallFTPData(a, b, c, d, e, f);
              ExtentReportListener.getTest().log(Status.INFO,"Adding pub with ftp partially data");
              ExtentReportListener.getTest().log(Status.INFO,"verifing publisher is not added ");

              String actualacro=text.get(0);
              System.out.println(actualacro);


              softAssert.assertAll();
              softAssert.assertEquals(actualacro,"","Publisher added with partiall publisher");
             /* softAssert.assertEquals(actualacro,"","Publisher added with partiall publisher");
              softAssert.assertEquals(actualacro,"","Publisher added with partiall publisher");
              softAssert.assertEquals(actualacro,"","Publisher added with partiall publisher");*/



          } else
          {
              System.out.println("row count mismatch");
          }
      }
      softAssert.assertAll();
          ExtentReportListener.getTest().log(Status.INFO,"Publisher is not added with PatiallFtpData");

  }

  @Test(priority = 20,description = "JMS:128-After Copied, verify the TAT modification be possible- version 3")
  public void EditCopyTatValue()
  {
      ExtentReportListener.getTest().assignCategory("Add Publisher");
      ExtentReportListener.getTest().assignAuthor(authorname);
     List<String>edittatval =addpublisherpage.editcopytat("AM","Amplifier");


      ExtentReportListener.getTest().log(Status.INFO,"Click the copy TaT icon");
      ExtentReportListener.getTest().log(Status.INFO,"Change the fasttrack-Latexnorml value");
      ExtentReportListener.getTest().log(Status.INFO,"Change the fasttrack-preedit value");
      ExtentReportListener.getTest().log(Status.INFO,"Change the fasttrack-IssueQc value");
      ExtentReportListener.getTest().log(Status.INFO,"Add the Publisher");
      ExtentReportListener.getTest().log(Status.INFO,"Navigate to Managemenu ");
      ExtentReportListener.getTest().log(Status.INFO,"Navigate to Edit Publisher page");
      ExtentReportListener.getTest().log(Status.INFO,"Verifing values are updated");

     String f_latexupdatedval=edittatval.get(0);
      System.out.println(f_latexupdatedval);
     String f_preditupdatedval=edittatval.get(1);
      System.out.println(f_preditupdatedval);
     String f_issueqc=edittatval.get(2);
      System.out.println(f_issueqc);
     SoftAssert softAssert=new SoftAssert();

      softAssert.assertEquals(f_latexupdatedval,"2","f_latex value not updated");
      softAssert.assertEquals(f_preditupdatedval,"4","f_preedit value not updated");

      softAssert.assertEquals(f_issueqc,"3","f_issueqc value not updated");

      softAssert.assertAll();
      ExtentReportListener.getTest().log(Status.INFO,"All the values are updated");




  }

  @Test( priority =21,description = "JMS-126 : Editing on SFTP should be possible. - Version 3 ")
  public  void VerifyFtpUpdatedValue()
  {
      ExtentReportListener.getTest().assignCategory("Add Publisher");
      ExtentReportListener.getTest().assignAuthor("Sakthi");
      List<String> updatedftpval=addpublisherpage.editFtpvalue("bp","BrilliantPost");
      ExtentReportListener.getTest().log(Status.INFO,"PreCondition-Create a publisher with some SFTP parameters and verify from Edit publisher page\n" +
              "\n");
      ExtentReportListener.getTest().log(Status.INFO,"After preconditions is verified, Modify the SFTP parameters and update\n" +
              "\n" +
              "(Note: Provide a Valid SFTP parameters)");
      ExtentReportListener.getTest().log(Status.INFO,"Check again from Edit-Pub Page, whether the updation is done correctly");
      ExtentReportListener.getTest().log(Status.INFO,"Proceed with Add article via SFTP from this publisher and check the Auto-filled parameters are updated ones, Not older ones");


      SoftAssert softAssert=new SoftAssert();

      softAssert.assertEquals(updatedftpval.get(0),"140.23.45","ftphost value not updated");
      softAssert.assertEquals(updatedftpval.get(1),"ftp username","ftp username value not updated");
      softAssert.assertAll();
      ExtentReportListener.getTest().log(Status.INFO,"Updated parameters has been fetched into Auto-fill");

  }

    @Test( priority =22,description = "JMS-661 : Modify the publisher details and verify - Version 1")
    public void VerifyUpdatedPub() throws IOException
    {
        List<String> pubmail=addpublisherpage.verifyUpdatedPub();
        System.out.println(pubmail);
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(pubmail.get(0),"sakthi@pdmrindia.com","Pub mail not updated");
        softAssert.assertEquals(pubmail.get(1),"2","general fresh ts val not updated");
        softAssert.assertEquals(pubmail.get(2),"1","general fresh ts val not updated");
        softAssert.assertEquals(pubmail.get(3),"1","general fresh ts val not updated");
        softAssert.assertEquals(pubmail.get(4),"1","general fresh ts val not updated");
        softAssert.assertEquals(pubmail.get(5),"1","general fresh ts val not updated");
        softAssert.assertEquals(pubmail.get(6),"1","general fresh ts val not updated");
        softAssert.assertEquals(pubmail.get(7),"1","general fresh ts val not updated");
        softAssert.assertEquals(pubmail.get(8),"1","general fresh ts val not updated");
        softAssert.assertEquals(pubmail.get(9),"1","general fresh ts val not updated");
        softAssert.assertEquals(pubmail.get(10),"1","general fresh ts val not updated");
        softAssert.assertEquals(pubmail.get(11),"2","general fresh ts val not updated");
        softAssert.assertEquals(pubmail.get(12),"1","general fresh ts val not updated");
        softAssert.assertEquals(pubmail.get(13),"1","general fresh ts val not updated");
        softAssert.assertEquals(pubmail.get(14),"1","general fresh ts val not updated");
        softAssert.assertEquals(pubmail.get(15),"1","general fresh ts val not updated");
        softAssert.assertEquals(pubmail.get(16),"1","general fresh ts val not updated");
        softAssert.assertEquals(pubmail.get(17),"1","general fresh ts val not updated");
        softAssert.assertEquals(pubmail.get(18),"1","general fresh ts val not updated");
        softAssert.assertEquals(pubmail.get(19),"1","general fresh ts val not updated");
        softAssert.assertEquals(pubmail.get(20),"1","general fresh ts val not updated");
        softAssert.assertEquals(pubmail.get(21),"1","general fresh ts val not updated");
        softAssert.assertEquals(pubmail.get(22),"1","general fresh ts val not updated");
        softAssert.assertAll();

    }

   /* @DataProvider(name = "adddiffpub")
    public  Object[][] adddiffpub() throws IOException {
        return ExcelReader.ReadExcelData("D:\\uploadtest\\addpublisher.xlsx",11);
    }
    @Test(dataProvider ="adddiffpub" )
    public void EditAContent(String a,String b) throws InterruptedException {
        System.out.println(a);
        System.out.println(b);
        ExtentReportListener.getTest().assignCategory("Add Publisher");
        addpublisherpage.AddTwoDiffPub(a,b);


    }*/

    @Test(priority = 23,description = " JMS-120 : Edit any of the details of the publisher and verify the updation - Version 3")
    public void VerifyMailUpdate()
    {
        ExtentReportListener.getTest().assignCategory("Add Publisher");
        ExtentReportListener.getTest().assignAuthor(authorname);
        List<String>edittatval =addpublisherpage.verifyMailOtherupdate();
        System.out.println(edittatval);
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(edittatval.get(1),edittatval.get(0),"Mail is not updated");


    }





































}
