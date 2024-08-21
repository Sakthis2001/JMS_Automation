package tests;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.lms.listeners.ExtentReportListener;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExcelReader;
import utils.ExcelUtils;

import java.io.IOException;
import java.security.PublicKey;
import java.util.List;

import static utils.ExcelReader.ReadExcelData;

public class PreRequestTest extends BaseTest {

    @Test
    public void NavigatetoBaseIcon()
    {
             prerequestpage= homepage.navigatetocommonpage();
             prerequestpage.navigatetobaseicon();
    }

    @Test(priority = 1)
    public void AddPublisher() throws InterruptedException {


        List<Object[]> excelData = ExcelUtils.getExcelData("D:\\uploadtest\\AddArticle.xlsx",0);

        for (Object[] row : excelData) {
            if (row.length == 2) {
                String Pub_acro = row[0].toString();
                String pub_name = row[1].toString();
                prerequestpage.DoAddPub(Pub_acro,pub_name);
            }
            else{
                System.out.println("Row does not have expected numbers: " + row.length);
            }
        }
    }

    @Test(priority = 2)
    public void AddJournal() throws InterruptedException {


        List<Object[]> excelData = ExcelUtils.getExcelData("D:\\uploadtest\\AddArticle.xlsx",1);

                for (Object[] row : excelData) {
                    if (row.length == 3) {
                        String Pub = row[0].toString();
                        String J_acrm = row[1].toString();
                        String J_name = row[2].toString();
                        prerequestpage.DoAddJournal(Pub, J_acrm,J_name);
                    }
                    else{
                        System.out.println("Row does not have expected numbers: " + row.length);
                    }
                }

            }



    @DataProvider(name = "getgraphicsuserdata")
    public Object[][] getgraphicspublisherdata() throws IOException
    {
        return ReadExcelData(".//src//test//resources//files//Prerequest.xlsx",0);

    }

    @DataProvider(name = "duplicatedata")
    public Object[][] addduplicateArticle() throws IOException {
        return ExcelReader.ReadExcelData(".//src//test//resources//files//AddArticle.xlsx", 2);
    }

    @Test(priority =3,dataProvider ="duplicatedata", description = "Upon providing valid inputs, Article can be added - need to be listed")
    public void addarticle(String journalacro,String articleid,String artname,String doinum,String workflow) throws InterruptedException {



        prerequestpage= homepage.navigatetocommonpage();
        Boolean isadded= prerequestpage.AddArticleByMandatoryFields(journalacro,articleid,artname,doinum,workflow);
        Assert.assertTrue(isadded,"Article is not added with only the mandatory data");
        ExtentReportListener.getTest().log(Status.INFO, " article is added in article view ");

    }

    @Test(priority = 4,dataProvider ="getgraphicsuserdata")
    public void addUserFunctionality(String name,String employeeid,String designation,String email,String access,String gender,String departmentname,String role)
    {

        prerequestpage= homepage.navigatetocommonpage();
        prerequestpage.adduser(name,employeeid,designation,email,access,gender,departmentname,role);
    }






            @AfterMethod
            public void AfterAllTest()
            {
                prerequestpage.ReloadDashBoard();
            }





}



