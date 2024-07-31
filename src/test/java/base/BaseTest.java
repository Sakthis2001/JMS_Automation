package base;

import com.microsoft.playwright.Page;
import org.lms.listeners.*;
import org.lms.pagefactory.PageFactory;
import org.lms.pages.*;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    Page page;
    PageFactory pf;
     protected Properties prop;

   protected HomePage homepage;

   protected AddPublisherPage addpublisherpage;

   protected AddArticlePage addarticlepage;

   protected AddPublisherManualPage addPublishermanualPage;

   protected AddArticleManualPage addarticlemanualpage;

   protected PreRequestPage prerequestpage;
   protected QuickLinksPage quickLinksPage;

    protected LatexNormalizationPage latexNormalizationPage;


    @BeforeClass
   public void startbrwoser() throws IOException {
       pf=new PageFactory();
       prop=pf.initprop();
       page=pf.initbrowser(prop);
       homepage=new HomePage(page);
       ExtentReportListener.setPage(page);
             // page.context().tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true));

   }





   @AfterClass
   public void teardown()
   {

    //   page.context().tracing().stopChunk(new Tracing.StopChunkOptions().setPath(Paths.get("trace.zip")));

       page.context().browser().close();
   }



}
