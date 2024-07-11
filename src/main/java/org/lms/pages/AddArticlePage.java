package org.lms.pages;

import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddArticlePage {

    private Page page;
    FileChooser fileChooser;

    private  String baseicon="//img[@src='/jms/src/assets/GeneralIcons/shortcuts.svg']";
    private String addarticleicon="id=add_article";
   /* private String uploadform="//*[@alt='Option 1 (Upload)']";

    private String selectprioritydropdown="id=priority";
    private String selectpriority="//*[@id='priority']//following::p[text()='High']";
    private String selecttat="//label[text()='TAT']//following::div[1]";
    private String selecttatinput="//label[text()='TAT']//following::li[1]//following::p[1]";
    private String Doino="id=doi";
    private String  selectworkflowdropdown="id=workFlow";
    private String selectworkflow="//*[@id='workFlow']//following::p[text()='Fresh']";
    private String noofpages="//input[@id='numberOfPages']";
    private String cebypass="id=CE by-pass";*/
    private String form="//img[@alt='Option 2 (Form)']";
    private String Selectpubdropdown="id=publisher";
    private  String selectpublisher="//p[normalize-space(text())='GGD(EMS Press)']\n";
    private String articleidinput="id=articleID";
    private String authormail="id=authorMail";
    private String authorname="id=authorName";
    private String articlename="id=articleName";
    private String selectpriority="id=priority";
    private String selectpriorityopt="//*[@id='priority']//following::p[text()='High']";
    private String receivedate="id=receivedDate";
    private String reviseddate="id=revisedDate";
    private String Accepteddate="id=historyAccDate";
    private String selecttat="//label[text()='TAT']//following::div[1]";
    private String selecttatinput="//label[text()='TAT']//following::li[1]//following::p[1]";
    private String Doino="id=doi";
    private String workflowselection="//*[text()='Assign Workflow']";
    private String  selectgeneralworkflow="//*[@alt='General']";
    private String assignbutton="//*[text()='Assign']";


   // private String selectworkflowopt="//*[@id='workFlow']//following::p[text()='Fresh']";
    private String noofpages="//input[@id='numberOfPages']";
    private String cebypass="id=CE by-pass";
    private String articletype="//*[@placeholder='Enter Article Type...']";
    private String TATShow="//*[text()='Turn Around Time']//preceding-sibling::img";
    private String importtatfromjournal="//*[@title='Import TATs from Journal']";
    private String confirmimportfromjour="//*[contains(text(),'Import TAT')]//following::div//following::newbutton[contains(text(),'Yes')]";
    private String startdate="id=startDate";
    private String ChecklistSelectionShow="//*[text()='Checklist Selections']//preceding-sibling::img";

    private String OnOpenAccess="id=acpOpenAccess";



    private String fileupload="//*[@alt='Upload']";
    private String addnotes="//*[@alt='Add Notes']";
    private String Plzwwritehere="//*[@data-placeholder='Type here...']";
    private String AddNoteutton="//*[text()='Add Note']";
    private String addnotetoastclose="//*[text()='JMS - Add Notes']//following::span[1]";
    private String checklist="//*[text()='Check Lists']";
    private String figurechecklist="id=figures";
    private String checlistalert="//*[text()='Alert']//following::newbutton[text()='Yes']";
    private String checklisttoast="//*[text()='JMS - Check Lists']//following::span[1]";
    private String mailpreview="//*[text()='Preview']";
    private String tomail="//label[text()='To']//following::input[@id='category'][1]";
    private String  tomailluser="//p[normalize-space(text())='compuscriptrep@gmail.com']//preceding-sibling::input";
    private String  Acknowledgementtomailluser="//p[normalize-space(text())='latexam@gmail.com']//preceding-sibling::input";


    private String ccmail="//label[text()='Cc']//following::input[@id='category'][1]";
    private String ccmailuser="//p[normalize-space(text())='nirmala@pdmrindia.com']//preceding-sibling::input";
    private String Acknowlegeemtnsavemailbutton="//button[text()='Save Mail']";
    private String Acknowledgementyesalert="//*[text()='Are you sure to Save the mail on acknowledgement?']//following::newbutton[text()='Yes']";
    private String Acknowlegementtoastclose="//*[text()='JMS - Mail']//following::span[1]";
    private String notificationmail="//*[text()='Notification']";
    private String savenotificationmail="//*[text()='Save Mail']";
    private String notificationalert="//*[text()='Are you sure to Save the mail on notification?']//following::newbutton[text()='Yes']";
    private String notificationsuccesstoastmail="//*[text()='A mail will be triggered once the article is added successfully']//preceding::span[text()='×']";
    private String addarticlebutton="(//*[text()='Add Article'])[2]";



    public AddArticlePage(Page page)
    {
        this.page=page;
    }

    public void addarticlepage()
    {
        page.locator(baseicon).click();
        page.locator(addarticleicon).click();
        //page.locator(form).click();

    }

    public void reloadpage()
    {
        page.reload();
    }

    public void DoAddArticle() throws InterruptedException {

        LocalDate today = LocalDate.now();
        LocalDate tomarrow = today.plusDays(1);
        LocalDate DayOftomarrow = today.plusDays(2);


        String formattedDate = today.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String tomorrow = (today.plusDays(1)).format(DateTimeFormatter.ISO_DATE);
        String dayoftomorrow = (today.plusDays(2)).format(DateTimeFormatter.ISO_DATE);

        page.locator(form).click();
        page.locator(Selectpubdropdown).click();
        page.locator(selectpublisher).click();
        page.locator(articleidinput).fill("1900000");
        page.locator(authormail).fill("abc@gmail.com");
        page.locator(authorname).fill("Mahindra");
        page.locator(articlename).fill("Thor");
        page.locator(selectpriority).click();
        page.locator(selectpriorityopt).click();
        page.locator(receivedate).fill(formattedDate);
        page.locator(reviseddate).fill(tomorrow);
        page.locator(Accepteddate).fill(dayoftomorrow);
        page.locator(selecttat).click();
        page.locator(selecttatinput).click();
        page.locator(Doino).fill("2020202");
        page.locator(workflowselection).click();
        page.locator(selectgeneralworkflow).click();
        page.locator(assignbutton).click();
        page.locator(noofpages).fill("200");
        page.locator(articletype).fill("Research");
        page.locator(cebypass).click();
        page.locator(TATShow).click();
        page.locator(importtatfromjournal).click();
        page.locator(confirmimportfromjour).click();
        page.locator(ChecklistSelectionShow).click();
        page.locator(startdate).fill(formattedDate);
        page.locator(OnOpenAccess).click();
        fileChooser=page.waitForFileChooser(()->page.locator(fileupload).click());
        fileChooser.setFiles(Paths.get("GGD-805.zip"));
        page.locator(addnotes).click();

        page.locator(Plzwwritehere).fill("this particular article is from general workflow");
        page.locator(AddNoteutton).click();
        page.locator(addnotetoastclose).click();

        page.locator(mailpreview).click();
        page.locator(ccmail).click();
        page.locator(ccmailuser).click();
        page.locator(tomail).click();
        page.locator(tomailluser).click();
        page.locator(Acknowlegeemtnsavemailbutton).click();
        page.locator(Acknowledgementyesalert).click();
        page.locator(Acknowlegementtoastclose).click();
        page.locator(notificationmail).click();
        page.locator(ccmail).click();
        page.locator(ccmailuser).click();
        page.locator(tomail).click();
        page.locator(Acknowledgementtomailluser).click();
        page.locator(savenotificationmail).click();
        page.locator(notificationalert).click();
        page.locator(notificationsuccesstoastmail).click();
       // page.locator(addarticlebutton).click();











    }



      /*  public void AddArticleWithUpload()
        {

            page.locator(baseicon).click();
            page.locator(addarticleicon).click();
            fileChooser = page.waitForFileChooser(() -> page.locator(uploadform).click());

            fileChooser.setFiles(Paths.get("GGD-805.zip"));
            page.locator(articleidinput).fill("222222222");

            page.locator(selectprioritydropdown).click();
            page.locator(selectpriority).click();

            page.locator(selecttat).click();
            page.locator(selecttatinput).click();

            page.locator(selectworkflowdropdown).click();
            page.locator(selectworkflow).click();

            page.locator(noofpages).fill("200");

            page.locator(Doino).fill("23456789");

            page.locator(cebypass).click();
        }*/






}
