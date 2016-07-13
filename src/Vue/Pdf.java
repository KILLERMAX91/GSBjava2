package Vue;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.print.DocFlavor.URL;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Class.FicheFrais;
import Controler.Controler;

public class Pdf {
	private static Controler unControler;
	private static FicheFrais uneFacture;
	private static String FILE = "facture.pdf";
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static Font small = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);  
	private static Font frais = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);  
	public Pdf(Controler leControler, FicheFrais facture){
		this.unControler = leControler;
		this.uneFacture = facture;
		try {
			Document document = new Document();
			
			
			
			File monFichier = new File(FILE);
			if(!monFichier.exists()) {
				 monFichier.createNewFile();
			}else{
				//creation d'un nouveau pdf (car probléme de droit quand le pdf est ouvert) 
				int i=0;
				do{
					FILE = "facture_"+i+".pdf";
					monFichier = new File(FILE);
					i++;
				}while(monFichier.exists());
				monFichier.createNewFile();
			}
			
			 
			 PdfWriter.getInstance(document, new FileOutputStream(monFichier, false));
		     document.open();
		     addMetaData(document);
		     addTitlePage(document);
		    
		     document.close();
		    //permet d'ouvrir le fichier pdf
		     Desktop.getDesktop().open(new File(FILE));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//c'est comme la balise meta en html
	  private static void addMetaData(Document document) {
		    document.addTitle("facture de "+uneFacture.getCleDate());
		    document.addSubject(""+uneFacture.getUnVisiteur().getNom());
		    document.addKeywords("Java, PDF, facture, "+uneFacture.getUnVisiteur().getNom());
		    document.addAuthor(""+uneFacture.getUnVisiteur().getNom());
		    document.addCreator(""+unControler.getCVPersonne().getUnComptable().getNom());
		}

	  private static void addTitlePage(Document document)
		      throws DocumentException {
		    Paragraph preface = new Paragraph();
	
		    try {
		    	
				
				Image image = Image.getInstance(document.getClass().getResource("/image/logo.jpg"));
				preface.add(image);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    preface.add(new Paragraph("facture de "+uneFacture.getCleDate(), catFont));

		    addEmptyLine(preface, 1);
		    
		    preface.add(new Paragraph("par : " + uneFacture.getUnVisiteur().getNom() + "",small));
		   
		    preface.add(new Paragraph("total a  payer "+uneFacture.getMontantValid(), small));
		   
		    preface.add(new Paragraph("etat "+uneFacture.getUnEtat().getLibelle(), small));
		    preface.add(new Paragraph("derniere modification "+uneFacture.getDerniereModif().dateFR(uneFacture.getIndiceDate()), small));
		   

		    preface.add(new Paragraph("telephone: "+uneFacture.getUnVisiteur().getTel(), small));
		    
		    preface.add(new Paragraph("email: "+uneFacture.getUnVisiteur().getMail(), small));
		    addEmptyLine(preface, 1);
		    for(int i=0;i<unControler.getCFraisForfait().taille();i++){
		    	preface.add(new Paragraph(unControler.getCFraisForfait().extraire(i).getLibelle()+" "+unControler.getCFraisForfait().extraire(i).getMontant()+"*"+uneFacture.extraireFraisForfait(i).getQuantite()+"="+(uneFacture.extraireFraisForfait(i).getQuantite()*unControler.getCFraisForfait().extraire(i).getMontant()), frais));
		    }
		   
		    addEmptyLine(preface, 1);
		    //table
		    PdfPTable table = new PdfPTable(4);

		    PdfPCell c1 = new PdfPCell(new Phrase("libelle"));
		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		    table.addCell(c1);

		    c1 = new PdfPCell(new Phrase("date"));
		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		    table.addCell(c1);

		    c1 = new PdfPCell(new Phrase("montant"));
		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		    table.addCell(c1);
		    
		    c1 = new PdfPCell(new Phrase("etat"));
		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		    table.addCell(c1);
		    
		    table.setHeaderRows(1);
		    
		    for(int i=0;i<uneFacture.tailleFraisHorsForfait();i++){
		    	table.addCell(""+uneFacture.extraireFraisHorsForfait(i).getLibelle());
		    	table.addCell(""+uneFacture.extraireFraisHorsForfait(i).getUneDate());
		    	table.addCell(""+uneFacture.extraireFraisHorsForfait(i).getMontant());
		    	table.addCell(""+uneFacture.extraireFraisHorsForfait(i).getUneEtat().getLibelle());
		    }
		    preface.add(table);
		    document.add(preface);
		    
		    document.newPage();
		 }
		
	  /*Creation de saut de ligne pour separer les differents paragraphes*/
		 private static void addEmptyLine(Paragraph paragraph, int number) {
				for (int i = 0; i < number; i++) {
					paragraph.add(new Paragraph(" "));
				}
		}
		
		
}
