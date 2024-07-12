package org.lms.pages;

import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Page;
import utils.ExcelUtils;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;



public class AddPublisherPage {

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


    public AddPublisherPage(Page page) {
        this.page = page;
    }

    public void verifyAddPublisherIconIsClickable() {
        page.locator(baseicon).click();
        page.locator(addpubicon).click();

    }


    public String addpublisher(String acro, String pub, String c, String d, String e, String f, String g, String h, String i, String j, String k, String l, String m, String n, String o, String p, String q, String r, String s, String t, String u, String v, String w, String x, String y, String z, String aa) {

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
        page.locator(managemenu).click();

        String val = page.locator("//th[text()='" + acro + "']").textContent();
        return val;


    }

    public String AddPublisherWithAllData(String a, String b, String c, String d, String e, String f, String g, String h, String i, String j, String k, String l, String m, String n, String o, String p, String q, String r, String s, String t, String u, String v, String w, String x, String y, String z, String aa, String bb, String cc, String dd, String ee, String ff, String gg, String hh, String ii, String jj, String kk, String ll, String mm, String nn, String oo, String pp, String qq, String rr, String ss) {
        page.locator(pub_acronym).fill(a);
        page.locator(pub_name_textbox).fill(b);
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
        page.locator(daysforlatexnormalization).fill(i);
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
        page.locator(printxml).fill(aa);
        page.locator(f_LatexNormal).fill(bb);
        page.locator(f_graphics).fill(cc);
        page.locator(f_preedit).fill(dd);
        page.locator(f_copyedit).fill(ee);
        page.locator(f_ts).fill(ff);
        page.locator(f_qc).fill(gg);
        page.locator(f_au_pag).fill(hh);
        page.locator(f_au_qc).fill(ii);
        page.locator(f_pe_pag).fill(jj);
        page.locator(f_pe_qc).fill(kk);
        page.locator(f_o_pag).fill(ll);
        page.locator(f_o_qc).fill(mm);
        page.locator(f_o_xml).fill(nn);
        page.locator(f_iss_pag).fill(oo);
        page.locator(f_iss_qc).fill(pp);
        page.locator(f_priweb_pag).fill(qq);
        page.locator(f_printweb_qc).fill(rr);
        page.locator(f_printweb_xml).fill(ss);


        fileChooser = page.waitForFileChooser(() -> page.locator(Imageuploadbutton).click());
        fileChooser.setFiles(Paths.get("Automation.jpg"));

        fileChooser = page.waitForFileChooser(() -> page.locator(styletemplate).click());
        fileChooser.setFiles(Paths.get("ABC.sty"));


        fileChooser = page.waitForFileChooser(() -> page.locator(guidelinesdoc).click());
        fileChooser.setFiles(Paths.get("guidelines.docx"));
        page.locator(addbutton).click();
        page.locator(addalertclose).click();
        page.locator(managemenu).click();
        String val = page.locator("//th[text()='" + a + "']").textContent();
        return val;


    }

    public String getacrronym() {
        page.locator(pub_acronym).fill("1");
        return page.locator(pub_acronym).textContent();

    }

    public void copytat() {
        page.locator(CopyTat).click();
        page.locator(CopyTatConfirm).click();
    }

    public void verify_Tat_values(String a, String b, String c, String d, String e, String f, String g, String h, String i, String j, String k, String l, String m, String n, String o, String p, String q, String r, String s, String t, String u, String v, String w, String x, String y, String z, String aa) {
        page.locator(pub_acronym).fill(a);
        page.locator(pub_name_textbox).fill(b);
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
        page.locator(daysforlatexnormalization).fill(i);
        page.locator(daysforgraphics).fill(j);
        page.locator(daysforPreediting).fill(k);
        page.locator(daysforcopyediting).fill(l);
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
        page.locator(CopyTat).click();

        page.locator(CopyTatConfirm).click();
        f_latex_val = page.locator(f_LatexNormal).inputValue();
        f_graph_val = page.locator(f_graphics).inputValue();
        f_pre_val = page.locator(f_preedit).inputValue();
        f_copy_edit_val = page.locator(f_copyedit).inputValue();
        f_ts_val = page.locator(f_ts).inputValue();
        f_qc_val = page.locator(f_qc).inputValue();


    }


