package org.lms.pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
public class LatexNormalizationPage {

    private Page page;
    FileChooser fileChooser;

    String username = "//*[@type='text']";
    String password = "//*[@type='password']";
    String logout = "//p[text()='Logout']";
    //addarticle

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


    private String addalertclose = "//h2[text()='JMS - Add Publisher']//following::span[1]";


    //addjournal
    private String baseicon = "//img[@src='/jms/src/assets/GeneralIcons/shortcuts.svg']";


    private String addjouricon = "id=add_journal";


    private String publisher_1 = "id=publisher";

    private String jor_acrm = "//input[contains(@data-testid,'journal-acronym')]";
    private String jor_fullname = "//input[@data-testid='journal-full-name']";
    private String jor_received_date = "//input[@data-testid='journal-received-date']";
    private String recto_facing = "//input[@type='checkbox']/..";
    private String layout_1 = "id=layout";
    private String layout_2 = "//*[text()='Single']";
    private String trimSizeWidth = "//input[@data-testid='trim-size-width']";

    private String trimSizeHeight = "//input[@data-testid='trim-size-height']";
    private String remarks = "id=remarks";
    private String category_1 = "//label[text()='Category']/../input";
    private String category_2_typeSetting = "//p[normalize-space()='Typesetting']";
    private String category_2_technicalEdit = "//p[normalize-space()='Technical Editing']";
    private String category_3_CopyEdit = "//p[normalize-space()='Copy Editing']";
    private String copyEditLevel_1 = "id=copyEditingLevel";
    private String copyEditLevel_2 = "//p[@for='L1']";
    private String pubType_1 = "//label[text()='Publishing Type']/../input";
    private String pubType_CheckAll = "//div[text()='Check All']";

    //--General--//
    private String G_FPdaysOfLatex = "//*[text()='General']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Latex Normalization']/../input";
    private String G_FPdaysOfGraphics = "//*[text()='General']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Graphics']/../input";
    private String G_FPdaysOfPreEdit = "//*[text()='General']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Pre-Editing']/../input";
    private String G_FPdaysOfCopyEdit = "//*[text()='General']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Copyediting']/../input";
    private String G_FPdaysOfTypeSetting = "//*[text()='General']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Typesetting']/../input";
    private String G_FPdaysOfQC = "//*[text()='General']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for QC']/../input";

    private String G_AUdaysOfPage = "//*[text()='General']/..//*[text()='TAT for AU Revises ']/../..//*[text()='Days for Pagination']/../input";
    private String G_AUdaysOfQC = "//*[text()='General']/..//*[text()='TAT for AU Revises ']/../..//*[text()='Days for QC']/../input";
    private String G_PEdaysOfPage = "//*[text()='General']/..//*[text()=' TAT for PE Revises ']/../..//*[text()='Days for Pagination']/../input";
    private String G_PEdaysOfQC = "//*[text()='General']/..//*[text()=' TAT for PE Revises ']/../..//*[text()='Days for QC']/../input";

    private String G_ONFdaysOfPage = "//*[text()='General']/..//*[text()=' TAT for Online First ']/../..//*[text()='Days for Pagination']/../input";
    private String G_ONFdaysOfQC = "//*[text()='General']/..//*[text()=' TAT for Online First ']/../..//*[text()='Days for QC']/../input";
    private String G_ONFdaysOfXML = "//*[text()='General']/..//*[text()=' TAT for Online First ']/../..//*[text()='Days for XML']/../input";

    private String G_ISSdaysOfPage = "//*[text()='General']/..//*[text()=' TAT for Issue']/../..//*[text()='Days for Pagination']/../input";
    private String G_ISSdaysOfQC = "//*[text()='General']/..//*[text()=' TAT for Issue']/../..//*[text()='Days for QC']/../input";

    private String G_PrintdaysOfPage = "//*[text()='General']/..//*[text()=' TAT for Print/Web ']/../..//*[text()='Days for Pagination']/../input";
    private String G_PrintdaysOfQC = "//*[text()='General']/..//*[text()=' TAT for Print/Web ']/../..//*[text()='Days for QC']/../input";
    private String G_PrintdaysOfXML = "//*[text()='General']/..//*[text()=' TAT for Print/Web ']/../..//*[text()='Days for XML']/../input";


    private String FS_FPdaysOfLatex = "//*[text()='Fasttrack']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Latex Normalization']/../input";
    private String FS_FPdaysOfGraphics = "//*[text()='Fasttrack']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Graphics']/../input";
    private String FS_FPdaysOfPreEdit = "//*[text()='Fasttrack']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Pre-Editing']/../input";
    private String FS_FPdaysOfCopyEdit = "//*[text()='Fasttrack']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Copyediting']/../input";
    private String FS_FPdaysOfTypeSetting = "//*[text()='Fasttrack']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Typesetting']/../input";
    private String FS_FPdaysOfQC = "//*[text()='Fasttrack']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for QC']/../input";

    private String FS_AUdaysOfPage = "//*[text()='Fasttrack']/..//*[text()='TAT for AU Revises ']/../..//*[text()='Days for Pagination']/../input";
    private String FS_AUdaysOfQC = "//*[text()='Fasttrack']/..//*[text()='TAT for AU Revises ']/../..//*[text()='Days for QC']/../input";
    private String FS_PEdaysOfPage = "//*[text()='Fasttrack']/..//*[text()=' TAT for PE Revises ']/../..//*[text()='Days for Pagination']/../input";
    private String FS_PEdaysOfQC = "//*[text()='Fasttrack']/..//*[text()=' TAT for PE Revises ']/../..//*[text()='Days for QC']/../input";

    private String FS_ONFdaysOfPage = "//*[text()='Fasttrack']/..//*[text()=' TAT for Online First ']/../..//*[text()='Days for Pagination']/../input";
    private String FS_ONFdaysOfQC = "//*[text()='Fasttrack']/..//*[text()=' TAT for Online First ']/../..//*[text()='Days for QC']/../input";
    private String FS_ONFdaysOfXML = "//*[text()='Fasttrack']/..//*[text()=' TAT for Online First ']/../..//*[text()='Days for XML']/../input";

    private String FS_ISSdaysOfPage = "//*[text()='Fasttrack']/..//*[text()=' TAT for Issue']/../..//*[text()='Days for Pagination']/../input";
    private String FS_ISSdaysOfQC = "//*[text()='Fasttrack']/..//*[text()=' TAT for Issue']/../..//*[text()='Days for QC']/../input";

    private String FS_PrintdaysOfPage = "//*[text()='Fasttrack']/..//*[text()=' TAT for Print/Web ']/../..//*[text()='Days for Pagination']/../input";
    private String FS_PrintdaysOfQC = "//*[text()='Fasttrack']/..//*[text()=' TAT for Print/Web ']/../..//*[text()='Days for QC']/../input";
    private String FS_PrintdaysOfXML = "//*[text()='Fasttrack']/..//*[text()=' TAT for Print/Web ']/../..//*[text()='Days for XML']/../input";


    private String styleTemplateUploadButton = "//*[@id='styles-upload']/../div";


    private String guideLineUploadButton = "//*[@id='guidelines-upload']/../div";


    private String addButton = "//button[@type='button']";


    private String alertCloseButton = "//h2[text()='JMS - Add Journal']//following::span[1]";


    //webelement for fasttrack


    private String addarticleicon = "id=add_article";

    private String form = "//img[@alt='Option 2 (Form)']";
    private String Selectpubdropdown = "id=publisher";
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
    private String assignbutton = "//*[text()='Assign']";


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
    private String Notificationtyesalert = "//*[text()='Are you sure to Save the mail on Notification?']//following::newbutton[text()='Yes']";
    private String Acknowlegementtoastclose = "//*[text()='JMS - Mail']//following::span[1]";
    private String notificationmail = "//*[text()='Notification']";


