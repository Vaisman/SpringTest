package com.svirski.spring.web.view;

import com.svirski.spring.core.models.Ticket;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import java.util.List;
import java.util.Map;

public class TicketListPdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(
            Map<String, Object> map,
            Document document,
            PdfWriter pdfWriter,
            javax.servlet.http.HttpServletRequest httpServletRequest,
            javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {

        List<Ticket> ticketList = (List<Ticket>) map.get("ticketList");

        Table table = new Table(2);
        table.addCell("id");
        table.addCell("ticket");

        for (Ticket ticket : ticketList){
            table.addCell(Long.toString(ticket.getId()));
            table.addCell(ticket.toString());
        }
        document.add(table);
    }
}
