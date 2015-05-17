package test;

import net.htmlparser.jericho.*;

import java.util.*;
import java.io.*;
import java.net.*;

public class WebDownloader {

	
	public static String sourceUrlString="http://www.teleman.pl/";//  file:data/test.html";
	private static Source source;

	
	//public static void main(String[] args) throws Exception {
	public void init() throws Exception{
		
		source = new Source(new URL(sourceUrlString));
		source.fullSequentialParse();

		List<Element> allElements=source.getAllElementsByClass("ellipsis");
		for (Element element : allElements) {
	
			String title=element.getContent().getTextExtractor().toString();	
			String timing = element.getAllElements(HTMLElementName.A).get(0).getAttributeValue("data-time");
			
			System.out.println(title + " " + timing);
		}
	
	}

private static String getTitle(Source source) {
	Element titleElement=source.getFirstElement(HTMLElementName.TITLE);
	if (titleElement==null) return null;
	// TITLE element never contains other tags so just decode it collapsing whitespace:
	return CharacterReference.decodeCollapseWhiteSpace(titleElement.getContent());
}

	
	
	
}
