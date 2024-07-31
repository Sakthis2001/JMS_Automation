package org.lms.pages;

import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddArticleManualPage {

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
    private  String checkall="//div[text()='Check All']";
    private String Checklistsubmitbutton="//button[text()='Submit CheckList']";



    public AddArticleManualPage(Page page)
    {
        this.page=page;
    }

    public void addarticlepage()
    {
        page.locator(baseicon).click();
        page.locator(addarticleicon).click();
        //page.locator(form).click();

    }

    public void uploadfiles() {
        fileChooser = page.waitForFileChooser(() -> page.locator(fileupload).click());
        fileChooser.setFiles(Paths.get("GGD-805.zip"));



    }

    public void reloadpage()
    {
        page.reload();
    }

    public void checklist()
    {
        page.locator(checklist).click();
        page.locator(figurechecklist).click();
       page.locator(Checklistsubmitbutton).click();
        page.locator(checlistalert).click();
        page.locator(checklisttoast).click();
    }
    public void AddNotes()
    {
        page.locator(addnotes).click();

        page.locator(Plzwwritehere).fill("this particular article is from general workflow");
        page.locator(AddNoteutton).click();
        page.locator(addnotetoastclose).click();
    }

    public void ArticleMail()
    {
        page.locator(mailpreview).click();
        page.locator(ccmail).click();
        page.locator(checkall).click();
        page.locator(tomail).click();
        page.locator(checkall).click();
        page.locator(Acknowlegeemtnsavemailbutton).click();
        page.locator(Acknowledgementyesalert).click();
        page.locator(Acknowlegementtoastclose).click();
        page.locator(notificationmail).click();
        page.locator(ccmail).click();
        page.locator(checkall).click();
        page.locator(tomail).click();
        page.locator(checkall).click();
        page.locator(savenotificationmail).click();
        page.locator(notificationalert).click();
        page.locator(notificationsuccesstoastmail).click();
    }
    public void DoAddArticle(String journalacro,String articleid,String name,String doinum,String workflow) throws InterruptedException {
        LocalDate today = LocalDate.now();
        LocalDate tomarrow = today.plusDays(1);
        LocalDate DayOftomarrow = today.plusDays(2);


        String formattedDate = today.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String tomorrow = (today.plusDays(1)).format(DateTimeFormatter.ISO_DATE);
        String dayoftomorrow = (today.plusDays(2)).format(DateTimeFormatter.ISO_DATE);

        String arttimeid = String.valueOf(System.currentTimeMillis());
        System.out.println(arttimeid);
        int doi=1;
        long doinumber=Long.parseLong(arttimeid);
        long doival=doi+doinumber;
        String doivalue=String.valueOf(doival);


        page.locator(form).click();
        page.locator(Selectpubdropdown).click();
        page.locator("//p[normalize-space(text())='"+journalacro+"']").click();
        page.locator(articleidinput).fill(arttimeid);
        page.locator(authormail).fill("abc@gmail.com");
        page.locator(authorname).fill("Mahindra");
        page.locator(articlename).fill(name);
        page.locator(selectpriority).click();
        page.locator(selectpriorityopt).click();
        page.locator(receivedate).fill(formattedDate);
        page.locator(reviseddate).fill(tomorrow);
        page.locator(Accepteddate).fill(dayoftomorrow);
        page.locator(selecttat).click();
        page.locator(selecttatinput).click();
        page.locator(Doino).fill(doivalue);
        page.locator(workflowselection).click();
        page.locator("//*[@alt='"+workflow+"']").click();
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
        uploadfiles();
        AddNotes();
        checklist();
        ArticleMail();
        // page.locator(addarticlebutton).click();
        Thread.sleep(10000000);

    }



}
