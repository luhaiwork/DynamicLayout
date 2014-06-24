package com.example.testtemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.sax.TextElementListener;
import android.support.v4.app.Fragment;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.example.testtemplate.bean.GridBean;
import com.example.testtemplate.system.MeasureUtil;
import com.example.testtemplate.system.ResourceTools;
import com.example.testtemplate.system.ResourceTools.ResourceTypeEnum;

public class PlaceholderFragment extends Fragment{
	private GridView gv_test;
	private LinearLayout ll_bottom;
	private List<GridBean> datalist;
	private InputStream localDataXMLInputStream = null;
	private int gridCount=1;
	private int gridMarginLeft=1;
	private int gridMarginTop=1;
	private int gridWidth=10;
	private int alignParentBottom=0;
	public PlaceholderFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		gv_test = (GridView) getActivity().findViewById(R.id.gv_test);
		ll_bottom = (LinearLayout) getActivity().findViewById(R.id.ll_bottom);
		testConfigReadWithSax();
		initView();
	}
	public void initView(){
		ViewGroup.MarginLayoutParams params = (MarginLayoutParams) gv_test
				.getLayoutParams();
		params.topMargin = MeasureUtil.dpToPx(getActivity(),gridMarginTop);
		params.leftMargin =MeasureUtil.dpToPx(getActivity(),gridMarginLeft);
		params.width=MeasureUtil.dpToPx(getActivity(), gridWidth);
		RelativeLayout.LayoutParams param=(LayoutParams) gv_test.getLayoutParams();
		param.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,alignParentBottom);
		gv_test.setNumColumns(gridCount);
		gv_test.requestLayout();
		gv_test.setAdapter(new MyAdapter());
	}
//	public void initData() {
//		if (testConfigRead.getMode() == 0) {
//			getMode1Data();
//			ll_bottom.setVisibility(View.GONE);
//			ViewGroup.MarginLayoutParams params = (MarginLayoutParams) gv_test
//					.getLayoutParams();
//			params.topMargin = 0;
//			params.rightMargin = 0;
//			params.topMargin = MeasureUtil.dpToPx(getActivity(), 200);
//			gv_test.setNumColumns(1);
//			gv_test.requestLayout();
//			gv_test.setAdapter(new MyAdapter());
//
//		} else {
//			getMode1Data();
//			ViewGroup.MarginLayoutParams params = (MarginLayoutParams) gv_test
//					.getLayoutParams();
//			ll_bottom.setVisibility(View.VISIBLE);
//			params.topMargin = 0;
//			params.rightMargin =MeasureUtil.dpToPx(getActivity(), 200);
//			gv_test.requestLayout();
//			gv_test.setNumColumns(2);
//			gv_test.setAdapter(new MyAdapter());
//		}
//	}

	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return datalist.size();
		}

		@Override
		public Object getItem(int position) {
			return datalist.get(position);
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}
		@Override
		public int getItemViewType(int position) {
//		// TODO Auto-generated method stub
//		return super.getItemViewType(position);
			return datalist.get(position).getStyle();
		}
		@Override
		public int getViewTypeCount() {
			return GridBean.ViewType.getTotType();
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;
			GridBean gridBean = datalist.get(position);
			if(convertView!=null){
				view=convertView;
			}else if(GridBean.ViewType.VIEW_TYPE_0.getViewType()==gridBean.getStyle()){
				view = getActivity().getLayoutInflater().inflate(
						R.layout.type1_list_item_01, null);
			}else {
				view = getActivity().getLayoutInflater().inflate(
						R.layout.type1_list_item_02, null);
			}
			RelativeLayout rl_item = (RelativeLayout) view
					.findViewById(R.id.rl_item);
			TextView tv_item = (TextView) view.findViewById(R.id.tv_item);
			tv_item.setText(gridBean.getTitle());
			ImageView img_test = (ImageView) view.findViewById(R.id.img_test);
			img_test.setImageResource(gridBean.getImage());
			return view;
		}
	}

