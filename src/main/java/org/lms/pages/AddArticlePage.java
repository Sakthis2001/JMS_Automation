package org.lms.pages;

import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AddArticlePage {

    private Page page;
    FileChooser fileChooser;

    String username="//*[@type='text']";
    String password="//*[@type='password']";
    String logout="//p[text()='Logout']";


    private String baseicon = "//img[@src='/jms/src/assets/GeneralIcons/shortcuts.svg']";
    private String addpubicon = "id=add_publisher";


    private String pub_acronym = "//input[@data-testid='publisher-acronym']";
    private String pub_name_textbox = "//input[@data-testid='publisher-name']";
    private String pub_mail_textbox = "//input[@data-testid='email-account']";
    private String desc_inputbox = "//*[@name='description']";
    private String selectdateinput = "//input[@type='date']";
    private String pub_location = "//input[@data-testid='publisher-location']";
    private String ftpusername = "//input[@data-testid='ftp-user-name']";
    private String ftppassword = "//input[@data-testid='ftp-password']";
    private String ftp_initial_directory = "//input[@data-testid='ftp-initial-directory']";
    private String daysforlatexnormalization = "(//h2[text()='General']//following::input[@data-testid='days-for-latex-normalization'])[1]";
    private String daysforgraphics = "(//h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-graphics'])[1]";
    private String daysforPreediting = "(//h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-pre-editing'])[1]";
    private String daysforcopyediting = "(//h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-copyediting'])[1]";
    private String daysfortypesettings = "(//h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-typesetting'])[1]";
    private String daysforqc = "(//h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-qc'])[1]";
    private String daysforaupag = "(//h2[text()='General']//following::h3[text()='TAT for AU Revises ']//following::input[@data-testid='number-of-days-for-pagination'])[1]";
    private String daysforauqc = "(//h2[text()='General']//following::h3[text()='TAT for AU Revises ']//following::input[@data-testid='number-of-days-for-qc'])[1]";
    private String daysforpepag = "(//h2[text()='General']//following::h3[text()=' TAT for PE Revises ']//following::input[@data-testid='number-of-days-for-pagination'])[1]";
    private String daysforpeqc = "(//h2[text()='General']//following::h3[text()=' TAT for PE Revises ']//following::input[@data-testid='number-of-days-for-qc'])[1]";
    private String daysforonlinepag = "(//h2[text()='General']//following::h3[text()=' TAT for Online First ']//following::input[@data-testid='hours-or-days-for-Pagination'])[1]";
    private String daysforonlineqc = "(//h2[text()='General']//following::h3[text()=' TAT for Online First ']//following::input[@data-testid='hours-or-days-for-QC'])[1]";
    private String daysforonlinexml = "(//h2[text()='General']//following::h3[text()=' TAT for Online First ']//following::input[@data-testid='hours-days-for-xml'])[1]";
    private String IssuePag = "(//h2[text()='General']//following::h3[text()=' TAT for Issue']//following::input[@data-testid='number-of-days-for-pagination'])[1]";
    private String IssueQC = "(//h2[text()='General']//following::h3[text()=' TAT for Issue']//following::input[@data-testid='number-of-days-for-qc'])[1]";
    private String printpag = "(//h2[text()='General']//following::h3[text()=' TAT for Print/Web ']//following::input[@data-testid='number-of-days-for-pagination'])[1]";
    private String printQC = "(//h2[text()='General']//following::h3[text()=' TAT for Print/Web ']//following::input[@data-testid='number-of-days-for-qc'])[1]";
    private String printxml = "(//h2[text()='General']//following::h3[text()=' TAT for Print/Web ']//following::input[@data-testid='number-of-days-for-xml'])[1]";
    private String addbutton = "//*[@data-testid='submit-button']";

    private String Imageuploadbutton = "//button[text()='Upload Image']";

    private String styletemplate = "//*[@id='styles-upload']//preceding::div[1]";

    private String guidelinesdoc = "//*[@id='guidelines-upload']//preceding::div[1]";
    private String CopyTat = "//*[@title='Replicate values from General to Fasttrack']";
    private String CopyTatConfirm = "//*[text()='Yes']";
    private String managemenu = "//div[@id='root']//following::p[text()='Manage']";

    //webelement for fasttrack

    private String f_LatexNormal = "//h2[text()='Fasttrack']//following::h3[text()='TAT for First Proof']//following::input[@data-testid='days-for-latex-normalization']";
    private String f_graphics = "//h2[text()='Fasttrack']//following::h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-graphics']";
    private String f_preedit = "//h2[text()='Fasttrack']//following::h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-pre-editing']";
    private String f_copyedit = "//h2[text()='Fasttrack']//following::h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-copyediting']";

    private String f_ts = "//h2[text()='Fasttrack']//following::h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-typesetting']";
    private String f_qc = "(//h2[text()='Fasttrack']//following::h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-qc'][1])";
    private String f_au_pag = "//h2[text()='Fasttrack']//following::h3[text()='TAT for AU Revises ']//following::input[@data-testid='number-of-days-for-pagination'][1]";
    private String f_au_qc = "//h2[text()='Fasttrack']//following::h3[text()='TAT for AU Revises ']//following::input[@data-testid='number-of-days-for-qc'][1]";
    private String f_pe_pag = "//h2[text()='Fasttrack']//following::h3[text()=' TAT for PE Revises ']//following::input[@data-testid='number-of-days-for-pagination'][1]";
    private String f_pe_qc = "//h2[text()='Fasttrack']//following::h3[text()=' TAT for PE Revises ']//following::input[@data-testid='number-of-days-for-qc'][1]";
    private String f_o_pag = "//h2[text()='Fasttrack']//following::h3[text()=' TAT for Online First ']//following::input[@data-testid='hours-or-days-for-Pagination']";
    private String f_o_qc = "//h2[text()='Fasttrack']//following::h3[text()=' TAT for Online First ']//following::input[@data-testid='hours-or-days-for-QC']";
    private String f_o_xml = "//h2[text()='Fasttrack']//following::h3[text()=' TAT for Online First ']//following::input[@data-testid='hours-days-for-xml']";
    private String f_iss_pag = "//h2[text()='Fasttrack']//following::h3[text()=' TAT for Issue']//following::input[@data-testid='number-of-days-for-pagination'][1]";
    private String f_iss_qc = "//h2[text()='Fasttrack']//following::h3[text()=' TAT for Issue']//following::input[@data-testid='number-of-days-for-qc'][1]";
    private String f_priweb_pag = "//h2[text()='Fasttrack']//following::h3[text()=' TAT for Print/Web ']//following::input[@data-testid='number-of-days-for-pagination'][1]";
    private String f_printweb_qc = "//h2[text()='Fasttrack']//following::h3[text()=' TAT for Print/Web ']//following::input[@data-testid='number-of-days-for-qc']";
    private String f_printweb_xml = "//h2[text()='Fasttrack']//following::h3[text()=' TAT for Print/Web ']//following::input[@data-testid='number-of-days-for-xml']";
    private String addjournalicon = "id=add_journal";
    private String verifyerrormsgforpub = "//*[text()='JMS - Add Publisher']//following::div[2]";
    private String updtaealerttext = "//*[text()='JMS - Update Publisher']//following::span[1]";
    private String addalertclose = "//h2[text()='JMS - Add Publisher']//following::span[1]";
    private String updatealertclose = "//h2[text()='JMS - Update Publisher']//following::span[1]";
    private String uploadupdatealert = "//*[text()='JMS - Upload']//following::span[text()='×']";

    private String verifypubforlog = "//th[text()='k']";
    private String viewtype = "";
    private String pubviewtype = "";
    private String updatebutton = "//button[text()='Update Publisher']";
    private String updatealert = "//h2[text()='JMS - Update Publisher']//following::span[1]";
    private String reuploadbutton = "//*[text()='Reupload']";
    private String journal_pub_drop = "id=publisher";
    private String journal_pub_list = "//*[@id='publisher']//following::li";
    public String f_latex_val;
    public String f_graph_val;
    public String f_pre_val;
    public String f_copy_edit_val;
    public String f_ts_val;
    public String f_qc_val;
    public String style_error_text = "//h2[text()='JMS - File Upload Restriction']//following::div[2]";
    public String getStyle_error_alert = "//h2[text()='JMS - File Upload Restriction']//following::span[1]";
    public String CountofGuideLinesFile = "//h2[text()='Latest Files']//following::li//following::p";
    public String styuploaderrormsg = "//*[text()='JMS - Upload']//following::div[1]";
    public String guidestyleerroralert = "//h2[text()='JMS - Upload']//following::div[2]";
    public String editstyshowfielddropdown = "(//h2[text()='Latest Files']//following-sibling::img[@title='Show'])[1]";
    private String removestyfiles = "//p[text()='styledoc.sty']";
    private String removefileclose = "//p[text()='styledoc.sty']//following::img[@title='Remove']";
    private String reovefileyes = "//*[text()='Remove File']//following::newbutton[text()='Yes']";
    private String ftphost = "//input[@data-testid='ftp-host']";
    private String StyShow="(//*[text()='Latest Files']//following::img[@title='Show'])[1]";


    //private  String baseicon="//img[@src='/jms/src/assets/GeneralIcons/shortcuts.svg']";
    private String addarticleicon="id=add_article";
    private String UploadLabel="//label[text()='Upload']";
    private String formLabel="//label[text()='Form']";
    private String clientftplabel="//label[text()='Client FTP']";
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
  private String addarticlealert= "//*[text()='JMS - Add Article']//following::span[1]";
  private String selectview="id=select_view";
  private String journalsview="//*[@id='select_view']//following::li//following::p[text()='Articles View']";






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

   /* public void DoAddArticle() {

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











    }*/



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


    public Boolean ensureThreeOption() throws InterruptedException {
       Boolean uploadvisible=page.locator(UploadLabel).isVisible();
        System.out.println(uploadvisible);
        Boolean formvisible=page.locator(formLabel).isVisible();
        System.out.println(formvisible);
        Boolean clientftpvisible=page.locator(clientftplabel).isVisible();
        System.out.println(clientftpvisible);



        return uploadvisible && formvisible && clientftpvisible;


    }

    public void uploadfiles()
    {
        fileChooser = page.waitForFileChooser(() -> page.locator(fileupload).click());
        fileChooser.setFiles(Paths.get("GGD-805.zip"));

    }





    public void DoAddArticle(String journalacro,String articleid,String artname,String doinum,String workflow) {
        page.locator(baseicon).click();

        assertThat(page.locator(addarticleicon)).isVisible();
        page.locator(addarticleicon).click();
        page.locator(form).click();

        LocalDate today = LocalDate.now();
        LocalDate tomarrow = today.plusDays(1);
        LocalDate DayOftomarrow = today.plusDays(2);


        String formattedDate = today.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String tomorrow = (today.plusDays(1)).format(DateTimeFormatter.ISO_DATE);
        String dayoftomorrow = (today.plusDays(2)).format(DateTimeFormatter.ISO_DATE);


        page.locator(Selectpubdropdown).click();
        page.locator("//p[normalize-space(text())='"+journalacro+"']").click();
        page.locator(articleidinput).fill(articleid);
        page.locator(authormail).fill("abc@gmail.com");
        page.locator(authorname).fill("Mahindra");
        page.locator(articlename).fill(artname);
        page.locator(selectpriority).click();
        page.locator(selectpriorityopt).click();
        page.locator(receivedate).fill(formattedDate);
        page.locator(reviseddate).fill(tomorrow);
        page.locator(Accepteddate).fill(dayoftomorrow);
        page.locator(selecttat).click();
        page.locator(selecttatinput).click();
        page.locator(Doino).fill(doinum);
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

        page.locator(addnotes).click();

        page.locator(Plzwwritehere).fill("this particular article is from general workflow");
        page.locator(AddNoteutton).click();
        page.locator(addnotetoastclose).click();

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
//page.locator(checkall).click();
        page.locator(checklist).click();
        page.locator(figurechecklist).click();
        page.locator(Checklistsubmitbutton).click();
        page.locator(checlistalert).click();
        page.locator(checklisttoast).click();




    }

    public Boolean addarticleacess(String journalacro,String articleid,String artname,String doinum,String workflow,String uname,String upass,String jacrm,String pubname)
    {
        page.locator(logout).click();
        page.locator(username).fill(uname);
        page.locator(password).fill(upass);
        page.keyboard().press("Enter");

        DoAddArticle(journalacro,articleid,artname,doinum,workflow);
        page.locator(addarticlebutton).click();
        page.locator(addarticlealert).click();
        page.locator(managemenu).click();
        page.locator(selectview).click();
        page.locator(journalsview).click();
   return page.locator("//*[text()='"+pubname+"']//following::th[text()='The Tenat']").isVisible();
        //page.locator()

    }

    public void AddArticleByMandatoryFields(String journalacro,String articleid,String artname,String doinum,String workflow)
    {
        DoAddArticle(journalacro,articleid,artname,doinum,workflow) ;
        page.locator(addarticlebutton).click();
        page.locator(addarticlealert).click();
        page.locator(managemenu).click();

    }

    public String ensureduplicationarticle(String journalacro,String articleid,String artname,String doinum,String workflow)
    {
        page.locator(baseicon).click();
        page.locator(addarticleicon).click();
        page.locator(form).click();

        LocalDate today = LocalDate.now();
        LocalDate tomarrow = today.plusDays(1);
        LocalDate DayOftomarrow = today.plusDays(2);


        String formattedDate = today.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String tomorrow = (today.plusDays(1)).format(DateTimeFormatter.ISO_DATE);
        String dayoftomorrow = (today.plusDays(2)).format(DateTimeFormatter.ISO_DATE);


        page.locator(Selectpubdropdown).click();
        page.locator("//p[normalize-space(text())='"+journalacro+"']").click();
        page.locator(articleidinput).fill(articleid);
        page.locator(authormail).fill("abc@gmail.com");
        page.locator(authorname).fill("Mahindra");
        page.locator(articlename).fill(artname);
        assertThat(page.locator("//*[text()='JMS - Add Article']//following::div[text()='Article ID("+articleid+") already exists!']")).isVisible();
        String erromsg=page.locator("//*[text()='JMS - Add Article']//following::div[text()='Article ID("+articleid+") already exists!']").textContent();
        page.locator(addarticlealert).click();
        return erromsg;



    }


    public void verifyUnauthorizedUserAddArticle(String uname,String upass)
    {
        page.locator(logout).click();
        page.locator(username).fill(uname);
        page.locator(password).fill(upass);
        page.locator(baseicon).click();
        assertThat(page.locator(addarticleicon)).isVisible();
    }
    public void doaddpub(String acro, String pub, String c, String d, String e, String f, String g, String h, String i, String j, String k, String l, String m, String n, String o, String p, String q, String r, String s, String t, String u, String v, String w, String x, String y, String z, String aa)
    {

        page.locator(baseicon).click();
        page.locator(addpubicon).click();
        page.locator(pub_acronym).fill(acro);
        page.locator(pub_name_textbox).fill(pub);
        // Assert.assertEquals(page.locator(pub_acronym).inputValue(), a, "pub_acronym not filled correctly");
        page.locator(pub_mail_textbox).fill(c);
        page.locator(desc_inputbox).fill(d);
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        page.locator(selectdateinput).fill(formattedDate);
        page.locator(selectdateinput).fill(formattedDate);
        page.locator(pub_location).fill(e);
        page.locator(ftpusername).fill(f);
        page.locator(ftppassword).fill(g);


        page.locator(ftp_initial_directory).fill(h);
        page.locator(daysforlatexnormalization).fill("1");
        page.locator(daysforgraphics).fill(j);
        page.locator(daysforPreediting).fill(k);
        page.locator(daysforcopyediting).fill(l);
        page.locator(daysforlatexnormalization).fill(m);
        page.locator(daysfortypesettings).fill(n);
        page.locator(daysforqc).fill(o);
        page.locator(daysforaupag).fill(p);
        page.locator(daysforauqc).fill(q);
        page.locator(daysforpepag).fill(r);
        page.locator(daysforpeqc).fill(s);
        page.locator(daysforonlinepag).fill(t);
        page.locator(daysforonlineqc).fill(u);
        page.locator(daysforonlinexml).fill(v);
        page.locator(IssuePag).fill(w);
        page.locator(IssueQC).fill(x);
        page.locator(printpag).fill(y);
        page.locator(printQC).fill(z);
        //assertThat(page.locator(printxml)).isAttached();
        page.locator(printxml).fill(aa);


        fileChooser = page.waitForFileChooser(() -> page.locator(Imageuploadbutton).click());
        fileChooser.setFiles(Paths.get("Automation.jpg"));

        fileChooser = page.waitForFileChooser(() -> page.locator(styletemplate).click());
        fileChooser.setFiles(Paths.get("ABC.sty"));


        fileChooser = page.waitForFileChooser(() -> page.locator(guidelinesdoc).click());
        fileChooser.setFiles(Paths.get("guidelines.docx"));

        page.locator(CopyTat).click();


        page.locator(CopyTatConfirm).click();


        page.locator(addbutton).click();
        page.locator(addalertclose).click();


    }


}

