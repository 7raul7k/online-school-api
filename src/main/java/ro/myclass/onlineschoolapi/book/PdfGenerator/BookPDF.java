package ro.myclass.onlineschoolapi.book.PdfGenerator;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import ro.myclass.onlineschoolapi.book.model.Book;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;
public class BookPDF {

    public List<Book> books;
    public int studentId;

    public BookPDF(List<Book> books, int studentId) {
        this.books = books;
        this.studentId = studentId;
    }

    public void writeTableHeader(PdfPTable table) {

        PdfPCell cell = new PdfPCell();

        Font font = FontFactory.getFont(FontFactory.COURIER);

        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Book Name", font));
        table.addCell(cell);

    }

    public void writeTableData(PdfPTable table){

        for(Book book: books){
            if(book.getStudent().getId() == studentId){

                table.addCell(String.valueOf(book.getId()));
                table.addCell(book.getBookName());
            }
        }
    }

    public void generate(HttpServletResponse response) throws Exception{


        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document,response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);
        fontTitle.setColor(Color.black);

        Paragraph paragraphTitle = new Paragraph("Student with id " + studentId + " Book List", fontTitle);
        paragraphTitle.setAlignment(Element.ALIGN_CENTER);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{1,3});
        table.setSpacingBefore(5);


        writeTableHeader(table);

        writeTableData(table);

        document.add(paragraphTitle);

        document.add(table);

        document.close();

    }
}