//	private MyPageConfig testConfigRead() throws XmlPullParserException,
//			IOException {
//		testConfigRead = new MyPageConfig();
//		localDataXMLInputStream = getLocalDataXMLInputStream();
//		// 方式一:使用工厂类XmlPullParserFactory
//		XmlPullParserFactory pullFactory = XmlPullParserFactory.newInstance();
//		XmlPullParser parser = pullFactory.newPullParser();
//		parser.setInput(localDataXMLInputStream, "UTF-8");
//		int eventType = parser.getEventType();
//		// 只要不是文档结束事件，就一直循环
//		String actName = null;
//		while (eventType != XmlPullParser.END_DOCUMENT) {
//			String name = parser.getName();
//			switch (eventType) {
//			// 触发开始文档事件
//			case XmlPullParser.START_DOCUMENT:
//
//				break;
//			// 触发开始元素事件
//			case XmlPullParser.START_TAG:
//				if ("activity".equals(name)) {
//					for (int i = 0; i < parser.getAttributeCount(); i++) {
//						if (parser.getAttributeName(i).equals("name")) {
//							actName = parser.getAttributeValue(i);
//						}
//					}
//					Log.i("tag", "actName:" + actName);
//				}
//				// 解析testpage 的act配置
//				if ("testpage".equals(actName) && "showMenu".equals(name)) {
//					String isShow = "";
//					for (int i = 0; i < parser.getAttributeCount(); i++) {
//						if ("isShow".equals(parser.getAttributeName(i))) {
//							isShow = parser.getAttributeValue(i);
//						}
//					}
//					testConfigRead.setShow(Boolean.parseBoolean(isShow));
//					Log.i("tag", "showMenu:" + isShow);
//				}
//				// 解析testpage 的act配置 mode
//				if ("testpage".equals(actName) && "mode".equals(name)) {
//					String mode = "";
//					for (int i = 0; i < parser.getAttributeCount(); i++) {
//						if ("val".equals(parser.getAttributeName(i))) {
//							mode = parser.getAttributeValue(i);
//						}
//					}
//					testConfigRead.setMode(Integer.parseInt(mode));
//					Log.i("tag", "mode:" + testConfigRead.getMode());
//				}
//				break;
//			case XmlPullParser.TEXT:
//				Log.i("tag", "name:" + name + "text:" + parser.getText());
//				break;
//			// 触发结束元素事件
//			case XmlPullParser.END_TAG:
//				if ("activity".equals(name)) {
//					actName = null;
//				}
//				break;
//			}
//			eventType = parser.next();
//		}
//		return testConfigRead;
//	}

	private void testConfigReadWithSax() {
		localDataXMLInputStream = getLocalDataXMLInputStream();
		RootElement root = new RootElement("config");
		Element elGridWidth = root.getChild("grid-width");
		elGridWidth.setTextElementListener(new TextElementListener() {
			
			@Override
			public void end(String body) {
				gridWidth=Integer.parseInt(body);
			}
			
			@Override
			public void start(Attributes attributes) {
				
			}
		});
		Element elGridCount = root.getChild("grid_count");
		elGridCount.setTextElementListener(new TextElementListener() {
			@Override
			public void end(String body) {
				gridCount=Integer.parseInt(body);
			}
			@Override
			public void start(Attributes attributes) {
			}
		});
		Element elGridAlginParentBottom = root.getChild("alignParentBottom");
		elGridAlginParentBottom.setTextElementListener(new TextElementListener() {
			@Override
			public void end(String body) {
				alignParentBottom=Integer.parseInt(body);
			}
			@Override
			public void start(Attributes attributes) {
			}
		});
		Element elgridMarginLeft = root.getChild("grid-margin-left");
		elgridMarginLeft.setEndTextElementListener(new TextElementListener() {
			@Override
			public void end(String body) {
				gridMarginLeft=Integer.parseInt(body);
			}
			@Override
			public void start(Attributes attributes) {
				
			}
		});
		Element elgridMarginTop = root.getChild("grid-margin-top");
		elgridMarginTop.setEndTextElementListener(new TextElementListener() {
			@Override
			public void end(String body) {
				gridMarginTop=Integer.parseInt(body);
			}
			@Override
			public void start(Attributes attributes) {
				
			}
		});
		datalist = new ArrayList<GridBean>();
		final GridBean gridBean= new GridBean();
		Element elItems = root.getChild("grid-items");
		Element gridItem = elItems.getChild("item");
		gridItem.setEndElementListener(new EndElementListener() {
			@Override
			public void end() {
				datalist.add(gridBean.clone());
			}
		});
		Element elStyle = gridItem.getChild("style");
		elStyle.setEndTextElementListener(new EndTextElementListener() {
			@Override
			public void end(String body) {
				gridBean.setStyle(Integer.parseInt(body));
			}
		});
		Element elImage = gridItem.getChild("image");
		elImage.setEndTextElementListener(new EndTextElementListener() {
			@Override
			public void end(String body) {
				gridBean.setImage(ResourceTools.getResourceFromId(getActivity(), ResourceTypeEnum.TYPE_DRAWABLE, body));
			}
		});
		Element elTitle=gridItem.getChild("title");
		elTitle.setEndTextElementListener(new EndTextElementListener() {
			@Override
			public void end(String body) {
				gridBean.setTitle(body);
			}
		});
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			Xml.parse(localDataXMLInputStream, Xml.Encoding.UTF_8,
					root.getContentHandler());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	private InputStream getLocalDataXMLInputStream() {
		AssetManager asset = getActivity().getAssets();
		InputStream input = null;
		try {
//			input = asset.open("type2_page_config.xml");
			input = asset.open(((MainActivity)getActivity()).xmlName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}

//	/**
//	 * 跳过
//	 * 
//	 * @param parser
//	 * @throws XmlPullParserException
//	 * @throws IOException
//	 */
//	private void skip(XmlPullParser parser) throws XmlPullParserException,
//			IOException {
//		if (parser.getEventType() != XmlPullParser.START_TAG) {
//			throw new IllegalStateException();
//		}
//		int depth = 1;
//		while (depth != 0) {
//			switch (parser.next()) {
//			case XmlPullParser.END_TAG:
//				depth--;
//				break;
//			case XmlPullParser.START_TAG:
//				depth++;
//				break;
//			}
//		}
//	}

//	class MyPageConfig {
//		private boolean isShow;
//		private int mode;
//
//		public boolean isShow() {
//			return isShow;
//		}
//
//		public void setShow(boolean isShow) {
//			this.isShow = isShow;
//		}
//
//		public int getMode() {
//			return mode;
//		}
//
//		public void setMode(int mode) {
//			this.mode = mode;
//		}
//
//	}

//	private void getMode1Data() {
//		datalist = new ArrayList<GridBean>();
//		GridBean bean1 = new GridBean();
//		bean1.setText("测试标题");
//		bean1.setBackgroundColor(getResources().getColor(R.color.brown));
//		bean1.setDrawable(getResources().getDrawable(R.drawable.img1));
//		datalist.add(bean1);
//		GridBean bean2 = new GridBean();
//		bean2.setText("标题sssssssss2");
//		bean2.setBackgroundColor(getResources().getColor(R.color.black));
//		bean2.setDrawable(getResources().getDrawable(R.drawable.img2));
//		datalist.add(bean2);
//		GridBean bean3 = new GridBean();
//		bean3.setText("不同长度的标题");
//		bean3.setBackgroundColor(getResources().getColor(R.color.red));
//		bean3.setDrawable(getResources().getDrawable(R.drawable.img3));
//		datalist.add(bean3);
//		GridBean bean4 = new GridBean();
//		bean4.setText("短");
//		bean4.setBackgroundColor(getResources().getColor(R.color.gray));
//		bean4.setDrawable(getResources().getDrawable(R.drawable.img4));
//		datalist.add(bean4);
//		GridBean bean5 = new GridBean();
//		bean5.setText("短长长短啊短，测试方式来看待福建省劳动纠纷时看到了几分克里斯多夫");
//		bean5.setBackgroundColor(getResources().getColor(R.color.brown));
//		bean5.setDrawable(getResources().getDrawable(R.drawable.img5));
//		datalist.add(bean5);
//	}

//	private void testNetWork() {
//		// ImageLoader img = new ImageLoader(queue, imageCache)
//		// Instantiate the RequestQueue.
//		RequestQueue queue = Volley.newRequestQueue(getActivity());
//		String url = "http://www.google.com";
////		JsonObjectRequest jr = new json
//		
//		StringRequest stringRequest = new StringRequest(Request.Method.GET,
//				url, new Response.Listener<String>() {
//					@Override
//					public void onResponse(String response) {
//						// TODO Auto-generated method stub
//						Log.e("tag",
//								"Response is: " + response.substring(0, 500));
//					}
//
//				}, new Response.ErrorListener() {
//
//					@Override
//					public void onErrorResponse(VolleyError error) {
//						// TODO Auto-generated method stub
//						Log.e("tag", "That didn't work!");
//					}
//				});
//		// Request a string response from the provided URL.
//		// StringRequest stringRequest = new StringRequest(Request.Method.GET,
//		// url,
//		// new Response.Listener() {
//		// @Override
//		// public void onResponse(String response) {
//		// // Display the first 500 characters of the response string.
//		// // mTextView.setText("Response is: "+ response.substring(0,500));
//		// Log.e("tag","Response is: "+ response.substring(0,500));
//		// }
//		// }, new Response.ErrorListener() {
//		// @Override
//		// public void onErrorResponse(VolleyError error) {
//		// // mTextView.setText("That didn't work!");
//		// Log.e("tag","That didn't work!");
//		// }
//		// });
//		// Add the request to the RequestQueue.
//		queue.add(stringRequest);
//		
//		
//		JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET,"http://www.baidu.com", null, new Response.Listener<JSONObject>() {
//
//			@Override
//			public void onResponse(JSONObject response) {
//				System.out.println("s");
//			}
//			
//		}, new Response.ErrorListener() {
//			@Override
//			public void onErrorResponse(VolleyError error) {
//				// TODO Auto-generated method stub
//				System.out.println("ssss");
//			}
//		});
//		queue.add(jr);
//	}
}
