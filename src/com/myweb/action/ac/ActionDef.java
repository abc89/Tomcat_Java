package com.myweb.action.ac;

import java.util.List;

import com.exception.DataBaseConfigPathError;
import com.myweb.exception.ActionConfigException;
import com.xml.XmlOperate;

public class ActionDef extends XmlOperate {
	public List<String> readNodeValues(String rpath, String nodeName)
			throws DataBaseConfigPathError {
		return super.readNodeValues(rpath, nodeName);
	}
    public String getActionResponseUrl(String action){
    	String actionUrl=null;
    	try {
			List<String> actions=readNodeValues("¡£/source/action/action_config.xml", "action");
			List<String> actionUrls=readNodeValues("¡£/source/action/action_config.xml", "action-url");
			if(actions.isEmpty()||actionUrls.isEmpty()||actions==null||actionUrls==null){
				throw new ActionConfigException(ActionConfigException.CONFIGERROR_OR_NOACTION,action+"./source/action/action_config.xml");
			}else {
				int size=actions.size();
				boolean success=false;
				for(int i=0;i<size;i++){
					if(actions.get(i).compareTo(action)==0){
						success=true;
						actionUrl=actionUrls.get(i);
					}
				}
				if(!success){
					throw new ActionConfigException(ActionConfigException.NOACTIONAME,action+"./source/action/action_config.xml");
					
				}
			}
		} catch (DataBaseConfigPathError e) {
			e.printStackTrace();
		} catch (ActionConfigException e) {			
			e.printStackTrace();
		}
		return actionUrl;
    }
}
