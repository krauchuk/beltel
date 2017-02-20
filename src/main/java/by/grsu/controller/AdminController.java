package by.grsu.controller;

import by.grsu.entity.*;
import by.grsu.service.Impl.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.*;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private RepnoteResServiceImpl repnoteResService;

    @Autowired
    private ReportingNoticeServiceImpl reportingNoticeService;

    @RequestMapping(value = "/new_notices", method = RequestMethod.GET)
    public String newRequest(ModelMap modelMap) {
        return "new_notices";
    }

    @RequestMapping(value = "/all_notices", method = RequestMethod.GET)
    public String allRequest(ModelMap modelMap) {
        modelMap.addAttribute("reportingNotice", reportingNoticeService.getAll());
        return "all_notices";
    }

    @RequestMapping(value = "/edit_db", method = RequestMethod.GET)
    public String editDB() {
        return "edit_db";
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteReportingNotice(@PathVariable long id) {
        repnoteResService.deleteByNoticeId(id);
        reportingNoticeService.delete(id);
        return "redirect:/all_notices";
    }

    @RequestMapping(value = "/change_status/{id}")
    public String changeReportingNoticeStatus(@PathVariable long id) {
        ReportingNotice reportingNotice = reportingNoticeService.getById(id);
        if (reportingNotice.isStatus() == false) {
            Timestamp stamp = new Timestamp(System.currentTimeMillis());
            Date date = new Date(stamp.getTime());
            reportingNotice.setDateDone(date);
            reportingNotice.setStatus(true);
            reportingNoticeService.save(reportingNotice);
            return "redirect:/notice/" + id;
        }
        reportingNotice.setStatus(false);
        reportingNoticeService.save(reportingNotice);
        return "redirect:/notice/" + id;
    }

    @RequestMapping(value = "/admin/get_pdf/{id}")
    public void generatePdfFile(@PathVariable long id, HttpServletResponse response, HttpServletRequest request){
        ReportingNotice reportingNotice = reportingNoticeService.getById(id);
        Employer employer = reportingNotice.getEmployers_id();
        List<RepnoteRes> repnoteRes = repnoteResService.getByNoticeId(id);
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
//        String fontPath = this.getClass().getResource("/font/times.ttf").toString();
        try {
            URL resource = request.getServletContext().getResource("/font/times.ttf");
            String fontPath = resource.getPath();
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            BaseFont baseFont = BaseFont.createFont(fontPath, "Cp1251", true);
            Font font = new Font(baseFont, 14);
            Font fontSmall = new Font(baseFont, 8);

            float tablePadding = 4f;
            float tableSpacingBefore = 10;
            float tableSpacingAfter = 5;

            PdfPTable headTable = new PdfPTable(2);
            headTable.setSpacingBefore(tableSpacingBefore);
            headTable.setSpacingAfter(tableSpacingAfter);
            headTable.setWidthPercentage(100);

            PdfPCell headTableCellEmpty = new PdfPCell(new Phrase("", font));
            headTableCellEmpty.setBorder(Rectangle.NO_BORDER);
            headTable.addCell(headTableCellEmpty);
            String headText = "Директору *filial*";
            PdfPCell headTableCell = new PdfPCell(new Phrase(headText + "\nРУП «Белтелеком»\n *divName*", font));
            headTable.setWidths(new float[]{65 - headText.length(), headText.length()});
            headTableCell.setUseAscender(true);
            headTableCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headTableCell.setPadding(tablePadding);
            headTableCell.setBorder(Rectangle.NO_BORDER);
            headTable.addCell(headTableCell);
            document.add(headTable);

            Paragraph paragraph = new Paragraph("ДОКЛАДНАЯ ЗАПИСКА\n_____________ № _________\n" + "*city*"
                    + "\n\n", font);
            paragraph.setAlignment(Element.ALIGN_LEFT);
            document.add(paragraph);

            paragraph = new Paragraph("О предоставлении доступа\nк ресурсам СКИТС", font);
            paragraph.setAlignment(Element.ALIGN_LEFT);
            paragraph.setLeading(15f);
            document.add(paragraph);

            PdfPTable infoTable = new PdfPTable(2);
            infoTable.setSpacingBefore(tableSpacingBefore);
            infoTable.setSpacingAfter(tableSpacingAfter);
            infoTable.setWidthPercentage(100);

            PdfPCell infoTableC1_1 = new PdfPCell(new Phrase("Структурное подразделение", font));
            infoTableC1_1.setUseAscender(true);
            infoTableC1_1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            infoTableC1_1.setPadding(tablePadding);
            PdfPCell infoTableC1_2 = new PdfPCell(new Phrase("*подразделение*", font));
            infoTableC1_2.setUseAscender(true);
            infoTableC1_2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            infoTableC1_2.setPadding(tablePadding);
            infoTable.addCell(infoTableC1_1);
            infoTable.addCell(infoTableC1_2);

            PdfPCell infoTableC2_1 = new PdfPCell(new Phrase("Пользователь (Ф.И.О.)", font));
            infoTableC2_1.setUseAscender(true);
            infoTableC2_1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            infoTableC2_1.setPadding(tablePadding);
            PdfPCell infoTableC2_2 = new PdfPCell(new Phrase(employer.getFio(), font));
            infoTableC2_2.setUseAscender(true);
            infoTableC2_2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            infoTableC2_2.setPadding(tablePadding);
            infoTable.addCell(infoTableC2_1);
            infoTable.addCell(infoTableC2_2);

            PdfPCell infoTableC3_1 = new PdfPCell(new Phrase("Должность", font));
            infoTableC3_1.setUseAscender(true);
            infoTableC3_1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            infoTableC3_1.setPadding(tablePadding);
            PdfPCell infoTableC3_2 = new PdfPCell(new Phrase(employer.getPost_id().getName(), font));
            infoTableC3_2.setUseAscender(true);
            infoTableC3_2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            infoTableC3_2.setPadding(tablePadding);
            infoTable.addCell(infoTableC3_1);
            infoTable.addCell(infoTableC3_2);

            PdfPCell infoTableC4_1 = new PdfPCell(new Phrase("Рабочий телефон с кодом города", font));
            infoTableC4_1.setUseAscender(true);
            infoTableC4_1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            infoTableC4_1.setPadding(tablePadding);
            PdfPCell infoTableC4_2 = new PdfPCell(new Phrase(employer.getTelJob(), font));
            infoTableC4_2.setUseAscender(true);
            infoTableC4_2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            infoTableC4_2.setPadding(tablePadding);
            infoTable.addCell(infoTableC4_1);
            infoTable.addCell(infoTableC4_2);

            PdfPCell infoTableC5_1 = new PdfPCell(new Phrase("Дополнительно", font));
            infoTableC5_1.setUseAscender(true);
            infoTableC5_1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            infoTableC5_1.setPadding(tablePadding);
            String ipAddr = " ";
            ipAddr += reportingNotice.getIpAddr();
            PdfPCell infoTableC5_2 = new PdfPCell(new Phrase("IP:" + ipAddr, font));
            infoTableC5_2.setUseAscender(true);
            infoTableC5_2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            infoTableC5_2.setPadding(tablePadding);
            infoTable.addCell(infoTableC5_1);
            infoTable.addCell(infoTableC5_2);

            document.add(infoTable);

            paragraph = new Paragraph("Руководитель подразделения", font);
            paragraph.setAlignment(Element.ALIGN_LEFT);
            document.add(paragraph);
            paragraph = new Paragraph("______________      _______________", font);
            paragraph.setAlignment(Element.ALIGN_RIGHT);
            paragraph.setLeading(0);
            document.add(paragraph);

            paragraph = new Paragraph("«___» ____________ 201__г.", font);
            paragraph.setAlignment(Element.ALIGN_LEFT);
            document.add(paragraph);

            paragraph = new Paragraph("(подпись)                            "
                    + "                 (Ф.И.О.)", fontSmall);
            paragraph.setTabSettings(new TabSettings(190f));
            paragraph.add(Chunk.TABBING);
            paragraph.setAlignment(Element.ALIGN_RIGHT);
            paragraph.setLeading(-10f);
            document.add(paragraph);

            paragraph = new Paragraph(" ", font);
            paragraph.setLeading(15f);
            document.add(paragraph);

            PdfPTable resTable = new PdfPTable(3);
            resTable.setSpacingBefore(tableSpacingBefore);
            resTable.setSpacingAfter(tableSpacingAfter);
            resTable.setWidthPercentage(100);
            resTable.setWidths(new float[]{0.5f, 1.6f, 2});
            PdfPCell resTableHeadC1_1 = new PdfPCell(new Phrase("№п/п", font));
            resTableHeadC1_1.setUseAscender(true);
            resTableHeadC1_1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            resTableHeadC1_1.setHorizontalAlignment(Element.ALIGN_CENTER);
            resTableHeadC1_1.setPadding(tablePadding);
            PdfPCell resTableHeadC1_2 = new PdfPCell(new Phrase("Наименование ресурса", font));
            resTableHeadC1_2.setUseAscender(true);
            resTableHeadC1_2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            resTableHeadC1_2.setHorizontalAlignment(Element.ALIGN_CENTER);
            resTableHeadC1_2.setPadding(tablePadding);
            PdfPCell resTableHeadC1_3 = new PdfPCell(new Phrase("Режим доступа к ресурсу", font));
            resTableHeadC1_3.setUseAscender(true);
            resTableHeadC1_3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            resTableHeadC1_3.setHorizontalAlignment(Element.ALIGN_CENTER);
            resTableHeadC1_3.setPadding(tablePadding);
            resTable.addCell(resTableHeadC1_1);
            resTable.addCell(resTableHeadC1_2);
            resTable.addCell(resTableHeadC1_3);

            for(int i = 0; i < repnoteRes.size(); i++){
                PdfPCell resTableC1_1 = new PdfPCell(new Phrase("" + (i + 1), font));
                resTableC1_1.setUseAscender(true);
                resTableC1_1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                resTableC1_1.setPadding(tablePadding);
                PdfPCell resTableC1_2 = new PdfPCell(new Phrase(repnoteRes.get(i).getSubResource_id().getResource_id().getName() + "/" +
                        repnoteRes.get(i).getSubResource_id().getName(), font));
                resTableC1_2.setUseAscender(true);
                resTableC1_2.setVerticalAlignment(Element.ALIGN_MIDDLE);
                resTableC1_2.setPadding(tablePadding);
                PdfPCell resTableC1_3 = new PdfPCell(new Phrase(repnoteRes.get(i).getRegimeAccess_id().getName(), font));
                resTableC1_3.setUseAscender(true);
                resTableC1_3.setVerticalAlignment(Element.ALIGN_MIDDLE);
                resTableC1_3.setPadding(tablePadding);
                resTable.addCell(resTableC1_1);
                resTable.addCell(resTableC1_2);
                resTable.addCell(resTableC1_3);
            }

            document.add(resTable);


            paragraph = new Paragraph("Системный администратор", font);
            paragraph.setAlignment(Element.ALIGN_LEFT);
            document.add(paragraph);
            paragraph = new Paragraph("______________      _______________", font);
            paragraph.setAlignment(Element.ALIGN_RIGHT);
            paragraph.setLeading(0);
            document.add(paragraph);

            paragraph = new Paragraph("«___» ____________ 201__г.", font);
            paragraph.setAlignment(Element.ALIGN_LEFT);
            document.add(paragraph);

            paragraph = new Paragraph("(подпись)                            "
                    + "                 (Ф.И.О.)", fontSmall);
            paragraph.setTabSettings(new TabSettings(190f));
            paragraph.add(Chunk.TABBING);
            paragraph.setAlignment(Element.ALIGN_RIGHT);
            paragraph.setLeading(-10f);
            document.add(paragraph);

            paragraph = new Paragraph(" ", font);
            paragraph.setLeading(15f);
            document.add(paragraph);

            paragraph = new Paragraph("Согласовано:", font);
            paragraph.setAlignment(Element.ALIGN_LEFT);
            document.add(paragraph);

            paragraph = new Paragraph("Руководитель/зам.руководителя", font);
            paragraph.setAlignment(Element.ALIGN_LEFT);
            document.add(paragraph);
            paragraph = new Paragraph("______________      _______________", font);
            paragraph.setAlignment(Element.ALIGN_RIGHT);
            paragraph.setLeading(0);
            document.add(paragraph);

            paragraph = new Paragraph("«___» ____________ 201__г.", font);
            paragraph.setAlignment(Element.ALIGN_LEFT);
            document.add(paragraph);

            paragraph = new Paragraph("(подпись)                            "
                    + "                 (Ф.И.О.)", fontSmall);
            paragraph.setTabSettings(new TabSettings(190f));
            paragraph.add(Chunk.TABBING);
            paragraph.setAlignment(Element.ALIGN_RIGHT);
            paragraph.setLeading(-10f);
            document.add(paragraph);

            paragraph = new Paragraph(" ", font);
            paragraph.setLeading(15f);
            document.add(paragraph);

            paragraph = new Paragraph("Специалист по защите информации", font);
            paragraph.setAlignment(Element.ALIGN_LEFT);
            document.add(paragraph);
            paragraph = new Paragraph("______________      _______________", font);
            paragraph.setAlignment(Element.ALIGN_RIGHT);
            paragraph.setLeading(0);
            document.add(paragraph);


            paragraph = new Paragraph("«___» ____________ 201__г.", font);
            paragraph.setAlignment(Element.ALIGN_LEFT);
            document.add(paragraph);

            paragraph = new Paragraph("(подпись)                            "
                    + "                 (Ф.И.О.)", fontSmall);
            paragraph.setTabSettings(new TabSettings(190f));
            paragraph.add(Chunk.TABBING);
            paragraph.setAlignment(Element.ALIGN_RIGHT);
            paragraph.setLeading(-10f);
            document.add(paragraph);

            paragraph = new Paragraph(" ", font);
            paragraph.setLeading(15f);
            document.add(paragraph);

            document.close();
            response.setContentType("application/pdf");
            response.getOutputStream().flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
