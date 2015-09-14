package frame;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

import DAO.CasaDAO;
import DAO.FornitoreDAO;
import DAO.LuvaglioDAO;
import DAO.RegioneDAO;
import DAO.TipologiaDAO;

import model.casa;
import model.fornitore;
import model.luvaglio;
import model.regione;
import model.tipologia;

public class metodUtils {
	
	
	boolean isNumber(String s){
		try{
		Integer.parseInt(s);
		//Se la stringa rappresenta un intero si ritorna true
		return true;
		}
		catch(NumberFormatException e){
		}

		//Altrimenti si controlla se rappresenta un numero con virgola
		try{
		Float.parseFloat(s);
		return true;
		}
		catch(NumberFormatException e){
		}

		//Se si arriva a questo punto significa che non è stato
		//possibile convertire la stringa nè come intero ne come float
		return false;
		}
	
	public static int isDoubleOrInt(String num){
		  try{
		    Integer.parseInt(num);
		    return 0;
		  }catch(Exception e1){
		    try{
		      Double.parseDouble(num);
		      return 1;
		    }catch(Exception e){
		      return -1;
		    }
		  }
		}
	
	public int getData(String set, String data){
		String ritorno="";
		char []dataV=data.toCharArray();
		int cont=0;
		if(set=="yyyy"){
			for(int a=0;a<dataV.length;a++){
				if(dataV[a]=='-'){
					cont++;
				}
				if(cont==2 && dataV[a]=='-'){
					ritorno=""+dataV[a+1]+dataV[a+2]+dataV[a+3]+dataV[a+4];
				}
			}
		}
		if(set=="mm"){
			for(int a=0;a<dataV.length;a++){
				if(dataV[a]=='-'){
					cont++;
				}
				if(cont==1 && dataV[a]=='-'){
					ritorno=""+dataV[a+1]+dataV[a+2];
				}
			}
		}
		if(set=="dd"){
			for(int a=0;a<dataV.length;a++){
				if(a==0){
					ritorno=""+dataV[a]+dataV[a+1];
				}
			}
		}
		int r=Integer.parseInt(ritorno);
		return r;
	}

	public String andataACapo(String stringa,int i){	
		String ap1, ap2;
		boolean test=true;
		boolean html=false;
		boolean controllo=false;
		int b=0;
		if(stringa!=null){
		for(int a=0;stringa.length()>a;a++){
			b++;
			if(stringa.charAt(a)=='.'){
				if(test)
				{
					stringa="<html> "+stringa;
					a=a+8;
					test=false;
					html=true;
				}
				b=0;
				
				ap1=stringa.substring(0,a+1);
				ap2=stringa.substring(a+1,stringa.length());
				stringa=ap1+"<br>"+ap2;
			}
			if(b%i==0 && b!=0){
				controllo=true;
			}
			if(controllo && stringa.charAt(a)==' '){
				b=0;
				controllo=false;
				if(test){
						stringa="<html> "+stringa;
						a=a+8;
						test=false;
						html=true;
				}	
				ap1=stringa.substring(0,a);
				ap2=stringa.substring(a+1,stringa.length());
				stringa=ap1+"<br>"+ap2;						
			}
			
		}
		if(html){
			stringa = stringa + " </html>";
		}		
		return stringa;
		}
		else
			return "";
	}
	
	public String[] getComboCasa() throws Exception{
		
		ArrayList<casa> listaCasa= CasaDAO.getCasa();
		String lista[] =new String[listaCasa.size()+1];
		int a=0;
		lista[0]="Nessuna selezione";
		for (casa e:listaCasa){
			a++;
			lista[a]=e.getNome();
		}
		return lista;
	}
	public String[] getComboTipologia() throws Exception{
		
		ArrayList<tipologia> listaTipo=TipologiaDAO.getListaTipo();
		String lista[] =new String[listaTipo.size()+1];
		int a=0;
		lista[0]="Nessuna selezione";
		for (tipologia e:listaTipo){
			a++;
			lista[a]=e.getTipo();
		}
		return lista;
	}
	
	public String[] getComboVigneto() throws Exception{
		
		ArrayList<luvaglio> listaVigneto= LuvaglioDAO.getListaUvaglio();
		String lista[] =new String[listaVigneto.size()+1];
		int a=0;
		lista[0]="Nessuna selezione";
		for (luvaglio e:listaVigneto){
			a++;
			lista[a]=e.getNome();
		}
		return lista;
	}	
	
	
	public String[] getComboRegione() throws Exception{
		
		ArrayList<regione> listaRegiones=RegioneDAO.getRegioni();
		String lista[] =new String[listaRegiones.size()+1];
		int a=0;
		lista[0]="Nessuna selezione";
		for (regione e:listaRegiones){
			a++;
			lista[a]=e.getNome();
		}
		return lista;
	}
	
	public static String convertiFloatString(String numero){
		int []index = null;
		int c=0;
		char []numChar=numero.toCharArray();
		char []finale = new char[numero.length()];
		int b=0;
		for(int a=0;a<=numero.length();a++){
			if(numChar[a]!='.'){
				finale[b]=numChar[a];
				b++;
			}
			
		}		
		numero=finale.toString();
		return numero;
	}
	
	public static String convertStringFloat(String numero){
		char[] app=numero.toCharArray();
		for(int a=0;a<numero.length();a++){
			
			if(numero.charAt(a)==','){
				app[a]='.';
			}
		}
		numero=new String(app);		
		return numero;
	}

	public String[] getComboFornitore() throws Exception {
		ArrayList<fornitore> listaFornitore= FornitoreDAO.getFornitore();
		String lista[] =new String[listaFornitore.size()+1];
		int a=0;
		lista[0]="Nessuna selezione";
		for (fornitore e:listaFornitore){
			a++;
			lista[a]=e.getNome();
		}
		return lista;
	}
	
	
}