    public String verifyaddpub(String a, String b, String c, String d, String e, String f, String g, String h, String i, String j, String k, String l, String m, String n, String o, String p, String q, String r, String s, String t, String u, String v, String w, String x, String y, String z, String aa) {
        page.locator(pub_acronym).fill(a);
        page.locator(pub_name_textbox).fill(b);
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
        page.locator(daysforlatexnormalization).fill(i);
        page.locator(daysforgraphics).fill(j);
        page.locator(daysforPreediting).fill(k);
        page.locator(daysforcopyediting).fill(l);
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
        page.locator(CopyTat).click();

        page.locator(CopyTatConfirm).click();
        f_latex_val = page.locator(f_LatexNormal).inputValue();
        f_graph_val = page.locator(f_graphics).inputValue();
        f_pre_val = page.locator(f_preedit).inputValue();
        f_copy_edit_val = page.locator(f_copyedit).inputValue();
        f_ts_val = page.locator(f_ts).inputValue();
        f_qc_val = page.locator(f_qc).inputValue();
        fileChooser = page.waitForFileChooser(() -> page.locator(Imageuploadbutton).click());
        fileChooser.setFiles(Paths.get("Automation.jpg"));

        fileChooser = page.waitForFileChooser(() -> page.locator(styletemplate).click());
        fileChooser.setFiles(Paths.get("ABC.sty"));


        fileChooser = page.waitForFileChooser(() -> page.locator(guidelinesdoc).click());
        fileChooser.setFiles(Paths.get("guidelines.docx"));
        page.locator(addbutton).click();
        page.locator(addalertclose).click();
        page.locator(managemenu).click();
        page.locator("//th[text()='" + a + "']//following::span[@data-target='#dropright'][1]").click();
        page.locator("//th[text()='" + a + "']//following::div[@id='dropright']/div[text()='Edit Publisher']").click();
        //String text=page.locator("//*[@id='real_input']//following::span[text()='"+c+"']").textContent();

        return f_latex_val;
    }


    public String VerifyPublisherNotDuplicate(String a, String b) {


        page.locator(pub_acronym).fill(a);
        page.locator(pub_name_textbox).fill(b);
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
        fileChooser.setFiles(Paths.get("guidelines.docx"));

        page.locator(addbutton).click();
        String text = page.locator(verifyerrormsgforpub).textContent();
        page.locator(addalertclose).click();
        page.reload();

        return text;
    }

    public String addpublisherwithlogo(String a, String b, String c) {
        page.locator(pub_acronym).fill(a);
        page.locator(pub_name_textbox).fill(b);
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
        fileChooser.setFiles(Paths.get(c));


        fileChooser = page.waitForFileChooser(() -> page.locator(styletemplate).click());
        fileChooser.setFiles(Paths.get("ABC.sty"));


        fileChooser = page.waitForFileChooser(() -> page.locator(guidelinesdoc).click());
        fileChooser.setFiles(Paths.get("guidelines.docx"));


        page.locator(addbutton).click();
        page.locator(addalertclose).click();
        page.locator(managemenu).click();
        page.locator("//th[text()='" + a + "']//following::span[@data-target='#dropright'][1]").click();
        page.locator("//th[text()='" + a + "']//following::div[@id='dropright']/div[text()='Edit Publisher']").click();
        String text = page.locator("//*[@id='real_input']//following::span[text()='" + c + "']").textContent();
        return text;


    }

    public void addpub(String a) {
        page.locator(pub_acronym).fill(a);
        page.locator(pub_name_textbox).fill("b");
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
        fileChooser.setFiles(Paths.get("guidelines.docx"));
        page.locator(addbutton).click();
        page.locator(addalertclose).click();


    }

