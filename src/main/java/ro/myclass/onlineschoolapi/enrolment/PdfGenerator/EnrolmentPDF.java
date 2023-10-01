package ro.myclass.onlineschoolapi.enrolment.PdfGenerator;


import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import ro.myclass.onlineschoolapi.enrolment.model.Enrolment;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.List;

public class EnrolmentPDF {

    public List<Enrolment> enrolmentList;

    public EnrolmentPDF(List<Enrolment> enrolmentList) {
        this.enrolmentList = enrolmentList;
    }

    public void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();

        cell.setBackgroundColor(Color.CYAN);

        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

        font.setColor(Color.white);

        cell.setPhrase(new Phrase("ID",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Student name",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Course Name",font));
        table.addCell(cell);
    }

    public void writeDataTable(PdfPTable table){
        for(Enrolment enrolment : enrolmentList){
            table.addCell(String.valueOf(enrolment.getId()));
            table.addCell(String.valueOf(enrolment.getStudent().getLastName() + " " + enrolment.getStudent().getLastName()));
            table.addCell(String.valueOf(enrolment.getCourse().getName()));
        }
    }
}
