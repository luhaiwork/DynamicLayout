package com.example.testtemplate.system;

import android.content.Context;
import android.content.res.Resources;

public class ResourceTools {
	public  enum ResourceTypeEnum{TYPE_DRAWABLE,TYPE_LAYOUT}
	public static int getResourceFromId(Context context,ResourceTypeEnum resourceType,String resourceName){
		int r_id=-1;
		switch (resourceType) {
		case TYPE_DRAWABLE:
			Resources res =context.getResources();
			r_id = res.getIdentifier(resourceName, "drawable",
					context.getPackageName());
			break;
		case TYPE_LAYOUT:
			break;
		default:
			break;
		}
		return r_id;
	}
}