    public String addpublisher(String a) {

        page.locator(pub_acronym).fill(a);
        page.locator(pub_name_textbox).fill("logotest");
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
        fileChooser.setFiles(Paths.get("guidelines.docx"));
        page.locator(addbutton).click();
        page.locator(addalertclose).click();
        page.locator(managemenu).click();
        page.locator("//th[text()='" + a + "']//following::span[@data-target='#dropright'][1]").click();
        page.locator("//th[text()='" + a + "']//following::div[@id='dropright']/div[text()='Edit Publisher']").click();

        //String tomorrow = (today.plusDays(1)).format(DateTimeFormatter.ISO_DATE);

        page.locator(selectdateinput).fill(tomorrow);
        page.locator(updatebutton).click();

        assertThat(page.locator(updatealert)).isVisible();
        String updatealerttext = page.locator(updtaealerttext).textContent();
        page.locator(updatealert).click();
        page.locator("//th[text()='" + a + "']//following::span[@data-target='#dropright'][1]").click();
        page.locator("//th[text()='" + a + "']//following::div[@id='dropright']/div[text()='Edit Publisher']").click();

        String date = page.locator(selectdateinput).inputValue();

        return date;
    }

    public String verifylog(String a, String b) {
        page.locator(managemenu).click();
        page.locator("//th[text()='" + a + "']//following::span[@data-target='#dropright'][1]").click();
        page.locator("//th[text()='" + a + "']//following::div[@id='dropright']/div[text()='Edit Publisher']").click();
        fileChooser = page.waitForFileChooser(() -> page.locator(reuploadbutton).click());
        fileChooser.setFiles(Paths.get(b));
        page.locator(updatebutton).click();
        assertThat(page.locator(updatealert)).isVisible();

        page.locator(updatealertclose).click();
        page.locator("//th[text()='" + a + "']//following::span[@data-target='#dropright'][1]").click();
        page.locator("//th[text()='" + a + "']//following::div[@id='dropright']/div[text()='Edit Publisher']").click();
        String imagename = page.locator("//span[text()='Reupload']//following::span[text()='" + b + "']").textContent();
        return imagename;


    }

    public List<String> pubavailinjournal(String a) {
        page.locator(managemenu).click();
        page.locator(baseicon).click();
        page.locator(addjournalicon).click();
        page.locator(journal_pub_drop).click();
        List<String> publist = page.locator(journal_pub_list).allInnerTexts();
        return publist;

    }

    public String checkrecentfiles() {
        fileChooser = page.waitForFileChooser(() -> page.locator(guidelinesdoc).click());
        fileChooser.setFiles(Paths.get("guidelines.docx"));
        String text = page.locator("//*[text()='guidelines.docx']//preceding::h2[1]").textContent();
        return text;

    }

    public String verifystyformat(String a) {
        fileChooser = page.waitForFileChooser(() -> page.locator(styletemplate).click());
        fileChooser.setFiles(Paths.get(a));
        String text = page.locator(style_error_text).textContent();
        page.locator(getStyle_error_alert).click();
        return text;
    }


    public String verifyfuidelinesdocuploadverification(String a) {
        fileChooser = page.waitForFileChooser(() -> page.locator(guidelinesdoc).click());
        fileChooser.setFiles(Paths.get(a));
        String text = page.locator(style_error_text).textContent();
        page.locator(getStyle_error_alert).click();
        return text;

    }

    public void addpub() {
        page.locator(pub_acronym).fill("pa");
        page.locator(pub_name_textbox).fill("Pentagon");
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

        /*fileChooser = page.waitForFileChooser(() -> page.locator(guidelinesdoc).click());
        fileChooser.setFiles(Paths.get("guidelines.docx"));*/

      /*  fileChooser = page.waitForFileChooser(() -> page.locator(Imageuploadbutton).click());
        fileChooser.setFiles(Paths.get("Automation.jpg"));*/

       /* fileChooser = page.waitForFileChooser(() -> page.locator(styletemplate).click());
        fileChooser.setFiles(Paths.get("ABC.sty"));*/



       /* fileChooser = page.waitForFileChooser(() -> page.locator(guidelinesdoc).click());
        fileChooser.setFiles(Paths.get("guidelines.docx"));*/


    }


