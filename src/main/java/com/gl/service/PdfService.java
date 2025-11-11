package com.gl.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gl.entity.Registration;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PdfService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    /**
     * Generate registration receipt PDF
     */
    public byte[] generateRegistrationReceipt(Registration registration) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Header
            Paragraph header = new Paragraph("REGISTRATION RECEIPT")
                    .setFontSize(20)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(20);
            document.add(header);

            // Receipt details
            Paragraph receiptInfo = new Paragraph()
                    .add("Receipt #: REG-" + registration.getId() + "\n")
                    .add("Date: " + LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")) + "\n")
                    .add("Payment Status: " + (registration.getStatus() == 1 ? "PAID" : "PENDING"))
                    .setMarginBottom(20);
            document.add(receiptInfo);

            // Registration details table
            Table table = new Table(UnitValue.createPercentArray(new float[]{1, 2}))
                    .useAllAvailableWidth()
                    .setMarginBottom(20);

            addTableRow(table, "Registration Type:", registration.getCategory());
            addTableRow(table, "Name:", registration.getName());
            addTableRow(table, "Email:", registration.getEmail());
            addTableRow(table, "Phone:", registration.getPhone());
            addTableRow(table, "Organization:", registration.getOrg());
            addTableRow(table, "Country:", registration.getCountry());
            addTableRow(table, "Amount Paid:", "$" + registration.getPrice() + " USD");
            
            if (registration.getCategory() != null && registration.getCategory().equals("Sponsor")) {
                addTableRow(table, "Sponsor Plan:", registration.getDescription());
            }

            document.add(table);

            // Footer
            Paragraph footer = new Paragraph()
                    .add("Thank you for your registration!\n")
                    .add("This is a computer-generated receipt and does not require a signature.\n")
                    .add("For any queries, please contact us.")
                    .setFontSize(10)
                    .setItalic()
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginTop(30);
            document.add(footer);

            document.close();
            
            log.info("Generated registration receipt for: {}", registration.getId());
            return baos.toByteArray();

        } catch (Exception e) {
            log.error("Error generating PDF receipt", e);
            throw new RuntimeException("Failed to generate PDF receipt", e);
        }
    }

    /**
     * Generate and save receipt to file
     */
    public String saveRegistrationReceipt(Registration registration) {
        try {
            byte[] pdfBytes = generateRegistrationReceipt(registration);
            
            String fileName = "receipt_" + registration.getId() + "_" + System.currentTimeMillis() + ".pdf";
            String filePath = uploadDir + File.separator + fileName;
            
            File uploadDirectory = new File(uploadDir);
            if (!uploadDirectory.exists()) {
                uploadDirectory.mkdirs();
            }
            
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                fos.write(pdfBytes);
            }
            
            log.info("Saved receipt to: {}", filePath);
            return fileName;
            
        } catch (Exception e) {
            log.error("Error saving PDF receipt", e);
            throw new RuntimeException("Failed to save PDF receipt", e);
        }
    }

    /**
     * Helper method to add table rows
     */
    private void addTableRow(Table table, String label, String value) {
        table.addCell(new Paragraph(label).setBold());
        table.addCell(new Paragraph(value != null ? value : "N/A"));
    }

    /**
     * Generate abstract submission confirmation PDF
     */
    public byte[] generateAbstractConfirmation(com.gl.entity.AbstractSubmission submission) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Header
            Paragraph header = new Paragraph("ABSTRACT SUBMISSION CONFIRMATION")
                    .setFontSize(18)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(20);
            document.add(header);

            // Submission details table
            Table table = new Table(UnitValue.createPercentArray(new float[]{1, 2}))
                    .useAllAvailableWidth();

            addTableRow(table, "Submission ID:", String.valueOf(submission.getId()));
            addTableRow(table, "Name:", submission.getTitle() + " " + submission.getFname());
            addTableRow(table, "Email:", submission.getEmail());
            addTableRow(table, "Organization:", submission.getOrg());
            addTableRow(table, "Country:", submission.getCountry());
            addTableRow(table, "Category:", submission.getCategory());
            addTableRow(table, "Session:", submission.getTrackName());
            addTableRow(table, "Presentation Title:", submission.getPresentationTitle());

            document.add(table);

            // Footer
            Paragraph footer = new Paragraph()
                    .add("\nYour abstract has been successfully submitted and is under review.\n")
                    .add("We will contact you with further information.\n\n")
                    .add("Thank you for your submission!")
                    .setFontSize(10)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginTop(20);
            document.add(footer);

            document.close();
            
            log.info("Generated abstract confirmation PDF for: {}", submission.getEmail());
            return baos.toByteArray();

        } catch (Exception e) {
            log.error("Error generating abstract confirmation PDF", e);
            throw new RuntimeException("Failed to generate abstract confirmation PDF", e);
        }
    }
}
