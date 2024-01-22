package patterns.example.util;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import patterns.example.model.Movie;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class MoviePDFExporter extends Exporter {
    public void export(List<Movie> movies, HttpServletResponse response) throws IOException {
        super.setResponseHeader(response, "application/pdf", ".pdf", "movies_");
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.DARK_GRAY);
        Paragraph paragraph = new Paragraph("List of Movies", font);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);
        table.setWidths(new float[]{1.2f, 3.5f, 3.0f, 3.0f, 3.0f, 1.7f});
        writeTableHeader(table);
        writeTableData(table, movies);
        document.add(table);
        document.close();
    }

    private void writeTableData(PdfPTable table, List<Movie> movies) {
        for (Movie movie : movies) {
            table.addCell(String.valueOf(movie.getId()));
            table.addCell(movie.getTitle());
            table.addCell(String.valueOf(movie.getType()));
            table.addCell(movie.getCountry());
            table.addCell(movie.getShortDescription());
            table.addCell(String.valueOf(movie.getCast()));
        }
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.DARK_GRAY);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Title", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Type", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Country", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Actors", font));
        table.addCell(cell);
    }
}