    public int VerifyNoOfFileInTeamplateAndGuidelines() {
        //addpub();
        fileChooser = page.waitForFileChooser(() -> page.locator(guidelinesdoc).click());

        fileChooser.setFiles(new Path[]{
                Paths.get("guidelines.docx"),
                Paths.get("test2.docx"),
                Paths.get("test3.docx")
        });

        int text = page.locator(CountofGuideLinesFile).count();
        return text;


    }

    public String editstyfiles() {
        addpub();
        fileChooser = page.waitForFileChooser(() -> page.locator(Imageuploadbutton).click());
        fileChooser.setFiles(Paths.get("Automation.jpg"));

        fileChooser = page.waitForFileChooser(() -> page.locator(styletemplate).click());
        fileChooser.setFiles(Paths.get("ABC.sty"));

        fileChooser = page.waitForFileChooser(() -> page.locator(guidelinesdoc).click());
        fileChooser.setFiles(Paths.get("guidelines.docx"));

        page.locator(addbutton).click();
        page.locator(addalertclose).click();
        page.locator(managemenu).click();
        page.locator("//th[text()='pa']//following::span[@data-target='#dropright'][1]").click();
        page.locator("//th[text()='pa']//following::div[@id='dropright']/div[text()='Edit Publisher']").click();

        page.locator(editstyshowfielddropdown).click();
        String styfilename = page.locator(removestyfiles).textContent();
        if (styfilename.equals("styledoc.sty")) {
            page.locator(removestyfiles).click();
            page.locator(removefileclose).click();
            page.locator(reovefileyes).click();
        } else {
            page.locator(editstyshowfielddropdown).click();
        }

        fileChooser = page.waitForFileChooser(() -> page.locator(styletemplate).click());
        fileChooser.setFiles(Paths.get("styledoc.sty"));

        page.locator(updatebutton).click();
        assertThat(page.locator(updatealertclose)).isVisible();

        String text = page.locator("//h2[text()='JMS - Update Publisher']//following::div[text()='Publisher Updated Successfully']").textContent();

        page.locator(updatealertclose).click();

        return text;
        // page.locator(updatealert).click();
       /* page.locator(updatealertclose).click();
        page.locator(managemenu).click();
        page.locator("//th[text()='pa']//following::span[@data-target='#dropright'][1]").click();
        page.locator("//th[text()='pa']//following::div[@id='dropright']/div[text()='Edit Publisher']").click();
*/

        //String text=page.locator("//h2[text()='Latest Files']//following::li//following::p[@title='styledoc.sty']").textContent();


    }

    public void addpubconstant() {
        page.locator(pub_acronym).fill("fa");
        page.locator(pub_name_textbox).fill("Fazer");
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

       /* fileChooser = page.waitForFileChooser(() -> page.locator(styletemplate).click());
        fileChooser.setFiles(Paths.get("ABC.sty"));*/


        fileChooser = page.waitForFileChooser(() -> page.locator(guidelinesdoc).click());
        fileChooser.setFiles(Paths.get("guidelines.docx"));

        page.locator(addbutton).click();
    }

    public String EnsureStyMandatory() {
        // addpubconstant();
       /* fileChooser = page.waitForFileChooser(() -> page.locator(Imageuploadbutton).click());
        fileChooser.setFiles(Paths.get("Automation.jpg"));*/
        String text = page.locator(styuploaderrormsg).textContent();
        page.locator(uploadupdatealert).click();
        return text;


    }

    public void reloadpage() {
        page.reload();
    }

    public String bool1;
    public String bool2;

