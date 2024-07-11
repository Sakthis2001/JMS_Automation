package org.lms.pages;

import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Page;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddPublisherManualPage {
    private Page page;
    FileChooser fileChooser;


    private  String baseicon="//img[@src='/jms/src/assets/GeneralIcons/shortcuts.svg']";
    private String addpubicon="id=add_publisher";


    private String pub_acronym="//input[@data-testid='publisher-acronym']";
    private String pub_name_textbox="//input[@data-testid='publisher-name']";
    private String pub_mail_textbox="//input[@data-testid='email-account']";
    private String desc_inputbox="//*[@name='description']";
    private String selectdateinput="//input[@type='date']";
    private String pub_location="//input[@data-testid='publisher-location']";
    private String ftpusername="//input[@data-testid='ftp-user-name']";
    private String ftppassword="//input[@data-testid='ftp-password']";
    private String ftp_initial_directory="//input[@data-testid='ftp-initial-directory']";
    private String daysforlatexnormalization="(//h2[text()='General']//following::input[@data-testid='days-for-latex-normalization'])[1]";
    private String daysforgraphics="(//h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-graphics'])[1]";
    private String daysforPreediting="(//h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-pre-editing'])[1]";
    private String daysforcopyediting="(//h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-copyediting'])[1]";
    private String daysfortypesettings="(//h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-typesetting'])[1]";
    private String daysforqc="(//h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-qc'])[1]";
    private String daysforaupag="(//h2[text()='General']//following::h3[text()='TAT for AU Revises ']//following::input[@data-testid='number-of-days-for-pagination'])[1]";
    private String daysforauqc="(//h2[text()='General']//following::h3[text()='TAT for AU Revises ']//following::input[@data-testid='number-of-days-for-qc'])[1]";
    private String daysforpepag="(//h2[text()='General']//following::h3[text()=' TAT for PE Revises ']//following::input[@data-testid='number-of-days-for-pagination'])[1]";
    private String daysforpeqc="(//h2[text()='General']//following::h3[text()=' TAT for PE Revises ']//following::input[@data-testid='number-of-days-for-qc'])[1]";
    private String daysforonlinepag="(//h2[text()='General']//following::h3[text()=' TAT for Online First ']//following::input[@data-testid='hours-or-days-for-Pagination'])[1]";
    private String daysforonlineqc="(//h2[text()='General']//following::h3[text()=' TAT for Online First ']//following::input[@data-testid='hours-or-days-for-QC'])[1]";
    private String daysforonlinexml="(//h2[text()='General']//following::h3[text()=' TAT for Online First ']//following::input[@data-testid='hours-days-for-xml'])[1]";
    private String IssuePag="(//h2[text()='General']//following::h3[text()=' TAT for Issue']//following::input[@data-testid='number-of-days-for-pagination'])[1]";
    private String IssueQC="(//h2[text()='General']//following::h3[text()=' TAT for Issue']//following::input[@data-testid='number-of-days-for-qc'])[1]";
    private String printpag="(//h2[text()='General']//following::h3[text()=' TAT for Print/Web ']//following::input[@data-testid='number-of-days-for-pagination'])[1]";
    private String printQC="(//h2[text()='General']//following::h3[text()=' TAT for Print/Web ']//following::input[@data-testid='number-of-days-for-qc'])[1]";
    private String printxml="(//h2[text()='General']//following::h3[text()=' TAT for Print/Web ']//following::input[@data-testid='number-of-days-for-xml'])[1]";
    private String addbutton="//*[@data-testid='submit-button']";

    private String Imageuploadbutton="//button[text()='Upload Image']";

    private String styletemplate="//*[@id='styles-upload']//preceding::div[1]";

    private String guidelinesdoc="//*[@id='guidelines-upload']//preceding::div[1]";
    private String CopyTat="//*[@title='Replicate values from General to Fasttrack']";
    private String CopyTatConfirm="//*[text()='Yes']";
    private String managemenu="//div[@id='root']//following::p[text()='Manage']";

    //webelement for fasttrack

    private String f_LatexNormal="//h2[text()='Fasttrack']//following::h3[text()='TAT for First Proof']//following::input[@data-testid='days-for-latex-normalization']";
    private String f_graphics="//h2[text()='Fasttrack']//following::h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-graphics']";
    private String f_preedit="//h2[text()='Fasttrack']//following::h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-pre-editing']";
    private String f_copyedit="//h2[text()='Fasttrack']//following::h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-copyediting']";

    private String f_ts="//h2[text()='Fasttrack']//following::h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-typesetting']";
    private String f_qc="(//h2[text()='Fasttrack']//following::h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-qc'][1])";
    private  String f_au_pag="//h2[text()='Fasttrack']//following::h3[text()='TAT for AU Revises ']//following::input[@data-testid='number-of-days-for-pagination'][1]";
    private String f_au_qc="//h2[text()='Fasttrack']//following::h3[text()='TAT for AU Revises ']//following::input[@data-testid='number-of-days-for-qc'][1]";
    private String f_pe_pag="//h2[text()='Fasttrack']//following::h3[text()=' TAT for PE Revises ']//following::input[@data-testid='number-of-days-for-pagination'][1]";
    private String f_pe_qc="//h2[text()='Fasttrack']//following::h3[text()=' TAT for PE Revises ']//following::input[@data-testid='number-of-days-for-qc'][1]";
    private String f_o_pag="//h2[text()='Fasttrack']//following::h3[text()=' TAT for Online First ']//following::input[@data-testid='hours-or-days-for-Pagination']";
    private String f_o_qc="//h2[text()='Fasttrack']//following::h3[text()=' TAT for Online First ']//following::input[@data-testid='hours-or-days-for-QC']";
    private String f_o_xml="//h2[text()='Fasttrack']//following::h3[text()=' TAT for Online First ']//following::input[@data-testid='hours-days-for-xml']";
    private String f_iss_pag="//h2[text()='Fasttrack']//following::h3[text()=' TAT for Issue']//following::input[@data-testid='number-of-days-for-pagination'][1]";
    private String f_iss_qc="//h2[text()='Fasttrack']//following::h3[text()=' TAT for Issue']//following::input[@data-testid='number-of-days-for-qc'][1]";
    private String f_priweb_pag="//h2[text()='Fasttrack']//following::h3[text()=' TAT for Print/Web ']//following::input[@data-testid='number-of-days-for-pagination'][1]";
    private String f_printweb_qc="//h2[text()='Fasttrack']//following::h3[text()=' TAT for Print/Web ']//following::input[@data-testid='number-of-days-for-qc']";
    private String f_printweb_xml="//h2[text()='Fasttrack']//following::h3[text()=' TAT for Print/Web ']//following::input[@data-testid='number-of-days-for-xml']";
    private String addjournalicon="id=add_journal";
    private String verifyerrormsgforpub="//*[text()='JMS - Add Publisher']//following::div[2]";
    private String updtaealerttext="//*[text()='JMS - Add Publisher']//following::span[1]";
    private String addalertclose="//h2[text()='JMS - Add Publisher']//following::span[1]";
    private String updatealertclose="//h2[text()='JMS - Update Publisher']//following::span[1]";
    private String verifypubforlog="//th[text()='k']";
    private String viewtype="";
    private String pubviewtype="";
    private String updatebutton="//button[text()='Update']";
    private String updatealert="//h2[text()='JMS - Update Publisher']//following::span[1]";
    private String reuploadbutton="//*[text()='Reupload']";
    private String journal_pub_drop="id=publisher";
    private String journal_pub_list="//*[@id='publisher']//following::li";
    public String  f_latex_val;
    public String f_graph_val;
    public  String f_pre_val;
    public String f_copy_edit_val;
    public String f_ts_val;
    public String f_qc_val;


    public AddPublisherManualPage(Page page)
    {
        this.page=page;
    }

    public void doaddpub(String acro,String pub) throws InterruptedException {
        page.locator(baseicon).click();
        page.locator(addpubicon).click();

        page.locator(pub_acronym).fill(acro);
        page.locator(pub_name_textbox).fill(pub);
        // Assert.assertEquals(page.locator(pub_acronym).inputValue(), a, "pub_acronym not filled correctly");
        page.locator(pub_mail_textbox).fill("sak@gmail.com");
        page.locator(desc_inputbox).fill("1");
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        page.locator(selectdateinput).fill(formattedDate);
        page.locator(selectdateinput).fill(formattedDate);
        page.locator(pub_location).fill("1");
        page.locator(ftpusername).fill("1");
        page.locator(ftppassword).fill("1");


        page.locator(ftp_initial_directory).fill("1");
        page.locator(daysforlatexnormalization).fill("1");
        page.locator(daysforgraphics).fill("1");
        page.locator(daysforPreediting).fill("1");
        page.locator(daysforcopyediting).fill("1");
        page.locator(daysforlatexnormalization).fill("1");
        page.locator(daysfortypesettings).fill("1");
        page.locator(daysforqc).fill("1");
        page.locator(daysforaupag).fill("1");
        page.locator(daysforauqc).fill("1");
        page.locator(daysforpepag).fill("1");
        page.locator(daysforpeqc).fill("1");
        page.locator(daysforonlinepag).fill("1");
        page.locator(daysforonlineqc).fill("1");
        page.locator(daysforonlinexml).fill("1");
        page.locator(IssuePag).fill("1");
        page.locator(IssueQC).fill("1");
        page.locator(printpag).fill("1");
        page.locator(printQC).fill("1");
        //assertThat(page.locator(printxml)).isAttached();
        page.locator(printxml).fill("1");

        page.locator(CopyTat).click();
        page.locator(CopyTatConfirm).click();







        fileChooser = page.waitForFileChooser(() -> page.locator(Imageuploadbutton).click());
        fileChooser.setFiles(Paths.get("Automation.jpg"));



        fileChooser = page.waitForFileChooser(() -> page.locator(styletemplate).click());
        fileChooser.setFiles(Paths.get("ABC.sty"));


        fileChooser = page.waitForFileChooser(() -> page.locator(guidelinesdoc).click());
        fileChooser.setFiles(new Path[] {
                Paths.get("guidelines.docx"),
                Paths.get("test2.docx"),
                Paths.get("test3.docx")

        });

        page.locator(CopyTat).click();


        page.locator(CopyTatConfirm).click();

        Thread.sleep(10000000);






        // page.locator(addbutton).click();


    }
}
