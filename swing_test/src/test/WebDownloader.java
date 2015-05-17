package test;

import net.htmlparser.jericho.*;

import java.util.*;
import java.io.*;
import java.net.*;

public class WebDownloader {

	
	public static String sourceUrlString="http://www.teleman.pl/";//  file:data/test.html";
	//private Source source;

	
	//public static void main(String[] args) throws Exception {
	public static void MainPageProgramms() throws Exception{
		
		Source source;
		
		source = new Source(new URL(sourceUrlString));
		source.fullSequentialParse();

		List<Element> allElements=source.getAllElementsByClass("ellipsis");
		for (Element element : allElements) {
	
			String title=element.getContent().getTextExtractor().toString();	
			String timing = element.getAllElements(HTMLElementName.A).get(0).getAttributeValue("data-time");
			
			System.out.println(title + " " + timing);
		
		}
	
	}
	
	public static String[][] stationsList() throws Exception{
		
		String subUrl = "program-tv/stacje";
		Source src = new Source(new URL(sourceUrlString+subUrl));
		src.fullSequentialParse();
		List<Element> stations = src.getAllElements(HTMLElementName.DIV);
		
		Element list = null;
		
		for (Element e : stations){
		
			if(e.getAttributeValue("id") != null){
				if (e.getAttributeValue("id").equals("stations-index")){
				
					//System.out.println(e.getAttributeValue("id"));
					//System.out.println(e.getAllElements().get(0).getAttributeValue("href"));
					list = e;
					break;
				}				
			}			
		}
		
		String[][] NamesWithAlias = new String[list.getAllElements(HTMLElementName.A).size()][2];
		
		for ( Element l : list.getAllElements(HTMLElementName.A)){
			
			int index = 0;
			String tvAlias = l.getAttributeValue("href");
			
			if(tvAlias == null) continue;
			
			String tvPname = l.getContent().getTextExtractor().toString();
			System.out.println(tvPname + ": " + tvAlias);
		
			NamesWithAlias[index][0] = tvPname;
			NamesWithAlias[index][1] = tvAlias;
			index++;
		}
		
		return NamesWithAlias;
		
	}
	
	
public static String[][] stationProgramms(String alias) throws Exception{
		
	String subUrl = "?hour=-1";
	Source src = new Source(new URL(sourceUrlString+alias+subUrl));
	src.fullSequentialParse();
	List<Element> stationsItems = src.getAllElements(HTMLElementName.UL);
	
	Element list = null;
	
	for (Element e : stationsItems){
	//System.out.println(e.getAttributeValue("id"));
		if(e.getAttributeValue("id") != null){
			if (e.getAttributeValue("id").equals("stationItems")){
			
				//System.out.println(e.getAttributeValue("id"));
				//System.out.println(e.getAllElements().get(0).getAttributeValue("href"));
				list = e;
				break;
			}				
		}			
	}
	
	String[][] MovieDetail = new String[list.getAllElements(HTMLElementName.LI).size()][3];
	
	//System.out.println(list.getAllElements(HTMLElementName.LI).size());
	int index = 0;
	for ( Element l : list.getAllElements(HTMLElementName.A)){
		
		String name = l.getContent().getTextExtractor().toString();
		if(name.isEmpty()) continue;
		//System.out.println(index+ " " +name);
	
		MovieDetail[index][0] = name;
		index++;
	}
	
	index=0;
	for ( Element l : list.getAllElements(HTMLElementName.EM)){
		
		String time = l.getContent().getTextExtractor().toString();
		//System.out.println(time);
		if(time.isEmpty()) continue;
		MovieDetail[index][1] = time;
		index++;
		
	}
	
	index = 0;
	for ( Element l : list.getAllElements(HTMLElementName.P)){
		
		
		if(l.getAttributeValue("class") != null) continue;
		
		String desc = l.getContent().getTextExtractor().toString();
		//System.out.println(desc);
		MovieDetail[index][2] = desc;
		index++;
	}
	
	return MovieDetail;		
}
	
	
	
	
	

private static String getTitle(Source source) {
	Element titleElement=source.getFirstElement(HTMLElementName.TITLE);
	if (titleElement==null) return null;
	// TITLE element never contains other tags so just decode it collapsing whitespace:
	return CharacterReference.decodeCollapseWhiteSpace(titleElement.getContent());
}

	
	
	
}
