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
    private String viewtype = "id=select_view";
    private String articleview = "//*[text()='Articles View']";
    private String pubview = "//*[@value='Publisher View']";
    private String Journview = "//*[@for='Journals View']";


    private String managemenu = "//div[@id='root']//following::p[text()='Manage']";
    public QuickLinksPage(Page page)
    {
        this.page = page;
    }

    public void reload()
    {
        page.reload();
    }

    public Boolean ensureThreeIcon() throws InterruptedException {

        page.locator(baseicon).click();
        Boolean uploadvisible=page.locator(addpubicon).isVisible();
        Boolean formvisible=page.locator(addjouricon).isVisible();
        Boolean clientftpvisible=page.locator(addarticleicon).isVisible();
        page.locator(managemenu).click();

        Boolean pubdit=page.waitForSelector("//*[@data-icon='ellipsis'][1]").isVisible();

        page.locator(viewtype).click();
        page.locator(Journview).click();
        Boolean jourdit=page.waitForSelector("//*[@data-icon='ellipsis'][1]").isVisible();

        page.locator(viewtype).click();
        page.locator(articleview).click();
        Boolean artedit=page.waitForSelector("//*[@data-icon='pencil'][1]").isVisible();

        return uploadvisible && formvisible && clientftpvisible&&pubdit&&jourdit&&artedit;
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
        page.locator(managemenu).click();


        page.locator(viewtype).click();
        page.locator(pubview).click();
        Boolean pubedit=page.locator("//*[@data-icon='pencil'][1]").isVisible();

        page.locator(viewtype).click();
        page.locator(Journview).click();
        Boolean journaledit=page.locator("//*[@data-icon='pencil'][1]").isVisible();

        page.locator(viewtype).click();
        page.locator(articleview).click();

        Boolean articleedit=page.locator("//*[@data-icon='pencil'][1]").isVisible();
        visibile.add(pubiconvisible);
        visibile.add(journaliconvisible);
        visibile.add(articleiconvisible);
        visibile.add(pubedit);
        visibile.add(journaledit);
        visibile.add(articleedit);
        return visibile;
    }

    public Boolean EnsureQuickLinksnotAvailableForManagerLogin(String uname,String upass) throws InterruptedException {
        page.locator(logout).click();
        page.locator(username).fill(uname);
        page.locator(password).fill(upass);

        page.keyboard().press("Enter");
        Thread.sleep(3000);
        List<Object> val=new ArrayList<>();
        return  page.locator(baseicon).isVisible();
    }

    public Boolean QuicklinksIsWorkingInFlowWise(String pmuname,String pmupass,String loginuname,String loginupass)
    {
        page.locator(logout).click();
        page.locator(username).fill(pmuname);
        page.locator(password).fill(pmupass);
        page.keyboard().press("Enter");
        page.locator(baseicon).click();
        page.locator(addpubicon).click();
        page.locator(baseicon).click();
        page.locator(addjouricon).click();
        page.locator(baseicon).click();
        page.locator(addarticleicon).click();
        page.locator(logout).click();
        page.locator(username).fill(loginuname);
        page.locator(password).fill(loginupass);
        page.keyboard().press("Enter");
        page.locator(baseicon).click();
        page.locator(addarticleicon).click();

        return true;


    }



}