    public void EnsureArchiveAndListFiles(String a, String b, String c, String d) {
        addpub();
        fileChooser = page.waitForFileChooser(() -> page.locator(styletemplate).click());
        fileChooser.setFiles(Paths.get("ABC.sty"));

        fileChooser = page.waitForFileChooser(() -> page.locator(Imageuploadbutton).click());
        fileChooser.setFiles(Paths.get("Automation.jpg"));


        fileChooser = page.waitForFileChooser(() -> page.locator(guidelinesdoc).click());
        fileChooser.setFiles(new Path[]{
                Paths.get(a),
                Paths.get(b),
                Paths.get(c),
                Paths.get(d)


        });

       /* page.locator("//p[@title='Resume.pdf']//following::img[@title='move to archieve']").click();
       // page.locator("//p[@title='" + d + "']//following::img[@title='move to archieve']").click();*/

        page.locator("//h2[text()='Latest Files']//following::p[@title='" + a + "']//following::div[1]").click();
        page.locator("//h2[text()='Move File']//following::div//following::newbutton[text()='Yes']").click();
        page.locator("//h2[text()='Latest Files']//following::p[@title='" + d + "']//following::div[1]").click();
        page.locator("//h2[text()='Move File']//following::div//following::newbutton[text()='Yes']").click();


        page.locator(addbutton).click();
        page.locator(addalertclose).click();
        page.locator(baseicon).click();
        page.locator(addjournalicon).click();
        page.locator(journal_pub_drop).click();
        page.locator("//p[normalize-space()='pa']").click();
        page.locator("//h3[text()='GuideLines Document']//following::div[@title='Import from Publisher']").click();
        page.locator("//h2[text()='Import Guideline Files']//following::div//following::newbutton[text()='Yes']").click();
        bool1 = page.locator("//*[text()='Archive Files']//following::li/p[@title='" + a + "']").textContent();
        bool2 = page.locator("//*[text()='Archive Files']//following::li/p[@title='" + d + "']").textContent();

    }

    public String AddPubWithAtleastOneRecentFiles(String a, String b) {

        addpub();
      /*  page.locator(pub_acronym).fill("pp");
        page.locator(pub_name_textbox).fill("Peppentagon");
        page.locator(pub_mail_textbox).fill("sak@gmail.com");
        page.locator(desc_inputbox).fill("1");
        LocalDate today = LocalDate.now();
        LocalDate tomarrow=today.plusDays(1);

        String formattedDate = today.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String tomorrow = (today.plusDays(1)).format(DateTimeFormatter.ISO_DATE);


        page.locator(selectdateinput).fill(tomorrow);

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
        page.locator(CopyTatConfirm).click();*/
        fileChooser = page.waitForFileChooser(() -> page.locator(Imageuploadbutton).click());
        fileChooser.setFiles(Paths.get("Automation.jpg"));
        fileChooser = page.waitForFileChooser(() -> page.locator(styletemplate).click());
        fileChooser.setFiles(Paths.get("ABC.sty"));
        /*fileChooser = page.waitForFileChooser(() -> page.locator(guidelinesdoc).click());
        fileChooser.setFiles(Paths.get("guidelines.docx"));*/

        fileChooser = page.waitForFileChooser(() -> page.locator(guidelinesdoc).click());

        fileChooser.setFiles(new Path[]{
                Paths.get(a),
                Paths.get(b)

        });

        page.locator("//h2[text()='Latest Files']//following::p[@title='" + a + "']//following::div[1]").click();
        page.locator("//h2[text()='Move File']//following::div//following::newbutton[text()='Yes']").click();
        page.locator("//h2[text()='Latest Files']//following::p[@title='" + b + "']//following::div[1]").click();
        page.locator("//h2[text()='Move File']//following::div//following::newbutton[text()='Yes']").click();

        page.locator(addbutton).click();
        String text = page.locator(guidestyleerroralert).textContent();
        return text;


    }

    public void pubAdd(String a, String b) {
        page.locator(pub_acronym).fill(a);
        page.locator(pub_name_textbox).fill(b);
        page.locator(pub_mail_textbox).fill("sak@gmail.com");
        page.locator(desc_inputbox).fill("1");
        LocalDate today = LocalDate.now();
        LocalDate tomarrow = today.plusDays(1);

        String formattedDate = today.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String tomorrow = (today.plusDays(1)).format(DateTimeFormatter.ISO_DATE);


        page.locator(selectdateinput).fill(formattedDate);

        page.locator(pub_location).fill("1");
       /* page.locator(ftpusername).fill("1");
        page.locator(ftppassword).fill("1");


        page.locator(ftp_initial_directory).fill("1");*/
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
    }

