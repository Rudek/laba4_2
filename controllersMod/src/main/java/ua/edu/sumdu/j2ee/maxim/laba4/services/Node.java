package ua.edu.sumdu.j2ee.maxim.laba4.services;

import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

/**
 * Class node for hierarchically submission categories.
 * @author Maxim Rudenko
 *
 */
public class Node implements JSONAware {
	private int level;
	private String text;
    private String Aattr;
    private List<Node> children;
	
    public Node(int level, String text, String Aattr) {
    	setLevel(level);
    	setText(text);
    	setAttr(Aattr);
    	children = new LinkedList<Node>();
    }
    
    public void setLevel(int level) {
    	this.level = level;
    }
	public void setText(String text) {
		this.text = text;
	}
	public void setAttr(String Aattr) { 
		this.Aattr = Aattr; 
	}
	public void addChild(Node node) { 
		children.add(node);
	}
	
	public int getLevel() { 
		return level; 
	}
	public String getText() { 
		return text; 
	}
	
	public String getAttr() { 
		return Aattr; 
	}
	public List<Node> getChildren() { 
		return children; 
	}
	
	public String toJSONString() {
		StringBuilder sb = new StringBuilder();
        
        sb.append("{");
        
        sb.append(JSONObject.escape("text"));
        sb.append(":");
        sb.append("\"").append(JSONObject.escape(text) ).append("\"");
        
        if ( this.getChildren().isEmpty() ) {
        	sb.append(",");
            sb.append(JSONObject.escape("a_attr"));
            sb.append(":");
            sb.append("{href: \"product/category/").append(Aattr).append("\"}");
        } else {
        	sb.append(", children: [");
        	int length = children.size();
            for ( int i = 0; i < length; i++ ) {
            	sb.append(children.get(i).toJSONString());
            	if ( i != length - 1 ) {
            		sb.append(",");
            	}
            }
            sb.append("]");
        }
        sb.append("}");
        
		return sb.toString();
	}
}