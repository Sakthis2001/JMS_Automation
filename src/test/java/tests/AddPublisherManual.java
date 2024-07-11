package tests;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.lms.listeners.ExtentReportListener;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.ExcelUtils;

import java.io.IOException;
import java.util.List;

import static utils.ExcelReader.ReadExcelData;
@Listeners(ExtentReportListener.class)
public class AddPublisherManual  extends BaseTest {

    @BeforeMethod
    public void beforeallTest()
    {
        addPublishermanualPage=homepage.navigatetoaddpublishermanualpage();
       // addPublishermanualPage.verifyAddPublisherIconIsClickable();

    }




    @Parameters({"acro", "pub"})
    @Test()
    public void AddPublisherWithCopyTatData(String acro,String pub) throws InterruptedException {
        ExtentReportListener.getTest().assignCategory("Add Publisher");
        ExtentReportListener.getTest().log(Status.INFO,"Enter all the input which are mandatory");
        ExtentReportListener.getTest().log(Status.INFO,"verifying publiher is added");



        addPublishermanualPage.doaddpub(acro, pub);


        ExtentReportListener.getTest().log(Status.INFO,"Publisher added successfully");
    }


}