    private String savenotificationmail = "//*[text()='Save Mail']";
    private String notificationalert = "//*[text()='Are you sure to Save the mail on notification?']//following::newbutton[text()='Yes']";
    private String notificationsuccesstoastmail = "//*[text()='A mail will be triggered once the article is added successfully']//preceding::span[text()='×']";
    private String addarticlebutton = "(//*[text()='Add Article'])[2]";
    private String checkall = "//div[text()='Check All']";
    private String Checklistsubmitbutton = "//button[text()='Submit CheckList']";
    private String addarticlealert = "//*[text()='JMS - Add Article']//following::span[1]";
    private String selectview = "id=select_view";
    private String pubview = "//p[text()='Publisher View']";
    private String count = "(//*[@id='select_view']//preceding::span[@title='login queue']//following::p)[1]";
    private String journalsview = "//p[text()='Journals View']";
    private String ArticlesView = "//p[text()='Articles View']";
    private String AssignArticleDropdown = "id=Process";
    private String assigntouser = "//*[text()='Sakthi (1948)']";
    private String assignarticlebutton = "//button[text()='Assign']";
    private String filterfields = "(//*[@alt='filter'])[1]";
    private String clearall = "//button[text()='Clear All']";
    private String selectall = "//button[text()='Select All']";
    private String pubfilter = "//label[text()='Publisher Acronym']";
    private String Articleidfilter = "//*[@name='Article Id']";
    private String journalfilter = "//*[@name='Journal Acronym']";
    private String StageFilter = "//*[@name='Stage Name']";
    private String freshlabel = "//span[text()='Fresh']";
    private String Reviseslabel = "//span[text()='Revises']";

    private String tatfilter = "//*[@name='Tat']";
    private String pausearticle = "//*[@title='pause']";
    private String playarticle = "//*[@title='start']";
    private String dynamicarticleeditor = "//*[@title='pause']//following::img[@title='editor']";
    private String completeeditoricon = "//img[@title='complete']";
    private String movetopreedit = "//newbutton[text()='Pre Editing']";

    private String pausereason = "//*[@placeholder='Reason']";
    private String pausesubmitbutton = "//*[text()='Yes']";
    private String holdicon = "//*[@title='hold']";
    private String users = "//p[text()='Users']";
    private String viewuserpub = "//*[text()='Access']//preceding::div[@class=' css-1y7rh0y-MultiValueGeneric2']";
    private String searchbar = "//*[@placeholder='Search...']";
    private String homemenu = "//p[text()='Home']";
    private String compilefile = "//*[@alt='Tex Compilation']";
    private String savefile = "//*[@alt='Save']";
    private String savefiletoastclose = "//h2[text()='JMS - File Saved']//following::span[text()='×']";
    private String slidemove = "//*[@type='button']";
    private String restartarticle = "//*[@alt='restart']";
    private String restartconfirm = "//*[@alt='restart']//following::newbutton[text()='Yes']";
    private String restartclosetab = "//*[text()='JMS - LaTex Restart']//following::span[text()='×']";
    private String startarticle = "//*[@title='start']";
    private String completefile = "//*[@title='complete']";
    private String movefromlattopre = "//newbutton[text()='Pre Editing']";
    private String latexinitializedone = "//label[text()='LaTeX Initial Done!']";
    private String latexnormalizedone = "//*[text()='LaTeX Normalize Done!']";
    private String Editorpage = "//img[@title='editor']";
    private String normalization = "//*[@alt='Normalization']";
    private String normalizatoncloseicon = "//*[text()='JMS - LaTex Normalization']//following::span[text()='×']";


    public LatexNormalizationPage(Page page) {
        this.page = page;
    }

    public void navigatetobaseicon() {
       /* page.locator(baseicon).click();
        page.locator(managemenu).click();*/
        page.locator(homemenu).click();

    }


    public void uploadfiles() {
        fileChooser = page.waitForFileChooser(() -> page.locator(fileupload).click());
        fileChooser.setFiles(Paths.get("GGD-805.zip"));


    }

    public void checklist() {
        page.locator(checklist).click();
        page.locator(figurechecklist).click();
        page.locator(Checklistsubmitbutton).click();
        page.locator(checlistalert).click();
        page.locator(checklisttoast).click();
    }

    public void AddNotes() {
        page.locator(addnotes).click();
        page.locator(Plzwwritehere).fill("this particular article is from general workflow");
        page.locator(AddNoteutton).click();
        page.locator(addnotetoastclose).click();
    }

