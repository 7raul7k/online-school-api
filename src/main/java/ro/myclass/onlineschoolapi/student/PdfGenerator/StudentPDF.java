package ro.myclass.onlineschoolapi.student.PdfGenerator;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import ro.myclass.onlineschoolapi.student.model.Student;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class StudentPDF {

    private List<Student> studentList;

    public StudentPDF(List<Student> studentList) {
        this.studentList = studentList;
    }

    public void writeTableHeader(PdfPTable table){

        PdfPCell cell = new PdfPCell();

        cell.setBackgroundColor(Color.cyan);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

        font.setColor(Color.white);

        cell.setPhrase(new Phrase("ID",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("First Name",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Last Name",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Age",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Adress",font));
        table.addCell(cell);

    }

    public void writeDataTable(PdfPTable table){
        for(Student student : studentList){
            table.addCell(String.valueOf(student.getId()));
            table.addCell(student.getFirstName());
            table.addCell(student.getLastName());
            table.addCell(String.valueOf(student.getAge()));
            table.addCell(String.valueOf(student.getAdress()));
        }
    }

    public void generate(HttpServletResponse response) throws Exception{

        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document,response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);
        fontTitle.setColor(Color.black);

        Paragraph paragraph = new Paragraph("Students Details",fontTitle);
        paragraph.setAlignment(Element.ALIGN_CENTER);

        PdfPTable table = new PdfPTable(5);

        table.setWidthPercentage(100f);
        table.setWidths(new int[]{1,2,2,1,3});
        table.setSpacingBefore(5);


        writeTableHeader(table);
        writeDataTable(table);

        document.add(paragraph);

        document.add(table);

        document.close();

    }

}
