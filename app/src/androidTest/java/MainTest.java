
import android.graphics.Point;
import android.support.v4.widget.DrawerLayout;
import android.test.ActivityInstrumentationTestCase2;
import android.view.Gravity;
import android.widget.ListView;


import com.robotium.solo.Solo;

import junit.framework.Assert;

import info.androidhive.slidingmenu.FindPeopleFragment;
import info.androidhive.slidingmenu.MainActivity;
import info.androidhive.slidingmenu.R;

/**
 * Created by xurxo on 25/11/2014.
 */
public class MainTest extends
        ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;

    public MainTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    private void OpenDrawer() {
        Point deviceSize = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(deviceSize);

        int screenWidth = deviceSize.x;
        int screenHeight = deviceSize.y;
        int fromX = 0;
        int toX = screenWidth / 2;
        int fromY = screenHeight / 2;
        int toY = fromY;

        solo.drag(fromX, toX, fromY, toY, 1);
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    public void testOpenDrawerFromDragAction() throws InterruptedException {
        OpenDrawer();

        solo.waitForView(ListView.class);

        DrawerLayout drawerLayout = (DrawerLayout) solo.getView(R.id.drawer_layout);

        Assert.assertTrue(drawerLayout.isDrawerOpen(Gravity.LEFT));
    }

    public void testVerifyFindPeopleOpenedFromDrawer() throws InterruptedException {
        OpenDrawer();

        solo.waitForView(ListView.class);

        solo.clickInList(2);

        Assert.assertTrue(solo.waitForText("Find People View",1,500));
    }
}
