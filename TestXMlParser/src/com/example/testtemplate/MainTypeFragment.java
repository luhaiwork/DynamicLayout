package com.example.testtemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;

import android.app.Fragment;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.sax.TextElementListener;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.android.volley.toolbox.ImageLoader;
import com.example.testtemplate.bean.Type2GridBean;
import com.example.testtemplate.system.MeasureUtil;
import com.example.testtemplate.system.MySingletonVolley;
import com.example.testtemplate.system.ResourceTools;
import com.example.testtemplate.system.ResourceTools.ResourceTypeEnum;

public class MainTypeFragment extends Fragment {
	@InjectView(R.id.pager)
	ViewPager viewPager;
	@InjectView(R.id.gv_test)
	GridView gv_test;
	List<View> pageViewList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main_type,
				container, false);
		ButterKnife.inject(this, rootView);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initConfigData();
		initViewPager();
		initGridView();
	}

	private InputStream getLocalDataXMLInputStream() {
		AssetManager asset = getActivity().getAssets();
		InputStream input = null;
		try {
			input = asset.open("page_config.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}

	private InputStream localDataXMLInputStream = null;
	/** 表格列数 */
	private int gridCount = 1;

	/**
	 * 初始化配置
	 */
	private void initConfigData() {
		localDataXMLInputStream = getLocalDataXMLInputStream();
		RootElement root = new RootElement("config");
		Element childActivity = root.getChild("fragment");
		Element elGridCount = childActivity.getChild("gridCount");
		elGridCount.setTextElementListener(new TextElementListener() {
			@Override
			public void end(String body) {
				gridCount = Integer.parseInt(body);
			}

			@Override
			public void start(Attributes attributes) {
			}
		});
		Element elGridItems = childActivity.getChild("grid-items");
		datalist = new ArrayList<Type2GridBean>();
		Element elGridItem = elGridItems.getChild("item");
		final Type2GridBean type2GridBean = new Type2GridBean();
		elGridItem.setEndElementListener(new EndElementListener() {
			@Override
			public void end() {
				datalist.add(type2GridBean.clone());
			}
		});
		Element elTitle = elGridItem.getChild("title");
		elTitle.setEndTextElementListener(new EndTextElementListener() {
			@Override
			public void end(String body) {
				type2GridBean.setText(body);
			}
		});
		Element elStyle = elGridItem.getChild("style");
		elStyle.setEndTextElementListener(new EndTextElementListener() {
			@Override
			public void end(String body) {
				type2GridBean.setViewType(Integer.parseInt(body));
			}
		});
		Element elText_bg = elGridItem.getChild("text_bg");
		elText_bg.setEndTextElementListener(new EndTextElementListener() {
			@Override
			public void end(String body) {
				type2GridBean.setTxtbackgroundResouce(ResourceTools
						.getResourceFromId(getActivity(),
								ResourceTypeEnum.TYPE_DRAWABLE, body));
			}
		});
		Element elImageUrl = elGridItem.getChild("imageurl");
		elImageUrl.setEndTextElementListener(new EndTextElementListener() {
			@Override
			public void end(String body) {
				type2GridBean.setImageUrl(body);
			}
		});
		Element elImageWidth = elGridItem.getChild("imageWidth");
		elImageWidth.setEndTextElementListener(new EndTextElementListener() {
			@Override
			public void end(String body) {
				type2GridBean.setImageWidth(Integer.parseInt(body));
			}
		});
		Element elImageHeight = elGridItem.getChild("imageHeight");
		elImageHeight.setEndTextElementListener(new EndTextElementListener() {
			@Override
			public void end(String body) {
				type2GridBean.setImageHeight(Integer.parseInt(body));
			}
		});
		Element elBackgroundResource = elGridItem.getChild("background");
		elBackgroundResource
				.setEndTextElementListener(new EndTextElementListener() {
					@Override
					public void end(String body) {
						type2GridBean.setBackgroundResouce(ResourceTools
								.getResourceFromId(getActivity(),
										ResourceTypeEnum.TYPE_DRAWABLE, body));
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

	private void initViewPager() {
		pageViewList = new ArrayList<View>();
		for (int i = 0; i < 5; i++) {
			View inflate = getActivity().getLayoutInflater().inflate(
					R.layout.viewpager_item, null);
			pageViewList.add(inflate);
		}
		MyPageAdapter adp = new MyPageAdapter();
		viewPager.setAdapter(adp);
	}

	private void initGridView() {
		gv_test.setAdapter(new MyAdapter());
		gv_test.setNumColumns(gridCount);
	}

	@Override
	public void onDestroyView() {
		ButterKnife.reset(this);
		super.onDestroyView();
	}

	private List<Type2GridBean> datalist;

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
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;
			if (convertView != null) {
				view = convertView;
			} else if (getItemViewType(position) == Type2GridBean.ViewType.VIEW_TYPE_0
					.getViewType()) {
				view = getActivity().getLayoutInflater().inflate(
						R.layout.type2_list_item_0, null);
			} else if (getItemViewType(position) == Type2GridBean.ViewType.VIEW_TYPE_01
					.getViewType()
					|| getItemViewType(position) == Type2GridBean.ViewType.VIEW_TYPE_03
							.getViewType()) {
				view = getActivity().getLayoutInflater().inflate(
						R.layout.type2_list_item_01, null);
			} else if (getItemViewType(position) == Type2GridBean.ViewType.VIEW_TYPE_02
					.getViewType()) {
				view = getActivity().getLayoutInflater().inflate(
						R.layout.type2_list_item_02, null);
			}
			Type2GridBean type2GridBean = datalist.get(position);
			view.setBackgroundResource(type2GridBean.getBackgroundResouce());
			RelativeLayout rl_txt_bg = (RelativeLayout) view
					.findViewById(R.id.rl_txt_bg);
			rl_txt_bg.setBackgroundResource(type2GridBean
					.getTxtbackgroundResouce());
			TextView tv_item = (TextView) view.findViewById(R.id.tv_item);
			tv_item.setText(datalist.get(position).getText());
			ImageView img_test = (ImageView) view.findViewById(R.id.img_test);
			ImageLoader mImageLoader = MySingletonVolley.getInstance(
					getActivity()).getImageLoader();
			// img_test.setImageDrawable(datalist.get(position).getDrawable());
			mImageLoader.get(datalist.get(position).getImageUrl(), ImageLoader
					.getImageListener(img_test, R.drawable.def_image,
							R.drawable.err_image));
			// ajust layout
			LayoutParams layoutParams = img_test.getLayoutParams();
			layoutParams.height = MeasureUtil.dpToPx(getActivity(),
					type2GridBean.getImageHeight());
			layoutParams.width = MeasureUtil.dpToPx(getActivity(),
					type2GridBean.getImageWidth());
			img_test.setLayoutParams(layoutParams);
			// 要是非上下布局，调整下文字高度与图片高度相同
			if (getItemViewType(position) != Type2GridBean.ViewType.VIEW_TYPE_0
					.getViewType()) {
				LayoutParams rlLayoutParams = rl_txt_bg.getLayoutParams();
				rlLayoutParams.height = MeasureUtil.dpToPx(getActivity(),
						type2GridBean.getImageHeight());
				rl_txt_bg.setLayoutParams(rlLayoutParams);
				android.widget.RelativeLayout.LayoutParams tvLayoutParam = (android.widget.RelativeLayout.LayoutParams) tv_item
						.getLayoutParams();
				tvLayoutParam.addRule(RelativeLayout.CENTER_VERTICAL,
						RelativeLayout.TRUE);
				tvLayoutParam.addRule(RelativeLayout.CENTER_IN_PARENT, 0);
				if (getItemViewType(position) == Type2GridBean.ViewType.VIEW_TYPE_01
						.getViewType()||getItemViewType(position) == Type2GridBean.ViewType.VIEW_TYPE_03
								.getViewType()) {
					tvLayoutParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,
							RelativeLayout.TRUE);
				} else {
					tvLayoutParam.addRule(RelativeLayout.ALIGN_PARENT_LEFT,
							RelativeLayout.TRUE);
				}
			}
			return view;
		}

		@Override
		public int getViewTypeCount() {
			return Type2GridBean.ViewType.getTotType();
		}

		@Override
		public int getItemViewType(int position) {
			return datalist.get(position).getViewType();
		}
	}

	class MyPageAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return pageViewList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;// 官方提示这样写
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(pageViewList.get(position), 0);// 添加页卡
			return pageViewList.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// super.destroyItem(container, position, object);
			container.removeView(pageViewList.get(position));// 删除页卡
		}

	}
}
