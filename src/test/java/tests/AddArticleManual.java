
package tests;

import base.BaseTest;
import org.lms.listeners.ExtentReportListener;
import org.lms.pages.AddArticlePage;
import org.testng.annotations.*;


@Listeners(ExtentReportListener.class)
public class AddArticleManual extends BaseTest {



    @BeforeMethod
    public void beforeallTest()
    {
        addarticlemanualpage=homepage.navigatetoaddarticlemanualpage();
        addarticlemanualpage.addarticlepage();

    }

    @AfterMethod
    public void Afteralltest()
    {
        addarticlemanualpage.reloadpage();
    }

    @Parameters({"journalacro","articleid", "articlename","doi","workflow"})
    @Test(priority =0)
    public void AddArticleByManual(String journalacro,String articleid,String articlename,String doinum,String workflow) throws InterruptedException {
        addarticlemanualpage.DoAddArticle(journalacro,articleid,articlename,doinum,workflow);


    }








}