    public void AddMail() {
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


    public void DoAddArticle(String journalacro, String articleid, String artname, String doinum, String workflow) throws InterruptedException {


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
        Thread.sleep(3000);
        page.locator(assignbutton).click();
        page.locator(noofpages).fill("200");
        page.locator(articletype).fill("Research");
        page.locator(cebypass).click();
        page.locator(TATShow).click();
        Thread.sleep(3000);
        page.locator(importtatfromjournal).click();
        page.locator(confirmimportfromjour).click();
        page.locator(ChecklistSelectionShow).click();
        page.locator(startdate).fill(formattedDate);
        page.locator(OnOpenAccess).click();
        AddMail();
        uploadfiles();
        AddNotes();
        checklist();
        page.locator(addarticlebutton).click();
        page.locator(addarticlealert).click();

    }


    public List<Object> verifyArticleGeneralDetails(String journalacro, String articleid, String artname, String doinum, String workflow, String uname, String upass, String luname, String lpass) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);


        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);
        page.locator(logout).click();
        page.locator(username).fill(uname);
        page.locator(password).fill(upass);
        page.keyboard().press("Enter");
        Boolean articleidisvisible = page.locator("//*[text()='" + arttimeid + "']").isVisible();

        //assign user
        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='assign'][1]").click();
        page.locator(AssignArticleDropdown).click();
        page.locator(assigntouser).click();
        page.locator(assignarticlebutton).click();

        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='show_notes'][1]").click();

        String xpath = "//h2[text()='Notes while adding article - " + arttimeid + "']//following::p[1]";
        String notes = page.locator("//h2[text()='Notes while adding article - " + arttimeid + "']//following::p[1]").textContent();
        page.locator("//h2[text()='Notes while adding article - " + arttimeid + "']//following::img[@title='Close']").click();
        page.locator(filterfields).click();
        page.locator(tatfilter).click();
        Boolean Tat = page.locator("(//*[text()='" + arttimeid + "']//following::td//following::span[text()='FastTrack'])[1]").isVisible();
        List<Object> generaldetails = new ArrayList<>();
        generaldetails.add(articleidisvisible);
        generaldetails.add(notes);
        generaldetails.add(Tat);
        page.locator(logout).click();
        page.locator(username).fill(luname);
        page.locator(password).fill(lpass);
        page.keyboard().press("Enter");
        Thread.sleep(10000);
        Boolean isarticleWIP = page.locator(pausearticle).isVisible();


        if (isarticleWIP.equals(true)) {
            page.locator(dynamicarticleeditor).click();
            page.locator(completeeditoricon).click();
            page.locator(movetopreedit).click();

            Download download = page.waitForDownload(() -> {
                // Perform the action that initiates download
                page.locator("//*[text()='" + arttimeid + "']//following::img[@title='attachments']").isVisible();
            });


            String path = "D:\\uploadtest\\";
            System.out.println(download.path());
            System.out.println(download.url());
            System.out.println(download.failure());
            System.out.println(download.suggestedFilename());


            download.saveAs(Paths.get(path, download.suggestedFilename()));
            generaldetails.add(path);

        } else {
            Download download = page.waitForDownload(() -> {
                // Perform the action that initiates download
                page.locator("//*[text()='" + arttimeid + "']//following::img[@title='attachments']").isVisible();
            });


            String path = "D:\\uploadtest\\";
            System.out.println(download.path());
            System.out.println(download.url());
            System.out.println(download.failure());
            System.out.println(download.suggestedFilename());


            download.saveAs(Paths.get(path, download.suggestedFilename()));
            generaldetails.add(path);
        }

        return generaldetails;
    }


    public void logoutlogin(String uname, String upass) {
        page.locator(logout).click();
        page.locator(username).fill(uname);
        page.locator(password).fill(upass);
        page.keyboard().press("Enter");
    }


    public List<Boolean> EnsureFilterUpdated(String uname, String upass, String latetluname, String latextlpass) throws InterruptedException {
        //logoutlogin(uname,upass);
        logoutlogin(uname, upass);
        page.locator(users).click();

        page.locator("(//*[text()='Sakthi'])[2]").click();
        Thread.sleep(5000);

        page.locator("//*[text()='1948']//following::img[@alt='edit'][1]").click();
        List<String> publishers = page.locator(viewuserpub).allInnerTexts();
        System.out.println(publishers);


        logoutlogin(latetluname, latextlpass);
        page.locator(filterfields).click();
        page.locator(clearall).click();

        Thread.sleep(7000);

        page.locator(filterfields).click();
        page.locator(filterfields).click();
        assertThat(page.locator(pubfilter)).isAttached();
        page.locator(pubfilter).click();
        Thread.sleep(4000);
        page.locator(filterfields).click();

        List<String> selectedpub = page.locator("//tr//following::td//span").allInnerTexts();
        System.out.println(selectedpub);

        boolean allMatchFound = true;
        Iterator<String> publisherIterator = publishers.iterator();
        System.out.println("publisheriterator" + publisherIterator);

        while (publisherIterator.hasNext()) {
            String publisher = publisherIterator.next();
            boolean matchFound = false;

            for (String selected : selectedpub) {
                if (selected.toLowerCase().contains(publisher.toLowerCase())) {
                    matchFound = true;
                    System.out.println("Match found for: " + publisher + " in " + selected);
                }
            }

            if (!matchFound) {
                System.out.println("No match found for: " + publisher);
                allMatchFound = false;
            }
            System.out.println(matchFound);


        }


        page.locator(filterfields).click();
        page.locator(journalfilter).click();
        Thread.sleep(4000);

        page.locator(filterfields).click();


        Boolean journacrovisible = page.locator("//th[text()='Journal acronym']").isVisible();
        System.out.println("journalacrovisiblility" + journacrovisible);
        page.locator(filterfields).click();
        page.locator(clearall).click();
        Thread.sleep(4000);
        page.locator(filterfields).click();
        int headercount = page.locator("//th").count();
        Boolean headervisiblity;
        if (headercount > 0) {
            headervisiblity = true;
        } else {
            headervisiblity = false;
        }
        System.out.println(headervisiblity);
        List<Boolean> verifydetails = new ArrayList<>();
        verifydetails.add(allMatchFound);
        verifydetails.add(journacrovisible);
        verifydetails.add(headervisiblity);
        return verifydetails;

    }

    public Boolean EnsuretoogleInFilter(String uname, String upass) {
        logoutlogin(uname, upass);
        page.locator(filterfields).click();
        page.locator(clearall).click();
        page.locator(selectall).click();
        return true;
    }

    public Boolean VerifySearchFunctionality(String journalacro, String articleid, String artname, String doinum, String workflow, String pmuser, String pmpassword, String uname, String upass) throws InterruptedException {
        logoutlogin(pmuser, pmpassword);
        String arttimeid = String.valueOf(System.currentTimeMillis());
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);


        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);


        logoutlogin(uname, upass);
        page.locator(searchbar).fill(arttimeid);
        assertThat(page.locator("//*[text()='" + arttimeid + "']")).isVisible();
        Boolean articlefilter = page.locator("//*[text()='" + arttimeid + "']").isVisible();
        return articlefilter;

    }

    public Boolean UserFreeToTakeArticle(String journalacro, String articleid, String artname, String doinum, String workflow, String pmuname, String pmupass, String latexuname, String latexupass) throws InterruptedException {
        logoutlogin(pmuname, pmupass);
        String arttimeid = String.valueOf(System.currentTimeMillis());
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);


        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);
        logoutlogin(latexuname, latexupass);
        Thread.sleep(4000);

        page.locator(searchbar).fill(latexuname);
        Boolean article = page.locator(pausearticle).isVisible();
        System.out.println("Article is in WIP" + article);
        Boolean articleenable = false;

        if (article.equals(true)) {
            System.out.println("If condition executed");
            page.locator(pausearticle).click();
            page.locator(pausereason).fill("leave");
            page.locator(pausesubmitbutton).click();
            page.locator("//img[@title='hold']").click();
            page.locator(searchbar).fill(arttimeid);
            Boolean val = page.locator(playarticle).isEditable();
            articleenable = val;


        } else {
            System.out.println("else condition executed");
            page.locator(searchbar).fill(arttimeid);
            Boolean val = page.locator(playarticle).isEditable();
            articleenable = val;


        }
        return articleenable;
    }


    public List<Boolean> UserArticlePlayIcon(String journalacro, String articleid, String artname, String doinum, String workflow, String pmuname, String pmupass, String latextluname, String latextlupass, String latexuname, String latexupass, String css) throws InterruptedException {
        logoutlogin(pmuname, pmupass);
        String arttimeid = String.valueOf(System.currentTimeMillis());
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);


        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);
        logoutlogin(latextluname, latextlupass);
        Thread.sleep(4000);
        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='assign'][1]").click();
        page.locator(AssignArticleDropdown).click();
        page.locator(assigntouser).click();
        page.locator(assignarticlebutton).click();
        logoutlogin(latexuname, latexupass);


        page.locator(filterfields).click();
        page.locator(clearall).click();
        page.locator(journalfilter).click();
        page.locator(Articleidfilter).click();

        page.locator(searchbar).fill(latexuname);

        Boolean articleeditable = page.locator("//*[@title='pause']").isVisible();
        Boolean articlepause = page.locator("//*[@title='hold']").isVisible();
        Boolean articleenable = true;

        if (articleeditable.equals(true)) {
            page.locator(pausearticle).click();
            page.locator(pausereason).fill("leave");
            page.locator(pausesubmitbutton).click();
            Thread.sleep(10000);


            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();


        } else if (articlepause.equals(true)) {
            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();


        }


        page.locator(searchbar).fill(arttimeid);
        Thread.sleep(5000);
        page.locator("//img[@title='start']").click();

        page.locator(homemenu).click();


        //page.locator("//*[text()='"+arttimeid+"']//following::img[@title='start']");
        Locator allPlayIcons = page.locator("//*[@title='start']");

        //multiple locators handle

        List<ElementHandle> allPlayIconsList = StreamSupport.stream(allPlayIcons.elementHandles().spliterator(), false)
                .collect(Collectors.toList());


        // Perform operations on the list of locators
        for (ElementHandle playIcon : allPlayIconsList) {
            // Example operation: click each play icon
            String val = playIcon.evaluate("(element, property) => window.getComputedStyle(element).getPropertyValue(property)", css).toString();
            if (val.equals("not-allowed")) {
                System.out.println("cursor" + val);
                articleenable = true;

            } else {
                System.out.println("cursor" + val);
                articleenable = false;
                break;
            }


        }

        Boolean articleFreetotake = articleenable;
        page.locator(searchbar).fill(arttimeid);
        Thread.sleep(4000);
        page.locator(Editorpage).click();

        page.locator(slidemove).click();

        //  page.locator(compilefile).click();
        page.locator(savefile).click();
        page.locator(savefiletoastclose).click();

        page.locator(completefile).click();

        page.locator(movefromlattopre).click();
        logoutlogin(pmuname, pmupass);
        String arttimeid1 = String.valueOf(System.currentTimeMillis());
        int doi1 = 1;
        long doinumber1 = Long.parseLong(arttimeid1);
        long doival1 = doi1 + doinumber1;
        String doivalue1 = String.valueOf(doival1);
        DoAddArticle(journalacro, arttimeid1, artname, doivalue1, workflow);
        logoutlogin(latexuname, latexupass);
        page.locator(searchbar).fill(arttimeid1);
        page.locator("//img[@title='start']").click();
        Boolean articleCanStart = true;
        List<Boolean> val = new ArrayList<>();
        val.add(articleenable);
        val.add(articleCanStart);
        return val;

    }

    public Boolean VerifyTlAssignFunctionality(String pmuname, String pmupass, String luname, String lupass, String journalacro, String articleid, String artname, String doinum, String workflow) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);
        logoutlogin(pmuname, pmupass);
        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);
        logoutlogin(luname, lupass);
        assertThat(page.locator("//*[text()='" + arttimeid + "']//following::img[@title='assign'][1]")).isVisible();
        return page.locator("//*[text()='" + arttimeid + "']//following::img[@title='assign'][1]").isVisible();
        //latex normalization


    }

    public Boolean otherDeptUserAvailablity(String latextluname, String latextlupass) {

        logoutlogin(latextluname, latextlupass);
        assertThat(page.locator("(//img[@title='assign'])[1]")).isVisible();
        page.locator("(//img[@title='assign'])[1]").isVisible();
        page.locator("(//img[@title='assign'])[1]").click();
        page.locator(AssignArticleDropdown).click();
        List<String> listofuser = page.locator("//*[@id='Process']//following::div[1]//following::ul//following::li").allInnerTexts();
        System.out.println("list of latexuser" + listofuser);
        Boolean val = false;
        for (String user : listofuser) {
            if (user.contains("1947") || user.contains("1997")) {
                System.out.println("if condition executed");
                System.out.println(user);
                val = false;
                break;
            } else {
                System.out.println("else condition executed");
                System.out.println(user);
                val = true;
            }
        }
        return val;
    }

    public Boolean verifyAlreadyStarted(String pmunmae, String pmupass, String journalacro, String articleid, String artname, String doinum, String workflow, String luname, String lupass, String ltlunmae, String ltupass) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);
        logoutlogin(pmunmae, pmupass);
        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);
        logoutlogin(luname, lupass);
        /////////////////////////////

        page.locator(searchbar).fill(luname);
        Thread.sleep(4000);
        Boolean articleeditable = page.locator("//*[@title='pause']").isVisible();
        System.out.println("articleeditable" + articleeditable);
        Boolean articlepause = page.locator("//*[@title='hold']").isVisible();
        Boolean articleenable = true;

        if (articleeditable.equals(true)) {
            page.locator(pausearticle).click();
            page.locator(pausereason).fill("leave");
            page.locator(pausesubmitbutton).click();
            Thread.sleep(10000);


            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();


        } else if (articlepause.equals(true)) {
            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();


        }
        Thread.sleep(4000);
        page.locator(searchbar).fill(arttimeid);
        assertThat(page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']")).isEditable();
        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start'][1]").click();
        logoutlogin(ltlunmae, ltupass);


        page.locator(filterfields).click();
        page.locator(clearall).click();
        page.locator(Articleidfilter).click();
        page.locator(searchbar).fill(arttimeid);

        Thread.sleep(3000);
        return page.locator("//*[text()='" + arttimeid + "']//following::img[@title='assign'][1]").isVisible();


    }

    public Boolean verifyAvailableOfYtsHold(String pmuname, String pmupass, String journalacro, String articleid, String artname, String doinum, String workflow, String luname, String lupass, String tluname, String tlupass, String luname1, String luname2) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);
        logoutlogin(pmuname, pmupass);
        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);
        logoutlogin(luname, lupass);
        page.locator(searchbar).fill(luname);
        Thread.sleep(4000);
        Boolean articleeditable = page.locator("//*[@title='pause']").isVisible();
        System.out.println("articleeditable" + articleeditable);
        Boolean articlepause = page.locator("//*[@title='hold']").isVisible();
        Boolean articleenable = true;

        if (articleeditable.equals(true)) {
            page.locator(pausearticle).click();
            page.locator(pausereason).fill("leave");
            page.locator(pausesubmitbutton).click();
            Thread.sleep(10000);


            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();


        } else if (articlepause.equals(true)) {
            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();


        }

        System.out.println("ppppp");
        page.locator(searchbar).fill(arttimeid);
        Thread.sleep(4000);
        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();

        page.locator(homemenu).click();
        System.out.println("ppppp");

        page.locator(searchbar).fill(arttimeid);
        Thread.sleep(4000);
        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='pause']").click();
        page.locator(pausereason).fill("leave");
        page.locator(pausesubmitbutton).click();
        page.locator(holdicon).click();
        page.locator(pausereason).fill("hold");
        page.locator(pausesubmitbutton).click();
        logoutlogin(tluname, tlupass);
        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='assign']").click();
        page.locator(AssignArticleDropdown).click();
        page.locator(assigntouser).click();
        page.locator(assignarticlebutton).click();

        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='assign']").click();
        page.locator(AssignArticleDropdown).click();
        page.locator("//*[text()='Hemalatha (4321)']").click();
        page.locator(assignarticlebutton).click();
        logoutlogin(luname1, luname2);
        page.locator(searchbar).fill(arttimeid);
        assertThat(page.locator("//*[text()='" + arttimeid + "']")).isVisible();
        return page.locator("//*[text()='" + arttimeid + "']").isVisible();


    }

    public void tlassignself(String pmuname, String pmupass, String journalacro, String articleid, String artname, String doinum, String workflow, String luname, String lupass, String tluname, String tlupass, String luname1, String luname2) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);
        logoutlogin(pmuname, pmupass);
        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);
        logoutlogin(tluname, tlupass);
        page.locator(searchbar).click();
        page.locator("//*[text='" + arttimeid + "']//following::img[@title='assign']").click();
        page.locator("//*[text()='Anbu (1915)']").click();


    }

    public void ReloadDashboard() {
        page.reload();
    }

    public Boolean VerifyUnAssignedUserCanStart(String pmuname, String pmupass, String journalacro, String articleid, String artname, String doinum, String workflow, String luname, String lupass, String tluname, String tlupass, String luname1, String luname2, String css) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);
        logoutlogin(pmuname, pmupass);
        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);
        logoutlogin(tluname, tlupass);
        page.locator(searchbar).fill(arttimeid);
        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='assign']").click();
        page.locator(AssignArticleDropdown).click();
        page.locator("//*[text()='Hemalatha (4321)']").click();
        page.locator(assignbutton).click();
        logoutlogin(luname, lupass);
        page.locator(searchbar).fill(arttimeid);
        assertThat(page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']")).isEditable();
        Locator playIcon = page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']");
        String val = playIcon.evaluate("(element, property) => window.getComputedStyle(element).getPropertyValue(property)", css).toString();
        if (val.equals("not-allowed")) {
            return true;
        } else {
            return false;
        }

    }

    public Boolean VerifyTlParallelWork(String pmuname, String pmupass, String journalacro, String articleid, String artname, String doinum, String workflow, String luname, String lupass, String tluname, String tlupass) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());
        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);

        logoutlogin(pmuname, pmupass);
        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);

        logoutlogin(tluname, tlupass);

        page.locator(searchbar).fill(tluname);
        Thread.sleep(5000);

        Boolean articleeditable = page.locator("//*[@title='pause']").isVisible();
        System.out.println("articleeditable" + articleeditable);

        Boolean articlepause = page.locator("//*[@title='hold']").isVisible();
        Boolean articleenable = true;

        if (articleeditable.equals(true)) {
            page.locator(pausearticle).click();
            page.locator(pausereason).fill("leave");
            page.locator(pausesubmitbutton).click();
            Thread.sleep(10000);


            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();


        } else if (articlepause.equals(true)) {
            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();
        }


        page.locator(searchbar).fill(arttimeid);
        assertThat(page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']")).isEditable();
        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();
        Thread.sleep(4000);

        page.locator(homemenu).click();
        String arttimeidd = String.valueOf(System.currentTimeMillis());
        int doii = 1;
        long doinumberr = Long.parseLong(arttimeidd);
        long doivall = doii + doinumberr;
        String doivaluee = String.valueOf(doivall);

        logoutlogin(pmuname, pmupass);


        DoAddArticle(journalacro, arttimeidd, artname, doivaluee, workflow);

        logoutlogin(tluname, tlupass);
        page.locator(searchbar).fill(arttimeidd);
        page.locator("//*[text()='" + arttimeidd + "']//following::img[@title='assign']").click();
        page.locator(AssignArticleDropdown).click();
        page.locator(assigntouser).click();
        page.locator(assignbutton).click();


        logoutlogin(luname, luname);
        page.locator(searchbar).fill(arttimeidd);
        assertThat(page.locator("//*[text()='" + arttimeidd + "']")).isVisible();
        return page.locator("//*[text()='" + arttimeidd + "']").isVisible();

    }

    public Boolean VerifytlAssignIncompleteArticle(String pmuname, String pmupass, String journalacro, String articleid, String artname, String doinum, String workflow, String luname, String lupass, String tluname, String tlupass, String luname1, String lupass1) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());

        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;

        String doivalue = String.valueOf(doival);
        logoutlogin(pmuname, pmupass);
        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);

        logoutlogin(luname, lupass);

        page.locator(searchbar).fill(luname);
        Thread.sleep(4000);

        Boolean articleeditable = page.locator("//*[@title='pause']").isVisible();
        System.out.println("articleeditable" + articleeditable);

        if (articleeditable.equals(true)) {
            page.locator(pausearticle).click();
            Thread.sleep(4000);
            page.locator(pausereason).fill("leave");
            page.locator(pausesubmitbutton).click();
            Thread.sleep(10000);


            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();


        }


        page.locator(searchbar).fill(arttimeid);

        Thread.sleep(3000);
        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();
        Thread.sleep(4000);

        page.locator(homemenu).click();
        page.locator(searchbar).fill(arttimeid);

        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='pause']").click();
        page.locator(pausereason).fill("hold");
        page.locator(pausesubmitbutton).click();


        logoutlogin(tluname, tlupass);
        page.locator(searchbar).fill(arttimeid);
        page.locator(holdicon).click();
        page.locator(pausereason).fill("hold");
        page.locator(pausesubmitbutton).click();
        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='assign']").click();
        page.locator(AssignArticleDropdown).click();
        page.locator("//*[text()='Hemalatha (4321)']").click();
        page.locator(assignbutton).click();

        logoutlogin(luname1, lupass1);
        page.locator(searchbar).fill(arttimeid);
        Thread.sleep(2000);
        return page.locator("//*[text()='" + arttimeid + "']").isVisible();


    }

    //Dashboard Action
    public String verifyLatexInitiaLDone(String pmuname, String pmupass, String journalacro, String articleid, String artname, String doinum, String workflow, String luname, String lupass, String tluname, String tlupass, String luname1, String lupass1) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());

        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;

        String doivalue = String.valueOf(doival);
        logoutlogin(pmuname, pmupass);
        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);

        logoutlogin(luname, lupass);
        System.out.println(luname);

        page.locator(searchbar).fill(luname);
        Thread.sleep(5000);


        Boolean articleeditable = page.locator("//*[@title='pause']").isVisible();
        System.out.println("articleeditable" + articleeditable);

        Boolean articlepause = page.locator("//*[@title='hold']").isVisible();
        System.out.println("articlepause" + articlepause);


        if (articleeditable.equals(true)) {
            page.locator(pausearticle).click();

            page.locator(pausereason).fill("leave");
            page.locator(pausesubmitbutton).click();
            Thread.sleep(10000);


            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();


        } else if (articlepause.equals(true)) {
            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();
        }


        page.locator(searchbar).fill(arttimeid);

        page.locator("//*[@title='start']").click();
        Thread.sleep(4000);
        return page.locator(latexinitializedone).textContent();


    }

    public List<Object> VerifyHomePageNavigation(String pmuname, String pmupass, String journalacro, String articleid, String artname, String doinum, String workflow, String luname, String lupass, String tluname, String tlupass, String luname1, String lupass1) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());

        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;

        String doivalue = String.valueOf(doival);
        logoutlogin(pmuname, pmupass);
        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);

        logoutlogin(luname, lupass);
        page.locator(searchbar).fill(luname);

        Thread.sleep(5000);

        Boolean articleeditable = page.locator("//*[@title='pause']").isVisible();
        System.out.println("articleeditable" + articleeditable);

        Boolean articlepause = page.locator("//*[@title='hold']").isVisible();
        System.out.println("paauseeditble" + articlepause);
        page.locator(searchbar).fill(luname);
        if (articleeditable.equals(true)) {
            page.locator(pausearticle).click();
            page.locator(pausereason).fill("leave");
            page.locator(pausesubmitbutton).click();
            Thread.sleep(10000);


            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();


        } else if (articlepause.equals(true)) {
            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();
        }

        page.locator(searchbar).fill(arttimeid);
        Thread.sleep(4000);
        assertThat(page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']")).isEditable();
        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();


        page.locator(homemenu).click();
        page.locator(searchbar).fill(arttimeid);
        Boolean homepage = page.locator(Editorpage).isVisible();
        page.locator(Editorpage).click();

        String editorpage = page.locator(latexinitializedone).textContent();

        page.locator(homemenu).click();
        page.locator(searchbar).fill(arttimeid);
        Boolean homepage1 = page.locator(Editorpage).isVisible();
        List<Object> navi = new ArrayList<>();
        navi.add(homepage);
        navi.add(editorpage);
        navi.add(homepage1);
        return navi;

    }

    public List<Boolean> verifyOtherArticleCanTakeInPause(String pmuname, String pmupass, String journalacro, String articleid, String artname, String doinum, String workflow, String luname, String lupass, String tluname, String tlupass, String luname1, String lupass1, String css) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());

        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;

        String doivalue = String.valueOf(doival);
        logoutlogin(pmuname, pmupass);
        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);
        logoutlogin(luname, lupass);
        page.locator(searchbar).fill(luname);


        Thread.sleep(5000);

        Boolean articleeditable = page.locator("//*[@title='pause']").isVisible();
        System.out.println("articleeditable" + articleeditable);

        Boolean articlepause = page.locator("//*[@title='hold']").isVisible();
        System.out.println("paauseeditble" + articlepause);
        page.locator(searchbar).fill(luname);
        page.locator(filterfields).click();
        page.locator(clearall).click();
        page.locator(Articleidfilter).click();
        if (articleeditable.equals(true)) {
            page.locator(pausearticle).click();
            page.locator(pausereason).fill("leave");
            page.locator(pausesubmitbutton).click();
            Thread.sleep(10000);


            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();


        } else if (articlepause.equals(true)) {
            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();
        }

        page.locator(searchbar).fill(arttimeid);
        Thread.sleep(4000);
        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();
        //Thread.sleep(3000);
        page.waitForSelector(homemenu).click();
        // page.locator(homemenu).click();
        page.locator(searchbar).fill(arttimeid);
        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='pause']").click();
        page.locator(pausereason).fill("hold");
        page.locator(pausesubmitbutton).click();
        page.locator(searchbar).fill(arttimeid);
        Boolean pausevisible = page.locator("//*[text()='" + arttimeid + "']//following::img[@title='pause']").isVisible();
        logoutlogin(pmuname, pmupass);

        String arttimeid1 = String.valueOf(System.currentTimeMillis());

        int doi1 = 1;
        long doinumber1 = Long.parseLong(arttimeid1);
        long doival1 = doi1 + doinumber1;

        String doivalue1 = String.valueOf(doival1);

        DoAddArticle(journalacro, arttimeid1, artname, doivalue1, workflow);
        logoutlogin(luname, lupass);
        page.locator(searchbar).fill(arttimeid1);
        Locator playIcon = page.locator("//*[text()='" + arttimeid1 + "']//following::img[@title='start']");
        Boolean articleenable = false;

        String val = playIcon.evaluate("(element, property) => window.getComputedStyle(element).getPropertyValue(property)", css).toString();
        if (val.equals("not-allowed")) {
            System.out.println("cursor" + val);
            articleenable = true;
        } else {
            System.out.println("cursor" + val);
            articleenable = false;
        }

        page.locator(searchbar).fill(arttimeid);
        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='resume']").click();
        Thread.sleep(3000);
        page.locator(homemenu).click();
        page.locator(searchbar).fill(arttimeid);
        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='pause']").click();
        page.locator(pausereason).fill("hold");
        page.locator(pausesubmitbutton).click();
        logoutlogin(pmuname, pmupass);
        String arttimeid2 = String.valueOf(System.currentTimeMillis());

        int doi2 = 1;
        long doinumber2 = Long.parseLong(arttimeid2);
        long doival2 = doi2 + doinumber2;

        String doivalue2 = String.valueOf(doival2);

        DoAddArticle(journalacro, arttimeid2, artname, doivalue2, workflow);
        logoutlogin(luname, lupass);
        page.locator(searchbar).fill(arttimeid2);

        Locator playIcon1 = page.locator("//*[text()='" + arttimeid2 + "']//following::img[@title='start']");
        Boolean articleenable1 = false;

        String val1 = playIcon1.evaluate("(element, property) => window.getComputedStyle(element).getPropertyValue(property)", css).toString();
        if (val1.equals("not-allowed")) {
            System.out.println("cursor" + val1);
            articleenable1 = true;
        } else {
            System.out.println("cursor" + val1);
            articleenable1 = false;
        }
        System.out.println(articleenable1);
        List<Boolean> Articlerestriction = new ArrayList<>();
        Articlerestriction.add(pausevisible);
        Articlerestriction.add(articleenable);
        Articlerestriction.add(articleenable1);
        return Articlerestriction;

    }

    public Boolean verifytUserTakeHoldTaskAfterTlAssign(String pmuname, String pmupass, String journalacro, String articleid, String artname, String doinum, String workflow, String luname, String lupass, String tluname, String tlupass, String luname1, String lupass1) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());

        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;

        String doivalue = String.valueOf(doival);
        logoutlogin(pmuname, pmupass);
        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);
        logoutlogin(luname, lupass);
        page.locator(searchbar).fill(luname);


        Thread.sleep(5000);

        Boolean articleeditable = page.locator("//*[@title='pause']").isVisible();
        System.out.println("articleeditable" + articleeditable);

        Boolean articlepause = page.locator("//*[@title='hold']").isVisible();
        System.out.println("paauseeditble" + articlepause);
        page.locator(searchbar).fill(luname);
        page.locator(filterfields).click();
        page.locator(clearall).click();
        page.locator(Articleidfilter).click();
        if (articleeditable.equals(true)) {
            page.locator(pausearticle).click();
            page.locator(pausereason).fill("leave");
            page.locator(pausesubmitbutton).click();
            Thread.sleep(10000);


            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();


        } else if (articlepause.equals(true)) {
            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();
        }

        page.locator(searchbar).fill(arttimeid);


        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();
        Thread.sleep(3000);
        page.locator(homemenu).click();

        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='pause']").click();
        page.locator(pausereason).fill("leave");
        page.locator(pausesubmitbutton).click();

        page.locator(searchbar).fill(arttimeid);
        Thread.sleep(30000);
        page.locator(holdicon).click();

        page.locator(pausereason).fill("hold");
        page.locator(pausesubmitbutton).click();
        logoutlogin(tluname, tlupass);
        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='assign']").click();
        page.locator(AssignArticleDropdown).click();
        page.locator(assigntouser).click();
        page.locator(assignbutton).click();
        logoutlogin(luname, lupass);
        page.locator(searchbar).fill(arttimeid);
        return page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").isEnabled();


    }


    public List<Boolean> verifytOtherUserTakeHoldTaskAfterTlAssign(String pmuname, String pmupass, String journalacro, String articleid, String artname, String doinum, String workflow, String luname, String lupass, String tluname, String tlupass, String luname1, String lupass1, String css) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());

        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;

        String doivalue = String.valueOf(doival);
        logoutlogin(pmuname, pmupass);
        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);
        logoutlogin(luname, lupass);
        page.locator(searchbar).fill(luname);


        Thread.sleep(5000);

        Boolean articleeditable = page.locator("//*[@title='pause']").isVisible();
        System.out.println("articleeditable" + articleeditable);

        Boolean articlepause = page.locator("//*[@title='hold']").isVisible();
        System.out.println("paauseeditble" + articlepause);
        page.locator(searchbar).fill(luname);
        page.locator(filterfields).click();
        page.locator(clearall).click();
        page.locator(Articleidfilter).click();
        if (articleeditable.equals(true)) {
            page.locator(pausearticle).click();
            page.locator(pausereason).fill("leave");
            page.locator(pausesubmitbutton).click();
            Thread.sleep(30000);


            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();


        } else if (articlepause.equals(true)) {
            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();
        }

        page.locator(searchbar).fill(arttimeid);


        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();
        Thread.sleep(3000);
        page.locator(homemenu).click();

        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='pause']").click();
        page.locator(pausereason).fill("leave");
        page.locator(pausesubmitbutton).click();

        page.locator(searchbar).fill(arttimeid);
        Thread.sleep(30000);
        page.locator(holdicon).click();

        page.locator(pausereason).fill("hold");
        page.locator(pausesubmitbutton).click();
        logoutlogin(tluname, tlupass);
        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='assign']").click();
        page.locator(AssignArticleDropdown).click();
        page.locator("//*[text()='Hemalatha (4321)']").click();
        page.locator(assignbutton).click();


        logoutlogin(luname, lupass);
        page.locator(searchbar).fill(arttimeid);
        Thread.sleep(3000);


        Locator olduseraccess = page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']");

        Boolean articleenable1 = false;

        String val1 = olduseraccess.evaluate("(element, property) => window.getComputedStyle(element).getPropertyValue(property)", css).toString();
        if (val1.equals("not-allowed")) {
            System.out.println("cursor" + val1);
            articleenable1 = true;
        } else {
            System.out.println("cursor" + val1);
            articleenable1 = false;
        }


        logoutlogin(luname1, lupass1);
        page.locator(searchbar).fill(arttimeid);
        Thread.sleep(3000);
        Locator newuseraccess = page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']");
        Boolean articleenable2 = false;
        String val2 = olduseraccess.evaluate("(element, property) => window.getComputedStyle(element).getPropertyValue(property)", css).toString();

        if (val2.equals("not-allowed")) {
            System.out.println("cursor" + val2);
            articleenable2 = true;
        } else {
            System.out.println("cursor" + val2);
            articleenable2 = false;
        }

        List<Boolean> articlestart = new ArrayList<>();
        articlestart.add(articleenable1);
        articlestart.add(articleenable2);
        return articlestart;


    }


    public List<Boolean> verifyUserTakeOtherAticleWhenHold(String pmuname, String pmupass, String journalacro, String articleid, String artname, String doinum, String workflow, String luname, String lupass, String tluname, String tlupass, String luname1, String lupass1, String css) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());

        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;

        String doivalue = String.valueOf(doival);
        logoutlogin(pmuname, pmupass);
        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);
        logoutlogin(luname, lupass);
        page.locator(searchbar).fill(luname);


        Thread.sleep(5000);

        Boolean articleeditable = page.locator("//*[@title='pause']").isVisible();
        System.out.println("articleeditable" + articleeditable);

        Boolean articlepause = page.locator("//*[@title='hold']").isVisible();
        System.out.println("paauseeditble" + articlepause);
        page.locator(searchbar).fill(luname);
        page.locator(filterfields).click();
        page.locator(clearall).click();
        page.locator(Articleidfilter).click();
        if (articleeditable.equals(true)) {
            page.locator(pausearticle).click();
            page.locator(pausereason).fill("leave");
            page.locator(pausesubmitbutton).click();
            Thread.sleep(30000);


            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();

        } else if (articlepause.equals(true)) {
            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();
        }

        page.locator(searchbar).fill(arttimeid);


        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();
        Thread.sleep(3000);
        page.locator(homemenu).click();

        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='pause']").click();
        page.locator(pausereason).fill("leave");
        page.locator(pausesubmitbutton).click();
        page.locator(searchbar).fill(arttimeid);
        Thread.sleep(30000);
        page.locator(holdicon).click();

        page.locator(pausereason).fill("hold");
        page.locator(pausesubmitbutton).click();
        page.locator(searchbar).fill(arttimeid);
        Boolean articleacess = page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").isVisible();

        logoutlogin(pmuname, pmupass);
        String arttimeid1 = String.valueOf(System.currentTimeMillis());

        int doi1 = 1;
        long doinumber1 = Long.parseLong(arttimeid1);
        long doival1 = doi1 + doinumber1;

        String doivalue1 = String.valueOf(doival1);
        logoutlogin(pmuname, pmupass);
        DoAddArticle(journalacro, arttimeid1, artname, doivalue, workflow);
        logoutlogin(luname, lupass);
        page.locator(searchbar).fill(arttimeid1);

        Locator startarticle = page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']");

        Boolean articleenable2 = false;

        String val2 = startarticle.evaluate("(element, property) => window.getComputedStyle(element).getPropertyValue(property)", css).toString();
        if (val2.equals("not-allowed")) {
            System.out.println("cursor" + val2);
            articleenable2 = true;
        } else {
            System.out.println("cursor" + val2);
            articleenable2 = false;
        }

        List<Boolean> isstart = new ArrayList<>();
        isstart.add(articleacess);
        isstart.add(articleenable2);
        return isstart;


    }

    public Boolean verifyAlertMsgForPause(String pmuname, String pmupass, String journalacro, String articleid, String artname, String doinum, String workflow, String luname, String lupass, String tluname, String tlupass, String luname1, String lupass1) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());

        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;

        String doivalue = String.valueOf(doival);
        logoutlogin(pmuname, pmupass);
        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);
        logoutlogin(luname, lupass);

        page.locator(searchbar).fill(luname);


        Thread.sleep(3000);

        Boolean articleeditable = page.locator("//*[@title='pause']").isVisible();
        System.out.println("articleeditable" + articleeditable);

        Boolean articlepause = page.locator("//*[@title='hold']").isVisible();
        System.out.println("paauseeditble" + articlepause);
        page.locator(searchbar).fill(luname);
        page.locator(filterfields).click();
        page.locator(clearall).click();
        page.locator(Articleidfilter).click();
        if (articleeditable.equals(true)) {
            page.locator(pausearticle).click();
            page.locator(pausereason).fill("leave");
            page.locator(pausesubmitbutton).click();
            Thread.sleep(30000);


            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();

        } else if (articlepause.equals(true)) {
            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();
        }

        page.locator(searchbar).fill(arttimeid);


        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();
        Thread.sleep(3000);
        page.locator(homemenu).click();

        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='pause']").click();
        assertThat(page.locator(pausereason)).isVisible();
        page.locator(pausereason).fill("leave");
        page.locator(pausesubmitbutton).click();
        return true;
    }

    public void VerifyUpdation(String pmuname, String pmupass, String journalacro, String articleid, String artname, String doinum, String workflow, String luname, String lupass, String tluname, String tlupass, String luname1, String lupass1) throws InterruptedException {
/*
        String arttimeid = String.valueOf(System.currentTimeMillis());

        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;

        String doivalue = String.valueOf(doival);
        logoutlogin(pmuname, pmupass);
        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);
        logoutlogin(luname, lupass);

        page.locator(searchbar).fill(luname);


        Thread.sleep(3000);

        Boolean articleeditable = page.locator("//*[@title='pause']").isVisible();
        System.out.println("articleeditable" + articleeditable);

        Boolean articlepause = page.locator("//*[@title='hold']").isVisible();
        System.out.println("paauseeditble" + articlepause);
        page.locator(searchbar).fill(luname);
        page.locator(filterfields).click();
        page.locator(clearall).click();
        page.locator(Articleidfilter).click();
        if (articleeditable.equals(true)) {
            page.locator(pausearticle).click();
            page.locator(pausereason).fill("leave");
            page.locator(pausesubmitbutton).click();
            Thread.sleep(30000);


            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();

        } else if (articlepause.equals(true)) {
            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();
        }

        page.locator(searchbar).fill(arttimeid);
        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();*/


        //test
        logoutlogin(luname, lupass);
        page.locator(searchbar).fill("1723214153694");
        page.locator("//*[text()='1723214153694']//following::img[@title='editor']").click();
        page.locator(slidemove).click();
        page.locator("//*[contains(text(),'documentclass')]//following::span[1]").click();
        page.keyboard().press("Delete");
        Thread.sleep(100000);


    }


    public Boolean CompleteArticleDirectVerify(String pmunmae, String pmupass, String journalacro, String articleid, String artname, String doinum, String workflow, String luname, String lupass, String ltlunmae, String ltupass, String luname1, String lupass1) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());

        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;

        String doivalue = String.valueOf(doival);
        logoutlogin(pmunmae, pmupass);
        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);
        logoutlogin(luname, lupass);

        page.locator(searchbar).fill(luname);


        Thread.sleep(3000);

        Boolean articleeditable = page.locator("//*[@title='pause']").isVisible();
        System.out.println("articleeditable" + articleeditable);

        Boolean articlepause = page.locator("//*[@title='hold']").isVisible();
        System.out.println("paauseeditble" + articlepause);
        page.locator(searchbar).fill(luname);
        page.locator(filterfields).click();
        page.locator(clearall).click();
        page.locator(Articleidfilter).click();
        if (articleeditable.equals(true)) {
            page.locator(pausearticle).click();
            page.locator(pausereason).fill("leave");
            page.locator(pausesubmitbutton).click();
            Thread.sleep(30000);


            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();

        } else if (articlepause.equals(true)) {
            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();
        }

        page.locator(searchbar).fill(arttimeid);


        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();
        page.locator(slidemove).click();
        page.locator(savefile).click();

        Thread.sleep(2000);
        Boolean stagemovementprevent = page.locator(movetopreedit).isVisible();
        return stagemovementprevent;

    }


    public Boolean RestartAndCompleteArticleDirectVerify(String pmunmae, String pmupass, String journalacro, String articleid, String artname, String doinum, String workflow, String luname, String lupass, String ltlunmae, String ltupass, String luname1, String lupass1) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());

        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;

        String doivalue = String.valueOf(doival);
        logoutlogin(pmunmae, pmupass);
        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);
        logoutlogin(luname, lupass);

        page.locator(searchbar).fill(luname);


        Thread.sleep(3000);

        Boolean articleeditable = page.locator("//*[@title='pause']").isVisible();
        System.out.println("articleeditable" + articleeditable);

        Boolean articlepause = page.locator("//*[@title='hold']").isVisible();
        System.out.println("paauseeditble" + articlepause);
        page.locator(searchbar).fill(luname);
        page.locator(filterfields).click();
        page.locator(clearall).click();
        page.locator(Articleidfilter).click();
        if (articleeditable.equals(true)) {
            page.locator(pausearticle).click();
            page.locator(pausereason).fill("leave");
            page.locator(pausesubmitbutton).click();
            Thread.sleep(30000);


            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();

        } else if (articlepause.equals(true)) {
            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();
        }

        page.locator(searchbar).fill(arttimeid);


        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();
        page.locator(slidemove).click();
        page.locator(restartarticle).click();
        page.locator(restartconfirm).click();
        page.locator(restartclosetab).click();
        page.locator(savefile).click();

        Thread.sleep(2000);
        Boolean stagemovementprevent = page.locator(movetopreedit).isVisible();
        return stagemovementprevent;

    }


    public Boolean VerifyCompleteWithoutCompile(String pmunmae, String pmupass, String journalacro, String articleid, String artname, String doinum, String workflow, String luname, String lupass, String ltlunmae, String ltupass, String luname1, String lupass1) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());

        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;

        String doivalue = String.valueOf(doival);
        logoutlogin(pmunmae, pmupass);
        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);
        logoutlogin(luname, lupass);

        page.locator(searchbar).fill(luname);


        Thread.sleep(3000);

        Boolean articleeditable = page.locator("//*[@title='pause']").isVisible();
        System.out.println("articleeditable" + articleeditable);

        Boolean articlepause = page.locator("//*[@title='hold']").isVisible();
        System.out.println("paauseeditble" + articlepause);
        page.locator(searchbar).fill(luname);
        page.locator(filterfields).click();
        page.locator(clearall).click();
        page.locator(Articleidfilter).click();
        if (articleeditable.equals(true)) {
            page.locator(pausearticle).click();
            page.locator(pausereason).fill("leave");
            page.locator(pausesubmitbutton).click();
            Thread.sleep(30000);


            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();

        } else if (articlepause.equals(true)) {
            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();
        }

        page.locator(searchbar).fill(arttimeid);


        page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();
        page.locator(slidemove).click();
        page.locator(normalization).click();
        page.locator(normalizatoncloseicon).click();
        page.locator(savefile).click();
        Thread.sleep(2000);
        Boolean stagemovementprevent = page.locator(movetopreedit).isVisible();
        return stagemovementprevent;

    }


    public boolean VerifyNormalizeMsg(String pmunmae, String pmupass, String journalacro, String articleid, String artname, String doinum, String workflow, String luname, String lupass, String ltlunmae, String ltupass, String luname1, String lupass1) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());

        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;

        String doivalue = String.valueOf(doival);
        logoutlogin(pmunmae, pmupass);
        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);

        logoutlogin(luname, lupass);
        System.out.println(luname);

        page.locator(searchbar).fill(luname);
        Thread.sleep(5000);


        Boolean articleeditable = page.locator("//*[@title='pause']").isVisible();
        System.out.println("articleeditable" + articleeditable);

        Boolean articlepause = page.locator("//*[@title='hold']").isVisible();
        System.out.println("articlepause" + articlepause);


        if (articleeditable.equals(true)) {
            page.locator(pausearticle).click();

            page.locator(pausereason).fill("leave");
            page.locator(pausesubmitbutton).click();
            Thread.sleep(10000);


            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();


        } else if (articlepause.equals(true)) {
            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();
        }
        page.locator(searchbar).fill(arttimeid);
        Thread.sleep(4000);

        page.locator(startarticle).click();
        Thread.sleep(60000);
        page.locator(normalization).click();
        Thread.sleep(30000);

        page.locator(normalizatoncloseicon).click();
        return page.locator(latexnormalizedone).isVisible();


    }

    public void VerifyFreshArticle(String pmunmae, String pmupass, String journalacro, String articleid, String artname, String doinum, String workflow, String luname, String lupass, String ltlunmae, String ltupass, String luname1, String lupass1) {
        logoutlogin(luname, lupass);
        page.locator(filterfields).click();
        page.locator(clearall).click();
        page.locator(Articleidfilter).click();
        page.locator(journalfilter).click();
        page.locator(StageFilter).click();
        page.locator(freshlabel).click();
        page.locator(freshlabel).click();


    }

    public Boolean VerifyCompletedArticleInLatex(String pmunmae, String pmupass, String journalacro, String articleid, String artname, String doinum, String workflow, String luname, String lupass, String ltlunmae, String ltupass, String luname1, String lupass1) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());

        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;

        String doivalue = String.valueOf(doival);
        logoutlogin(pmunmae, pmupass);
        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);

        logoutlogin(luname, lupass);
        System.out.println(luname);

        page.locator(searchbar).fill(luname);
        Thread.sleep(5000);


        Boolean articleeditable = page.locator("//*[@title='pause']").isVisible();
        System.out.println("articleeditable" + articleeditable);

        Boolean articlepause = page.locator("//*[@title='hold']").isVisible();
        System.out.println("articlepause" + articlepause);


        if (articleeditable.equals(true)) {
            page.locator(pausearticle).click();

            page.locator(pausereason).fill("leave");
            page.locator(pausesubmitbutton).click();
            Thread.sleep(10000);


            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();


        } else if (articlepause.equals(true)) {
            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();
        }
        page.locator(searchbar).fill(arttimeid);
        Thread.sleep(4000);

        page.locator(startarticle).click();
        page.locator(slidemove).click();
        page.waitForSelector(latexinitializedone, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));

        page.locator(normalization).click();
        page.waitForSelector(latexnormalizedone, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        page.locator(normalizatoncloseicon).click();
        page.locator(savefile).click();
        page.locator(completefile).click();
        page.locator(movetopreedit).click();
        page.locator(movefromlattopre).click();
        logoutlogin(ltlunmae,ltupass);
        page.locator(searchbar).fill(arttimeid);
        return page.locator("//*[text()='"+arttimeid+"']").isVisible();

    }

    public List<Boolean> NonStartedArticle(String pmunmae, String pmupass, String journalacro, String articleid, String artname, String doinum, String workflow, String luname, String lupass, String ltlunmae, String ltupass, String luname1, String lupass1, String css) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());

        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        System.out.println(arttimeid);

        String doivalue = String.valueOf(doival);
        logoutlogin(pmunmae, pmupass);
        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);
        logoutlogin(luname,lupass);



        page.locator(searchbar).fill(luname);
        Thread.sleep(5000);


        Boolean articleeditable = page.locator("//*[@title='pause']").isVisible();
        System.out.println("articleeditable" + articleeditable);

        Boolean articlepause = page.locator("//*[@title='hold']").isVisible();
        System.out.println("articlepause" + articlepause);


        if (articleeditable.equals(true)) {
            page.locator(pausearticle).click();

            page.locator(pausereason).fill("leave");
            page.locator(pausesubmitbutton).click();
            Thread.sleep(10000);


            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();


        } else if (articlepause.equals(true)) {
            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();
        }

        page.locator(searchbar).fill(arttimeid);
        Thread.sleep(3000);

        Boolean articleenable;
        Locator playIcon=page.locator("//*[@title='start']");
        String val = playIcon.evaluate("(element, property) => window.getComputedStyle(element).getPropertyValue(property)", css).toString();
        if (val.equals("not-allowed")) {
            System.out.println("cursor" + val);
            articleenable = true;
        } else {
            System.out.println("cursor" + val);
            articleenable = false;

        }

        Boolean user1=articleenable;

        logoutlogin(luname1,lupass1);



        page.locator(searchbar).fill(luname1);

        Boolean articleeditable1 = page.locator("//*[@title='pause']").isVisible();
        System.out.println("articleeditable" + articleeditable1);

        Boolean articlepause1 = page.locator("//*[@title='hold']").isVisible();
        System.out.println("articlepause" + articlepause1);

        if (articleeditable1.equals(true)) {
            page.locator(pausearticle).click();

            page.locator(pausereason).fill("leave");
            page.locator(pausesubmitbutton).click();
            Thread.sleep(10000);


            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();


        } else if (articlepause1.equals(true)) {
            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();
        }

        page.locator(searchbar).fill(arttimeid);
        Thread.sleep(3000);
        Boolean articleenable1;
        Locator playIcon1=page.locator("//*[@title='start']");
        String val1 = playIcon1.evaluate("(element, property) => window.getComputedStyle(element).getPropertyValue(property)", css).toString();
        if (val1.equals("not-allowed")) {
            System.out.println("cursor" + val);
            articleenable1 = true;
        } else {
            System.out.println("cursor" + val);
            articleenable1 = false;

        }

        Boolean user2=articleenable1;
        List<Boolean> starticon=new ArrayList<>();
        starticon.add(articleenable);
        starticon.add(articleenable1);
        return starticon;


    }

    public Boolean VerifyOtherUserEntry(String pmunmae, String pmupass, String journalacro, String articleid, String artname, String doinum, String workflow, String luname, String lupass, String ltlunmae, String ltupass, String luname1, String lupass1,String css) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());

        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;

        String doivalue = String.valueOf(doival);
        logoutlogin(pmunmae, pmupass);
        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);

        logoutlogin(luname, lupass);
        System.out.println(luname);

        page.locator(searchbar).fill(luname);
        Thread.sleep(5000);


        Boolean articleeditable = page.locator("//*[@title='pause']").isVisible();
        System.out.println("articleeditable" + articleeditable);

        Boolean articlepause = page.locator("//*[@title='hold']").isVisible();
        System.out.println("articlepause" + articlepause);


        if (articleeditable.equals(true)) {
            page.locator(pausearticle).click();

            page.locator(pausereason).fill("leave");
            page.locator(pausesubmitbutton).click();
            Thread.sleep(10000);


            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();


        } else if (articlepause.equals(true)) {
            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();
        }
        page.locator(searchbar).fill(arttimeid);
        Thread.sleep(4000);

        page.locator(startarticle).click();


        logoutlogin(luname1, lupass1);


        page.locator(searchbar).fill(luname1);
        Thread.sleep(5000);


        Boolean articleeditable1 = page.locator("//*[@title='pause']").isVisible();
        System.out.println("articleeditable" + articleeditable1);

        Boolean articlepause1 = page.locator("//*[@title='hold']").isVisible();
        System.out.println("articlepause" + articlepause1);


        if (articleeditable1.equals(true)) {
            page.locator(pausearticle).click();

            page.locator(pausereason).fill("leave");
            page.locator(pausesubmitbutton).click();
            Thread.sleep(10000);


            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();


        } else if (articlepause1.equals(true)) {
            Locator holdericon = page.locator(holdicon);
            List<ElementHandle> allPlayIconsList = StreamSupport.stream(holdericon.elementHandles().spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println(allPlayIconsList.size());
            int count = allPlayIconsList.size();


            // Iterate over each element and click it
            for (int i = 0; i < count; i++) {
                page.locator(holdicon).nth(i).click();
                // Optionally add a delay if needed
                Thread.sleep(3000);
            }

            page.locator(pausereason).fill("hold");
            page.locator(pausesubmitbutton).click();
            // page.locator("//*[text()='" + arttimeid + "']//following::img[@title='start']").click();
        }


        page.locator(searchbar).fill(arttimeid);
        Locator playIcon = page.locator(startarticle);
        return page.locator(startarticle).isVisible();


    }

    public List<Boolean> VerifMultipleTLEntry(String pmunmae, String pmupass, String journalacro, String articleid, String artname, String doinum, String workflow, String luname, String lupass, String ltlunmae, String ltupass, String luname1, String lupass1,String ltlunmae1,String ltluname2) throws InterruptedException {
        String arttimeid = String.valueOf(System.currentTimeMillis());

        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;

        String doivalue = String.valueOf(doival);
        logoutlogin(pmunmae, pmupass);
        DoAddArticle(journalacro, arttimeid, artname, doivalue, workflow);
        logoutlogin(ltlunmae,ltupass);
        page.locator(searchbar).fill(arttimeid);
        Thread.sleep(3000);
        Boolean tl1=page.locator("//*[@text()='"+arttimeid+"']").isVisible();

        logoutlogin(ltlunmae1,ltluname2);
        page.locator(searchbar).fill(arttimeid);
        Thread.sleep(3000);
        Boolean tl2=page.locator("//*[@text()='"+arttimeid+"']").isVisible();

        List<Boolean> verifytldashboard=new ArrayList<>();
        verifytldashboard.add(tl1);
        verifytldashboard.add(tl2);
        return verifytldashboard;






    }












    }








