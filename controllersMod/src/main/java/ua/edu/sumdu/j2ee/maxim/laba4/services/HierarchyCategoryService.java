package ua.edu.sumdu.j2ee.maxim.laba4.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import ua.edu.sumdu.j2ee.maxim.laba4.entities.CategoryLevel;

@Service
public class HierarchyCategoryService {

	/**
	 * Method return all categories hierarchically in JSON format.
	 * @return JSON string
	 * @throws SQLException - if SQL error occur.
	 */
	public String getCategoriesJSON(List<CategoryLevel> listHierarchyCategory) {
			
		JSONArray listJSON = new JSONArray();
		Node root = null;
		int curLevel = 1;
		int level = 1;
		int sizeList = listHierarchyCategory.size();
		for ( int i = 0; i < sizeList; i++ ) {
			CategoryLevel category = listHierarchyCategory.get(i);
			level = category.getLevel();
			Node node = new Node(level, category.getName(), Long.toString(category.getId()));
			if ( level == 1 ) {
				if ( curLevel != level || (level == curLevel && curLevel == 1 && i != 0 ) ) {
					listJSON.add(root);
				} 
				root = node;
				curLevel = level;
			} else  {
				curLevel = level;
				addNode(root,node);
			}
	    }
		listJSON.add(root);
		return listJSON.toJSONString();
	}

	private void addNode(Node root, Node node) {
		int level = node.getLevel();
		while (root.getLevel() + 1 != level) {
			int countChildren = root.getChildren().size();
			root = root.getChildren().get(countChildren-1);
		}
		root.addChild(node);
	}
}
