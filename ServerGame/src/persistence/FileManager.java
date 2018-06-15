package persistence;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import models.Goal;

public class FileManager {
	
	public static final String YES = "yes";
	public static final String SPACE = "4";
	public static final String IDENT = "{http://xml.apache.org/xslt}indent-amount";
	public static final File NEW_FILE = new File("file.xml");

	/*public ArrayList<Game> read() throws Exception{
		ArrayList<Game> gameList = new ArrayList<>();
		File file = FILE;
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
		Element root = document.getDocumentElement();
		NodeList list = root.getElementsByTagName(TITLE_ENTITY);
		for (int i = 0; i < list.getLength(); i++) {
			Element game = (Element) list.item(i);
			String id = game.getAttribute(ID);
			String name = game.getElementsByTagName(NAME).item(0).getTextContent();
			String rating = getRating(game);
			String temp = game.getElementsByTagName(RELEASEDATE).item(0).getTextContent();
			String date = "";
			if (!temp.isEmpty() && !rating.isEmpty()) {
				date = Manager.getDate(temp);
				gameList.add(Manager.createGame(id, name, rating, date));	
			}	
		}
		return gameList;
	}
*/
	public void writeReport(ArrayList<Goal> goalList) throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException{
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Element games = document.createElement("Goals");
		for (Goal goal : goalList) {
			Element information = createElement(document, goal);
			games.appendChild(information);
		}
		document.appendChild(games);
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, YES);
		transformer.setOutputProperty(IDENT, SPACE);
		transformer.transform(new DOMSource(document),new StreamResult(NEW_FILE));
	}
//	
	public Element createElement(Document document, Goal goal){
		Element element = document.createElement("Goal");
		Element id = document.createElement("Id");
		id.setTextContent(String.valueOf(goal.getIdClient()));
		element.appendChild(id);
		Element posX = document.createElement("PosX");
		posX.setTextContent(String.valueOf(goal.getPosX()));
		element.appendChild(posX);
		Element posY = document.createElement("PosY");
		posY.setTextContent(String.valueOf(goal.getPosY()));
		element.appendChild(posY);
		return element;
	}
}