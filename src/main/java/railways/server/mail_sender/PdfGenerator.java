package railways.server.mail_sender;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import railways.database.model.Journey;
import railways.database.model.User;
import railways.database.model.UserJourney;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;

public class PdfGenerator {
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 32, Font.BOLD);
    private static Font headerText = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD);
    private static Font cellText = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.ITALIC);

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    private static String FILE_PATH_TEMPLATE = "src/main/resources/temp_pdf_files/{0}_{1}_{2}.pdf";

    private Journey journey;
    private String file;
    private long place;
    private User user;

    public PdfGenerator(UserJourney userJourney) {
        this.user = userJourney.getUser();
        this.journey = userJourney.getJourney();
        this.place = userJourney.getPlace();

        this.file = MessageFormat.format(
                FILE_PATH_TEMPLATE,
                journey.getId(),
                user.getId(),
                place
        );
    }

    public String generate() {
        try {
            Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            addMetaData(document);
            addContent(document);
            document.close();

            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean delete() {
        try {
            Files.delete(Paths.get(file));

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private static void addMetaData(Document document) {
        document.addTitle("Railway ticket");
        document.addSubject("Railways");
        document.addAuthor("Yurii Karabas");
        document.addCreator("Yurii Karabas");
    }

    private void addContent(Document document) throws DocumentException {
        Paragraph paragraph = new Paragraph(
                "Ticket for " + user.getName() + " " + user.getSurname()
                , catFont
        );
        paragraph.setAlignment(Element.ALIGN_CENTER);

        document.add(paragraph);
        document.add(Chunk.NEWLINE);
        document.add(createTable());
    }

    private PdfPCell generateTableCell(String text, Font font, int element) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(element);
        cell.setVerticalAlignment(element);

        return cell;
    }

    private PdfPTable createTable() throws BadElementException {
        PdfPTable table = new PdfPTable(5);

        table.addCell(generateTableCell("From", headerText, Element.ALIGN_CENTER));
        table.addCell(generateTableCell("To", headerText, Element.ALIGN_CENTER));
        table.addCell(generateTableCell("Place", headerText, Element.ALIGN_CENTER));
        table.addCell(generateTableCell("Price", headerText, Element.ALIGN_CENTER));
        table.addCell(generateTableCell("Date", headerText, Element.ALIGN_CENTER));

        table.setHeaderRows(1);

        table.addCell(generateTableCell(journey.getFrom(), cellText, Element.ALIGN_CENTER));
        table.addCell(generateTableCell(journey.getTo(), cellText, Element.ALIGN_CENTER));
        table.addCell(generateTableCell(Long.toString(place), cellText, Element.ALIGN_CENTER));
        table.addCell(generateTableCell(Long.toString(journey.getPrice()), cellText, Element.ALIGN_CENTER));
        table.addCell(generateTableCell(dateFormat.format(journey.getDate()), cellText, Element.ALIGN_CENTER));

        return table;
    }
}
