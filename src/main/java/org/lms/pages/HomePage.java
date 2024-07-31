package org.lms.pages;

import com.microsoft.playwright.Page;
import static  com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HomePage {

    private Page page;

    private String baseicon = "//img[@src='/jms/src/assets/GeneralIcons/shortcuts.svg']";
    private String addpubicon="id=add_publisher";



    public HomePage(Page page) {
        this.page = page;
    }

    public String verifyurl() {
        return page.url();
    }

    public String verifytitle() {
        return page.title();
    }


    public boolean  isbaseicondisplayed() {

        assertThat(page.locator(baseicon)).isAttached();
       return page.locator(baseicon).isVisible();


    }


    public AddPublisherPage navigatetoaddpublisherpage() {

        page.url();

        return new AddPublisherPage(page);
    }

    public AddPublisherManualPage navigatetoaddpublishermanualpage() {

        page.url();

        return new AddPublisherManualPage(page);
    }

    public AddArticleManualPage navigatetoaddarticlemanualpage() {

        page.url();

        return new AddArticleManualPage(page);
    }







    public AddArticlePage navigatetoaddarticlepage() {

        page.url();

        return new AddArticlePage(page);
    }

    public PreRequestPage navigatetocommonpage() {

        page.url();

        return new PreRequestPage(page);


    }

    public QuickLinksPage navigatetoquickPage()
    {
        page.url();
        return new QuickLinksPage(page);
    }

    public LatexNormalizationPage navigatetoLatex()
    {
        page.url();
        return new LatexNormalizationPage(page);
    }


}
