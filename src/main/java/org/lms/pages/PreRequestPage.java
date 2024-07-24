package org.lms.pages;

import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PreRequestPage {

    private Page page;
    FileChooser fileChooser;


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


    private String addalertclose = "//h2[text()='JMS - Add Publisher']//following::span[1]";

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

    private String acknowledgement = "//div[contains(text(),'Journal Added Successfully')]";

    private String alertCloseButton = "//h2[text()='JMS - Add Journal']//following::span[1]";

    //createUser
    private String users="//p[text()='Users']";
    private String AccessUserdropdown="//label[text()='Access']//following::div[1]";

    private String adduserbutton="//button[text()='Add User']";
    private String empname="id=user-name";
    private String employee_id="id=employee-id";

    private String designationdropdown="//*[@data-testid='add-user-select-designation']";
    private String graphicsdesignaation="//*[@data-testid='add-user-select-designation']//following::option[text()='Senior Graphics Designer']";
    private String acessdropdown="id=react-select-9-input";
    private String graphicsdeptaccess="//div[text()='Graphics']";
    private String user_mail="//*[@for='user-mail']";
    private String slectgender="id=user-gender";
    private String selectdepartment="id=user-department";
    private String pubdropdown="//*[text()='Publisher']//following::input[1]";
    private String publisherselect="//*[text()='Publisher']//following::input[1]//following::div[text()='AT']";
    private String userrole="id=user-role";
    private String useraddbutton="//button[@type='submit']";

    public PreRequestPage(Page page) {
        this.page = page;

    }

    public void navigatetobaseicon()
    {
        page.locator(baseicon).click();
    }

    public void DoAddPub(String a,String b)
    {
        page.locator(baseicon).click();
        page.locator(addpubicon).click();
        page.locator(pub_acronym).fill(a);
        page.locator(pub_name_textbox).fill(b);
        page.locator(pub_mail_textbox).fill("sak@gmail.com");
        page.locator(desc_inputbox).fill("1");
        LocalDate today = LocalDate.now();
        LocalDate tomarrow = today.plusDays(1);

        String formattedDate = today.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String tomorrow = (today.plusDays(1)).format(DateTimeFormatter.ISO_DATE);


        page.locator(selectdateinput).fill(tomorrow);

        page.locator(pub_location).fill("1");
        page.locator(ftpusername).fill("1");
        page.locator(ftppassword).fill("1");


        page.locator(ftp_initial_directory).fill("1");
        page.locator(daysforlatexnormalization).fill("2");
        page.locator(daysforgraphics).fill("2");
        page.locator(daysforPreediting).fill("2");
        page.locator(daysforcopyediting).fill("2");
        page.locator(daysforlatexnormalization).fill("2");
        page.locator(daysfortypesettings).fill("2");
        page.locator(daysforqc).fill("2");
        page.locator(daysforaupag).fill("2");
        page.locator(daysforauqc).fill("2");
        page.locator(daysforpepag).fill("2");
        page.locator(daysforpeqc).fill("2");
        page.locator(daysforonlinepag).fill("2");
        page.locator(daysforonlineqc).fill("2");
        page.locator(daysforonlinexml).fill("2");
        page.locator(IssuePag).fill("2");
        page.locator(IssueQC).fill("2");
        page.locator(printpag).fill("2");
        page.locator(printQC).fill("2");
        //assertThat(page.locator(printxml)).isAttached();
        page.locator(printxml).fill("2");



        page.locator(CopyTat).click();
        page.locator(CopyTatConfirm).click();
        uploadfiles();


        page.locator(addbutton).click();
        page.locator(addalertclose).click();
        page.locator(managemenu).click();


    }

    public void uploadfiles()
    {
        fileChooser = page.waitForFileChooser(() -> page.locator(Imageuploadbutton).click());
        fileChooser.setFiles(Paths.get("Automation.jpg"));

        fileChooser = page.waitForFileChooser(() -> page.locator(styletemplate).click());
        fileChooser.setFiles(Paths.get("ABC.sty"));


        fileChooser = page.waitForFileChooser(() -> page.locator(guidelinesdoc).click());
        fileChooser.setFiles(Paths.get("guidelines.docx"));
    }


    public void DoAddJournal(String Pub, String J_acrm, String J_name) throws InterruptedException
    {
        page.locator(baseicon).click();
        page.locator(addjouricon).click();
            System.out.println(Pub);
            System.out.println(jor_acrm);
            System.out.println(J_name);
            page.locator(baseicon).click();
            page.locator(addjouricon).click();
            page.locator(publisher_1).click();
            page.locator("//div[@class='_options-container_15e5e_189']//following::ul/li//p[text()='" + Pub + "']").click();
            page.locator(jor_acrm).fill(J_acrm);
            page.locator(jor_fullname).fill(J_name);
            LocalDate currentDate = LocalDate.now();
            String formattedDate = currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
            page.locator(jor_received_date).fill(formattedDate);
            page.locator(recto_facing).click();
            page.locator(layout_1).click();
            page.locator(layout_2).click();
            page.locator(trimSizeWidth).fill("1");
            page.locator(trimSizeHeight).fill("2");
            page.locator(remarks).fill("Test Remarks");
            page.locator(category_1).click();
            page.locator(category_2_technicalEdit).click();
            page.locator(category_2_typeSetting).click();
            page.locator(category_3_CopyEdit).click();
            page.locator(copyEditLevel_1).click();
            page.locator(copyEditLevel_2).click();
            page.locator(pubType_1).click();
            page.locator(pubType_CheckAll).click();
            page.locator(G_FPdaysOfLatex).fill("1");
            page.locator(G_FPdaysOfGraphics).fill("1");
            page.locator(G_FPdaysOfPreEdit).fill("1");
            page.locator(G_FPdaysOfCopyEdit).fill("1");
            page.locator(G_FPdaysOfTypeSetting).fill("1");
            page.locator(G_FPdaysOfQC).fill("1");
            page.locator(FS_FPdaysOfLatex).fill("1");
            page.locator(FS_FPdaysOfGraphics).fill("1");
            page.locator(FS_FPdaysOfPreEdit).fill("1");
            page.locator(FS_FPdaysOfCopyEdit).fill("1");
            page.locator(FS_FPdaysOfTypeSetting).fill("1");
            page.locator(FS_FPdaysOfQC).fill("1");
            page.evaluate("window.scrollBy(0, 320)");
            page.locator(G_AUdaysOfPage).fill("1");
            page.locator(G_AUdaysOfQC).fill("1");
            page.locator(FS_AUdaysOfPage).fill("1");
            page.locator(FS_AUdaysOfQC).fill("1");
            page.locator(G_PEdaysOfPage).fill("1");
            page.locator(G_PEdaysOfQC).fill("1");
            page.locator(FS_PEdaysOfPage).fill("1");
            page.locator(FS_PEdaysOfQC).fill("1");
            page.evaluate("window.scrollBy(0, 320)");
            page.locator(G_ONFdaysOfPage).fill("1");
            page.locator(G_ONFdaysOfQC).fill("1");
            page.locator(G_ONFdaysOfXML).fill("1");
            page.locator(FS_ONFdaysOfPage).fill("1");
            page.locator(FS_ONFdaysOfQC).fill("1");
            page.locator(FS_ONFdaysOfXML).fill("1");

            page.locator(G_ISSdaysOfPage).fill("1");
            page.locator(G_ISSdaysOfQC).fill("1");
            page.locator(FS_ISSdaysOfPage).fill("1");
            page.locator(FS_ISSdaysOfQC).fill("1");
            page.locator(G_PrintdaysOfPage).fill("1");
            page.locator(G_PrintdaysOfQC).fill("1");
            page.locator(G_PrintdaysOfXML).fill("1");
            page.locator(FS_PrintdaysOfPage).fill("1");
            page.locator(FS_PrintdaysOfQC).fill("1");
            page.locator(FS_PrintdaysOfXML).fill("1");
            fileChooser = page.waitForFileChooser(() -> page.locator(styleTemplateUploadButton).click());
            fileChooser.setFiles(Paths.get("ems_journal.sty"));
            fileChooser = page.waitForFileChooser(() -> page.locator(guideLineUploadButton).click());
            fileChooser.setFiles(Paths.get("EMS_Press.pdf"));

            page.locator(addButton).click();
            page.locator(alertCloseButton).click();
            page.locator(managemenu).click();

        }

    public void adduser(String name,String employeeid,String designation,String email,String gender,String departmentname,String role)
    {
        page.locator(users).click();
        page.locator(adduserbutton).click();
       /*
        page.locator(sakthiuser).hover();
        page.locator(EditUser).click();*/
        page.locator(empname).fill(name);
        page.locator(employee_id).fill(employeeid);

        Locator desigdropdown= page.locator(designationdropdown);
        desigdropdown.selectOption(new SelectOption().setLabel(designation));



        page.locator(AccessUserdropdown).click();
        page.locator(graphicsdeptaccess).click();
        page.locator(user_mail).fill(email);

        Locator genderdropdown = page.locator(slectgender);
        genderdropdown.selectOption(new SelectOption().setLabel(gender));
        Locator deptdropdown=page.locator(selectdepartment);
        deptdropdown.selectOption(new SelectOption().setLabel(departmentname));
        page.locator(pubdropdown).click();
        page.locator(publisherselect).click();
        Locator rolename= page.locator(userrole);
        rolename.selectOption(new SelectOption().setLabel(role));
        page.locator(useraddbutton).click();




        // Select by value

    }



    public void ReloadDashBoard() {
        page.reload();
    }
}










