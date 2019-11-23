package tool;

import app.alert.AlertWarning;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import model.Bill;
import model.BillDetail;
import model.Discount;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;

public class Printer {
	public static void printBill(Bill bill) throws IOException, DocumentException, ParseException {
		Path dir = Paths.get("./bill");
		if (!Files.exists(dir)) {
			Files.createDirectories(dir);
		}

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
		detailTable.addCell(createNumberCell("Quantity\n ", font));
		detailTable.addCell(createNumberCell("Price\n ", font));
		detailTable.addCell(createNumberCell("Total\n ", font));
		billDetails.forEach(billDetail -> {
			detailTable.addCell(createCell(billDetail.getNameFood(), font));
			detailTable.addCell(createCell(billDetail.getNameCategory(), font));
			detailTable.addCell(createNumberCell(String.valueOf(billDetail.getQuantity()), font));
			detailTable.addCell(createNumberCell(String.valueOf(billDetail.getPrice()), font));
			detailTable.addCell(createNumberCell(String.valueOf(billDetail.getTotal()), font));
		});
		billDetailParagraph.add(detailTable);

		PdfPTable totalTable = new PdfPTable(2);
		totalTable.setWidthPercentage(100);
		totalTable.addCell(createCell("\n\nTotal before sale", font));
		totalTable.addCell(createNumberCell("\n\n$" + bill.getTotalBefore(), font));
		totalTable.addCell(createCell("Sale", font));
		totalTable.addCell(createNumberCell(bill.getSale() + "%", font));
		totalTable.addCell(createCell("Total", font));
		totalTable.addCell(createNumberCell("$" + bill.getTotal(), font));
		billDetailParagraph.add(totalTable);
		document.add(billDetailParagraph);

		ArrayList<Discount> discounts = api.Discount.getInstance().getGiveOutDiscounts();
		if (discounts != null) {
			Paragraph discountParagraph = new Paragraph();
			discountParagraph.setFont(font);
			discountParagraph.setAlignment(Element.ALIGN_JUSTIFIED);
			discountParagraph.add("\nDiscount code for customer:\n");
			discounts.forEach(discount -> discountParagraph.add("Sale " + discount.getSale() + "%: " + discount.getName() + "\n"));
			document.add(discountParagraph);
		} else {
			AlertWarning.getInstance().showAndWait("Fail!", "Cannot print discount code.\nPlease notify staff.");
		}

		document.close();
	}

	private static PdfPCell createCell(String content, Font font) {
		PdfPCell cell = new PdfPCell(new Phrase(content, font));
		cell.setBorder(Rectangle.NO_BORDER);
		return cell;
	}

	private static PdfPCell createNumberCell(String content, Font font) {
		PdfPCell cell = createCell(content, font);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		return cell;
	}
}
