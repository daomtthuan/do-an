package tool;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import model.Bill;
import model.BillDetail;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.util.ArrayList;

public class Printer {
	public static void printBill(Bill bill) throws FileNotFoundException, DocumentException, ParseException {
		ArrayList<BillDetail> billDetails = api.BillDetail.getInstance().getBillDetails(bill.getId());
		Document document = new Document(PageSize.A6, 5, 5, 5, 5);
		PdfWriter.getInstance(document, new FileOutputStream("./bill/" + bill.getId() + ".pdf"));

		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.BLACK);
		Paragraph headerParagraph = new Paragraph();
		headerParagraph.setAlignment(Element.ALIGN_CENTER);
		headerParagraph.add(new Chunk("MILK TEA SHOP\n", FontFactory.getFont(FontFactory.COURIER, 13, BaseColor.BLACK)));
		headerParagraph.add(new Chunk("1 – Ly Tu Trong, An Phu, Ninh Kieu, Can Tho\n\n", font));
		headerParagraph.add(new Chunk("BILL\n\n", FontFactory.getFont(FontFactory.COURIER, 13, BaseColor.BLACK)));
		document.add(headerParagraph);

		Paragraph billParagraph = new Paragraph();
		billParagraph.setFont(font);
		billParagraph.setAlignment(Element.ALIGN_JUSTIFIED);
		billParagraph.add("Id Bill: " + bill.getId() + "\n");
		billParagraph.add("Customer: " + (bill.getIdCustomer() > 0 ? bill.getIdCustomer() + " - " + bill.getNameCustomer() : "-") + "\n");
		billParagraph.add("Employee: " + bill.getIdEmployee() + " - " + bill.getNameEmployee() + "\n");
		billParagraph.add("Discount code: " + (!bill.getNameDiscount().equals("") ? bill.getNameDiscount() : "-") + "\n");
		billParagraph.add("Check in: " + bill.getCheckIn() + "\n\n");
		document.add(billParagraph);

		Paragraph billDetailParagraph = new Paragraph();
		billDetailParagraph.setFont(font);
		billDetailParagraph.setAlignment(Element.ALIGN_JUSTIFIED);
		billDetailParagraph.add("Detail:\n");

		PdfPTable detailTable = new PdfPTable(5);
		detailTable.setWidthPercentage(100);
		detailTable.addCell(createCell("Food\n ", font));
		detailTable.addCell(createCell("Category\n ", font));
		detailTable.addCell(createCell("Quantity\n ", font));
		detailTable.addCell(createCell("Price\n ", font));
		detailTable.addCell(createCell("Total\n ", font, Element.ALIGN_RIGHT));
		billDetails.forEach(billDetail -> {
			detailTable.addCell(createCell(billDetail.getNameFood(), font));
			detailTable.addCell(createCell(billDetail.getNameCategory(), font));
			detailTable.addCell(createCell(String.valueOf(billDetail.getQuantity()), font));
			detailTable.addCell(createCell(String.valueOf(billDetail.getPrice()), font));
			detailTable.addCell(createCell(String.valueOf(billDetail.getTotal()), font, Element.ALIGN_RIGHT));
		});
		billDetailParagraph.add(detailTable);

		PdfPTable totalTable = new PdfPTable(2);
		totalTable.setWidthPercentage(100);
		totalTable.addCell(createCell("\n\nTotal before sale", font));
		totalTable.addCell(createCell("\n\n$" + bill.getTotalBefore(), font, Element.ALIGN_RIGHT));
		totalTable.addCell(createCell("Sale", font));
		totalTable.addCell(createCell(bill.getSale() + "%", font, Element.ALIGN_RIGHT));
		totalTable.addCell(createCell("Total", font));
		totalTable.addCell(createCell("$" + bill.getTotal(), font, Element.ALIGN_RIGHT));
		billDetailParagraph.add(totalTable);
		document.add(billDetailParagraph);

		document.close();
	}

	private static PdfPCell createCell(String content, Font font) {
		PdfPCell cell = new PdfPCell(new Phrase(content, font));
		cell.setBorder(Rectangle.NO_BORDER);
		return cell;
	}

	private static PdfPCell createCell(String content, Font font, int align) {
		PdfPCell cell = createCell(content, font);
		cell.setHorizontalAlignment(align);
		return cell;
	}
}
