package org.lms.pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import java.time.DayOfWeek;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AddArticlePage {

    private Page page;
    FileChooser fileChooser;

    String username = "//*[@type='text']";
    String password = "//*[@type='password']";
    String logout = "//p[text()='Logout']";


    private String baseicon = "//img[@src='/jms/src/assets/GeneralIcons/shortcuts.svg']";
    private String addpubicon = "id=add_publisher";
    private String viewtype = "id=select_view";
    private String articleview = "//*[text()='Articles View']";


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
    private String querymenu = "//div[@id='root']//following::p[text()='Query']";

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
    private String noteeditor = "//div[@data-placeholder='Type here...']";
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
    private String StyShow = "(//*[text()='Latest Files']//following::img[@title='Show'])[1]";


    //private  String baseicon="//img[@src='/jms/src/assets/GeneralIcons/shortcuts.svg']";
    private String addarticleicon = "id=add_article";
    private String UploadLabel = "//label[text()='Upload']";
    private String formLabel = "//label[text()='Form']";
    private String clientftplabel = "//label[text()='Client FTP']";
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
    private String form = "//img[@alt='Option 2 (Form)']";
    private String Selectpubdropdown = "id=publisher";
    private String selectpublisher = "//p[normalize-space(text())='GGD(EMS Press)']\n";
    private String articleidinput = "id=articleID";
    private String authormail = "id=authorMail";
    private String authorname = "id=authorName";
    private String articlename = "id=articleName";
    private String selectpriority = "id=priority";
    private String selectpriorityopt = "//*[@id='priority']//following::p[text()='High']";
    private String receivedate = "id=receivedDate";
    private String reviseddate = "id=revisedDate";
    private String Accepteddate = "id=historyAccDate";
    private String selecttat = "//label[text()='TAT']//following::div[1]";
    private String selecttatinput = "//label[text()='TAT']//following::li[1]//following::p[1]";
    private String Doino = "id=doi";
    private String workflowselection = "//*[text()='Assign Workflow']";
    private String selectgeneralworkflow = "//*[@alt='General']";
    private String assignbutton = "//*[text()='Assign']";


    // private String selectworkflowopt="//*[@id='workFlow']//following::p[text()='Fresh']";
    private String noofpages = "//input[@id='numberOfPages']";
    private String cebypass = "id=CE by-pass";
    private String articletype = "//*[@placeholder='Enter Article Type...']";
    private String TATShow = "//*[text()='Turn Around Time']//preceding-sibling::img";
    private String importtatfromjournal = "//*[@title='Import TATs from Journal']";
    private String confirmimportfromjour = "//*[contains(text(),'Import TAT')]//following::div//following::newbutton[contains(text(),'Yes')]";
    private String startdate = "id=startDate";
    private String ChecklistSelectionShow = "//*[text()='Checklist Selections']//preceding-sibling::img";

    private String OnOpenAccess = "id=acpOpenAccess";
    private String filefieldexpand = "//*[text()='File Upload']";

    private String fileupload = "//*[@alt='Upload']";
    private String addnotes = "//*[@alt='Add Notes']";
    private String Plzwwritehere = "//*[@data-placeholder='Type here...']";
    private String AddNoteutton = "//*[text()='Add Note']";
    private String addnotetoastclose = "//*[text()='JMS - Add Notes']//following::span[1]";
    private String checklist = "//*[text()='Check Lists']";
    private String figurechecklist = "id=figures";
    private String checlistalert = "//*[text()='Alert']//following::newbutton[text()='Yes']";
    private String checklisttoast = "//*[text()='JMS - Check Lists']//following::span[1]";
    private String mailpreview = "//*[text()='Preview']";
    private String tomail = "//label[text()='To']//following::input[@id='category'][1]";
    private String tomailluser = "//p[normalize-space(text())='compuscriptrep@gmail.com']//preceding-sibling::input";
    private String Acknowledgementtomailluser = "//p[normalize-space(text())='latexam@gmail.com']//preceding-sibling::input";


    private String ccmail = "//label[text()='Cc']//following::input[@id='category'][1]";
    private String ccmailuser = "//p[normalize-space(text())='nirmala@pdmrindia.com']//preceding-sibling::input";
    private String Acknowlegeemtnsavemailbutton = "//button[text()='Save Mail']";
    private String Acknowledgementyesalert = "//*[text()='Are you sure to Save the mail on acknowledgement?']//following::newbutton[text()='Yes']";
    private String Acknowlegementtoastclose = "//*[text()='JMS - Mail']//following::span[1]";
    private String notificationmail = "//*[text()='Notification']";
    private String Acknomail = "//*[text()='Acknowledgement']";

    private String savenotificationmail = "//*[text()='Save Mail']";
    private String notificationalert = "//*[text()='Are you sure to Save the mail on notification?']//following::newbutton[text()='Yes']";
    private String notificationsuccesstoastmail = "//*[text()='A mail will be triggered once the article is added successfully']//preceding::span[text()='×']";
    private String addarticlebutton = "(//*[text()='Add Article'])[2]";
    private String checkall = "//div[text()='Check All']";
    private String Checklistsubmitbutton = "//button[text()='Submit CheckList']";
    private String addarticlealert = "//*[text()='JMS - Add Article']//following::span[1]";
    private String selectview = "id=select_view";
    private String journalsview = "//*[@id='select_view']//following::li//following::p[text()='Articles View']";

    private String supplement = "id=supplement";
    private String aopFree = "id=acpFree";
    private String aopopenacess = "id=acpOpenAccess";
    private String tables = "id=tables";
    private String displayfigures = "id=displayFigures";
    private String InlineFigures = "id=inlineFigures";
    private String stockmenu = "//p[text()='Stock']";
    private String users = "//p[text()='Users']";
    private String sakthiuser = "(//p[text()='Sakthi'])[2]";
    private String EditUser = "//p[text()='1948']//following::img[@alt='edit']";
    private String AccessUserdropdown = "//label[text()='Access']//following::div[1]";
    private String User_Latexacess = "//label[text()='Access']//following::div[1]//following::div[text()='LaTeX Executive']";
    private String User_publisherdropdown = "//*[@for='user-publisher']//following::div[1]";
    private String User_publisher = "//*[@for='user-publisher']//following::div[1]//following::div[text()='AT']";
    private String User_Update = "//*[@type='submit']";
    private String updateuseralertclose = "//h2[text()='JMS - Update User']//following::span[text()='×'][1]";
    private String Checklist_display_fig = "id=displayFigures";
    private String displayfigures_count = "//*[@id='displayFigures']//following::input[@type='number']";
    private String Inlinefig_count = "//*[@id='inlineFigures']//following::input[@type='number']";
    private String addarticlefilealertclose = "//*[text()='JMS - Add Article']//following::span[text()='×']";
    //createUser

    private String adduserbutton = "//button[text()='Add User']";
    private String empname = "id=user-name";
    private String employee_id = "id=employee-id";

    private String designationdropdown = "//*[@data-testid='add-user-select-designation']";
    private String graphicsdesignaation = "//*[@data-testid='add-user-select-designation']//following::option[text()='Senior Graphics Designer']";
    private String acessdropdown = "id=react-select-9-input";
    private String graphicsdeptaccess = "//div[text()='Graphics']";
    private String user_mail = "//*[@for='user-mail']";
    private String slectgender = "id=user-gender";
    private String selectdepartment = "id=user-department";
    private String pubdropdown = "//*[text()='Publisher']//following::input[1]";
    private String publisherselect = "//*[text()='Publisher']//following::input[1]//following::div[text()='AT']";
    private String userrole = "id=user-role";
    private String useraddbutton = "//button[@type='submit']";

    private String fileuploadalerttext = "//*[text()='JMS - Add Article']//following::div[text()='Kindly upload a .zip file']";
    private String Filerestrictionalert = "//*[text()='JMS - File Upload Restriction']//following::div[2]";
    private String Filerestrictionalertclose = "//*[text()='JMS - File Upload Restriction']//following::span[text()='×']";
    private String closenote = "//*[text()='Article']//preceding::img[1]";

    //TAT Days
    private String f_latexdays = "id=daysForLaTeXNorFP";
    private String f_graphicsdays = "id=daysForGraphicsFP";
    private String f_predays = "id=daysForPreFP";
    private String f_Ceditdays = "id=daysForCopyFP";
    private String f_paginationdays = "id=daysForPaginationFP";
    private String f_qcdays = "id=daysForQCFP";
    private String au_pagdays = "id=daysForPaginationAU";
    private String au_qcdays = "id=daysForQCAU";
    private String pe_pagdays = "id=daysForPaginationPE";
    private String pe_qcdays = "id=daysForQCPE";
    private String online_pagdays = "id=hoursDaysForPagination";
    private String online_qcdays = "id=hoursDaysForQC";
    private String online_xmldays = "id=hoursDaysForXML";
    private String issue_pagdays = "id=daysForPaginationIssue";
    private String issue_qcdays = "id=daysForQCIssue";
    private String printpagdays = "id=daysForPaginationWeb";
    private String printqcdays = "id=daysForQCWeb";
    private String print_xmldays = "id=daysForXMLWeb";
    private String  firstproofdueDate="id=dueOn";


    public AddArticlePage(Page page) {
        this.page = page;
    }


    public void addarticlepage() {
        page.locator(baseicon).click();
        page.locator(addarticleicon).click();
        //page.locator(form).click();

    }

    public void reloadpage() {
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
        Boolean uploadvisible = page.locator(UploadLabel).isVisible();
        System.out.println(uploadvisible);
        Boolean formvisible = page.locator(formLabel).isVisible();
        System.out.println(formvisible);
        Boolean clientftpvisible = page.locator(clientftplabel).isVisible();
        System.out.println(clientftpvisible);


        return uploadvisible && formvisible && clientftpvisible;


    }

    public void uploadfiles() {
        fileChooser = page.waitForFileChooser(() -> page.locator(fileupload).click());
        fileChooser.setFiles(Paths.get("GGD-805.zip"));


    }

    public void uploadmultiplefiles() {
        fileChooser.setFiles(new Path[]{
                Paths.get("GGD-805.zip"),
                Paths.get("GGD-806.zip"),

        });
    }

    public void uploadadditionalfiles() {
        fileChooser.setFiles(new Path[]{
                Paths.get("Resume.pdf"),
                Paths.get("EMS_Press.pdf"),
                Paths.get("Ai.jpg"),
                Paths.get("Automation.jpg"),
                Paths.get("ems_journal.jpg")


        });


    }

    public String DoAddArticlereturndoi(String journalacro, String articleid, String artname, String doinum, String workflow) {
        //int doi=1;
        page.locator(baseicon).click();
       /* String arttimeid = String.valueOf(System.currentTimeMillis());
        System.out.println(articlename);

        long doinumber = Long.parseLong(arttimeid);
        Class<?> type = ((Object)doinumber).getClass();
        System.out.println("Data type of doinumber: " + type.getSimpleName());
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);*/


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
        page.locator("//p[normalize-space(text())='" + journalacro + "']").click();
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
        page.locator("//*[@alt='" + workflow + "']").click();
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
        return doinum;


    }

    public void AddNotes() {
        page.locator(addnotes).click();
        page.locator(Plzwwritehere).fill("this particular article is from general workflow");
        page.locator(AddNoteutton).click();
        page.locator(addnotetoastclose).click();
    }


    public void DoAddArticle(String journalacro, String articleid, String artname, String doinum, String workflow) {

        System.out.println(articleid);
        System.out.println(doinum);

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
        page.locator("//p[normalize-space(text())='" + journalacro + "']").click();
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
        page.locator("//*[@alt='" + workflow + "']").click();
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
        // uploadfiles();

      /*  page.locator(addnotes).click();

        page.locator(Plzwwritehere).fill("this particular article is from general workflow");
        page.locator(AddNoteutton).click();
        page.locator(addnotetoastclose).click();*/

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


    }

    public void DoAddArticleForDate(String journalacro, String articleid, String artname, String doinum, String workflow,LocalDate startworkdate) {

        System.out.println(articleid);
        System.out.println(doinum);

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
        page.locator("//p[normalize-space(text())='" + journalacro + "']").click();
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
        page.locator("//*[@alt='" + workflow + "']").click();
        page.locator(assignbutton).click();
        page.locator(noofpages).fill("200");
        page.locator(articletype).fill("Research");
        page.locator(cebypass).click();
        page.locator(TATShow).click();
        page.locator(importtatfromjournal).click();
        page.locator(confirmimportfromjour).click();
        page.locator(ChecklistSelectionShow).click();
        page.locator(startdate).fill(String.valueOf(startworkdate));
        page.locator(OnOpenAccess).click();
        // uploadfiles();

      /*  page.locator(addnotes).click();

        page.locator(Plzwwritehere).fill("this particular article is from general workflow");
        page.locator(AddNoteutton).click();
        page.locator(addnotetoastclose).click();*/

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


    }

    public void DoAddArticleForMail(String journalacro, String articleid, String artname, String doinum, String workflow) {

        System.out.println(articleid);
        System.out.println(doinum);

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
        page.locator("//p[normalize-space(text())='" + journalacro + "']").click();
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
        page.locator("//*[@alt='" + workflow + "']").click();
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
        checklist();
        AddNotes();


//page.locator(checkall).click();


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


    public void checklist() {
        page.locator(checklist).click();
        page.locator(figurechecklist).click();
        page.locator(Checklistsubmitbutton).click();
        page.locator(checlistalert).click();
        page.locator(checklisttoast).click();
    }

    public void AddArticle(String journalacro, String articleid, String artname, String doinum, String workflow) {
        page.locator(baseicon).click();
        String arttimeid = String.valueOf(System.currentTimeMillis());
        System.out.println(articlename);


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
        page.locator("//p[normalize-space(text())='" + journalacro + "']").click();
        page.locator(articleidinput).fill(articleid);
        page.locator(authormail).fill("abc@gmail.com");
        page.locator(authorname).fill("Mahindra");
        page.locator(articlename).fill(articleid);
        page.locator(selectpriority).click();
        page.locator(selectpriorityopt).click();
        page.locator(receivedate).fill(formattedDate);
        page.locator(reviseddate).fill(tomorrow);
        page.locator(Accepteddate).fill(dayoftomorrow);
        page.locator(selecttat).click();
        page.locator(selecttatinput).click();
        page.locator(Doino).fill(doinum);
        page.locator(workflowselection).click();
        page.locator("//*[@alt='" + workflow + "']").click();
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


    }


    public Boolean addarticleacess(String journalacro, String articleid, String artname, String doinum, String workflow, String uname, String upass, String jacrm, String pubname) {

        String arttimeid = String.valueOf(System.currentTimeMillis());
        System.out.println(articlename);
        System.out.println(arttimeid);
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);
        page.locator(logout).click();
        page.locator(username).fill(uname);
        page.locator(password).fill(upass);
        page.keyboard().press("Enter");

        DoAddArticle(journalacro, arttimeid, artname, doinum, workflow);
        checklist();
        AddNotes();
        page.locator(addarticlebutton).click();
        page.locator(addarticlealert).click();
        page.locator(managemenu).click();
        page.locator(selectview).click();
        page.locator(journalsview).click();
        return page.locator("//*[text()='" + pubname + "']//following::th[text()='The Tenat']").isVisible();
        //page.locator()

    }

    public void AddArticleByMandatoryFields(String journalacro, String articleid, String artname, String doinum, String workflow) {
        DoAddArticle(journalacro, articleid, artname, doinum, workflow);
        checklist();
        AddNotes();
        page.locator(addarticlebutton).click();
        page.locator(addarticlealert).click();
        page.locator(managemenu).click();

    }

    public String ensureduplicationarticle(String journalacro, String articleid, String artname, String doinum, String workflow) {
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
        page.locator("//p[normalize-space(text())='" + journalacro + "']").click();
        page.locator(articleidinput).fill(articleid);
        page.locator(authormail).fill("abc@gmail.com");
        page.locator(authorname).fill("Mahindra");
        page.locator(articlename).fill(artname);
        assertThat(page.locator("//*[text()='JMS - Add Article']//following::div[text()='Article ID(" + articleid + ") already exists!']")).isVisible();
        String erromsg = page.locator("//*[text()='JMS - Add Article']//following::div[text()='Article ID(" + articleid + ") already exists!']").textContent();
        page.locator(addarticlealert).click();
        return erromsg;


    }


    public Boolean verifyUnauthorizedUserAddArticle(String uname, String upass) {
        page.locator(logout).click();
        page.locator(username).fill(uname);
        page.locator(password).fill(upass);
        page.keyboard().press("Enter");
        try {
            page.locator("xpath=//img[@src='/jms/src/assets/GeneralIcons/shortcuts.svg']")
                    .waitFor(new Locator.WaitForOptions().setTimeout(2000).setState(WaitForSelectorState.VISIBLE));
            return true;
        } catch (PlaywrightException e) {
            return false;
        }


    }

    public void doaddpub(String acro, String pub, String c, String d, String e, String f, String g, String h, String i, String j, String k, String l, String m, String n, String o, String p, String q, String r, String s, String t, String u, String v, String w, String x, String y, String z, String aa) {

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


    public List<String> Verifychecklistfunctionality(String uname, String upass, String cssProperty) {
        page.locator(logout).click();
        page.locator(username).fill(uname);
        page.locator(password).fill(upass);
        page.keyboard().press("Enter");
        addarticlepage();
        page.locator(form).click();

        page.locator(checklist).click();

        page.locator(supplement).click();
        page.locator(aopFree).click();
        page.locator(aopopenacess).click();
        page.locator(tables).click();
        page.locator(displayfigures).click();
        page.locator(InlineFigures).click();

        List<String> allradio = new ArrayList<>();

        String supplementradio = page.locator(supplement).evaluate("(element, property) => window.getComputedStyle(element).getPropertyValue(property)", cssProperty).toString();
        String aopFreeradio = page.locator(aopFree).evaluate("(element, property) => window.getComputedStyle(element).getPropertyValue(property)", cssProperty).toString();
        String aopopenacessradio = page.locator(aopopenacess).evaluate("(element, property) => window.getComputedStyle(element).getPropertyValue(property)", cssProperty).toString();
        String tablesradio = page.locator(tables).evaluate("(element, property) => window.getComputedStyle(element).getPropertyValue(property)", cssProperty).toString();
        String displayfiguresradio = page.locator(displayfigures).evaluate("(element, property) => window.getComputedStyle(element).getPropertyValue(property)", cssProperty).toString();
        String InlineFiguresradio = page.locator(InlineFigures).evaluate("(element, property) => window.getComputedStyle(element).getPropertyValue(property)", cssProperty).toString();


        allradio.add(supplementradio);
        allradio.add(aopFreeradio);
        allradio.add(aopopenacessradio);
        allradio.add(tablesradio);
        allradio.add(displayfiguresradio);
        allradio.add(aopFreeradio);
        allradio.add(InlineFiguresradio);
        return allradio;


    }

    public Boolean verifyArticleMovedToLatex(String journalacro, String articleid, String artname, String doinum, String workflow, String uname, String upass, String displayfigcount, String inlinefigcount) {
        String arttimeid = String.valueOf(System.currentTimeMillis());
        System.out.println(articlename);
        System.out.println(arttimeid);
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);

        page.locator(logout).click();
        page.locator(username).fill("7000");
        page.locator(password).fill("7000");
        page.keyboard().press("Enter");
        addarticlepage();
        page.locator(form).click();
        DoAddArticle(journalacro, arttimeid, artname, doinum, workflow);
        checklist();
        AddNotes();

        page.locator(addarticlebutton).click();
        page.locator(logout).click();
        page.locator(username).fill(uname);
        page.locator(password).fill(upass);
        page.keyboard().press("Enter");
        // page.locator(stockmenu).click();
        assertThat(page.locator("//td[text()='" + arttimeid + "']")).isAttached();
        boolean val = page.locator("//td[text()='" + arttimeid + "']").isVisible();
        return val;

    }


    public Boolean verifyArticleShouldMoveToGraphics(String journalacro, String articleid, String artname, String doinum, String workflow, String uname, String upass, String displayfigcount, String inlinefigcount) {
        String arttimeid = String.valueOf(System.currentTimeMillis());
        System.out.println(articlename);
        System.out.println(arttimeid);
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);
        page.locator(logout).click();
        page.locator(username).fill("7000");
        page.locator(password).fill("7000");
        page.keyboard().press("Enter");
        addarticlepage();
        page.locator(form).click();
        DoAddArticle(journalacro, arttimeid, artname, doinum, workflow);
        checklist();
        AddNotes();
        page.locator(ChecklistSelectionShow).click();
        page.locator(displayfigures).click();
        page.locator(displayfigures_count).fill(displayfigcount);

        page.locator(InlineFigures).click();
        page.locator(Inlinefig_count).fill(inlinefigcount);


        page.locator(addarticlebutton).click();
        page.locator(logout).click();
        page.locator(username).fill(uname);
        page.locator(password).fill(upass);
        page.keyboard().press("Enter");
        assertThat(page.locator("(//*[text()='" + arttimeid + "'])[1]")).isAttached();

        return true;

    }

    public List<Boolean> verifyArticleShouldMoveToGraphicsAndLatex(String journalacro, String articleid, String artname, String doinum, String workflow, String uname, String upass, String displayfigcount, String inlinefigcount, String luname, String lupass) {
        String arttimeid = String.valueOf(System.currentTimeMillis());
        System.out.println(articlename);
        System.out.println(arttimeid);
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);

        page.locator(logout).click();
        page.locator(username).fill("7000");
        page.locator(password).fill("7000");
        page.keyboard().press("Enter");
        addarticlepage();
        page.locator(form).click();
        DoAddArticle(journalacro, arttimeid, artname, doinum, workflow);
        checklist();
        page.locator(ChecklistSelectionShow).click();
        page.locator(displayfigures).click();
        page.locator(displayfigures_count).fill(displayfigcount);

        page.locator(InlineFigures).click();
        page.locator(Inlinefig_count).fill(inlinefigcount);


        page.locator(addarticlebutton).click();
        page.locator(logout).click();
        page.locator(username).fill(uname);
        page.locator(password).fill(upass);
        page.keyboard().press("Enter");
        assertThat(page.locator("(//*[text()='" + arttimeid + "'])[1]")).isAttached();
        Boolean articleingraphics = page.locator("(//*[text()='" + arttimeid + "'])[1]").isVisible();
        page.locator(logout).click();
        page.locator(username).fill(luname);
        page.locator(password).fill(lupass);
        page.keyboard().press("Enter");
        assertThat(page.locator("(//*[text()='" + arttimeid + "'])[1]")).isAttached();
        boolean articleinlatex = page.locator("(//*[text()='" + arttimeid + "'])[1]").isVisible();
        List<Boolean> articlevisiblile = new ArrayList<>();
        articlevisiblile.add(articleingraphics);
        articlevisiblile.add(articleinlatex);
        return articlevisiblile;


    }

    public String addarticlewithoutZipFile(String journalacro, String articleid, String artname, String doinum, String workflow) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());
        System.out.println(articlename);
        System.out.println(arttimeid);
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);

        page.locator(logout).click();
        page.locator(username).fill("7000");
        page.locator(password).fill("7000");
        page.keyboard().press("Enter");
        addarticlepage();
        page.locator(form).click();
        AddArticle(journalacro, arttimeid, artname, doivalue, workflow);
        //checklist();
        //

        page.locator(addarticlebutton).click();

        String text = page.locator(fileuploadalerttext).textContent();

        page.locator(addarticlefilealertclose).click();
        return text;


    }


    public String addarticlewithMultipleZipFile(String journalacro, String articleid, String artname, String doinum, String workflow) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());
        System.out.println(articlename);
        System.out.println(arttimeid);
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);
        page.locator(logout).click();
        page.locator(username).fill("7000");
        page.locator(password).fill("7000");
        page.keyboard().press("Enter");

        addarticlepage();
        page.locator(form).click();
        AddArticle(journalacro, arttimeid, artname, doivalue, workflow);
        checklist();
        //uploadfiles();
        fileChooser = page.waitForFileChooser(() -> page.locator(fileupload).click());
        fileChooser.setFiles(Paths.get("GGD-806.zip"));
        fileChooser = page.waitForFileChooser(() -> page.locator(fileupload).click());
        fileChooser.setFiles(Paths.get("GGD-806.zip"));

        //page.locator(addarticlebutton).click();

        String text = page.locator(Filerestrictionalert).textContent();

        page.locator(Filerestrictionalertclose).click();
        return text;


    }

    public Boolean addarticlewithMultipleAdditionalFile(String journalacro, String articleid, String artname, String doinum, String workflow) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());
        System.out.println(articlename);
        System.out.println(arttimeid);
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);

        page.locator(logout).click();
        page.locator(username).fill("7000");
        page.locator(password).fill("7000");
        page.keyboard().press("Enter");

        addarticlepage();
        page.locator(form).click();
        String doin = DoAddArticlereturndoi(journalacro, arttimeid, artname, doivalue, workflow);
        //uploadfiles();
        fileChooser = page.waitForFileChooser(() -> page.locator(fileupload).click());
        fileChooser.setFiles(Paths.get("GGD-806.zip"));

        fileChooser = page.waitForFileChooser(() -> page.locator(fileupload).click());
        fileChooser.setFiles(Paths.get("Resume.pdf"));

        fileChooser = page.waitForFileChooser(() -> page.locator(fileupload).click());
        fileChooser.setFiles(Paths.get("EMS_Press.pdf"));

        fileChooser = page.waitForFileChooser(() -> page.locator(fileupload).click());
        fileChooser.setFiles(Paths.get("Ai.jpg"));

        fileChooser = page.waitForFileChooser(() -> page.locator(fileupload).click());
        fileChooser.setFiles(Paths.get("Automation.jpg"));

        fileChooser = page.waitForFileChooser(() -> page.locator(fileupload).click());
        fileChooser.setFiles(Paths.get("ems_journal.jpg"));


        page.locator(addarticlebutton).click();
        page.locator(managemenu).click();
        page.locator(viewtype).click();
        page.locator(articleview).click();
        assertThat(page.locator("//em[text()='" + doin + "']")).isAttached();
        page.locator("(//em[text()='" + doin + "'])[1]//preceding::td[2]").click();
        page.locator(filefieldexpand).click();
        List<Boolean> files = new ArrayList<>();
        // assertThat(page.locator("//p[text()='Resume.pdf']")).isAttached();
        //files.add(page.locator("//p[text()='Resume.pdf']").isVisible());


        assertThat(page.locator("//p[text()='EMS_Press.pdf']").nth(1)).isVisible();
        // files.add( page.locator("//p[text()='EMS_Press.pdf']").isVisible());

        //assertThat(page.locator("//p[text()='Ai.jpg']")).isVisible();
        // files.add( page.locator("//p[text()='Ai.jpg']").isVisible());

        assertThat(page.locator("//p[text()='Resume.pdf']").nth(1)).isVisible();
        //files.add(page.locator("//p[text()='Automation.jpg']").isVisible());

       // assertThat(page.locator("//p[text()='ems_journal.jpg']")).isVisible();
        //files.add( page.locator("//p[text()='ems_journal.jpg']").isVisible());
        return true;

    }

    public List<Boolean> ensuredownloadandRemoveoption(String journalacro, String articleid, String artname, String doinum, String workflow) {
        String arttimeid = String.valueOf(System.currentTimeMillis());
        System.out.println(articlename);
        System.out.println(arttimeid);
        int doi = 1;
        long doinumber = Long.parseLong(articleid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);

        addarticlepage();
        page.locator(form).click();
        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);

        //uploadfiles();
        /*fileChooser = page.waitForFileChooser(() -> page.locator(fileupload).click());
        fileChooser.setFiles(Paths.get("Ai.jpg"));*/
        page.locator(filefieldexpand).click();

        assertThat(page.locator("//p[@title='GGD-805.zip']//following::img[1]")).isAttached();
        assertThat(page.locator("//p[@title='GGD-805.zip']//following::img[2]")).isAttached();
       /* assertThat(page.locator("//p[@title='Ai.jpg']//following::img[1]")).isAttached();
        assertThat(page.locator("//p[@title='Ai.jpg']//following::img[2]")).isAttached();
*/

        List<Boolean> downloadremove = new ArrayList<>();

        downloadremove.add(page.locator("//p[@title='GGD-805.zip']//following::img[1]").isVisible());
        downloadremove.add(page.locator("//p[@title='GGD-805.zip']//following::img[2]").isVisible());

      /*  downloadremove.add(page.locator("//p[@title='Ai.jpg']//following::img[1]").isVisible());
        downloadremove.add(page.locator("//p[@title='Ai.jpg']//following::img[2]").isVisible());*/

        return downloadremove;


    }

    public String EnsureDownloadFunctionality(String journalacro, String articleid, String artname, String doinum, String workflow) {
        String arttimeid = String.valueOf(System.currentTimeMillis());
        System.out.println(articlename);
        System.out.println(arttimeid);
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);

        addarticlepage();
        page.locator(form).click();
        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);
        uploadfiles();
        //page.locator(filefieldexpand).click();
        Download download = page.waitForDownload(() -> {
            // Perform the action that initiates download
            page.locator("//p[@title='GGD-805.zip']//following::img[2]").click();
        });


        String path = "D:\\uploadtest\\";
        System.out.println(download.path());
        System.out.println(download.url());
        System.out.println(download.failure());
        System.out.println(download.suggestedFilename());


        download.saveAs(Paths.get(path, download.suggestedFilename()));
        return path;


    }

    public boolean EnsureRemovalFunctionality(String journalacro, String articleid, String artname, String doinum, String workflow) {
        String arttimeid = String.valueOf(System.currentTimeMillis());
        System.out.println(articlename);
        System.out.println(arttimeid);
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);
        addarticlepage();
        page.locator(form).click();
        DoAddArticlereturndoi(journalacro, arttimeid, artname, doivalue, workflow);
        uploadfiles();
        page.locator(filefieldexpand).click();
        page.locator("//p[@title='GGD-805.zip']//following::img[1]").click();
        return page.locator("//p[@title='GGD-805.zip']").isVisible();


    }

    public List<String> VerifyPubJournalArticleInNotes(String journalacro, String articleid, String artname, String doinum, String workflow, String pub, String jour) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());
        System.out.println(articlename);
        System.out.println(arttimeid);
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);
        DoAddArticle(journalacro, arttimeid, artname, doinum, workflow);
        AddNotes();
        // uploadfiles();
        page.locator(addnotes).click();


        List<String> pubjorart = new ArrayList<>();

        // assertThat(page.locator("//*[text()='"+pub+"']//following::p[text()='"+pub+"']")).isAttached();
        pubjorart.add(page.locator("//*[text()='Publisher']//following::p[text()='" + pub + "']").textContent());

        // assertThat(page.locator("//*[text()='"+jour+"']//following::p[text()='"+jour+"']")).isAttached();
        pubjorart.add(page.locator("//*[text()='Journal']//following::p[text()='" + jour + "']").textContent());
        pubjorart.add(arttimeid);

        // assertThat(page.locator("//*[text()='"+arttimeid+"']//following::p[text()='"+arttimeid+"']")).isAttached();
        pubjorart.add(page.locator("//*[text()='Article']//following::p[text()='" + arttimeid + "']").textContent());

        return pubjorart;


    }

    public String VerifyNotesContent(String journalacro, String articleid, String artname, String doinum, String workflow, String pub, String jour, String cont) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());
        System.out.println(articlename);
        System.out.println(arttimeid);
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);
        DoAddArticle(journalacro, arttimeid, artname, doinum, workflow);
        checklist();
        AddNotes();
        page.locator(addnotes).click();
        // page.locator(Plzwwritehere).fill(cont);
        //page.locator(AddNoteutton).click();
        // page.locator(addnotetoastclose).click();
        //page.locator(addnotes).click();
        return page.locator(Plzwwritehere).textContent();


    }

    public List<String> VerifyNotesContentAfterChangeJournal(String journalacro, String articleid, String artname, String doinum, String workflow, String pub, String jour, String cont) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());
        System.out.println(articlename);
        System.out.println(arttimeid);
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);
        DoAddArticle(journalacro, arttimeid, artname, doinum, workflow);
        checklist();

        page.locator(addnotes).click();

      /*  page.locator(Plzwwritehere).fill("this particular article is from general workflow");
        page.locator(AddNoteutton).click();
        page.locator(addnotetoastclose).click();*/
        String before = page.locator(Plzwwritehere).textContent();
        page.locator(closenote).click();

        page.locator(Selectpubdropdown).click();
        page.locator("//p[normalize-space(text())='MT(M)']").click();

        page.locator(addnotes).click();
        String After = page.locator(Plzwwritehere).textContent();
        List<String> Notecontent = new ArrayList<>();
        Notecontent.add(before);
        Notecontent.add(After);
        return Notecontent;


    }


    public String NoteCloseButton(String journalacro, String articleid, String artname, String doinum, String workflow, String pub, String jour, String cont) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());
        System.out.println(articlename);
        System.out.println(arttimeid);
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);
        DoAddArticle(journalacro, arttimeid, artname, doinum, workflow);
        checklist();

        page.locator(addnotes).click();
        page.locator(Plzwwritehere).fill("Here you go");
        page.locator(closenote).click();

        page.locator(addnotes).click();
        String notecontent = page.locator(Plzwwritehere).textContent();
        return notecontent;
    }

    public List<String> VerifyPubJournalArticleInChecklist(String journalacro, String articleid, String artname, String doinum, String workflow, String pub, String jour) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());
        System.out.println(articlename);
        System.out.println(arttimeid);
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);
        DoAddArticle(journalacro, arttimeid, artname, doinum, workflow);
        AddNotes();
        checklist();
        // uploadfiles();

        page.locator(checklist).click();

        List<String> pubjorart = new ArrayList<>();


        // assertThat(page.locator("//*[text()='"+pub+"']//following::p[text()='"+pub+"']")).isAttached();
        pubjorart.add(page.locator("//*[text()='Publisher']//following::p[text()='" + pub + "']").textContent());

        // assertThat(page.locator("//*[text()='"+jour+"']//following::p[text()='"+jour+"']")).isAttached();
        pubjorart.add(page.locator("//*[text()='Journal']//following::p[text()='" + jour + "']").textContent());
        pubjorart.add(arttimeid);

        // assertThat(page.locator("//*[text()='"+arttimeid+"']//following::p[text()='"+arttimeid+"']")).isAttached();
        pubjorart.add(page.locator("//*[text()='Article']//following::p[text()='" + arttimeid + "']").textContent());

        return pubjorart;

    }

    public Boolean IsCheckboxIsChecked(String journalacro, String articleid, String artname, String doinum, String workflow, String pub, String jour) {
        String arttimeid = String.valueOf(System.currentTimeMillis());
        System.out.println(articlename);
        System.out.println(arttimeid);
        int doi = 1;
        long doinumber = Long.parseLong(articleid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);
        DoAddArticle(journalacro, arttimeid, artname, doinum, workflow);
        checklist();
        AddNotes();
        page.locator(checklist).click();
        return page.locator(figurechecklist).isChecked();

    }

    public Boolean IsCheckboxIsCheckedInEditArticle(String journalacro, String articleid, String artname, String doinum, String workflow, String pub, String jour) {
        String arttimeid = String.valueOf(System.currentTimeMillis());

        System.out.println(arttimeid);
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);
        System.out.println(doivalue);

        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);
        checklist();
        AddNotes();
        page.locator(addarticlebutton).click();
        page.locator(addarticlealert).click();
        assertThat(page.locator("//em[text()='" + doivalue + "']")).isAttached();
        page.locator("(//em[text()='" + doivalue + "'])[1]//preceding::td[2]").click();
        page.locator(checklist).click();

        page.locator(tables).click();
        return page.locator(tables).isChecked();
    }

    public Boolean VerifyChecklistAfterChangeHigherLevelInfo(String journalacro, String articleid, String artname, String doinum, String workflow, String pub, String jour) {
        String arttimeid = String.valueOf(System.currentTimeMillis());

        System.out.println(arttimeid);
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);
        System.out.println(doivalue);

        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);
        checklist();
        AddNotes();
        page.locator(Selectpubdropdown).click();
        page.locator("//p[normalize-space(text())='MT(M)']").click();
        page.locator(checklist).click();
        return page.locator(figurechecklist).isChecked();


    }

    public Boolean ChecklistCloseButton(String journalacro, String articleid, String artname, String doinum, String workflow, String pub, String jour) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());

        System.out.println(arttimeid);
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);
        System.out.println(doivalue);

        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);

        AddNotes();

        page.locator(checklist).click();
        page.locator(figurechecklist).click();
        page.locator(closenote).click();
        page.locator(checklist).click();
        return page.locator(figurechecklist).isChecked();
        // page.locator()

    }

    public Boolean IsQueryShowing(String journalacro, String articleid, String artname, String doinum, String workflow, String pub, String jour) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());

        System.out.println(arttimeid);
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);
        System.out.println(doivalue);



        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);

        AddNotes();

        checklist();
        uploadfiles();
        page.locator(addarticlebutton).click();
        page.locator(addarticlealert).click();
        page.locator(querymenu).click();
        String art_id = "//*[text()='" + arttimeid + "']";
        System.out.println("artid" + art_id);
        assertThat(page.locator(art_id).nth(1)).isAttached();
        return page.locator(art_id).nth(1).isVisible();
    }

    public List<String> verifyTatFromJournal(String journalacro, String articleid, String artname, String doinum, String workflow, String pub, String jour, String f_latex, String f_gra, String f_pre, String f_copy, String au_pag, String au_qc, String pe_pag, String pe_qc, String onlinepag, String onlineqc, String onlinexml, String issuepag, String issueqc, String printpag, String printqc, String print_xml) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());

        System.out.println(arttimeid);

        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);
        System.out.println(doivalue);

        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);
        page.locator(TATShow).click();
        List<String> journalstat = new ArrayList<>();

        journalstat.add(page.locator(f_latexdays).inputValue());
        journalstat.add(page.locator(f_graphicsdays).inputValue());
        journalstat.add(page.locator(f_predays).inputValue());
        journalstat.add(page.locator(f_Ceditdays).inputValue());
        journalstat.add(page.locator(f_paginationdays).inputValue());
        journalstat.add(page.locator(f_qcdays).inputValue());
        journalstat.add(page.locator(au_pagdays).inputValue());
        journalstat.add(page.locator(au_qcdays).inputValue());
        journalstat.add(page.locator(pe_pagdays).inputValue());
        journalstat.add(page.locator(pe_qcdays).inputValue());
        journalstat.add(page.locator(online_pagdays).inputValue());
        journalstat.add(page.locator(online_qcdays).inputValue());
        journalstat.add(page.locator(online_xmldays).inputValue());
        journalstat.add(page.locator(issue_pagdays).inputValue());
        journalstat.add(page.locator(issue_qcdays).inputValue());
        journalstat.add(page.locator(printpagdays).inputValue());
        journalstat.add(page.locator(printqcdays).inputValue());
        journalstat.add(page.locator(print_xmldays).inputValue());
        return journalstat;


    }


    public List<String> ModifyTatAfterImport(String journalacro, String articleid, String artname, String doinum, String workflow, String pub, String jour, String f_latex, String f_gra, String f_pre, String f_copy, String au_pag, String au_qc, String pe_pag, String pe_qc, String onlinepag, String onlineqc, String onlinexml, String issuepag, String issueqc, String printpag, String printqc, String print_xml) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());

        System.out.println(arttimeid);

        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);
        System.out.println(doivalue);

        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);
        uploadfiles();
        page.locator(TATShow).click();


        page.locator(f_latexdays).fill("3");
        page.locator(f_graphicsdays).fill("3");
        page.locator(f_predays).fill("3");
        page.locator(f_Ceditdays).fill("3");
        page.locator(f_paginationdays).fill("3");
        page.locator(f_qcdays).fill("3");
        page.locator(au_pagdays).fill("3");
        page.locator(au_qcdays).fill("3");
        page.locator(pe_pagdays).fill("3");
        page.locator(pe_qcdays).fill("3");
        page.locator(online_pagdays).fill("3");
        page.locator(online_qcdays).fill("3");
        page.locator(online_xmldays).fill("3");
        page.locator(issue_pagdays).fill("3");
        page.locator(issue_qcdays).fill("3");
        page.locator(printpagdays).fill("3");
        page.locator(printqcdays).fill("3");
        page.locator(print_xmldays).fill("3");
        checklist();
        page.locator(addarticlebutton).click();
        page.locator(addarticlealert).click();

        page.locator("(//em[text()='"+doivalue+"'])[1]//preceding::td[2]").click();

        page.locator(TATShow).click();
        List<String> journalmodifiedTat= new ArrayList<>();

        journalmodifiedTat.add(page.locator(f_latexdays).inputValue());
        journalmodifiedTat.add(page.locator(f_graphicsdays).inputValue());
        journalmodifiedTat.add(page.locator(f_predays).inputValue());
        journalmodifiedTat.add(page.locator(f_Ceditdays).inputValue());
        journalmodifiedTat.add(page.locator(f_paginationdays).inputValue());
        journalmodifiedTat.add(page.locator(f_qcdays).inputValue());
        journalmodifiedTat.add(page.locator(au_pagdays).inputValue());
        journalmodifiedTat.add(page.locator(au_qcdays).inputValue());
        journalmodifiedTat.add(page.locator(pe_pagdays).inputValue());
        journalmodifiedTat.add(page.locator(pe_qcdays).inputValue());
        journalmodifiedTat.add(page.locator(online_pagdays).inputValue());
        journalmodifiedTat.add(page.locator(online_qcdays).inputValue());
        journalmodifiedTat.add(page.locator(online_xmldays).inputValue());
        journalmodifiedTat.add(page.locator(issue_pagdays).inputValue());
        journalmodifiedTat.add(page.locator(issue_qcdays).inputValue());
        journalmodifiedTat.add(page.locator(printpagdays).inputValue());
        journalmodifiedTat.add(page.locator(printqcdays).inputValue());
        journalmodifiedTat.add(page.locator(print_xmldays).inputValue());
        return journalmodifiedTat;


    }

    public String VerifyDueDateAutoCalculate(String journalacro, String articleid, String artname, String doinum, String workflow, String pub, String jour) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());

        System.out.println(arttimeid);
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);
        System.out.println(doivalue);





        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);

        AddNotes();

        checklist();
        uploadfiles();
        page.locator(TATShow).click();
        String firstproofduedate =page.locator(firstproofdueDate).inputValue();
        return firstproofduedate;

    }


    public Boolean VerifyStartWorkingDAte(String journalacro, String articleid, String artname, String doinum, String workflow, String pub, String jour, LocalDate resultDate)
    {
        String arttimeid = String.valueOf(System.currentTimeMillis());

        System.out.println(arttimeid);
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);
        System.out.println(doivalue);





        DoAddArticleForDate(journalacro, arttimeid, artname, doivalue, workflow,resultDate);

        AddNotes();

        checklist();
        uploadfiles();
        page.locator(addarticlebutton).click();
        page.locator(addarticlealert).click();
        assertThat(page.locator("//*[text()='"+doivalue+"']")).isAttached();
        return page.locator("//*[text()='"+doivalue+"']").isVisible();

    }

    //Mail preview Scripts

    public Boolean ToggleBetweenNotificationAndAcknowledgementMail(String journalacro, String articleid, String artname, String doinum, String workflow, String pub, String jour)
    {
        String arttimeid = String.valueOf(System.currentTimeMillis());

        System.out.println(arttimeid);
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);
        System.out.println(doivalue);

        DoAddArticleForMail(journalacro, arttimeid, artname, doivalue, workflow);
        page.locator(mailpreview).click();
        page.locator(notificationmail).click();
        page.locator(Acknomail).click();
        return true;





    }


}

