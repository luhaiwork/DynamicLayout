// Generated code from Butter Knife. Do not modify!
package com.example.testtemplate;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class MainTypeFragment$$ViewInjector {
  public static void inject(Finder finder, final com.example.testtemplate.MainTypeFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131034173, "field 'gv_test'");
    target.gv_test = (android.widget.GridView) view;
    view = finder.findRequiredView(source, 2131034179, "field 'viewPager'");
    target.viewPager = (android.support.v4.view.ViewPager) view;
  }

  public static void reset(com.example.testtemplate.MainTypeFragment target) {
    target.gv_test = null;
    target.viewPager = null;
  }
}
