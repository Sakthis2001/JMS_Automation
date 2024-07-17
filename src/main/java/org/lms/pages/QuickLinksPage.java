package org.lms.pages;

import com.microsoft.playwright.Page;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;



public class QuickLinksPage {

    private Page page;
    private String baseicon = "//img[@src='/jms/src/assets/GeneralIcons/shortcuts.svg']";
    private String addpubicon="id=add_publisher";
    private String addjouricon = "id=add_journal";
    private String addarticleicon="id=add_article";
    String username="//*[@type='text']";
    String password="//*[@type='password']";
    String logout="//p[text()='Logout']";


    public QuickLinksPage(Page page)
    {
        this.page = page;
    }



    public Boolean ensureThreeIcon() {

        page.locator(baseicon).click();
        Boolean uploadvisible=page.locator(addpubicon).isVisible();

        Boolean formvisible=page.locator(addjouricon).isVisible();

        Boolean clientftpvisible=page.locator(addarticleicon).isVisible();




        return uploadvisible && formvisible && clientftpvisible;
    }


    public List<Boolean> EnsureLoginUserHasArticleIcon(String uname,String upass)
    {

        page.locator(logout).click();
        page.locator(username).fill(uname);
        page.locator(password).fill(upass);
        page.keyboard().press("Enter");
        page.locator(baseicon).click();
        List<Boolean> visibile=new ArrayList<>();
        Boolean pubiconvisible=page.locator(addpubicon).isVisible();
        System.out.println(pubiconvisible);
        Boolean journaliconvisible= page.locator(addjouricon).isVisible();
        System.out.println(journaliconvisible);
        Boolean articleiconvisible=  page.locator(addarticleicon).isVisible();
        System.out.println(articleiconvisible);
        visibile.add(pubiconvisible);
        visibile.add(journaliconvisible);
        visibile.add(articleiconvisible);
        return visibile;
    }

    public Boolean EnsureQuickLinksnotAvailableForManagerLogin(String uname,String upass)
    {
        page.locator(logout).click();
        page.locator(username).fill(uname);
        page.locator(password).fill(upass);
        page.keyboard().press("Enter");
        return  page.locator(baseicon).isVisible();
    }

    public Boolean QuicklinksIsWorkingInFlowWise(String uname,String upass)
    {
        page.locator(logout).click();
        page.locator(username).fill(uname);
        page.locator(password).fill(upass);
        page.keyboard().press("Enter");
        page.locator(baseicon).click();
        page.locator(addpubicon).click();
        page.locator(baseicon).click();
        page.locator(addjouricon).click();
        page.locator(baseicon).click();
        page.locator(addarticleicon).click();
        return true;


    }



}