    public String AddPubWithoutFtp(String a, String b) {
        pubAdd(a, b);

        fileChooser = page.waitForFileChooser(() -> page.locator(Imageuploadbutton).click());
        fileChooser.setFiles(Paths.get("Automation.jpg"));
        fileChooser = page.waitForFileChooser(() -> page.locator(styletemplate).click());
        fileChooser.setFiles(Paths.get("ABC.sty"));
        fileChooser = page.waitForFileChooser(() -> page.locator(guidelinesdoc).click());
        fileChooser.setFiles(Paths.get("guidelines.docx"));
        page.locator(addbutton).click();
        page.locator(addalertclose).click();
        page.locator(managemenu).click();
        String val = page.locator("//th[text()='" + a + "']").textContent();
        return val;


    }

    public List<String> AddPubWithPartiallFTPData(String a, String b, String c, String d, String e, String f) {
        pubAdd(a, b);
        fileChooser = page.waitForFileChooser(() -> page.locator(Imageuploadbutton).click());
        fileChooser.setFiles(Paths.get("Automation.jpg"));
        fileChooser = page.waitForFileChooser(() -> page.locator(styletemplate).click());
        fileChooser.setFiles(Paths.get("ABC.sty"));
        fileChooser = page.waitForFileChooser(() -> page.locator(guidelinesdoc).click());
        fileChooser.setFiles(Paths.get("guidelines.docx"));

    /*    String ftphost=c;
        String ftpusername=d;
        String ftppassword=e;
        String ftpinitdir=f;*/
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);
        System.out.println(f);
        int i=0;
        while (i<=3)
        {
            if (i == 0) {
                page.locator(ftphost).fill(c);
            } else if (i == 1) {
                page.locator(ftpusername).fill(d);

            } else if (i == 2) {
                page.locator(ftppassword).fill(e);

            } else {
                page.locator(ftp_initial_directory).fill(f);
            }
            i++;
        }


        page.locator(addbutton).click();
        page.locator(addalertclose).click();
        page.locator(managemenu).click();
        String acroname1 = page.locator("//th[text()='" + a + "']").textContent();
        String acroname2 = page.locator("//th[text()='" + a + "']").textContent();
        String acroname3 = page.locator("//th[text()='" + a + "']").textContent();
        String acroname4 = page.locator("//th[text()='" + a + "']").textContent();

        List<String> acro = new ArrayList<>();

        acro.add(acroname1);
        acro.add(acroname2);
        acro.add(acroname3);
        acro.add(acroname4);


