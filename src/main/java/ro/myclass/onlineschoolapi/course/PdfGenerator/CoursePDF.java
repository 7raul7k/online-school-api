package ro.myclass.onlineschoolapi.course.PdfGenerator;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import ro.myclass.onlineschoolapi.course.model.Course;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class CoursePDF {

    private List<Course> courses;

    public CoursePDF(List<Course> courses) {
        this.courses = courses;
    }

    public void writeTableHeader(PdfPTable table){

        PdfPCell cell = new PdfPCell();

        cell.setBackgroundColor(Color.cyan);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

        font.setColor(Color.white);

        cell.setPhrase(new Phrase("ID",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Course Name",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Department",font));
        table.addCell(cell);

    }

    public void writeTableData(PdfPTable table){

        for(Course course : courses ){
            table.addCell(String.valueOf(course.getId()));
            table.addCell(course.getName());
            table.addCell(course.getDepartment());
        }
    }

    public void generate(HttpServletResponse response) throws Exception{

        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document,response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);
        fontTitle.setColor(Color.black);

        Paragraph paragraph = new Paragraph("Courses Details",fontTitle);

        paragraph.setAlignment(Element.ALIGN_CENTER);

        PdfPTable table = new PdfPTable(3);

        table.setWidthPercentage(100f);
        table.setWidths(new int[]{1,3,2});
        table.setSpacingBefore(5);

        writeTableHeader(table);
        writeTableData(table);

        document.add(paragraph);

        document.add(table);

        document.close();
    }


}
