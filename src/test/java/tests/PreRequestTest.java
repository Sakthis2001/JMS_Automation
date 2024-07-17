package tests;

import base.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.ExcelUtils;

import java.security.PublicKey;
import java.util.List;

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


            @AfterMethod
            public void AfterAllTest()
            {
                prerequestpage.ReloadDashBoard();
            }


}



