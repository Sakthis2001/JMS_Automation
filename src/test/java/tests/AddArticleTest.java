
package tests;

import base.BaseTest;
import org.lms.listeners.ExtentReportListener;
import org.lms.pages.AddArticlePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(ExtentReportListener.class)
public class AddArticleTest extends BaseTest {



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

    @Test(priority =1,description = "Upon providing valid inputs, Article can be added - need to be listed")
    public void addarticle() throws InterruptedException {

        addarticlepage.DoAddArticle();
    }

    @AfterMethod
    public void Afteralltest()
    {
        addarticlepage.reloadpage();
    }










}