        return acro;

    }


    public List<String> editcopytat(String a, String b) {
        pubAdd(a, b);
        fileChooser = page.waitForFileChooser(() -> page.locator(Imageuploadbutton).click());
        fileChooser.setFiles(Paths.get("Automation.jpg"));
        fileChooser = page.waitForFileChooser(() -> page.locator(styletemplate).click());
        fileChooser.setFiles(Paths.get("ABC.sty"));
        fileChooser = page.waitForFileChooser(() -> page.locator(guidelinesdoc).click());
        fileChooser.setFiles(Paths.get("guidelines.docx"));
        page.locator(f_LatexNormal).fill("2");
        page.locator(f_copyedit).fill("4");
        page.locator(f_iss_qc).fill("3");
        page.locator(addbutton).click();
        page.locator(addalertclose).click();
        page.locator(managemenu).click();
        page.locator("//th[text()='"+a+"']//following::span[@data-target='#dropright'][1]").click();
        page.locator("//th[text()='"+a+"']//following::div[@id='dropright']/div[text()='Edit Publisher']").click();
        List<String> tateditval = new ArrayList<>();

        tateditval.add(page.locator(f_LatexNormal).inputValue());
        tateditval.add(page.locator(f_copyedit).inputValue());
        tateditval.add(page.locator(f_iss_qc).inputValue());

        return tateditval;
    }

    public List<String> editFtpvalue(String a, String b)
    {
        pubAdd(a, b);
        page.locator(ftpusername).fill("FTP");
        page.locator(ftppassword).fill("FTPpassword");
        page.locator(ftphost).fill("130.23.45");
        page.locator(ftp_initial_directory).fill("./ftp/allfiles");
        fileChooser = page.waitForFileChooser(() -> page.locator(Imageuploadbutton).click());
        fileChooser.setFiles(Paths.get("Automation.jpg"));
        fileChooser = page.waitForFileChooser(() -> page.locator(styletemplate).click());
        fileChooser.setFiles(Paths.get("ABC.sty"));
        fileChooser = page.waitForFileChooser(() -> page.locator(guidelinesdoc).click());
        fileChooser.setFiles(Paths.get("guidelines.docx"));
        page.locator(addbutton).click();
        page.locator(addalertclose).click();
        page.locator(managemenu).click();
        page.locator("//th[text()='"+a+"']//following::span[@data-target='#dropright'][1]").click();
        page.locator("//th[text()='"+a+"']//following::div[@id='dropright']/div[text()='Edit Publisher']").click();
        page.locator(ftphost).fill("140.23.45");
        page.locator(ftpusername).fill("ftp username");

        page.locator(updatebutton).click();
        page.locator(updatealertclose).click();
        page.locator(managemenu).click();
        page.locator("//th[text()='"+a+"']//following::span[@data-target='#dropright'][1]").click();
        page.locator("//th[text()='"+a+"']//following::div[@id='dropright']/div[text()='Edit Publisher']").click();
        List<String> ftpupdated=new ArrayList<>();
        ftpupdated.add(page.locator(ftphost).inputValue());
        ftpupdated.add(page.locator(ftpusername).inputValue());
        return ftpupdated;

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


    public String EditStyContInPubANotPubB(String a, String b)
    {
        pubAdd(a, b);
        uploadfiles();

        page.locator(addbutton).click();
        page.locator(addalertclose).click();
        page.locator(managemenu).click();
        return a;

    }

    public void EditAStyContent(String a) throws InterruptedException {
       // EditStyContInPubANotPubB( a,b);
        page.locator("//th[text()='"+a+"']//following::span[@data-target='#dropright'][1]").click();
        page.locator("//th[text()='"+a+"']//following::div[@id='dropright']/div[text()='Edit Publisher']").click();
        page.locator(StyShow).click();
        page.locator("//p[text()='ABC.sty']//following::img[@title='Edit']").click();
        assertThat(page.locator("(//button//preceding::span)[6]")).isVisible();
        page.locator("(//button//preceding::span)[6]").click();
        page.keyboard().press("Control+A");
        page.keyboard().press("Delete");

        // Optionally, fill the element with new text
      //  page.locator("(//button//preceding::span)[6]").fill("New text to be pasted");


        // page.wait(10000);

        page.locator("(//*[text()='ABC.sty'])[2]//following::button[text()='Save']").click();

        assertThat(page.locator("(//*[text()='ABC.sty'])[2]//following::button[text()='Saved']")).isAttached();
        page.locator("(//*[text()='ABC.sty'])[2]//following::img[@title='Close']").click();
        page.locator(updatebutton).click();
       // page.locator(updatealert).click();
        page.locator(updatealertclose).click();


    }

    public void EditBStyContent(String b) {
        page.locator("//th[text()='"+b+"']//following::span[@data-target='#dropright'][1]").click();
        page.locator("//th[text()='"+b+"']//following::div[@id='dropright']/div[text()='Edit Publisher']").click();
        page.locator(StyShow).click();
        page.locator("//p[text()='ABC.sty']//following::img[@title='Edit']").click();
        assertThat(page.locator("(//button//preceding::span)[6]")).isVisible();
        page.locator("(//button//preceding::span)[6]").click();
        page.keyboard().press("Control+A");
        page.keyboard().press("Delete");

        // Optionally, fill the element with new text
        //  page.locator("(//button//preceding::span)[6]").fill("New text to be pasted");


        // page.wait(10000);

        page.locator("(//*[text()='ABC.sty'])[2]//following::button[text()='Save']").click();

        assertThat(page.locator("(//*[text()='ABC.sty'])[2]//following::button[text()='Saved']")).isAttached();
        page.locator("(//*[text()='ABC.sty'])[2]//following::img[@title='Close']").click();
        page.locator(updatebutton).click();
        // page.locator(updatealert).click();
        page.locator(updatealertclose).click();
    }
}



